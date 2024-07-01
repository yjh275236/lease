package com.yjh.lease.web.admin.service.impl;

import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import com.yjh.lease.common.constant.RedisConstant;
import com.yjh.lease.common.exception.LeaseException;
import com.yjh.lease.common.result.ResultCodeEnum;
import com.yjh.lease.common.utils.JwtUtil;
import com.yjh.lease.model.entity.SystemUser;
import com.yjh.lease.model.enums.BaseStatus;
import com.yjh.lease.web.admin.mapper.SystemUserMapper;
import com.yjh.lease.web.admin.service.LoginService;
import com.yjh.lease.web.admin.vo.login.CaptchaVo;
import com.yjh.lease.web.admin.vo.login.LoginVo;
import com.yjh.lease.web.admin.vo.system.user.SystemUserInfoVo;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private SystemUserMapper systemUserMapper;
    @Override
    public String login(LoginVo loginVo) {
        //1.判断是否输入了验证码
        if (!StringUtils.hasText(loginVo.getCaptchaCode())) {
            throw new LeaseException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_NOT_FOUND);
        }

        //2.校验验证码
        String code = redisTemplate.opsForValue().get(loginVo.getCaptchaKey());
        if (code == null) {
            throw new LeaseException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_EXPIRED);
        }

        if (!code.equals(loginVo.getCaptchaCode().toLowerCase())) {
            throw new LeaseException(ResultCodeEnum.ADMIN_CAPTCHA_CODE_ERROR);
        }

        //3.校验用户是否存在
        SystemUser systemUser = systemUserMapper.selectOneByUsername(loginVo.getUsername());

        if (systemUser == null) {
            throw new LeaseException(ResultCodeEnum.ADMIN_ACCOUNT_NOT_EXIST_ERROR);
        }

        //4.校验用户是否被禁
        if (systemUser.getStatus() == BaseStatus.DISABLE) {
            throw new LeaseException(ResultCodeEnum.ADMIN_ACCOUNT_DISABLED_ERROR);
        }

        //5.校验用户密码
        if (!systemUser.getPassword().equals(DigestUtils.md5Hex(loginVo.getPassword()))) {
            throw new LeaseException(ResultCodeEnum.ADMIN_ACCOUNT_ERROR);
        }

        //6.创建并返回TOKEN
        return JwtUtil.createToken(systemUser.getId(), systemUser.getUsername());
    }

    @Override
    public CaptchaVo getCaptcha() {
        // 创建一个SpecCaptcha对象，指定宽度为130，高度为48，验证码长度为4
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 4);
        // 设置验证码类型为默认类型
        specCaptcha.setCharType(Captcha.TYPE_DEFAULT);

        // 获取验证码文本，并将其转换为小写
        String code = specCaptcha.text().toLowerCase();
        // 生成一个唯一的key，以RedisConstant.ADMIN_LOGIN_PREFIX为前缀，后面拼接一个随机UUID
        String key = RedisConstant.ADMIN_LOGIN_PREFIX + UUID.randomUUID();
        // 将验证码图片转换为Base64编码的字符串
        String image = specCaptcha.toBase64();
        // 将验证码文本存储到Redis中，以key为键，code为值，设置过期时间为RedisConstant.ADMIN_LOGIN_CAPTCHA_TTL_SEC秒
        redisTemplate.opsForValue().set(key, code, RedisConstant.ADMIN_LOGIN_CAPTCHA_TTL_SEC, TimeUnit.SECONDS);

        // 创建一个CaptchaVo对象，将图片和key作为参数传入
        return new CaptchaVo(image, key);
    }

    @Override
    public SystemUserInfoVo getLoginUserInfo(Long userId) {
        SystemUser systemUser = systemUserMapper.selectById(userId);
        SystemUserInfoVo systemUserInfoVo = new SystemUserInfoVo();
        systemUserInfoVo.setName(systemUser.getName());
        systemUserInfoVo.setAvatarUrl(systemUser.getAvatarUrl());
        return systemUserInfoVo;
    }
}
