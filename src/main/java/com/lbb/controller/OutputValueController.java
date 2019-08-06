package com.lbb.controller;

import com.lbb.bean.req.OutputAddEditReq;
import com.lbb.bean.req.OutputSearchReq;
import com.lbb.bean.req.OutputValueIdReq;
import com.lbb.bean.resp.ApiResp;
import com.lbb.service.OutputValueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/output")
public class OutputValueController {

    @Autowired
    private OutputValueService outputValueService;

    /**
     * @description: 获取产出时期列表
     * @param:
     * @return:
     * @author: lubingbin
     * @date: 2019-08-05 23:13
     **/
    @PostMapping(value = "/getOutputTimeList")
    public ApiResp getOutputTimeList() {
        return outputValueService.getOutputTimeList();
    }

    /**
     * @description: 获取某期的产值
     * @param:
     * @return:
     * @author: lubingbin
     * @date: 2019-08-04 23:15
     **/
    @PostMapping(value = "/getByOutputTime")
    public ApiResp getByOutputTime(@RequestBody OutputSearchReq req) {
        return outputValueService.getByOutputTime(req);
    }

    /**
     * @description: 新增产值
     * @param:
     * @return:
     * @author: lubingbin
     * @date: 2019-08-04 23:16
     **/
    @PostMapping(value = "/add")
    public ApiResp add(@RequestBody OutputAddEditReq req) {
        return outputValueService.outputValueAdd(req);
    }

    /**
     * @description: 修改产值
     * @param:
     * @return:
     * @author: lubingbin
     * @date: 2019-08-04 23:16
     **/
    @PostMapping(value = "/edit")
    public ApiResp edit(@RequestBody OutputAddEditReq req) {
        return outputValueService.outputValueEdit(req);
    }

    /**
     * @description: 删除产值
     * @param:
     * @return:
     * @author: lubingbin
     * @date: 2019-08-04 23:21
     **/
    @PostMapping(value = "/delete")
    public ApiResp delete(@RequestBody OutputValueIdReq req) {
        return outputValueService.outputValueDelete(req);
    }
}
