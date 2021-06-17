package com.example.singlecell.controller;

import com.example.singlecell.model.*;
import com.example.singlecell.service.DegService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deg")
@CrossOrigin(origins = "*", maxAge=3600)
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

    /**
     * 详情页
     * @param reqData
     * @return
     */
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public ResponseData<CtmSearchDetailRst> processDetail(@RequestBody RequestData<CtmSearchDetailRec> reqData){
        return degService.processDetail(reqData.getData());
    }

    /**
     * 级联搜索
     * @param reqData
     * @return
     */
    @RequestMapping(value = "/onionsearch", method = RequestMethod.POST)
    public ResponseData<List<CtmSearchRst>> processOnionList(@RequestBody RequestData<CtmOnionSearchRec> reqData){
        return degService.processOnionSearch(reqData.getData());
    }

}
