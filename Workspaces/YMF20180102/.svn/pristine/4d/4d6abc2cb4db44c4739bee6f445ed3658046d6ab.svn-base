package com.yeepay.g3.ymf.boss.search;

import java.util.List;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c)2015
 * Company: YeePay
 *
 * @author chen.liu on 2017/6/26.
 */
public class SearchResult<T> {

    private Long totalCount;

    private List<T> items;

    public Long getTotalCount() {
        return totalCount;
    }

    public List<T> getItems() {
        return items;
    }


    public static final class SearchResultBuilder<T> {
        private Long totalCount;
        private List<T> items;

        private SearchResultBuilder() {
        }

        public static SearchResultBuilder builder() {
            return new SearchResultBuilder();
        }

        public SearchResultBuilder totalCount(Long totalCount) {
            this.totalCount = totalCount;
            return this;
        }

        public SearchResultBuilder items(List<T> items) {
            this.items = items;
            return this;
        }

        public SearchResult build() {
            SearchResult searchResult = new SearchResult();
            searchResult.totalCount = this.totalCount;
            searchResult.items = this.items;
            return searchResult;
        }
    }
}
