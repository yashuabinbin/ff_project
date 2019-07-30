package com.lbb.service;

import com.lbb.bean.req.OutputValueEditAddReq;
import com.lbb.bean.req.OutputValueIdReq;
import com.lbb.bean.req.OutputValueListReq;
import com.lbb.bean.resp.ApiResp;

public interface OutputValueService {

    ApiResp outputValueAdd(OutputValueEditAddReq req);

    ApiResp outputValueEdit(OutputValueEditAddReq req);

    ApiResp outputValueList(OutputValueListReq req);

    ApiResp outputValueDelete(OutputValueIdReq req);
}
