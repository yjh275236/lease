package com.yjh.lease.web.app.mapper;

import com.yjh.lease.model.entity.FacilityInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author liubo
* @description 针对表【facility_info(配套信息表)】的数据库操作Mapper
* @createDate 2023-07-26 11:12:39
* @Entity entity.com.yjh.lease.model.FacilityInfo
*/
public interface FacilityInfoMapper extends BaseMapper<FacilityInfo> {

    List<FacilityInfo> selectListByRoomId(Long id);

    List<FacilityInfo> selectListByApartmentId(Long id);
}




