package com.yunmu.core.dao.personnel;

import com.yunmu.core.model.personnel.Personnel;
import com.yunmu.core.model.personnel.PersonnelExample;
import com.yunmu.core.model.personnel.PersonnelExt;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PersonnelMapperExt {


    List<PersonnelExt> getPersonnalPage(Map<String, Object> params);
}