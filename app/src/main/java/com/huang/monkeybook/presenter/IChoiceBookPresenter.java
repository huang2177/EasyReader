//Copyright (c) 2017. 章钦豪. All rights reserved.
package com.huang.monkeybook.presenter;

import com.huang.basemvplib.IPresenter;
import com.huang.monkeybook.bean.SearchBookBean;

public interface IChoiceBookPresenter extends IPresenter {

    int getPage();

    void initPage();

    void toSearchBooks(String key);

    void addBookToShelf(final SearchBookBean searchBookBean);

    String getTitle();
}