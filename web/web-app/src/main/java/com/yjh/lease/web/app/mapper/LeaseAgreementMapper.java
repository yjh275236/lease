package com.yjh.lease.web.app.mapper;

import com.yjh.lease.model.entity.LeaseAgreement;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yjh.lease.web.app.vo.agreement.AgreementItemVo;

import java.util.List;

/**
* @author liubo
* @description 针对表【lease_agreement(租约信息表)】的数据库操作Mapper
* @createDate 2023-07-26 11:12:39
* @Entity entity.com.yjh.lease.model.LeaseAgreement
*/
public interface LeaseAgreementMapper extends BaseMapper<LeaseAgreement> {

    List<AgreementItemVo> listItemByPhone(String phone);
}




