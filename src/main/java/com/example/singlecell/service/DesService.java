package com.example.singlecell.service;

import com.example.singlecell.mapper.DegMapper;
import com.example.singlecell.mapper.DesMapper;
import com.example.singlecell.model.*;
import com.example.singlecell.utils.CommonMethod;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sun.awt.image.ImageWatched;
import sun.security.krb5.internal.crypto.Des;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class DesService {

    @Autowired
    private DesMapper desMapper;

    @Autowired
    private DegMapper degMapper;

    @Value("${custom.basepath}")
    private String basepath;

    @Value("${custom.downloadprofileurl}")
    private String downloadProfileUrl;

    public ResponseData<List<CtmBrowseRst>> processBrowse(CtmBrowseRec data){
        ResponseData<List<CtmBrowseRst>> responseData = new ResponseData<>();
        List<CtmBrowseRst> dataList = new ArrayList<>();

        List<Description> firstDes = desMapper.findGroupByTissue();
        if(firstDes.isEmpty()){

        }else{
            for(Description first : firstDes){
                CtmBrowseRst ctmBrowseRst = new CtmBrowseRst();
                ctmBrowseRst.setId(first.getDatabaseid());
                String tissue = first.getTissue();
                ctmBrowseRst.setLabel(tissue);
                List<Description> secondList = desMapper.findByTissueGroupByDataset(tissue);
                List<CtmChild> childList = new ArrayList<>();
                for(Description second : secondList){
                    CtmChild ctmChild = new CtmChild();
                    ctmChild.setIndexId(second.getId());
                    ctmChild.setLabel(second.getDatasetname());
                    childList.add(ctmChild);
                }
                ctmBrowseRst.setChildren(childList);

                dataList.add(ctmBrowseRst);
            }
        }

        responseData.setResultSet(dataList);
        responseData.setStatus(ReturnStatus.OK);
        return responseData;
    }

    public ResponseData<CtmBrowseDetailRst> processDetail(CtmBrowseDetailRec data) {

        ResponseData<CtmBrowseDetailRst> responseData = new ResponseData<>();
        String dataset = data.getDataset();
        CtmBrowseDetailRst ctmBrowseDetailRst = new CtmBrowseDetailRst();


        Description description = desMapper.findByDataset(dataset);

        if(description == null){
            responseData.setStatus(ReturnStatus.ERR0001);
            responseData.setExtInfo("传入数据有误");
            return responseData;
        }else{

            String tissue = description.getTissue();

            //第一部分
            CtmFirstBrowse firstBrowse = new CtmFirstBrowse();
            BeanUtils.copyProperties(description, firstBrowse);
            String accession = firstBrowse.getAccession();
//            "<p>" + metabolite + " (" +"<a target=\"_blank\" href=\"https://pubchem.ncbi.nlm.nih.gov/compound/"
//                    + metabolitePubChemCID + "\">"+ metabolitePubChemCID + "</a></span>" + ")" + "</p>";
            String accessionHtml = "<p>" + "<a target=\"_blank\" href=\"https://www.ncbi.nlm.nih.gov/geo/query/acc.cgi?acc="
                    + accession + "\"  style:\"font-size:18px;font-weight:500;\">"+ accession + "</a></span>" + "</p>";
            firstBrowse.setAccessionHtml(accessionHtml);

            String publication = firstBrowse.getPublication();
            String publicationHtml = "";
            if(publication == null || "".equals(publication)){
                publicationHtml = "";
            }else{
                publicationHtml = "<p>"  +"<a  style:\"font-size:18px;font-weight:500;\" target=\"_blank\" href=\"" + description.getPubweb()    //https://pubmed.ncbi.nlm.nih.gov/"
                        + publication + "\">"+ publication + "</a></span>" + "</p>";
            }

            firstBrowse.setPublicationHtml(publicationHtml);

            //第二部分
            CtmSecondBrowse ctmSecondBrowse = new CtmSecondBrowse();
            String umapPath = basepath + "/" + dataset + "/UMAP.png";
            File umap = new File(umapPath);
            if(umap.exists()){
                String umapStr = CommonMethod.getImageStr(umapPath);
                ctmSecondBrowse.setUmapStr(umapStr);
            }

            //第三部分
            CtmThirdBrowse ctmThirdBrowse = new CtmThirdBrowse();
            String csvPath = basepath + "/" + dataset + "/cell_num.csv";
            File csvFile = new File(csvPath);
            if(csvFile.exists()){
                try {
                    InputStreamReader read = new InputStreamReader(
                            new FileInputStream(csvFile), StandardCharsets.UTF_8);//考虑到编码格式
                    BufferedReader bufferedReader = new BufferedReader(read);
                    String lineTxt = null;

                    LinkedList<String> cellTypeList = new LinkedList<String>();
                    LinkedList<String> sampleList = new LinkedList<>();

                    LinkedList<Double> numberList = new LinkedList<>();
                    LinkedList<Double> sumList = new LinkedList<>();
                    Double sum = new Double(0);
                    Boolean tag = true;

                    while ((lineTxt = bufferedReader.readLine()) != null) {
                        String dataS[] = lineTxt.replace("\"", "").split(",");
                        if("Celltype".equals(dataS[0]) || "CellType".equals(dataS[0])){
                            continue;
                        }
                        String cellType = dataS[0];
                        String sample = dataS[1];
                        Double num = Double.valueOf(dataS[2]);

                        if(cellTypeList.contains(cellType)){
                            //包含重复的，cellType的一圈已经走完了
                        }else{
                            cellTypeList.add(cellType);
                        }

                        if(!sampleList.contains(sample)){
                            if(tag){
                                tag = false;
                            }else{
                                sumList.add(sum);
                            }
                            sum = new Double(0);
                            sampleList.add(sample);
                        }
                        sum += num;
                        numberList.add(num);
                    }
                    sumList.add(sum);
                    //一圈下来，将所有数据保存起来

                    //共有多少列(num数据的间隔，也是echats中数据的个数)
                    int sampleSize = sampleList.size();
                    //每一列共有多少个值（共有多少种细胞类型）
                    int cellTypeSize = cellTypeList.size();

                    List<ThiredData> thiredDataList = new ArrayList<>();

                    for(int i = 0; i < cellTypeSize; i ++){
                        String cellTypeName = cellTypeList.get(i);
                        LinkedList<Integer> dataList = new LinkedList<>();
                        for(int j = 0; j < sampleSize; j ++){
                            int index = j*cellTypeSize + i;

                            Double shang = division(numberList.get(index), sumList.get(j), 2) * 100;
                            Integer result = shang.intValue();
//                        System.out.println(i + " " + j + " " + index + " : " + numberList.get(index) +"/" +  sumList.get(j) + " = " + result);
                            dataList.add(result);
                        }
                        ThiredData thiredData = new ThiredData();
                        thiredData.setData(dataList);
                        thiredData.setName(cellTypeName);
                        thiredDataList.add(thiredData);
                    }

                    ctmThirdBrowse.setDataList(thiredDataList);
                    ctmThirdBrowse.setSampleList(sampleList);

//                    System.out.println(new Gson().toJson(ctmThirdBrowse));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            //第四部分

            List<String> cellTypeList = degMapper.findcelltypeList(dataset);
            CtmFourthBrowse fourth = new CtmFourthBrowse();
            if(cellTypeList.isEmpty()){

            }else{

                fourth.setDataset(dataset);
                fourth.setTissue(tissue);
                List<ListData> dropList = new ArrayList<>();

                List<FourthData> fourthDataList = new ArrayList<>();
                for(String cellTypeName : cellTypeList){
                    FourthData fourthData = new FourthData();
                    ListData listData = new ListData();
                    fourthData.setName(cellTypeName);
                    String volcanoPath = basepath + "/" + dataset + "/火山图/" + cellTypeName + "_Volcano.png";
                    String imgStr = CommonMethod.getImageStr(volcanoPath);
                    if(imgStr == null){
                        continue;
                    }
                    fourthData.setImgStr(imgStr);

                    listData.setKey(cellTypeName);
                    listData.setValue(cellTypeName);

                    fourthDataList.add(fourthData);
                    dropList.add(listData);
                }
                fourth.setDataList(fourthDataList);
                fourth.setDropList(dropList);
            }

            //第五部分
            CtmFifthBrowse ctmFifthBrowse = new CtmFifthBrowse();
            String heatmapPath = basepath + "/" + dataset + "/Heatmap.png";
            File heatmap = new File(heatmapPath);
            if(heatmap.exists()){
                String heatmapStr = CommonMethod.getImageStr(heatmapPath);
                ctmFifthBrowse.setHeatmapStr(heatmapStr);
            }

            ctmBrowseDetailRst.setFirst(firstBrowse);
            ctmBrowseDetailRst.setSecond(ctmSecondBrowse);
            ctmBrowseDetailRst.setThird(ctmThirdBrowse);
            ctmBrowseDetailRst.setFourth(fourth);
            ctmBrowseDetailRst.setFifth(ctmFifthBrowse);

            responseData.setStatus(ReturnStatus.OK);
            responseData.setResultSet(ctmBrowseDetailRst);
            return responseData;
        }
    }

    public ResponseData<List<CtmSearchRule>> processTissueList() {
        ResponseData<List<CtmSearchRule>> responseData = new ResponseData<>();
        List<String> tissueList = desMapper.findTissue();
        List<CtmSearchRule> resultList = new ArrayList<>();
        if(tissueList.isEmpty()){
            responseData.setStatus(ReturnStatus.OK);
            responseData.setResultSet(resultList);
            return responseData;
        }else{
            for(String str : tissueList){
                CtmSearchRule ctmSearchRule = new CtmSearchRule();
                ctmSearchRule.setLabel(str);
                ctmSearchRule.setValue(str);
                resultList.add(ctmSearchRule);
            }
        }
        responseData.setStatus(ReturnStatus.OK);
        responseData.setResultSet(resultList);
        return responseData;
    }

    public ResponseData<List<CtmSearchRule>> processDatasetList(CtmDatasetRec data) {
        ResponseData<List<CtmSearchRule>> responseData = new ResponseData<>();
        String tissue = data.getTissue();
        List<String> datasetList = desMapper.findDatasetName(tissue);
        List<CtmSearchRule> resultList = new ArrayList<>();
        if(datasetList.isEmpty()){
            responseData.setStatus(ReturnStatus.OK);
            responseData.setResultSet(resultList);
            return responseData;
        }else{
            for(String str : datasetList){
                CtmSearchRule ctmSearchRule = new CtmSearchRule();
                ctmSearchRule.setLabel(str);
                ctmSearchRule.setValue(str);
                resultList.add(ctmSearchRule);
            }
        }
        responseData.setStatus(ReturnStatus.OK);
        responseData.setResultSet(resultList);
        return responseData;

    }

    public ResponseData<List<CtmSearchRule>> processCellTypeList(CtmCellTypeRec data) {
        ResponseData<List<CtmSearchRule>> responseData = new ResponseData<>();
        String dataset = data.getDataset();
        List<String> cellTypeList = degMapper.findcelltypeList(dataset);
        List<CtmSearchRule> resultList = new ArrayList<>();
        if(cellTypeList.isEmpty()){
            responseData.setStatus(ReturnStatus.OK);
            responseData.setResultSet(resultList);
            return responseData;
        }else{
            for(String str : cellTypeList){
                CtmSearchRule ctmSearchRule = new CtmSearchRule();
                ctmSearchRule.setLabel(str);
                ctmSearchRule.setValue(str);
                resultList.add(ctmSearchRule);
            }
        }
        responseData.setStatus(ReturnStatus.OK);
        responseData.setResultSet(resultList);
        return responseData;
    }

    public ResponseData<List<Description>> processList() {

        ResponseData<List<Description>> responseData = new ResponseData<>();
        List<Description> descriptioncs = new ArrayList<>();
        descriptioncs = desMapper.findList();
        responseData.setStatus(ReturnStatus.OK);
        responseData.setResultSet(descriptioncs);
        return responseData;
    }


    public double division(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public ResponseData<CtmBrowseWindowRst> processWindow(CtmBrowsesWindowRec data) {

        ResponseData<CtmBrowseWindowRst> responseData = new ResponseData<>();
        CtmBrowseWindowRst ctmData = new CtmBrowseWindowRst();

        String dataset = data.getDataset();
        String celltype = data.getCelltype();

        ctmData.setDataset(dataset);
        ctmData.setCelltype(celltype);

        List<Deg> degList = degMapper.findByDatasetAndCellType(dataset, celltype);

        List<CtmWindowDeg> windowDegs = new ArrayList<>();

        if(degList.isEmpty()){

        }else{
            for(Deg deg : degList){
                CtmWindowDeg ctmWindowDeg = new CtmWindowDeg();
                BeanUtils.copyProperties(deg, ctmWindowDeg);
                String gene = deg.getGene();
                String geneHtml = "<p style:\"font-size:18px;font-weight:500;\">" +"<a target=\"_blank\" href=\"https://www.ncbi.nlm.nih.gov/gene/?term="
                        + gene + "\">"+ gene + "</a></span>" + "</p>";
                ctmWindowDeg.setGeneHtml(geneHtml);

                windowDegs.add(ctmWindowDeg);
            }
        }

        String upPath =  basepath + "/" + dataset + "/GO/" + celltype + "_Go_up.png";
        String upImgStr = CommonMethod.getImageStr(upPath);

        String downPath =  basepath + "/" + dataset + "/GO/" + celltype + "_Go_down.png";
        String downImgStr = CommonMethod.getImageStr(downPath);

        ctmData.setDataList(windowDegs);
        ctmData.setUpImgStr(upImgStr);
        ctmData.setDownImgStr(downImgStr);

        responseData.setStatus(ReturnStatus.OK);
        responseData.setResultSet(ctmData);
        return responseData;
    }

    public ResponseData<CtmViolinRst> processViolin(CtmViolinRec data) {

        ResponseData<CtmViolinRst> responseData = new ResponseData<>();
        CtmViolinRst ctmViolinRst = new CtmViolinRst();

        String dataset = data.getDataset();
        String celltype = data.getCelltype();
        String gene = data.getGene();

//        System.out.println(dataset + " " + celltype + " " + gene);

        String violinPath = basepath + "/" + dataset + "/小提琴图/" + celltype + "_" + gene + "_Violin.png";

//        System.out.println(violinPath);

        File violin = new File(violinPath);
        if(violin.exists()){
            String volcanoStr = CommonMethod.getImageStr(violinPath);
            ctmViolinRst.setImgStr(volcanoStr);
            ctmViolinRst.setName(gene);
        }

        String umapPath = basepath + "/" + dataset + "/UMAP/" + celltype + "_" + gene + "_UMAP.png";
        File umap = new File(umapPath);
        if(umap.exists()){
            String umapStr = CommonMethod.getImageStr(umapPath);
            ctmViolinRst.setUmapStr(umapStr);
        }

        responseData.setStatus(ReturnStatus.OK);
        responseData.setResultSet(ctmViolinRst);
        return responseData;
    }

    public XSSFWorkbook searchDownloadExcel(String dataset, String celltype) {

        List<Deg> degList = degMapper.findByDatasetAndCellType(dataset, celltype);

        XSSFWorkbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("Goods");// 创建一张表
        Row titleRow = sheet.createRow(0);// 创建第一行，起始为0
        titleRow.createCell(0).setCellValue("Dataset");// 第一列
        titleRow.createCell(1).setCellValue("Tissue");
        titleRow.createCell(2).setCellValue("Cell type");
        titleRow.createCell(3).setCellValue("Gene");
        titleRow.createCell(4).setCellValue("Log2FC");
        titleRow.createCell(5).setCellValue("P value");
        int cell = 1;

        if(!degList.isEmpty()){

            for(Deg deg : degList) {
                Row row = sheet.createRow(cell);// 从第二行开始保存数据
                //humanMouse
                row.createCell(0).setCellValue(deg.getDataset());
                //Gut Microbiota(ID)
                row.createCell(1).setCellValue(deg.getTissue());
                //Strain
                row.createCell(2).setCellValue(deg.getCelltype());
                //Metabolite(ID)
                row.createCell(3).setCellValue(deg.getGene());
                //Gene(ID)
                row.createCell(4).setCellValue(deg.getLogfc());
                //Alteration
                row.createCell(5).setCellValue(deg.getPvalue());
                cell++;
            }
        }

        return wb;
    }

    public ResponseData<List<CtmDownloadList>> processDownloadList() {

        ResponseData<List<CtmDownloadList>> responseData = new ResponseData<>();
        List<CtmDownloadList> ctmDownloadLists = new ArrayList<>();

        List<Description> desList = desMapper.findList();
        if(desList.isEmpty()){

        }else{
            for(Description description : desList){
                CtmDownloadList ctmDownloadList = new CtmDownloadList();
                BeanUtils.copyProperties(description, ctmDownloadList);

                String dataset = description.getDatasetname();

                String accession = description.getAccession();
                String accessionHtml = "<p>" + "<a target=\"_blank\" href=\"https://www.ncbi.nlm.nih.gov/geo/query/acc.cgi?acc="
                        + accession + "\">"+ accession + "</a></span>" + "</p>";
                ctmDownloadList.setAccessionHtml(accessionHtml);

                String profileHtml = "<p>" + "<a target=\"_blank\" href=\"" + downloadProfileUrl +
                         dataset + "\">download</a></span>" + "</p>";

                ctmDownloadList.setProfile(profileHtml);

                ctmDownloadLists.add(ctmDownloadList);
            }
        }

        responseData.setStatus(ReturnStatus.OK);
        responseData.setResultSet(ctmDownloadLists);
        return responseData;
    }

    public ResponseData<List<CtmFirstPageTree>> processFirstTree() {

        ResponseData<List<CtmFirstPageTree>> responseData = new ResponseData<>();

        List<CtmFirstPageTree> treeList = new ArrayList<>();

        List<Description> descriptions = desMapper.findListOrderByDID();
        if(!descriptions.isEmpty()){
            for(Description description : descriptions){
                CtmFirstPageTree ctmFirstPageTree = new CtmFirstPageTree();
                ctmFirstPageTree.setId(description.getDatabaseid());
                ctmFirstPageTree.setLabel(description.getTissue());
                treeList.add(ctmFirstPageTree);
            }
        }

        responseData.setStatus(ReturnStatus.OK);
        responseData.setResultSet(treeList);
        return responseData;
    }

    public void downloadfile(String id, HttpServletResponse response) {

        String filePath = basepath + "/" + id + "/" + id + ".csv";
        String fileName = id + ".csv";

        File file = new File(filePath);
        if(file.exists()){
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName+";"+"filename*=utf-8''"+fileName);
            response.setContentType("multipart/form-data");
            ServletOutputStream out = null;
            FileInputStream in = null;
            try {
                in = new FileInputStream(new File(filePath));
                out = response.getOutputStream();
                // 读取文件流
                int len = 0;
                byte[] buffer = new byte[1024 * 10];
                while ((len = in.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
                out.flush();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                try {
                    out.close();
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }else{

        }

    }
}
