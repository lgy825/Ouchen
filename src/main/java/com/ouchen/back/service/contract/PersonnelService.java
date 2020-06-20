package com.ouchen.back.service.contract;

import com.ouchen.core.constant.GenericPage;
import com.ouchen.core.model.personnel.Personnel;
import com.ouchen.core.model.personnel.PersonnelExt;

import java.util.Map;

public interface PersonnelService {
    public GenericPage<PersonnelExt> getPageByCondition(Map<String, Object> params);

    public Boolean insert(Personnel personnel);

    public Personnel getPersonnelById(String id);

    Boolean update(Personnel personnel);

    boolean updateStatus(Personnel personnel);
}
