package com.yjh.lease.web.app.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjh.lease.model.entity.RoomInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yjh.lease.web.app.vo.room.RoomItemVo;
import com.yjh.lease.web.app.vo.room.RoomQueryVo;

import java.math.BigDecimal;

/**
* @author liubo
* @description 针对表【room_info(房间信息表)】的数据库操作Mapper
* @createDate 2023-07-26 11:12:39
* @Entity entity.com.yjh.lease.model.RoomInfo
*/
public interface RoomInfoMapper extends BaseMapper<RoomInfo> {

    IPage<RoomItemVo> pageRoomItemByQuery(Page<RoomItemVo> page, RoomQueryVo queryVo);

    BigDecimal selectMinRentByApartmentId(Long id);

    IPage<RoomItemVo> pageItemByApartmentId(IPage<RoomItemVo> page, Long id);
}