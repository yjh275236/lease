package com.yjh.lease.web.app.mapper;

import com.yjh.lease.model.entity.AttrValue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yjh.lease.web.app.vo.attr.AttrValueVo;

import java.util.List;

/**
* @author liubo
* @description 针对表【attr_value(房间基本属性值表)】的数据库操作Mapper
* @createDate 2023-07-26 11:12:39
* @Entity entity.com.yjh.lease.model.AttrValue
*/
public interface AttrValueMapper extends BaseMapper<AttrValue> {

    List<AttrValueVo> selectListByRoomId(Long id);
}




