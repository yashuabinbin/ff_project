package com.lbb.controller;

import com.lbb.bean.req.ContractIdReq;
import com.lbb.bean.req.SubContractorEditAddReq;
import com.lbb.bean.req.SubContractorIdReq;
import com.lbb.bean.req.SubContractorListReq;
import com.lbb.bean.resp.ApiResp;
import com.lbb.service.SubContractorService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/subContractor")
public class SubContractorController extends BaseController {

    @Resource
    private SubContractorService subContractorService;

    /**
     * @description: 获取分包列表
     * @param:
     * @return:
     * @author: lubingbin
     * @date: 2019/6/1 9:54 AM
     **/
    @PostMapping(value = "/list")
    public ApiResp subContractorList(@RequestBody SubContractorListReq req) {
        return subContractorService.subContractorList(req);
    }

    /**
     * @description: 获取所有分包
     * @param:
     * @return:
     * @author: lubingbin
     * @date: 2019/6/2 9:35 AM
     **/
    @PostMapping(value = "/listAll")
    public ApiResp subContractorListAll() {
        return subContractorService.subContractorListAll();
    }

    /**
     * @description: 分包添加
     * @param:
     * @return:
     * @author: lubingbin
     * @date: 2019/6/1 9:54 AM
     **/
    @PostMapping(value = "/add")
    public ApiResp subContractorAdd(@RequestBody SubContractorEditAddReq req) {
        return subContractorService.subContractorAdd(req);
    }

    /**
     * @description: 分包修改
     * @param:
     * @return:
     * @author: lubingbin
     * @date: 2019/6/1 9:54 AM
     **/
    @PostMapping(value = "/edit")
    public ApiResp subContractorEdit(@RequestBody SubContractorEditAddReq req) {
        return subContractorService.subContractorEdit(req);
    }

    /**
     * @description: 分包删除
     * @param:
     * @return:
     * @author: lubingbin
     * @date: 2019/6/1 9:54 AM
     **/
    @PostMapping(value = "/delete")
    public ApiResp subContractorDelete(@RequestBody SubContractorIdReq req) {
        return subContractorService.subContractorDelete(req);
    }

    /**
     * @description: 获取某合同下的分包信息及分摊比率
     * @param: 
     * @return: 
     * @author: lubingbin
     * @date: 2019/6/2 4:08 PM
     **/
    @PostMapping(value = "/getSubContractorWithShareRate")
    public ApiResp getSubContractorWithShareRate(@RequestBody ContractIdReq req) {
        return subContractorService.getSubContractorWithShareRate(req);
    }
}
