package com.example.singlecell.controller;

import com.example.singlecell.model.*;
import com.example.singlecell.service.DesService;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.security.krb5.internal.crypto.Des;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/des")
@CrossOrigin(origins = "*", maxAge=3600)
public class DesController {

    @Autowired
    private DesService desService;

    /**
     * browse页树形结构
     * @param reqData
     * @return
     */
    @RequestMapping(value = "/browse", method = RequestMethod.POST)
    public ResponseData<List<CtmBrowseRst>> processBrowse(@RequestBody RequestData<CtmBrowseRec> reqData){
        return desService.processBrowse(reqData.getData());
    }

    //browse详情页
    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public ResponseData<CtmBrowseDetailRst> processDetail(@RequestBody RequestData<CtmBrowseDetailRec> reqData){
        return desService.processDetail(reqData.getData());
    }

    @RequestMapping(value = "/browseWindow", method = RequestMethod.POST)
    public ResponseData<CtmBrowseWindowRst> processWindow(@RequestBody RequestData<CtmBrowsesWindowRec> requestData){
        return desService.processWindow(requestData.getData());
    }

    @RequestMapping(value = "/tissueList", method = RequestMethod.POST)
    public ResponseData<List<CtmSearchRule>> processTissueList(@RequestBody RequestData reqData){
        return desService.processTissueList();
    }

    @RequestMapping(value = "/datasetList", method = RequestMethod.POST)
    public ResponseData<List<CtmSearchRule>> processDatasetList(@RequestBody RequestData<CtmDatasetRec> reqData){
        return desService.processDatasetList(reqData.getData());
    }

    @RequestMapping(value = "/cellTypeList", method = RequestMethod.POST)
    public ResponseData<List<CtmSearchRule>> processCellTypeList(@RequestBody RequestData<CtmCellTypeRec> reqData){
        return desService.processCellTypeList(reqData.getData());
    }

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseData<List<Description>> processList(@RequestBody RequestData reqData){
        return desService.processList();
    }

    @RequestMapping(value = "/violin", method = RequestMethod.POST)
    public ResponseData<CtmViolinRst> processViolin(@RequestBody RequestData<CtmViolinRec> reqData){
        return desService.processViolin(reqData.getData());
    }

    @RequestMapping("/Excel")
    public Object excelFind(HttpServletRequest request,
                            @RequestParam(name = "dataset", required = true) String dataset,
                            @RequestParam(name = "celltype", required = true) String celltype,
                            HttpServletResponse response) {
        XSSFWorkbook wb = new XSSFWorkbook();

//        String dataset = request.getParameter("dataset");
//        String celltype = request.getParameter("celltype");

        System.out.println("dataset:" + dataset + "*****celltype:" + celltype);

        wb = desService.searchDownloadExcel(dataset, celltype);

        String fileName = "data.xlsx";
        OutputStream outputStream =null;
        try {
            fileName = URLEncoder.encode(fileName,"UTF-8");
            //设置ContentType请求信息格式
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            outputStream = response.getOutputStream();
            wb.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }

}
