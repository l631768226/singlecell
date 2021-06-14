package com.example.singlecell.controller;

import com.example.singlecell.model.CtmBrowseRec;
import com.example.singlecell.model.RequestData;
import com.example.singlecell.model.ResponseData;
import com.example.singlecell.service.DesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.security.krb5.internal.crypto.Des;

import java.util.List;

@RestController
@RequestMapping("/des")
public class DesController {

    @Autowired
    private DesService desService;

    /**
     * browse页树形结构
     * @param reqData
     * @return
     */
    @RequestMapping(value = "/browse", method = RequestMethod.POST)
    public ResponseData<List<String>> processBrowse(@RequestBody RequestData<CtmBrowseRec> reqData){
        return desService.processBrowse(reqData.getData());
    }



}
