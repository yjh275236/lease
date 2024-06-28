package com.yjh.lease.web.admin.controller.system;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjh.lease.common.result.Result;
import com.yjh.lease.model.entity.SystemUser;
import com.yjh.lease.model.enums.BaseStatus;
import com.yjh.lease.web.admin.service.SystemUserService;
import com.yjh.lease.web.admin.vo.system.user.SystemUserItemVo;
import com.yjh.lease.web.admin.vo.system.user.SystemUserQueryVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Tag(name = "后台用户信息管理")
@RestController
@RequestMapping("/admin/system/user")
public class SystemUserController {

    @Autowired
    private SystemUserService service;

    @Operation(summary = "根据条件分页查询后台用户列表")
    @GetMapping("page")
    public Result<IPage<SystemUserItemVo>> page(@RequestParam long current, @RequestParam long size, SystemUserQueryVo queryVo) {

        IPage<SystemUser> page = new Page<>(current, size);
        IPage<SystemUserItemVo> systemUserPage = service.pageSystemUserByQuery(page, queryVo);
        return Result.ok(systemUserPage);
    }

    @Operation(summary = "根据ID查询后台用户信息")
    @GetMapping("getById")
    public Result<SystemUserItemVo> getById(@RequestParam Long id) {
        SystemUserItemVo systemUser = service.getSystemUserById(id);
        return Result.ok(systemUser);
    }

    @Operation(summary = "保存或更新后台用户信息")
    @PostMapping("saveOrUpdate")
    public Result saveOrUpdate(@RequestBody SystemUser systemUser) {
        if(systemUser.getPassword() != null){
            systemUser.setPassword(DigestUtils.md5Hex(systemUser.getPassword()));
        }
        service.saveOrUpdate(systemUser);
        return Result.ok();
    }

    @Operation(summary = "判断后台用户名是否可用")
    @GetMapping("isUserNameAvailable")
    public Result<Boolean> isUsernameExists(@RequestParam String username) {
        // 创建一个LambdaQueryWrapper对象，用于构建查询条件
        LambdaQueryWrapper<SystemUser> queryWrapper = new LambdaQueryWrapper<>();
        // 在查询条件中添加用户名相等的条件
        queryWrapper.eq(SystemUser::getUsername, username);
        // 调用服务层方法，根据查询条件统计符合条件的用户数量
        long count = service.count(queryWrapper);
        // 返回一个Result对象，将用户名是否可用的结果作为数据返回
        // 如果用户数量为0，则表示用户名可用，返回true；否则返回false
        return Result.ok(count == 0);
    }


    @DeleteMapping("deleteById")
    @Operation(summary = "根据ID删除后台用户信息")
    public Result removeById(@RequestParam Long id) {
        service.removeById(id);
        return Result.ok();
    }

    @Operation(summary = "根据ID修改后台用户状态")
    @PostMapping("updateStatusByUserId")
    public Result updateStatusByUserId(@RequestParam Long id, @RequestParam BaseStatus status) {
        LambdaUpdateWrapper<SystemUser> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(SystemUser::getId, id);
        updateWrapper.set(SystemUser::getStatus, status);
        service.update(updateWrapper);
        return Result.ok();
    }
}
