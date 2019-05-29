//Copyright (c) 2017. 章钦豪. All rights reserved.
package com.huang.monkeybook.model;

import com.huang.monkeybook.cache.ACache;
import com.huang.monkeybook.bean.LibraryBean;
import com.huang.monkeybook.bean.SearchBookBean;

import java.util.List;
import io.reactivex.Observable;

public interface IGxwztvBookModel extends IStationBookModel {

    Observable<List<SearchBookBean>> getKindBook(String url, int page);

    /**
     * 获取主页信息
     */
    Observable<LibraryBean> getLibraryData(ACache aCache);

    /**
     * 解析主页数据
     */
    Observable<LibraryBean> analyLibraryData(String data);
}
