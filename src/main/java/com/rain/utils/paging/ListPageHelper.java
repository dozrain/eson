package com.rain.utils.paging;

import java.util.Collections;
import java.util.List;

/**
 * Created by mail on 2017\12\19 0019.
 */
public class ListPageHelper<T> {
    private List<T> data;

    private int lastPage;

    private int nowPage;

    private int nextPage;

    private int pageSize;

    private int totalPage;

    private int totalCount;

    public int getPageSize() {
        return pageSize;
    }

    public List<T> getData() {
        return data;
    }
    public int getLastPage() {
        return lastPage;
    }

    public int getNowPage() {
        return nowPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public ListPageHelper(List<T> data, int nowPage, int pageSize) {
        if (data == null || data.isEmpty()) {
            throw new IllegalArgumentException("data must be not empty!");
        }
        this.data = data;
        this.pageSize = pageSize;
        this.nowPage = nowPage;
        this.totalCount = data.size();
        this.totalPage = (totalCount + pageSize - 1) / pageSize;
        this.lastPage = nowPage - 1 > 1 ? nowPage - 1 : 1;
        this.nextPage = nowPage >= totalPage ? totalPage : nowPage + 1;
    }
    public List<T> getPagedList() {
        int fromIndex = (nowPage - 1) * pageSize;
        if (fromIndex >= data.size()) {
            return Collections.emptyList();//空数组
        }
        if(fromIndex<0){
            return Collections.emptyList();//空数组
        }
        int toIndex = nowPage * pageSize;
        if (toIndex >= data.size()) {
            toIndex = data.size();
        }
        return data.subList(fromIndex, toIndex);
    }
}
