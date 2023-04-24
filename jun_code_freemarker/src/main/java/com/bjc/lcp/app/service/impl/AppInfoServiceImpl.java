package com.bjc.lcp.app.service.impl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bjc.lcp.app.entity.AppInfoEntity;
import com.bjc.lcp.app.mapper.AppInfoMapper;
import com.bjc.lcp.app.service.AppInfoService;

/**
 * @description 应用表服务层实现
 * @author wujun
 * @date 2023-04-24
 */
@Service
public class AppInfoServiceImpl extends ServiceImpl<AppInfoMapper, AppInfoEntity> implements AppInfoService {

	@Autowired
    private AppInfoMapper appInfoMapper;

}



