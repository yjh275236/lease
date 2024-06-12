package com.yjh.lease.common.mybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.yjh.lease.web.*.mapper")
public class MybatisPlusConfiguration {
}
