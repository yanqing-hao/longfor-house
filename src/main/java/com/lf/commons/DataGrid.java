package com.lf.commons;

import java.util.List;

/**
 * 返回DataGrid类型数据
 * @param <T>
 */
public class DataGrid<T> {
	
	private Long total;
	
	private List<T> rows;

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	
	
}
