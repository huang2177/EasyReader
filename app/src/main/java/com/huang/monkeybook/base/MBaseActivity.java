//Copyright (c) 2017. 章钦豪. All rights reserved.
package com.huang.monkeybook.base;

import com.huang.basemvplib.IPresenter;
import com.huang.basemvplib.impl.BaseActivity;
import com.umeng.analytics.MobclickAgent;

public abstract class MBaseActivity<T extends IPresenter> extends BaseActivity<T>{
    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
