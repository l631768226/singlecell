package com.example.singlecell.controller;

import com.example.singlecell.model.CtmSearchRec;
import com.example.singlecell.model.CtmSearchRst;
import com.example.singlecell.model.RequestData;
import com.example.singlecell.model.ResponseData;
import com.example.singlecell.service.DegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/deg")
public class DegController {

    @Autowired
    private DegService degService;

    /**
     * 搜索
     * @param reqData
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ResponseData<List<CtmSearchRst>> processList(@RequestBody RequestData<CtmSearchRec> reqData){
        return degService.processSearche(reqData.getData());
    }


}
