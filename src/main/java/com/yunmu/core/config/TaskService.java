package com.yunmu.core.config;

import com.yunmu.back.controller.FileController;
import com.yunmu.core.dao.owner.OwnerMapper;
import com.yunmu.core.dao.owner.OwnerMapperExt;
import com.yunmu.core.util.IdUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class TaskService {

    @Autowired
    private OwnerMapperExt ownerMapperExt;
    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    @Scheduled(fixedRate = 10*24*3600*1000)
    public void deleteInvalidCheckCode() {

        //更新业主的token信息
        String tokenId= IdUtils.getId(11);
        ownerMapperExt.updateTokon(tokenId);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.info(sdf.format(new Date())+" 时间更新了业主的token信息");
    }


}
