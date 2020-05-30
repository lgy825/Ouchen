package com.yunmu.core.model.sys;

import java.io.Serializable;
import java.util.Date;

public class SysMenu implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_sys_menu.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_sys_menu.parent_id
     *
     * @mbggenerated
     */
    private String parentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_sys_menu.menu_type
     *
     * @mbggenerated
     */
    private Integer menuType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_sys_menu.menu_name
     *
     * @mbggenerated
     */
    private String menuName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_sys_menu.menu_href
     *
     * @mbggenerated
     */
    private String menuHref;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_sys_menu.menu_target
     *
     * @mbggenerated
     */
    private String menuTarget;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_sys_menu.menu_sort
     *
     * @mbggenerated
     */
    private Integer menuSort;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_sys_menu.is_show
     *
     * @mbggenerated
     */
    private Integer isShow;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_sys_menu.create_by
     *
     * @mbggenerated
     */
    private String createBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_sys_menu.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_sys_menu.update_by
     *
     * @mbggenerated
     */
    private String updateBy;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_sys_menu.update_time
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_sys_menu.del_flag
     *
     * @mbggenerated
     */
    private Integer delFlag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_sys_menu.shiro_flag
     *
     * @mbggenerated
     */
    private String shiroFlag;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunmu_sys_menu.icon_class
     *
     * @mbggenerated
     */
    private String iconClass;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table yunmu_sys_menu
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_sys_menu.id
     *
     * @return the value of yunmu_sys_menu.id
     *
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_sys_menu.id
     *
     * @param id the value for yunmu_sys_menu.id
     *
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_sys_menu.parent_id
     *
     * @return the value of yunmu_sys_menu.parent_id
     *
     * @mbggenerated
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_sys_menu.parent_id
     *
     * @param parentId the value for yunmu_sys_menu.parent_id
     *
     * @mbggenerated
     */
    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_sys_menu.menu_type
     *
     * @return the value of yunmu_sys_menu.menu_type
     *
     * @mbggenerated
     */
    public Integer getMenuType() {
        return menuType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_sys_menu.menu_type
     *
     * @param menuType the value for yunmu_sys_menu.menu_type
     *
     * @mbggenerated
     */
    public void setMenuType(Integer menuType) {
        this.menuType = menuType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_sys_menu.menu_name
     *
     * @return the value of yunmu_sys_menu.menu_name
     *
     * @mbggenerated
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_sys_menu.menu_name
     *
     * @param menuName the value for yunmu_sys_menu.menu_name
     *
     * @mbggenerated
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_sys_menu.menu_href
     *
     * @return the value of yunmu_sys_menu.menu_href
     *
     * @mbggenerated
     */
    public String getMenuHref() {
        return menuHref;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_sys_menu.menu_href
     *
     * @param menuHref the value for yunmu_sys_menu.menu_href
     *
     * @mbggenerated
     */
    public void setMenuHref(String menuHref) {
        this.menuHref = menuHref == null ? null : menuHref.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_sys_menu.menu_target
     *
     * @return the value of yunmu_sys_menu.menu_target
     *
     * @mbggenerated
     */
    public String getMenuTarget() {
        return menuTarget;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_sys_menu.menu_target
     *
     * @param menuTarget the value for yunmu_sys_menu.menu_target
     *
     * @mbggenerated
     */
    public void setMenuTarget(String menuTarget) {
        this.menuTarget = menuTarget == null ? null : menuTarget.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_sys_menu.menu_sort
     *
     * @return the value of yunmu_sys_menu.menu_sort
     *
     * @mbggenerated
     */
    public Integer getMenuSort() {
        return menuSort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_sys_menu.menu_sort
     *
     * @param menuSort the value for yunmu_sys_menu.menu_sort
     *
     * @mbggenerated
     */
    public void setMenuSort(Integer menuSort) {
        this.menuSort = menuSort;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_sys_menu.is_show
     *
     * @return the value of yunmu_sys_menu.is_show
     *
     * @mbggenerated
     */
    public Integer getIsShow() {
        return isShow;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_sys_menu.is_show
     *
     * @param isShow the value for yunmu_sys_menu.is_show
     *
     * @mbggenerated
     */
    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_sys_menu.create_by
     *
     * @return the value of yunmu_sys_menu.create_by
     *
     * @mbggenerated
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_sys_menu.create_by
     *
     * @param createBy the value for yunmu_sys_menu.create_by
     *
     * @mbggenerated
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_sys_menu.create_time
     *
     * @return the value of yunmu_sys_menu.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_sys_menu.create_time
     *
     * @param createTime the value for yunmu_sys_menu.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_sys_menu.update_by
     *
     * @return the value of yunmu_sys_menu.update_by
     *
     * @mbggenerated
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_sys_menu.update_by
     *
     * @param updateBy the value for yunmu_sys_menu.update_by
     *
     * @mbggenerated
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_sys_menu.update_time
     *
     * @return the value of yunmu_sys_menu.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_sys_menu.update_time
     *
     * @param updateTime the value for yunmu_sys_menu.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_sys_menu.del_flag
     *
     * @return the value of yunmu_sys_menu.del_flag
     *
     * @mbggenerated
     */
    public Integer getDelFlag() {
        return delFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_sys_menu.del_flag
     *
     * @param delFlag the value for yunmu_sys_menu.del_flag
     *
     * @mbggenerated
     */
    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_sys_menu.shiro_flag
     *
     * @return the value of yunmu_sys_menu.shiro_flag
     *
     * @mbggenerated
     */
    public String getShiroFlag() {
        return shiroFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_sys_menu.shiro_flag
     *
     * @param shiroFlag the value for yunmu_sys_menu.shiro_flag
     *
     * @mbggenerated
     */
    public void setShiroFlag(String shiroFlag) {
        this.shiroFlag = shiroFlag == null ? null : shiroFlag.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunmu_sys_menu.icon_class
     *
     * @return the value of yunmu_sys_menu.icon_class
     *
     * @mbggenerated
     */
    public String getIconClass() {
        return iconClass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunmu_sys_menu.icon_class
     *
     * @param iconClass the value for yunmu_sys_menu.icon_class
     *
     * @mbggenerated
     */
    public void setIconClass(String iconClass) {
        this.iconClass = iconClass == null ? null : iconClass.trim();
    }
}