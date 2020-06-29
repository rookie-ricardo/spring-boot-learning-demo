package org.example.security.auth.result;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * <p>
 *  Api分页工具类
 * </p>
 *
 * @author 阿澈
 * @since 2020-01-05
 */
@JsonIgnoreProperties(value = {"records","total","size","current","records","searchCount","orders"})
public class ApiPage<T> extends Page<T> {

    public ApiPage() {
        super();
    }

    public ApiPage(long current, long size) {
        super(current, size);
    }

    public ApiPage(long current, long size, long total) {
        super(current, size, total);
    }

    public ApiPage(long current, long size, boolean isSearchCount) {
        super(current, size, isSearchCount);
    }

    public ApiPage(long current, long size, long total, boolean isSearchCount) {
        super(current, size, total, isSearchCount);
    }

    /**
     * 总数
     */
    private long totalCount = 0;

    /**
     * 每页显示条数，默认 10
     */
    private long pageSize = 10;

    /**
     * 当前页
     */
    private long currentPage = 1;


    /**
     * 查询数据列表
     */
    private List<T> data;


    public long getTotalCount() {
        return super.getTotal();
    }

    public long getPageSize() {
        return super.getSize();
    }

    public long getCurrentPage() {
        return super.getCurrent();
    }

    public List<T> getData() {
        return super.getRecords();
    }
}
