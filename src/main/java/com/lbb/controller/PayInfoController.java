package com.lbb.controller;

import com.lbb.bean.req.PayEditAddReq;
import com.lbb.bean.req.PayIdReq;
import com.lbb.bean.req.PayInfoListReq;
import com.lbb.bean.resp.ApiResp;
import com.lbb.service.PayService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/payInfo")
public class PayInfoController {

    @Resource
    private PayService payService;

    /**
     * @description: 付款添加
     * @param:
     * @return:
     * @author: lubingbin
     * @date: 2019/6/2 5:07 PM
     **/
    @PostMapping(value = "/add")
    public ApiResp payInfoAdd(@RequestBody PayEditAddReq req) {
        return payService.payInfoAdd(req);
    }

   /**
    * @description: 付款修改
    * @param:
    * @return:
    * @author: lubingbin
    * @date: 2019/6/2 10:43 PM
    **/
    @PostMapping(value = "/edit")
    public ApiResp payInfoEdit(@RequestBody PayEditAddReq req) {
        return payService.payInfoEdit(req);
    }


    /**
     * @description: 付款列表
     * @param:
     * @return:
     * @author: lubingbin
     * @date: 2019/6/2 6:06 PM
     **/
    @PostMapping(value = "/list")
    public ApiResp payList(@RequestBody PayInfoListReq req) {
        return payService.payInfoList(req);
    }

    /**
     * @description: 删除付款
     * @param:
     * @return:
     * @author: lubingbin
     * @date: 2019/6/2 10:46 PM
     **/
    @PostMapping(value = "/delete")
    public ApiResp payDelete(@RequestBody PayIdReq req) {
        return payService.payDelete(req);
    }

    /**
     * @description: 获取所有收款单位列表
     * @param: 
     * @return: 
     * @author: lubingbin
     * @date: 2019/6/3 10:12 AM
     **/
    @PostMapping(value = "/listAllPayee")
    public ApiResp listAllPayee() {
        return payService.listAllPayee();
    }
}
