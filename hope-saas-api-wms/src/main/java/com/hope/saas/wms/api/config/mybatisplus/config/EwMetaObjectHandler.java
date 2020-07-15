package com.hope.saas.wms.api.config.mybatisplus.config;

import com.baomidou.mybatisplus.mapper.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * @author Maduo
 * @date 2020/3/20 11:07
 */
@Slf4j
public class EwMetaObjectHandler extends MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        try {
            //设置添加时间
            Object addTime = getFieldValByName("addTime", metaObject);
            if (addTime == null) {
                setFieldValByName("addTime", new Date(), metaObject);
            }
            //设置版本号
            Object version = getFieldValByName("version", metaObject);
            if (version == null) {
                setFieldValByName("version", 0, metaObject);
            }
            //设置逻辑删除字段
            Object deleteFlag = getFieldValByName("deleteFlag", metaObject);
            if (deleteFlag == null) {
                setFieldValByName("deleteFlag", false, metaObject);
            }

        } catch (Exception e) {
            log.error("添加自动填充异常:" + e);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {

        try {
            //设置修改时间
            Object updateTime = getFieldValByName("updateTime", metaObject);
            if (updateTime == null) {
                setFieldValByName("updateTime", new Date(), metaObject);
            }

        } catch (Exception e) {
            log.error("修改自动填充异常:" + e);
        }
    }
}
