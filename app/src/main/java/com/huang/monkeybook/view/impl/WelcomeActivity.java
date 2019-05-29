//Copyright (c) 2017. 章钦豪. All rights reserved.
package com.huang.monkeybook.view.impl;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.huang.basemvplib.IPresenter;
import com.huang.monkeybook.R;
import com.huang.monkeybook.base.MBaseActivity;
import com.huang.monkeybook.bean.MainResult;
import com.huang.monkeybook.common.Config;
import com.monke.immerselayout.ImmerseFrameLayout;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.utils.HttpLog;

public class WelcomeActivity extends MBaseActivity {

    private ImageView ivBg;
    private TextView ivIcon;
    private TextView tvIntro;
    private ImmerseFrameLayout whiteBg;


    private ValueAnimator welAnimator;

    @Override
    protected IPresenter initInjector() {
        return null;
    }

    @Override
    protected void onCreateActivity() {
        setContentView(R.layout.activity_welcome);
    }

    @Override
    protected void initData() {
        welAnimator = ValueAnimator.ofFloat(1f, 0f).setDuration(800);
        welAnimator.setStartDelay(1000);
    }

    @Override
    protected void bindView() {
        ivBg = findViewById(R.id.iv_bg);
        ivIcon = findViewById(R.id.iv_icon);
        tvIntro = findViewById(R.id.tv_intro);
        whiteBg = findViewById(R.id.bg_white);
    }

    @Override
    protected void bindEvent() {
        welAnimator.addUpdateListener(animation -> {
            float alpha = (Float) animation.getAnimatedValue();
            ivIcon.setAlpha(alpha);
            getWindow().getDecorView().getBackground().setAlpha((int) (255 * alpha));

            tvIntro.setAlpha(1f - alpha);
            whiteBg.setAlpha(1f - alpha);
        });
        welAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                doHttp();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    @Override
    protected void firstRequest() {
        welAnimator.start();
    }

    private void doHttp() {
        EasyHttp.get(Config.MANI_URL)
                .params("app_id", Config.APP_ID)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        HttpLog.e(e.getCode() + "");
                        startDefPage();
                    }

                    @Override
                    public void onSuccess(String json) {
                        if (TextUtils.isEmpty(json)) {
                            startDefPage();
                            return;
                        }
                        HttpLog.e(json);
                        MainResult result = new Gson().fromJson(json, MainResult.class);
                        if (!result.isSuccess()) {
                            startDefPage();
                            return;
                        }
                        if (result.isUpdate()) {
                            startUpdatePage(result.getUpdate_url());
                            return;
                        }
                        if (result.isWap()) {
                            startH5Page(result.getWap_url());
                            return;
                        }
                        startDefPage();
                    }
                });
    }

    /**
     * 跳转至默认界面
     */
    private void startDefPage() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivityByAnim(intent, android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }

    /**
     * 跳转至默认界面
     */
    private void startH5Page(String wapUrl) {
        Intent intent = new Intent(this, H5Activity.class);
        intent.putExtra("url", wapUrl);
        startActivityByAnim(intent, android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }

    /**
     * 跳转至默认界面
     */
    private void startUpdatePage(String updateUrl) {
        Intent intent = new Intent(this, UpdateActivity.class);
        intent.putExtra("url", updateUrl);
        startActivityByAnim(intent, android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }
}
