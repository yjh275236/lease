package com.yjh.lease.web.admin.controller.login;


import com.yjh.lease.common.login.LoginUserHolder;
import com.yjh.lease.common.result.Result;
import com.yjh.lease.web.admin.service.LoginService;
import com.yjh.lease.web.admin.vo.login.CaptchaVo;
import com.yjh.lease.web.admin.vo.login.LoginVo;
import com.yjh.lease.web.admin.vo.system.user.SystemUserInfoVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "后台管理系统登录管理")
@RestController
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private LoginService service;

    @Operation(summary = "获取图形验证码")
    @GetMapping("login/captcha")
    public Result<CaptchaVo> getCaptcha() {
        CaptchaVo captcha = service.getCaptcha();
        return Result.ok(captcha);
    }

    @Operation(summary = "登录")
    @PostMapping("login")
    public Result<String> login(@RequestBody LoginVo loginVo) {
        String token = service.login(loginVo);
        return Result.ok(token);
    }
//    @Operation(summary = "获取登陆用户个人信息")
//    @GetMapping("info")
//    public Result<SystemUserInfoVo> info(@RequestHeader("access-token") String token) {
//        Claims claims = JwtUtil.parseToken(token);
//        Long userId = claims.get("userId", Long.class);
//        SystemUserInfoVo userInfo = service.getLoginUserInfo(userId);
//        return Result.ok(userInfo);
//    }
@Operation(summary = "获取登陆用户个人信息")
@GetMapping("info")
public Result<SystemUserInfoVo> info() {
    SystemUserInfoVo userInfo = service.getLoginUserInfo(LoginUserHolder.getLoginUser().getUserId());
    return Result.ok(userInfo);
    }
}