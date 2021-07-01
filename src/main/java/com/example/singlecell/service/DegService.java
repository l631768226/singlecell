package com.example.singlecell.service;

import com.example.singlecell.mapper.DegMapper;
import com.example.singlecell.model.*;
import com.example.singlecell.utils.CommonMethod;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class DegService {

    @Autowired
    private DegMapper degMapper;

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
        System.out.println(volcanoPath);
        File volcano = new File(volcanoPath);
        if(volcano.exists()){
            String volcanoStr = CommonMethod.getImageStr(volcanoPath);
            ctmSearchDetailRst.setVolcaStr(volcanoStr);
        }

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
        if(!degList.isEmpty()){
            for(Deg deg : degList){
                String resultCellType = deg.getCelltype();
                String volcanoPath = basepath + "/" + dataset + "/火山图/" + resultCellType + "_Volcano.png";
                String imgStr = CommonMethod.getImageStr(volcanoPath);
                if(imgStr == null){
                    continue;
                }
                CtmSearchSecondRst ctmSearchSecondRst = new CtmSearchSecondRst();
                ctmSearchSecondRst.setImgStr(imgStr);
                ctmSearchSecondRst.setName(resultCellType);
                ctmSearchSecondRst.setCelltype(resultCellType);
                ctmSearchSecondRst.setDataset(deg.getDataset());
                resultList.add(ctmSearchSecondRst);
            }
        }
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

        String downPath =  basepath + "/" + dataset + "/GO/" + celltype + "_Go_down.png";
        String downImgStr = CommonMethod.getImageStr(downPath);

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
