package org.regulus.common.model;

/**
 * 
 * @ClassName: PageModel  
 * @Description: 分页对象
 * @author ejshi  
 * @date 2018年1月31日 下午10:55:17  
 *
 */
public class PageModel implements java.io.Serializable{

	private static final long serialVersionUID = 543931401528442285L;

	private static final int DEFAULT_PAGE_NUM = 1;
	private static final int DEFAULT_PAGE_SIZE = 10;

	/**
	 * 页数，如第一页 ，pageNum = 1
	 */
	private Integer pageNum = DEFAULT_PAGE_NUM;
	/**
	 * 每页数据条数，如每页10条，pageSize = 10
	 */
	private Integer pageSize = DEFAULT_PAGE_SIZE;
	
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
