package kr.co.cgac.cbnu.utilities;

import java.io.Serializable;

public class Pagination implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int page;
	private int limit;
	private Long totalCount;
	private Long totalPages;
	private int offset;
	
	public Pagination(){
		this(1,15,0L,0L);
	}	
	
	public Pagination(int page, int limit){
		this.page = page;
		this.limit = limit;
		this.totalCount = 0L;
		this.totalPages = 0L;
	}
	
	public Pagination(int page, int limit, Long totalCount, Long totalPages){
		this.page = page;
		this.limit = limit;
		this.totalCount = totalCount;
		this.totalPages = totalPages;
	}
	public int getPage() {
		return page;
	}

	public int totalPages(){
		return (int) Math.ceil((double)this.totalCount/limit);
		
	}
	
	public int nextPage(){
		return this.page+1;
	}
	
	public int previousPage(){
		return this.page-1;
	}
	
	public boolean hasNextPage(){
		return this.nextPage() <=this.totalPages()? true :false;
	}
	
	public boolean hasPreviousPage(){
		return this.previousPage()>=1 ? true : false;
	}	
	
	public int offset(){
		this.offset = (this.page-1)* limit;
		return this.offset;
	}
	
	public void setPage(int currentPage) {
		this.page = currentPage;
		this.offset();
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public Long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Long totalCount) {
		this.totalCount = totalCount;
		this.totalPages = (long) totalPages();
		/*if(this.totalPages() < this.page){
			throw new CustomGenericException("7777", "THE TOTAL PAGES HAS ONLY " + this.totalPages() +" AND YOUR CURRENT PAGE IS "+ this.page);
		}*/
	}

	public Long getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Long totalPages) {
		this.totalPages = totalPages;
	}

	/**
	 * @return the offset
	 */
	public int getOffset() {
		return offset;
	}

	/**
	 * @param offset the offset to set
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
