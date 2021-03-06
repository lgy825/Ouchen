package com.ouchen.core.dao.owner;

import com.ouchen.core.model.owner.Owner;
import com.ouchen.core.model.owner.OwnerExt;

import java.util.List;
import java.util.Map;

public interface OwnerMapperExt {

    List<OwnerExt> getOwnerPage(Map<String, Object> params);

    String getOwnerRealNameById(String id);

    OwnerExt getOwnerByCondition(Owner owner);

    List<OwnerExt> isExistOwner(Owner owner);

    void  updateTokon(String tokenId);


}