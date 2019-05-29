//Copyright (c) 2017. 章钦豪. All rights reserved.
package com.huang.monkeybook.view;

import com.huang.basemvplib.IView;
import java.io.File;

public interface IImportBookView extends IView{

    /**
     * 新增书籍
     * @param newFile
     */
    void addNewBook(File newFile);

    /**
     * 书籍搜索完成
     */
    void searchFinish();

    /**
     * 添加成功
     */
    void addSuccess();

    /**
     * 添加失败
     */
    void addError();
}