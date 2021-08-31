package com.example.singlecell.controller;

import com.example.singlecell.mapper.CellnumMapper;
import com.example.singlecell.mapper.GodownMapper;
import com.example.singlecell.model.Cellnum;
import com.example.singlecell.model.GoDown;
import com.example.singlecell.utils.CommonMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private GodownMapper godownMapper;

    @Autowired
    private CellnumMapper cellnumMapper;


    @RequestMapping(value = "/init", method = {RequestMethod.POST})
    public String processInit(){

        String csvPath =  "E:\\GO_echart.txt";
        File csvFile = new File(csvPath);
        if(csvFile.exists()) {
            try {
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(csvFile), StandardCharsets.UTF_8);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;

                List<GoDown> godownList = new ArrayList<>();

                while ((lineTxt = bufferedReader.readLine()) != null) {
                    System.out.println(lineTxt);
                    String dataS[] = lineTxt.split("\t");
                    System.out.println(dataS.length);
                    String ontology = dataS[0];
                    if("ONTOLOGY".equals(ontology)){
                        continue;
                    }

                    GoDown goDown = new GoDown();
                    goDown.setOntology(ontology);
                    goDown.setGoid(dataS[1]);
                    goDown.setDescription(dataS[2]);
                    String pvalue = dataS[3];
                    goDown.setPvalue(CommonMethod.changeDataToD(pvalue));
                    goDown.setLogpvalue(Double.valueOf(dataS[4]));
                    goDown.setGeneid(dataS[5]);
                    goDown.setStatus(dataS[6]);
                    goDown.setDataset(dataS[8]);
                    goDown.setCelltype(dataS[7]);
                    godownList.add(goDown);

                }

                int resultInt = godownMapper.insertBatchGoDown(godownList);

                if(resultInt > 0){
                    return "OK";
                }else{
                    return "empty";
                }

            } catch (IOException e) {
                e.printStackTrace();
                return "error";
            }
        }else{
            return "noexist";
        }
    }


}
