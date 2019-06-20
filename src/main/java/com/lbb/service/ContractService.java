package com.lbb.service;

import com.lbb.bean.req.ContractEditAddReq;
import com.lbb.bean.req.ContractIdReq;
import com.lbb.bean.req.ContractListReq;
import com.lbb.bean.resp.ApiResp;
import com.lbb.bean.resp.PageResp;
import com.lbb.bean.vo.ContractVO;

public interface ContractService {

    ApiResp<PageResp<ContractVO>> contractList(ContractListReq req);

    ApiResp contractAdd(ContractEditAddReq req);

    ApiResp contractEdit(ContractEditAddReq req);

    ApiResp contractDelete(ContractIdReq req);

    ApiResp contractListAll();
}
