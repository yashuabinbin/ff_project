package com.lbb.bean.resp;

import com.lbb.bean.vo.SysUserVO;
import lombok.Data;

@Data
public class LoginResp {

    private String token;

    private SysUserVO sysUser;

}
