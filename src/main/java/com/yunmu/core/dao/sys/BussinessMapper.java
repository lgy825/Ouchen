package com.yunmu.core.dao.sys;

import com.yunmu.core.model.sys.Bussiness;
import com.yunmu.core.model.sys.BussinessExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BussinessMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunmu_bussiness
     *
     * @mbggenerated
     */
    int countByExample(BussinessExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunmu_bussiness
     *
     * @mbggenerated
     */
    int deleteByExample(BussinessExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunmu_bussiness
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunmu_bussiness
     *
     * @mbggenerated
     */
    int insert(Bussiness record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunmu_bussiness
     *
     * @mbggenerated
     */
    int insertSelective(Bussiness record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunmu_bussiness
     *
     * @mbggenerated
     */
    List<Bussiness> selectByExample(BussinessExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunmu_bussiness
     *
     * @mbggenerated
     */
    Bussiness selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunmu_bussiness
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Bussiness record, @Param("example") BussinessExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunmu_bussiness
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Bussiness record, @Param("example") BussinessExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunmu_bussiness
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Bussiness record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunmu_bussiness
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Bussiness record);
}