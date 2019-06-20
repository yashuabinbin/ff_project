package com.lbb.controller;

import com.lbb.bean.req.InvoiceEditAddReq;
import com.lbb.bean.req.InvoiceIdReq;
import com.lbb.bean.req.InvoiceInfoListReq;
import com.lbb.bean.resp.ApiResp;
import com.lbb.service.InvoiceService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/invoiceInfo")
public class InvoiceInfoController {

    @Resource
    private InvoiceService invoiceService;

    @PostMapping(value = "/add")
    public ApiResp invoiceInfoAdd(@RequestBody InvoiceEditAddReq req) {
        return invoiceService.invoiceInfoAdd(req);
    }


    @PostMapping(value = "/edit")
    public ApiResp payInfoEdit(@RequestBody InvoiceEditAddReq req) {
        return invoiceService.invoiceInfoEdit(req);
    }


    @PostMapping(value = "/list")
    public ApiResp invoiceInfoList(@RequestBody InvoiceInfoListReq req) {
        return invoiceService.invoiceInfoList(req);
    }


    @PostMapping(value = "/delete")
    public ApiResp invoiceDelete(@RequestBody InvoiceIdReq req) {
        return invoiceService.invoiceDelete(req);
    }


    @PostMapping(value = "/listAllInvoiceContent")
    public ApiResp listAllInvoiceContent() {
        return invoiceService.listAllInvoiceContent();
    }

}
