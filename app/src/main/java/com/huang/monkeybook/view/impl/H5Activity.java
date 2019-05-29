package com.huang.monkeybook.view.impl;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ProgressBar;

import com.huang.monkeybook.R;
import com.huang.monkeybook.widget.webview.X5WebView;
import com.tencent.smtt.sdk.DownloadListener;

/**
 * Des:
 * Created by huang on 2019/5/28 0028 10:06
 */
public class H5Activity extends Activity implements X5WebView.OnProgressListener, DownloadListener {
    private X5WebView mWebView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h5);

        initView();
    }

    private void initView() {
        mWebView = findViewById(R.id.web);
        mProgressBar = findViewById(R.id.progressBar);

        mWebView.loadUrl(getIntent().getStringExtra("url"));

        mWebView.setProgressListener(this);
        mWebView.setDownloadListener(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.clearHistory();
            mWebView.clearCache(true);
            mWebView.destroy();
            mWebView = null;
        }
    }


    @Override
    public void onPageStarted() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onPageFinished() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onProgress(int i) {
        mProgressBar.setProgress(i);
    }

    @Override
    public void onDownloadStart(String url, String s1, String s2, String s3, long l) {
        Intent intent = new Intent(this, UpdateActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("type", "0");
        startActivity(intent);
        finish();
    }
}
