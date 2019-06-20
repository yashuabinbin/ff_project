package com.lbb.service;

import com.lbb.bean.req.PayEditAddReq;
import com.lbb.bean.req.PayIdReq;
import com.lbb.bean.req.PayInfoListReq;
import com.lbb.bean.resp.ApiResp;

public interface PayService {

    ApiResp payInfoAdd(PayEditAddReq req);

    ApiResp payInfoList(PayInfoListReq req);

    ApiResp payInfoEdit(PayEditAddReq req);

    ApiResp payDelete(PayIdReq req);

    ApiResp listAllPayee();
}
