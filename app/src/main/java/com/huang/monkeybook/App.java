//Copyright (c) 2017. 章钦豪. All rights reserved.
package com.huang.monkeybook;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;
import android.util.Base64;

import com.huang.monkeybook.common.Config;
import com.huang.monkeybook.service.DownloadService;
import com.tencent.smtt.sdk.QbSdk;
import com.umeng.analytics.MobclickAgent;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.cache.converter.SerializableDiskConverter;

import java.net.Proxy;

import cn.jpush.android.api.JPushInterface;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class App extends MultiDexApplication {

    private static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        initUM();
        initEasyHttp();
        initX5WebView();
        instance = this;
        ProxyManager.initProxy();
        startService(new Intent(this, DownloadService.class));

        JPushInterface.init(this);//初始化JPush
    }

    private void initUM() {
        //        if (BuildConfig.IS_RELEASE) {
//            String channel = "debug";
//            try {
//                ApplicationInfo appInfo = getPackageManager()
//                        .getApplicationInfo(getPackageName(),
//                                PackageManager.GET_META_DATA);
//                channel = appInfo.metaData.getString("UMENG_CHANNEL_VALUE");
//            } catch (PackageManager.NameNotFoundException e) {
//                e.printStackTrace();
//            }
//            MobclickAgent.startWithConfigure(new MobclickAgent.UMAnalyticsConfig(this, getString(R.string.umeng_key), channel, MobclickAgent.EScenarioType.E_UM_NORMAL, true));
//        }
    }


    /**
     * 初始化网络请求
     */
    private void initEasyHttp() {
        EasyHttp.init(this);//默认初始化,必须调用
        EasyHttp.getInstance()
                //可以全局统一设置全局URL
                .setBaseUrl(Config.BASE_URL)//设置全局URL  url只能是域名 或者域名+端口号
                .debug("EasyHttp", true)
                //如果使用默认的60秒,以下三行也不需要设置
                .setReadTimeOut(60 * 1000)
                .setWriteTimeOut(60 * 100)
                .setConnectTimeout(60 * 100)
                //不需要可以设置为0
                .setOkproxy(Proxy.NO_PROXY)//防止抓包
                .setCertificates()
                .setRetryCount(3)//网络不好自动重试3次
                .setRetryDelay(500)//每次延时500ms重试
                .setRetryIncreaseDelay(500)//每次延时叠加500ms
                .setCacheDiskConverter(new SerializableDiskConverter())//默认缓存使用序列化转化
                .addInterceptor(chain -> {
                    Request request = chain.request();
                    if (TextUtils.equals(request.url().host(), "www.ds06ji.com")) {
                        Response response = chain.proceed(request);
                        String body = response.body().string();
                        String json = new String(Base64.decode(body, Base64.DEFAULT));
                        return response.newBuilder().body(ResponseBody.create(MediaType.parse("UTF-8"), json)).build();
                    }
                    return chain.proceed(request);
                });
    }

    /**
     * 初始化X5WebView
     */
    private void initX5WebView() {
        QbSdk.initX5Environment(getApplicationContext(), null);
    }

    public static App getInstance() {
        return instance;
    }
}
