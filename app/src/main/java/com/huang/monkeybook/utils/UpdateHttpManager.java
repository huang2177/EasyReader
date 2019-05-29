package com.huang.monkeybook.utils;

import android.support.annotation.NonNull;
import android.util.Log;

import com.vector.update_app.HttpManager;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.DownloadProgressCallBack;
import com.zhouyou.http.exception.ApiException;

import java.io.File;
import java.util.Map;

/**
 * Created by Administrator on 2019/5/27.
 */

public class UpdateHttpManager implements HttpManager {
    @Override
    public void asyncGet(@NonNull String url, @NonNull Map<String, String> params, @NonNull Callback callBack) {

    }

    @Override
    public void asyncPost(@NonNull String url, @NonNull Map<String, String> params, @NonNull Callback callBack) {
        Log.e("------","asyncPost");
    }

    @Override
    public void download(@NonNull String url, @NonNull String path, @NonNull String fileName, @NonNull FileCallback callback) {
        EasyHttp.downLoad(url)
                .savePath(path)
                .saveName(fileName)//不设置默认名字是时间戳生成的
                .execute(new DownloadProgressCallBack<String>() {
                    @Override
                    public void update(long bytesRead, long contentLength, boolean done) {
                        int progress = (int) (bytesRead*100  / contentLength);
                        callback.onProgress(progress, contentLength);
                    }

                    @Override
                    public void onStart() {
                        callback.onBefore();
                    }

                    @Override
                    public void onComplete(String path) {
                        callback.onResponse(new File(path));
                    }

                    @Override
                    public void onError(ApiException e) {
                        callback.onError(e.toString());
                    }
                });
    }
}
