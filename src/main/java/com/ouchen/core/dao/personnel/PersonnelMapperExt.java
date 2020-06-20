package com.ouchen.core.dao.personnel;

import com.ouchen.core.model.personnel.PersonnelExt;

import java.util.List;
import java.util.Map;

public interface PersonnelMapperExt {


    List<PersonnelExt> getPersonnalPage(Map<String, Object> params);
}