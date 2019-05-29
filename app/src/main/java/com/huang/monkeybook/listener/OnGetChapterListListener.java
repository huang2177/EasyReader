//Copyright (c) 2017. 章钦豪. All rights reserved.
package com.huang.monkeybook.listener;

import com.huang.monkeybook.bean.BookShelfBean;

public interface OnGetChapterListListener {
    public void success(BookShelfBean bookShelfBean);
    public void error();
}
