package org.durcframework.entity;


/**
 * 负责查询的实体类,其它关于查询的类都要继承该类
 * 
 * @author hc.tang 2013年11月14日
 * 
 */
public class SearchEntity {

	/** 第一页 */
	private int pageIndex = 1;
	/** 每页10条记录 */
	private int pageSize = 10;

	private String sortname;
	private String sortorder;
	
	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		if(pageIndex <= 0){
			pageIndex = 1;
		}
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if(pageSize <= 0){
			pageSize = 10;
		}
		this.pageSize = pageSize;
	}

	public String getSortname() {
		return sortname;
	}

	public void setSortname(String sortname) {
		this.sortname = sortname;
	}

	public String getSortorder() {
		return sortorder;
	}

	public void setSortorder(String sortorder) {
		this.sortorder = sortorder;
	}
}
