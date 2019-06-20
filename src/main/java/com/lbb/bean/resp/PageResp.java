package com.lbb.bean.resp;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@ToString
public class PageResp<T> {

    private List<T> list;

    private Integer pageIndex;

    private Integer pageSize;

    private Long total;

    private Map<String, BigDecimal> sumMap = new HashMap<>();

    public PageResp(List<T> list, Long total) {
        this.list = list;
        this.total = total;
    }

    public PageResp() {
    }
}
