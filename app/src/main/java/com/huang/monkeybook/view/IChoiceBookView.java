//Copyright (c) 2017. 章钦豪. All rights reserved.
package com.huang.monkeybook.view;

import com.huang.monkeybook.view.adapter.ChoiceBookAdapter;
import com.huang.basemvplib.IView;
import com.huang.monkeybook.bean.SearchBookBean;

import java.util.List;

public interface IChoiceBookView extends IView{

    void refreshSearchBook(List<SearchBookBean> books);

    void loadMoreSearchBook(List<SearchBookBean> books);

    void refreshFinish(Boolean isAll);

    void loadMoreFinish(Boolean isAll);

    void searchBookError();

    void addBookShelfSuccess(List<SearchBookBean> searchBooks);

    void addBookShelfFailed(int code);

    ChoiceBookAdapter getSearchBookAdapter();

    void updateSearchItem(int index);

    void startRefreshAnim();
}
