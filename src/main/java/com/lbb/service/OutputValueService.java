package com.lbb.service;

import com.lbb.bean.req.OutputAddEditReq;
import com.lbb.bean.req.OutputSearchReq;
import com.lbb.bean.req.OutputValueIdReq;
import com.lbb.bean.resp.ApiResp;

public interface OutputValueService {

    ApiResp getByOutputTime(OutputSearchReq req);

    ApiResp getOutputTimeList();

    ApiResp outputValueAdd(OutputAddEditReq req);

    ApiResp outputValueEdit(OutputAddEditReq req);

    ApiResp outputValueDelete(OutputValueIdReq req);
}
