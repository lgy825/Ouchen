package com.yunmu.back.service.owner;

import com.yunmu.core.constant.GenericPage;
import com.yunmu.core.model.owner.Owner;
import com.yunmu.core.model.owner.OwnerExt;

import java.util.Map;

/**
 * Created by 13544 on 2019/5/19.
 */
public interface OwnerService {

    public GenericPage<OwnerExt> getPageByCondition(Map<String, Object> params);

    public Boolean insert(Owner owner);

    public Owner getOwnerById(String id);

    Boolean update(Owner owner);

    String getOwnerRealName(String id);



    

}
