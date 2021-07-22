package com.example.singlecell.service;

import com.example.singlecell.mapper.DegMapper;
import com.example.singlecell.mapper.GodownMapper;
import com.example.singlecell.mapper.VolcanoMapper;
import com.example.singlecell.model.*;
import com.example.singlecell.utils.CommonMethod;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class DegService {

    @Autowired
    private DegMapper degMapper;

    @Autowired
    private VolcanoMapper volcanoMapper;

    @Autowired
    private GodownMapper godownMapper;

    //Down  Stable  Up
    private List<String> colors = Arrays.asList("#003366", "#CCCCCC", "#CC3333");

    private List<String> legendStatus = Arrays.asList("Down", "Stable", "Up");

    Map<String, String> legendColor = ImmutableMap.of(
            "Down", "#003366",
            "Stable", "#CCCCCC",
            "Up", "#CC3333"
    );

    @Value("${custom.basepath}")
    private String basepath;

    public ResponseData<List<CtmWindowDeg>> processSearche(CtmSearchRec data){
        ResponseData<List<CtmWindowDeg>> responseData = new ResponseData<>();

        List<CtmWindowDeg> ctmWindowDegList = new ArrayList<>();

        String gene = data.getGene();

        List<Deg> degList = degMapper.findListByGene(gene);
        if(!degList.isEmpty()){
            for(Deg deg : degList){
                CtmWindowDeg ctmWindowDeg = new CtmWindowDeg();
                BeanUtils.copyProperties(deg, ctmWindowDeg);
                String geneHtml = "<p style:\"font-size:18px;font-weight:500;\">" +"<a target=\"_blank\" href=\"https://www.ncbi.nlm.nih.gov/gene/?term="
                        + gene + "\">"+ gene + "</a></span>" + "</p>";
                ctmWindowDeg.setGeneHtml(geneHtml);

                ctmWindowDegList.add(ctmWindowDeg);
            }
        }

        responseData.setStatus(ReturnStatus.OK);
        responseData.setResultSet(ctmWindowDegList);
        return responseData;

    }

    public ResponseData<CtmSearchDetailRst> processDetail(CtmSearchDetailRec data) {

        ResponseData<CtmSearchDetailRst> responseData = new ResponseData<>();

        Deg deg = degMapper.findById(data.getId());

        if(deg == null){
            responseData.setStatus(ReturnStatus.ERR0001);
            responseData.setExtInfo("传入数据有误");
            return responseData;
        }

        CtmSearchDetailRst ctmSearchDetailRst = new CtmSearchDetailRst();
        BeanUtils.copyProperties(deg, ctmSearchDetailRst);

        String dataset = deg.getDataset();

        String hasImg = "1";

        //火山图
        String cellType = deg.getCelltype();
        String volcanoPath = basepath + "/" + dataset + "/火山图/" + cellType + "_Volcano.png";
        File volcano = new File(volcanoPath);
        if(volcano.exists()){
            String volcanoStr = CommonMethod.getImageStr(volcanoPath);
            ctmSearchDetailRst.setVolcaStr(volcanoStr);


//            List<String> geneList = degMapper.findListByTDC(deg.getTissue(), dataset, cellType);
//            if(!geneList.isEmpty()) {

//                List<VolcanoData> volcanoDataList = volcanoMapper.findListInGene(geneList);

                List<VolcanoData> volcanoDataList = volcanoMapper.findListByCellTypeAndDataset(cellType, dataset);
                List<CtmVolcanoSeries> volcanoList = new ArrayList<>();

                if (!volcanoDataList.isEmpty()) {
                    for (int i = 0; i <= 2; i++) {
                        String status = legendStatus.get(i);
                        CtmVolcanoSeries ctmVolcanoSeries = new CtmVolcanoSeries();
                        MarkLine markLine = new MarkLine();
                        List<Axis> axisList = new ArrayList<>();
                        XAxis xleft = new XAxis();
                        XAxis xright = new XAxis();
                        YAxis yAxis = new YAxis();

                        List<VolcanoValueData> valueDataList = new ArrayList<>();

                        boolean tag = false;

                        for (VolcanoData volcanoData : volcanoDataList) {
                            String currentStatus = volcanoData.getStatus();
                            if (status.equals(currentStatus)) {
                                String xInterceptLeft = volcanoData.getXinterceptleft();

                                if(xInterceptLeft != null && !"".equals(xInterceptLeft)){
                                    xleft.setxAxis(volcanoData.getXinterceptleft());
                                    xright.setxAxis(volcanoData.getXinterceptright());
                                    yAxis.setyAxis(volcanoData.getYintercept());
                                    tag = true;
                                }

                                VolcanoValueData volcanoValueData = new VolcanoValueData(legendColor.get(currentStatus));
                                List<String> valueList = new ArrayList<>();
                                valueList.add(volcanoData.getLogfc());
                                valueList.add(volcanoData.getPvalue());
                                valueList.add(volcanoData.getGene());
                                valueList.add(currentStatus);
                                volcanoValueData.setValue(valueList);

                                valueDataList.add(volcanoValueData);
                            } else {
                                continue;
                            }
                        }
                        if(tag) {
                            axisList.add(xleft);
                            axisList.add(xright);
                            axisList.add(yAxis);
                            markLine.setData(axisList);
                            ctmVolcanoSeries.setMarkLine(markLine);
                        }
                        ctmVolcanoSeries.setData(valueDataList);
                        ctmVolcanoSeries.setName(status);
                        volcanoList.add(ctmVolcanoSeries);
                    }


                }
                ctmSearchDetailRst.setLegendData(legendStatus);
                ctmSearchDetailRst.setSeries(volcanoList);
                ctmSearchDetailRst.setColor(colors);
            }
//        }



        String gene = deg.getGene();
        //小提琴图
        String violinPath = basepath + "/" + dataset + "/小提琴图/" + cellType + "_" + gene + "_Violin.png";
        File violin = new File(violinPath);
        if(violin.exists()){
            String volcanoStr = CommonMethod.getImageStr(violinPath);
            ctmSearchDetailRst.setViolinStr(volcanoStr);
        }

        //Heatmap
        String heatmapPath = basepath + "/" + dataset + "/Heatmap.png";
        File heatmap = new File(heatmapPath);
        if(heatmap.exists()){
            String heatmapStr = CommonMethod.getImageStr(heatmapPath);
            ctmSearchDetailRst.setHeatmapStr(heatmapStr);
        }
        //umap
        String umapPath = basepath + "/" + dataset + "/UAMP.png";
        File umap = new File(umapPath);
        if(umap.exists()){
            String umapStr = CommonMethod.getImageStr(umapPath);
            ctmSearchDetailRst.setUmapStr(umapStr);
        }else{
            hasImg = "0";
        }
        ctmSearchDetailRst.setHasPic(hasImg);

        responseData.setStatus(ReturnStatus.OK);
        responseData.setResultSet(ctmSearchDetailRst);
        return responseData;
    }

    public ResponseData<CtmOnionSearchRst> processOnionSearch(CtmOnionSearchRec data) {

        ResponseData<CtmOnionSearchRst> responseData = new ResponseData<>();
        CtmOnionSearchRst ctmOnionSearchRst = new CtmOnionSearchRst();


        List<CtmSearchSecondRst> resultList = new ArrayList<>();

        String celltype = data.getCelltype();
        String tissue = data.getTissue();
        String dataset = data.getDataset();

        List<Deg> degList = degMapper.findByTDC(tissue, dataset, celltype);
        CtmSearchSecondRst ctmSearchSecondRst = new CtmSearchSecondRst();
        if(!degList.isEmpty()){
            for(Deg deg : degList) {
                String resultCellType = deg.getCelltype();
                String volcanoPath = basepath + "/" + dataset + "/火山图/" + resultCellType + "_Volcano.png";
                String imgStr = CommonMethod.getImageStr(volcanoPath);
                if (imgStr == null) {
                    continue;
                }
                ctmSearchSecondRst.setImgStr(imgStr);
                ctmSearchSecondRst.setName(resultCellType);
                ctmSearchSecondRst.setCelltype(resultCellType);
                ctmSearchSecondRst.setDataset(deg.getDataset());
            }

//            List<String> geneList = degMapper.findListByTDC(tissue, dataset, celltype);
//            if(!geneList.isEmpty()) {

//                    List<VolcanoData> volcanoDataList = volcanoMapper.findListInGene(geneList);

                    List<VolcanoData> volcanoDataList = volcanoMapper.findListByCellTypeAndDataset(celltype, dataset);
                    System.out.println(volcanoDataList.size());
                    List<CtmVolcanoSeries> volcanoList = new ArrayList<>();

                    if (!volcanoDataList.isEmpty()) {
                        for (int i = 0; i <= 2; i++) {
                            String status = legendStatus.get(i);
                            CtmVolcanoSeries ctmVolcanoSeries = new CtmVolcanoSeries();
                            MarkLine markLine = new MarkLine();
                            List<Axis> axisList = new ArrayList<>();
                            XAxis xleft = new XAxis();
                            XAxis xright = new XAxis();
                            YAxis yAxis = new YAxis();

                            List<VolcanoValueData> valueDataList = new ArrayList<>();

                            boolean tag = false;

                            for (VolcanoData volcanoData : volcanoDataList) {
                                String currentStatus = volcanoData.getStatus();
                                if (status.equals(currentStatus)) {
                                    String xInterceptLeft = volcanoData.getXinterceptleft();

                                    if(xInterceptLeft != null && !"".equals(xInterceptLeft)){
                                        xleft.setxAxis(volcanoData.getXinterceptleft());
                                        xright.setxAxis(volcanoData.getXinterceptright());
                                        yAxis.setyAxis(volcanoData.getYintercept());
                                        tag = true;
                                    }

                                    VolcanoValueData volcanoValueData = new VolcanoValueData(legendColor.get(currentStatus));
                                    List<String> valueList = new ArrayList<>();
                                    valueList.add(volcanoData.getLogfc());
                                    valueList.add(volcanoData.getPvalue());
                                    valueList.add(volcanoData.getGene());
                                    valueList.add(currentStatus);
                                    volcanoValueData.setValue(valueList);

                                    valueDataList.add(volcanoValueData);
                                } else {
                                    continue;
                                }
                            }
                            if(tag) {
                                axisList.add(xleft);
                                axisList.add(xright);
                                axisList.add(yAxis);
                                markLine.setData(axisList);
                                ctmVolcanoSeries.setMarkLine(markLine);
                            }
                            ctmVolcanoSeries.setData(valueDataList);
                            ctmVolcanoSeries.setName(status);
                            volcanoList.add(ctmVolcanoSeries);
                        }


                    }
                    ctmSearchSecondRst.setLegendData(legendStatus);
                    ctmSearchSecondRst.setSeries(volcanoList);
                    ctmSearchSecondRst.setColor(colors);
                    resultList.add(ctmSearchSecondRst);
                }
//        }
        ctmOnionSearchRst.setSearchList(resultList);

        List<CtmWindowDeg> windowDegs = new ArrayList<>();

        List<Deg> degsList = degMapper.findByDatasetAndCellType(dataset, celltype);

        if(degsList.isEmpty()){

        }else{
            for(Deg deg : degsList){
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
        if(upImgStr != null){
            List<GoDown> goDownList = godownMapper.
                    findListByCelltypeAndDataset(celltype, dataset, "up");
            List<List<Object>> upDataList = new ArrayList<>();
            List<Object> tablenameList = Arrays.asList("score", "amount", "product");
            upDataList.add(tablenameList);
            if(!goDownList.isEmpty()){
                Double maxPvalue = goDownList.get(0).getPvalue();
                Double minPvalue = goDownList.get(goDownList.size() - 1).getPvalue();
                for(GoDown goDown : goDownList){
                    Double pvalue = goDown.getPvalue();
                    Double logpvalue = goDown.getLogpvalue();
                    String description = goDown.getDescription();
                    List<Object> dataList = new ArrayList<>();
                    dataList.add(pvalue);
                    dataList.add(logpvalue);
                    dataList.add(description);
                    dataList.add(goDown.getOntology());
                    dataList.add(goDown.getGoid());
                    dataList.add(goDown.getGeneid());
                    upDataList.add(dataList);
                }
                ctmOnionSearchRst.setUpMin(minPvalue);
                ctmOnionSearchRst.setUpMax(maxPvalue);
            }
            ctmOnionSearchRst.setUpDataList(upDataList);
        }

        String downPath =  basepath + "/" + dataset + "/GO/" + celltype + "_Go_down.png";
        String downImgStr = CommonMethod.getImageStr(downPath);
        if(downImgStr != null){
            List<GoDown> goDownList = godownMapper.
                    findListByCelltypeAndDataset(celltype, dataset, "down");
            List<List<Object>> downDataList = new ArrayList<>();
            List<Object> tablenameList = Arrays.asList("score", "amount", "product");
            downDataList.add(tablenameList);
            if(!goDownList.isEmpty()){
                Double maxPvalue = goDownList.get(0).getPvalue();
                Double minPvalue = goDownList.get(goDownList.size() - 1).getPvalue();
                for(GoDown goDown : goDownList){
                    Double pvalue = goDown.getPvalue();
                    Double logpvalue = goDown.getLogpvalue();
                    String description = goDown.getDescription();
                    List<Object> dataList = new ArrayList<>();
                    dataList.add(pvalue);
                    dataList.add(logpvalue);
                    dataList.add(description);
                    dataList.add(goDown.getOntology());
                    dataList.add(goDown.getGoid());
                    dataList.add(goDown.getGeneid());
                    downDataList.add(dataList);
                }
                ctmOnionSearchRst.setDownMin(minPvalue);
                ctmOnionSearchRst.setDownMax(maxPvalue);
            }
            ctmOnionSearchRst.setDownDataList(downDataList);
        }

        ctmOnionSearchRst.setDataList(windowDegs);
        ctmOnionSearchRst.setDownImgStr(downImgStr);
        ctmOnionSearchRst.setUpImgStr(upImgStr);
        responseData.setStatus(ReturnStatus.OK);
        responseData.setResultSet(ctmOnionSearchRst);
        return responseData;
    }

    public ResponseData<List<CtmLikeRst>> processGeneLike(CtmLike data) {

        ResponseData<List<CtmLikeRst>> responseData = new ResponseData<>();

        String gene = data.getGene();

        if(gene == null || "".equals(gene)){
            responseData.setStatus(ReturnStatus.ERR0001);
            responseData.setExtInfo("传入参数有误");
            return responseData;
        }else{
            gene = gene + "%";
        }

        List<String> geneList = degMapper.findGeneLike(gene);
        List<CtmLikeRst> resultList = new ArrayList<>();

        if(!geneList.isEmpty()){
            for(String str : geneList){
                CtmLikeRst ctmLikeRst = new CtmLikeRst();
                ctmLikeRst.setValue(str);
                resultList.add(ctmLikeRst);
            }
        }


        responseData.setStatus(ReturnStatus.OK);
        responseData.setResultSet(resultList);
        return responseData;
    }
}
