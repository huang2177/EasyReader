package com.huang.basemvplib.impl;

import android.support.annotation.NonNull;

import com.huang.basemvplib.IPresenter;
import com.huang.basemvplib.IView;

public abstract class BasePresenterImpl<T extends IView> implements IPresenter {
    protected T mView;

    @Override
    public void attachView(@NonNull IView iView) {
        mView = (T) iView;
    }
}
