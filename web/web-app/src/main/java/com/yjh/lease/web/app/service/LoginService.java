package com.yjh.lease.web.app.service;

import com.yjh.lease.web.app.vo.user.LoginVo;

public interface LoginService {
    void getSMSCode(String phone);

    String login(LoginVo loginVo);
}
