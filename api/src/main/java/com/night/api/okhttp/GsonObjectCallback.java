package com.night.api.okhttp;

import android.os.Handler;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 类的用途 如果要将得到的json直接转化为集合 建议使用该类 该类的onSuccess() onFailed()方法运行在主线程
 */

public abstract class GsonObjectCallback<T> implements Callback {
    private Handler handler = OkHttp3Utils.getInstance().getHandler();

    // 主线程处理
    public abstract void onSuccess(T t);

    // 主线程处理
    public abstract void onFailed(Call call, IOException e);

    // 请求失败
    @Override
    public void onFailure(final Call call, final IOException e) {

        handler.post(new Runnable() {
            @Override
            public void run() {
                onFailed(call, e);
            }
        });
        // Runnable runnable = new Runnable() {
        // @Override
        // public void run() {
        // onFailure(call, e);
        // }
        // };
        // ThreadPoolManager.getInstance().execute(runnable);
    }

    // 请求json 并直接返回泛型的对象 主线程处理
    @Override
    public void onResponse(Call call, Response response) throws IOException {
        String json = response.body().string();
        Class<T> cls = null;

        Class clz = this.getClass();
        ParameterizedType type = (ParameterizedType) clz.getGenericSuperclass();
        Type[] types = type.getActualTypeArguments();
        cls = (Class<T>) types[0];
        Gson gson = new Gson();
        final T t = gson.fromJson(json, cls);
        handler.post(new Runnable() {
            @Override
            public void run() {
                onSuccess(t);
            }
        });

    }
}
