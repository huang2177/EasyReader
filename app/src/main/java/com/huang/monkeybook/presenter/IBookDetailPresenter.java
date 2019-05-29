//Copyright (c) 2017. 章钦豪. All rights reserved.
package com.huang.monkeybook.presenter;

import com.huang.basemvplib.IPresenter;
import com.huang.monkeybook.bean.BookShelfBean;
import com.huang.monkeybook.bean.SearchBookBean;

public interface IBookDetailPresenter extends IPresenter {

    int getOpenfrom();

    SearchBookBean getSearchBook();

    BookShelfBean getBookShelf();

    Boolean getInBookShelf();

    void getBookShelfInfo();

    void addToBookShelf();

    void removeFromBookShelf();
}
