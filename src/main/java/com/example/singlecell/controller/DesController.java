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

    /**
     * 旭日图
     * @param reqData
     * @return
     */
    @RequestMapping(value = "/sun", method = RequestMethod.POST)
    public ResponseData<List<TissueSunChart>> processSunChart(@RequestBody RequestData<CtmBrowseRec> reqData){
        return desService.processSunChart(reqData.getData());
    }

    /**
     * 首页的树形结构图
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/firstTree", method = RequestMethod.POST)
    public ResponseData<List<CtmFirstPageTree>> processFirstTree(@RequestBody RequestData<?> requestData){
        return desService.processFirstTree();
    }

    /**
     * browse详情页，共5个部分结果
     * @param reqData
     * @return
     */
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


    @RequestMapping(value = "/file/download", method = {RequestMethod.POST, RequestMethod.GET})
    void downloadfile(@RequestParam(name = "id", required = true) String id,
                      HttpServletResponse response){
        desService.downloadfile(id, response);
    }

    @RequestMapping(value = "/alldegs", method = {RequestMethod.POST, RequestMethod.GET})
    void downloaddegs(@RequestParam(name = "id", required = true) String id,
                      HttpServletResponse response){
        desService.downloaddegs(id, response);
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


    @RequestMapping("/searchExcel")
    public Object excelSearchFind(HttpServletRequest request,
                            @RequestParam(name = "gene", required = true) String gene,
                            HttpServletResponse response) {
        XSSFWorkbook wb = new XSSFWorkbook();

//        String dataset = request.getParameter("dataset");
//        String celltype = request.getParameter("celltype");

        wb = desService.searchFirstDownloadExcel(gene);

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


    @RequestMapping(value = "/downloadList", method = RequestMethod.POST)
    public ResponseData<List<CtmDownloadList>> processDownloadList(@RequestBody RequestData<?> requestData){
        return desService.processDownloadList();
    }

}
