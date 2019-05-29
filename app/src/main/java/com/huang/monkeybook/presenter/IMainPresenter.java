//Copyright (c) 2017. 章钦豪. All rights reserved.
package com.huang.monkeybook.presenter;

import com.huang.basemvplib.IPresenter;

public interface IMainPresenter extends IPresenter {
    void queryBookShelf(Boolean needRefresh);
}
