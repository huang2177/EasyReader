package com.huang.monkeybook.view.impl;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;

import com.huang.monkeybook.R;
import com.huang.monkeybook.utils.FileUtil;
import com.huang.monkeybook.utils.UpdateHttpManager;
import com.vector.update_app.UpdateAppBean;
import com.vector.update_app.UpdateAppManager;
import com.vector.update_app.service.DownloadService;

import java.io.File;

public class UpdateActivity extends Activity {
    private ProgressBar roundProgress;

    private AlertDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        initView();
        updateVersion();
    }

    private void initView() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_update, null);
        roundProgress = view.findViewById(R.id.progressBar);


        mDialog = new AlertDialog.Builder(this)
                .setView(view)
                .setCancelable(false)
                .create();
    }


    private void updateVersion() {
        UpdateAppBean updateAppBean = new UpdateAppBean();
        updateAppBean.dismissNotificationProgress(true);
        updateAppBean.setHttpManager(new UpdateHttpManager());
        updateAppBean.setTargetPath(FileUtil.getFilePath(this));
        updateAppBean.setApkFileUrl(getIntent().getStringExtra("url"));

        UpdateAppManager.download(this, updateAppBean, new DownloadService.DownloadCallback() {
            @Override
            public void onStart() {
                mDialog.show();
                roundProgress.setVisibility(View.VISIBLE);
            }

            @Override
            public void onProgress(float f, long totalSize) {
                roundProgress.setProgress((int) f);
                Log.e("---", ((int) f) + "%");
            }

            @Override
            public void setMax(long totalSize) {
            }

            @Override
            public boolean onFinish(File file) {
                mDialog.dismiss();
                finish();
                return true;
            }

            @Override
            public void onError(String msg) {
                Log.e("-------", msg);
            }

            @Override
            public boolean onInstallAppAndAppOnForeground(File file) {
                return false;
            }
        });
    }
}
