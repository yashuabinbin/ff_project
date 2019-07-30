package com.lbb.controller;

import com.lbb.bean.req.OutputValueEditAddReq;
import com.lbb.bean.req.OutputValueIdReq;
import com.lbb.bean.req.OutputValueListReq;
import com.lbb.bean.resp.ApiResp;
import com.lbb.service.OutputValueService;
import com.sun.xml.internal.rngom.parse.host.Base;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/outputValue")
public class OutputValueController extends BaseController {

    @Resource
    private OutputValueService outputValueService;

    @PostMapping(value = "/add")
    public ApiResp invoiceInfoAdd(@RequestBody OutputValueEditAddReq req) {
        return outputValueService.outputValueAdd(req);
    }


    @PostMapping(value = "/edit")
    public ApiResp payInfoEdit(@RequestBody OutputValueEditAddReq req) {
        return outputValueService.outputValueEdit(req);
    }


    @PostMapping(value = "/list")
    public ApiResp invoiceInfoList(@RequestBody OutputValueListReq req) {
        return outputValueService.outputValueList(req);
    }

    @PostMapping(value = "/delete")
    public ApiResp invoiceDelete(@RequestBody OutputValueIdReq req) {
        return outputValueService.outputValueDelete(req);
    }
}
