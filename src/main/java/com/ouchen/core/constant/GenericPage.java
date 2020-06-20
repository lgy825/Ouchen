package com.ouchen.core.constant;

import java.util.Collections;
import java.util.List;

/**
 * 支持泛型的分页数据对象的默认实现类
 */
@SuppressWarnings("unchecked")
public class GenericPage<T> implements java.io.Serializable {

	private static final long serialVersionUID = 5764825062853239934L;

	/** 页大小（每页数据个数） */
	private int pageSize;

	/** 当前页号 */
	private int pageNo;

	/** 数据总个数 */
	private long totalCount = 0;
	
	/** 数据总页数数 */
	private int totalPage = 0;
	
	/** 当前页数据列表 */
	private List<T> list;
	
	public int getTotalPage() {
		double totalResults = new Long(getTotalCount()).doubleValue();
		return (totalResults % getPageSize()==0)?new Double(Math.floor(totalResults / getPageSize())).intValue():(new Double(Math.floor(totalResults / getPageSize())).intValue()+1);
//		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public GenericPage() {}

	/**
	 * 根据当前页号、页大小（每页数据个数）、当前页数据列表、数据总个数构造分页数据对象的实例。
	 * @param pageNo 当前页号
	 * @param pageSize 页大小（每页数据个数）
	 * @param elements 当前页数据列表
	 * @param totalCount 数据总个数
	 */
	public GenericPage(
			int pageNo, int pageSize, List<T> elements, long totalCount) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.list = elements;
		this.totalCount = totalCount;
	}
	
    /**
     * 定义一空页
     *
     * @see #emptyPage()
     */
	public static final GenericPage EMPTY_PAGE = new GenericPage(
			0, 0, Collections.emptyList(), 0);

    /**
     * 获取一空页
     */
	public static <E> GenericPage<E> emptyPage() {
		return (GenericPage<E>) EMPTY_PAGE;
	}



	/* (non-Javadoc)
	 * @see com.harmony.framework.query.generic.IGenericPage#getTotalCount()
	 */
	public long getTotalCount() {
		return totalCount;
	}



	/* (non-Javadoc)
	 * @see com.harmony.framework.query.generic.IGenericPage#getPageSize()
	 */
	public int getPageSize() {
		return pageSize;
	}

	/* (non-Javadoc)
	 * @see com.harmony.framework.query.generic.IGenericPage#getPageNo()
	 */
	public int getPageNo() {
		return pageNo;
	}
	
	public List<T> getList() {
		return list;
	}
	
    /**
     * 根据页大小（每页数据个数）获取给定页号的第一条数据在总数据中的位置（从1开始）
     * @param pageNo 给定的页号
     * @param pageSize 页大小（每页数据个数）
     * @return 给定页号的第一条数据在总数据中的位置（从1开始）
     */
    public static int getStartOfPage(int pageNo, int pageSize) {
        int startIndex = (pageNo - 1) * pageSize + 1;
        if (startIndex < 1) startIndex = 1;
        return startIndex;
    }
    

}
