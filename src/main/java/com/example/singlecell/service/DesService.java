package com.example.singlecell.service;

import com.example.singlecell.mapper.DesMapper;
import com.example.singlecell.model.CtmBrowseRec;
import com.example.singlecell.model.ResponseData;
import com.example.singlecell.model.ReturnStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DesService {

    @Autowired
    private DesMapper desMapper;

    public ResponseData<List<String>> processBrowse(CtmBrowseRec data){
        ResponseData<List<String>> responseData = new ResponseData<>();
        String tissue = data.getTissue();
        List<String> dataList = new ArrayList<>();
        if(tissue == null || "".equals(tissue)){
            //整体列表
            dataList = desMapper.findTissue();
        }else{
            dataList = desMapper.findDatasetName(tissue);
        }

        responseData.setResultSet(dataList);
        responseData.setStatus(ReturnStatus.OK);
        return responseData;
    }

}
