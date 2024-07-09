package com.yjh.lease.web.app.mapper;

import com.yjh.lease.model.entity.GraphInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yjh.lease.model.enums.ItemType;
import com.yjh.lease.web.app.vo.graph.GraphVo;

import java.util.List;

/**
* @author liubo
* @description 针对表【graph_info(图片信息表)】的数据库操作Mapper
* @createDate 2023-07-26 11:12:39
* @Entity entity.com.yjh.lease.model.GraphInfo
*/
public interface GraphInfoMapper extends BaseMapper<GraphInfo> {

    List<GraphVo> selectListByItemTypeAndId(ItemType itemType, Long id);
}




