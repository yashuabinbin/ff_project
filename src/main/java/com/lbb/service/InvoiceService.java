package com.lbb.service;

import com.lbb.bean.req.InvoiceEditAddReq;
import com.lbb.bean.req.InvoiceIdReq;
import com.lbb.bean.req.InvoiceInfoListReq;
import com.lbb.bean.resp.ApiResp;

public interface InvoiceService {

    ApiResp invoiceDelete(InvoiceIdReq req);

    ApiResp invoiceInfoAdd(InvoiceEditAddReq req);

    ApiResp invoiceInfoEdit(InvoiceEditAddReq req);

    ApiResp invoiceInfoList(InvoiceInfoListReq req);

    ApiResp listAllInvoiceContent();
}
