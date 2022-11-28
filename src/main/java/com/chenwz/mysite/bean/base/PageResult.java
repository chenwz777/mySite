package com.chenwz.mysite.bean.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("分页结果集")
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 总记录数
     */
    @ApiModelProperty("总记录数")
    private Long total = 0L;

    /**
     * 当前页码
     */
    @ApiModelProperty("当前页码")
    private Long current = 1L;

    /**
     * 结果集
     */
    @ApiModelProperty("结果集")
    private List<T> items;

    public PageResult() {

    }

    /**
     * 将mybatis-plus的page转成自定义的PageResult
     */
    public PageResult(Page<T> page) {
        this.setItems(page.getRecords());
        this.setTotal(page.getTotal());
        this.setCurrent(page.getCurrent());
    }

    public PageResult(IPage<T> page) {
        this.setItems(page.getRecords());
        this.setTotal(page.getTotal());
        this.setCurrent(page.getCurrent());
    }

    /**
     * 将mybatis-plus的page转成自定义的PageResult
     * 可单独设置rows
     */
    public PageResult(Page<T> page, List<T> t) {
        this.setItems(t);
        this.setTotal(page.getTotal());
        this.setCurrent(page.getCurrent());
    }

    public PageResult(IPage<T> page, List<T> t) {
        this.setItems(t);
        this.setTotal(page.getTotal());
        this.setCurrent(page.getCurrent());
    }

}
