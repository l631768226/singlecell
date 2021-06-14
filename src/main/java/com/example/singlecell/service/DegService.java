package com.example.singlecell.service;

import com.example.singlecell.mapper.DegMapper;
import com.example.singlecell.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DegService {

    @Autowired
    private DegMapper degMapper;

    public ResponseData<List<CtmSearchRst>> processSearche(CtmSearchRec data){
        ResponseData<List<CtmSearchRst>> responseData = new ResponseData<>();
        String gene = data.getGene();
        List<CtmSearchRst> ctmSearchRsts = new ArrayList<>();

        List<Deg> degList = degMapper.findListByGene(gene);

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
            responseData.setStatus(ReturnStatus.OK);
            responseData.setResultSet(ctmSearchRsts);
            return responseData;
        }
    }
}
