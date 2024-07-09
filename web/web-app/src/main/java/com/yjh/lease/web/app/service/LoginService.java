package com.yjh.lease.web.app.service;

import com.yjh.lease.web.app.vo.user.LoginVo;
import com.yjh.lease.web.app.vo.user.UserInfoVo;

public interface LoginService {
    void getSMSCode(String phone);

    String login(LoginVo loginVo);

    UserInfoVo getUserInfoId(Long id);}
