package com.hope.saas.wms.api.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hope.saas.common.entity.wms.ApiReqInfoEntity;
import com.hope.saas.wms.api.dao.ApiReqInfoDao;
import com.hope.saas.wms.api.service.ApiReqInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * api请求记录
 *
 * @author Maduo
 * @date 2020-03-20 14:09:44
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ApiReqInfoServiceImpl extends ServiceImpl<ApiReqInfoDao, ApiReqInfoEntity> implements ApiReqInfoService {

}