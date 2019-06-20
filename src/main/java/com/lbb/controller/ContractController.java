package com.lbb.controller;

import com.lbb.bean.req.ContractEditAddReq;
import com.lbb.bean.req.ContractIdReq;
import com.lbb.bean.req.ContractListReq;
import com.lbb.bean.resp.ApiResp;
import com.lbb.service.ContractService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/contract")
public class ContractController extends BaseController {

    @Resource
    private ContractService contractService;

    /**
     * @description: 获取合同列表
     * @param:
     * @return:
     * @author: lubingbin
     * @date: 2019/6/1 9:54 AM
     **/
    @PostMapping(value = "/list")
    public ApiResp contractList(@RequestBody ContractListReq req) {
        return contractService.contractList(req);
    }

    /**
     * @description: 获取所有合同
     * @param:
     * @return:
     * @author: lubingbin
     * @date: 2019/6/2 3:22 PM
     **/
    @PostMapping(value = "/listAll")
    public ApiResp contractListAll() {
        return contractService.contractListAll();
    }

    /**
     * @description: 合同添加
     * @param:
     * @return:
     * @author: lubingbin
     * @date: 2019/6/1 9:54 AM
     **/
    @PostMapping(value = "/add")
    public ApiResp contractAdd(@RequestBody ContractEditAddReq req) {
        return contractService.contractAdd(req);
    }

       /**
     * @description: 合同修改
     * @param:
     * @return:
     * @author: lubingbin
     * @date: 2019/6/1 9:54 AM
     **/
    @PostMapping(value = "/edit")
    public ApiResp contractEdit(@RequestBody ContractEditAddReq req) {
        return contractService.contractEdit(req);
    }

    /**
     * @description: 合同删除
     * @param:
     * @return:
     * @author: lubingbin
     * @date: 2019/6/1 9:54 AM
     **/
    @PostMapping(value = "/delete")
    public ApiResp contractDelete(@RequestBody ContractIdReq req) {
        return contractService.contractDelete(req);
    }

}
