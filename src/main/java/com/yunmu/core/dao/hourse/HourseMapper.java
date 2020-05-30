package com.yunmu.core.dao.hourse;

import com.yunmu.core.model.hourse.Hourse;
import com.yunmu.core.model.hourse.HourseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface HourseMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunmu_hourse
     *
     * @mbggenerated
     */
    int countByExample(HourseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunmu_hourse
     *
     * @mbggenerated
     */
    int deleteByExample(HourseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunmu_hourse
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunmu_hourse
     *
     * @mbggenerated
     */
    int insert(Hourse record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunmu_hourse
     *
     * @mbggenerated
     */
    int insertSelective(Hourse record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunmu_hourse
     *
     * @mbggenerated
     */
    List<Hourse> selectByExample(HourseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunmu_hourse
     *
     * @mbggenerated
     */
    Hourse selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunmu_hourse
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Hourse record, @Param("example") HourseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunmu_hourse
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Hourse record, @Param("example") HourseExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunmu_hourse
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Hourse record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunmu_hourse
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Hourse record);
}