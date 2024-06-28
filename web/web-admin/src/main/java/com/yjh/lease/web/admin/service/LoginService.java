package com.yjh.lease.web.admin.service;

import com.yjh.lease.web.admin.vo.login.CaptchaVo;
import com.yjh.lease.web.admin.vo.login.LoginVo;

public interface LoginService {

    CaptchaVo getCaptcha();

    String login(LoginVo loginVo);
}
