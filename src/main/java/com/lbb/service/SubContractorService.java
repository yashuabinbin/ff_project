package com.lbb.service;

import com.lbb.bean.req.ContractIdReq;
import com.lbb.bean.req.SubContractorEditAddReq;
import com.lbb.bean.req.SubContractorIdReq;
import com.lbb.bean.req.SubContractorListReq;
import com.lbb.bean.resp.ApiResp;

public interface SubContractorService {

    ApiResp subContractorList(SubContractorListReq req);

    ApiResp subContractorAdd(SubContractorEditAddReq req);

    ApiResp subContractorEdit(SubContractorEditAddReq req);

    ApiResp subContractorDelete(SubContractorIdReq req);

    ApiResp subContractorListAll();

    ApiResp getSubContractorWithShareRate(ContractIdReq req);
}
