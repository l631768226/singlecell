package com.example.singlecell.controller;

import com.example.singlecell.mapper.DesMapper;
import com.example.singlecell.model.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.security.krb5.internal.crypto.Des;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestContrller {

    @Autowired
    private DesMapper desMapper;


    @RequestMapping(value = "/1", method = {RequestMethod.GET, RequestMethod.POST})
    public String processTest(){
        return "OK";
    }


    @RequestMapping(value = "/2", method = {RequestMethod.GET, RequestMethod.POST})
    public List<Description> processDesList(){
        return desMapper.findList();
    }

}
