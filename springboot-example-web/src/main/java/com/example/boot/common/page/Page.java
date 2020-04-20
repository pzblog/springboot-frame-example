package com.example.boot.common.page;

import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Page<T> implements IPage<T> {

	private static final long serialVersionUID = 8545996863226528798L;

	/**
	 * 默认每页记录数量
	 */
	private static final int DEFAULT_SIZE = 15;

	/**
	 * 查询数据列表
	 */
	@JsonProperty("rows")
	private List<T> records = Collections.emptyList();
	/**
	 * 总记录数
	 */
	@JsonProperty("count")
	private long total = 0;
	/**
	 * 每页显示条数，默认 10
	 */
	@JsonProperty("size")
	private long size = DEFAULT_SIZE;
	/**
	 * 当前页
	 */
	@JsonIgnore
	private long current = 1;
	/**
	 * <p>
	 * SQL 排序 ASC 数组
	 * </p>
	 */
	@JsonIgnore
	private String[] ascs;
	/**
	 * <p>
	 * SQL 排序 DESC 数组
	 * </p>
	 */
	@JsonIgnore
	private String[] descs;
	/**
	 * <p>
	 * 自动优化 COUNT SQL
	 * </p>
	 */
	@JsonIgnore
	private boolean optimizeCountSql = true;
	/**
	 * 是否进行 count 查询
	 */
	@JsonIgnore
	private boolean isSearchCount = true;

	/**
	 * 是否分页
	 */
	@JsonIgnore
	private boolean isPageable = true;

	public Page() {
	}

	/**
	 * <p>
	 * 分页构造函数
	 * </p>
	 * 
	 * @param current 当前页
	 * @param size    每页显示条数
	 */
	public Page(Integer current, Integer size) {
		this(current, size, 0);
	}

	public Page(Integer current, Integer size, Integer total) {
		this(current, size, total, true);
	}

	/**
	 * 分页构造函数
	 * 
	 * @param isPageable 是否分页
	 */
	public Page(boolean isPageable) {
		this(1, -1, 0, true);
		this.isPageable = isPageable;
	}

	public Page(Number current, Number size, boolean isSearchCount) {
		this(current, size, 0, isSearchCount);
	}

	public Page(Number current, Number size, Number total, boolean isSearchCount) {
		if (current != null && current.longValue() > 0) {
			this.current = current.longValue();
		}
		if (size != null && size.longValue() > 0) {
			this.size = size.longValue();
		}
		if (total != null && total.longValue() > 0) {
			this.total = total.longValue();
		}
		this.isSearchCount = isSearchCount;
	}

	/**
	 * <p>
	 * 是否存在上一页
	 * </p>
	 * 
	 * @return true / false
	 */
	public boolean hasPrevious() {
		return this.current > 1;
	}

	/**
	 * <p>
	 * 是否存在下一页
	 * </p>
	 * 
	 * @return true / false
	 */
	public boolean hasNext() {
		return this.current < this.getPages();
	}

	@Override
	public List<T> getRecords() {
		return this.records;
	}

	@Override
	public Page<T> setRecords(List<T> records) {
		this.records = records;
		if (!this.isPageable) {
			this.total = (this.records != null ? this.records.size() : 0);
		}
		return this;
	}

	@Override
	public long getPages() {
		if (getSize() == 0) {
			return 0L;
		}
		if (!this.isPageable) {
			return getTotal() > 0 ? 1L : 0L;
		}
		long pages = getTotal() / getSize();
		if (getTotal() % getSize() != 0) {
			pages++;
		}
		return pages;
	}

	@Override
	public long getTotal() {
		return this.total;
	}

	@Override
	public Page<T> setTotal(Long total) {
		this.total = total;
		return this;
	}

	@Override
	public long getSize() {
		return this.size;
	}

	@Override
	public Page<T> setSize(long size) {
		this.size = (size <= 0 ? DEFAULT_SIZE : size);
		return this;
	}

	@Override
	public long getCurrent() {
		return this.current;
	}

	@Override
	public Page<T> setCurrent(long current) {
		this.current = (current <= 0 ? 1 : current);
		return this;
	}

	@Override
	public String[] ascs() {
		return ascs;
	}

	public Page<T> setAscs(List<String> ascs) {
		if (CollectionUtils.isNotEmpty(ascs)) {
			this.ascs = ascs.toArray(new String[0]);
		}
		return this;
	}

	/**
	 * <p>
	 * 升序
	 * </p>
	 * 
	 * @param ascs 多个升序字段
	 */
	public Page<T> setAsc(String... ascs) {
		this.ascs = ascs;
		return this;
	}

	@Override
	public String[] descs() {
		return descs;
	}

	public Page<T> setDescs(List<String> descs) {
		if (CollectionUtils.isNotEmpty(descs)) {
			this.descs = descs.toArray(new String[0]);
		}
		return this;
	}

	/**
	 * <p>
	 * 降序
	 * </p>
	 * 
	 * @param descs 多个降序字段
	 */
	public Page<T> setDesc(String... descs) {
		this.descs = descs;
		return this;
	}

	@Override
	public boolean optimizeCountSql() {
		return optimizeCountSql;
	}

	@JsonIgnore
	@Override
	public boolean isSearchCount() {
		if (total < 0) {
			return false;
		}
		return isSearchCount;
	}

	Page<T> setSearchCount(boolean isSearchCount) {
		this.isSearchCount = isSearchCount;
		return this;
	}

	public Page<T> setOptimizeCountSql(boolean optimizeCountSql) {
		this.optimizeCountSql = optimizeCountSql;
		return this;
	}

}
