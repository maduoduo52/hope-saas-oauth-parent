package com.hope.saas.wms.api.dao.sys;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hope.saas.common.entity.wms.sys.SysPostEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 职位表
 *
 * @author Maduo
 * @date 2020-05-22 17:44:00
 */
@Mapper
public interface SysPostDao extends BaseMapper<SysPostEntity> {

    @Select("SELECT * FROM sys_post WHERE id in(SELECT post_id FROM sys_user_post WHERE user_id in(SELECT id FROM customer_info WHERE delete_flag= 0 and  `user_name` = #{userName}))")
    List<SysPostEntity> selectByLoginName(@Param("userName") String userName);
}