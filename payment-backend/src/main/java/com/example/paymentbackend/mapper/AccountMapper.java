package com.example.paymentbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.paymentbackend.entity.po.AccountPO;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Administrator
* @description 针对表【account】的数据库操作Mapper
* @createDate 2024-09-21 11:51:30
* @Entity com.example.paymentproject.entity.dto.Account
*/
@Mapper
public interface AccountMapper extends BaseMapper<AccountPO> {

}




