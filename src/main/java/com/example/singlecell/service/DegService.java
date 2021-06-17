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

    public ResponseData<List<CtmSearchRst>> processSearche(CtmSearchRec data){
        ResponseData<List<CtmSearchRst>> responseData = new ResponseData<>();
        String gene = data.getGene();
        List<CtmSearchRst> ctmSearchRsts = new ArrayList<>();
        List<Deg> degList = new ArrayList<>();
        if(gene != null && !"".equals(gene)){
            if(data.getPageSize() == null || data.getPage() == null){
                degList = degMapper.findListByGene(gene);
            }else{
                PageHelper.startPage(data.getPage(), data.getPageSize());
                degList = degMapper.findListByGene(gene);
            }
        }else{
            //基因信息为空，级联搜索
            String tissue = data.getTissue();
            String dataset = data.getDataset();
            String celltype = data.getCelltype();

            if(data.getPageSize() == null || data.getPage() == null){
                degList = degMapper.findSearch(tissue, dataset, celltype);
            }else{
                PageHelper.startPage(data.getPage(), data.getPageSize());
                degList = degMapper.findSearch(tissue, dataset, celltype);
            }
        }

        if(degList.isEmpty()){
            responseData.setStatus(ReturnStatus.OK);
            responseData.setResultSet(ctmSearchRsts);
            return responseData;
        }else{
            for(Deg deg :  degList){
                CtmSearchRst ctmSearchRst = new CtmSearchRst();
                BeanUtils.copyProperties(deg, ctmSearchRst);
                ctmSearchRsts.add(ctmSearchRst);
            }

            if(data.getPage()!= null && data.getPageSize() != null){
                PageInfo<CtmSearchRst> pageInfo = new PageInfo<>(ctmSearchRsts);
                responseData.setPaging(pageInfo);
            }

            responseData.setStatus(ReturnStatus.OK);
            responseData.setResultSet(ctmSearchRsts);
            return responseData;
        }
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
        String umapPath = basepath + "/" + dataset + "/UMAP.png";
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

    public ResponseData<List<CtmSearchRst>> processOnionSearch(CtmOnionSearchRec data) {

        ResponseData<List<CtmSearchRst>> responseData = new ResponseData<>();

        return responseData;
    }
}
