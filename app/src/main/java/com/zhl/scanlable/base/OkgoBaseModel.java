package com.zhl.scanlable.base;


import android.content.Context;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.zhl.scanlable.app.MyApplication;
import com.zhl.scanlable.utils.PreferenceUtil;
import com.zhl.scanlable.utils.log.LogUtil;

import java.util.Map;

/**
 * Created by Administrator on 2017/12/19.
 */

public class OkgoBaseModel {

    public static OkgoBaseModel getInstance() {
        return OkgoBaseModel.SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final OkgoBaseModel instance = new OkgoBaseModel();
    }

    public void getRequestNet(Context context, String url, Map<String, String> params, final ICallListener<String> iCallListener) {
//        String F_ID = PreferenceUtil.getString(MyApplication.getInstance(), BaseConstants.F_ID, "");
        String baseUrl = "http://" + PreferenceUtil.getString(context, "IP", "") + ":" + PreferenceUtil.getString(context, "DK", "") + "/";
        String URL = baseUrl + url;
        OkGo.<String>post(URL)
                .tag(this)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        iCallListener.callSuccess(response.body());
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        iCallListener.onFinish();
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        iCallListener.callFailed();
                    }
                });
    }

    public void getRequestNetGET(Context context, String url, Map<String, String> params, final ICallListener<String> iCallListener) {
//        String F_ID = PreferenceUtil.getString(MyApplication.getInstance(), BaseConstants.F_ID, "");
        String baseUrl = "http://" + PreferenceUtil.getString(context, "IP", "") + ":" + PreferenceUtil.getString(context, "DK", "") + "/";
        String URL = baseUrl + url;
        LogUtil.e(URL);
        LogUtil.e(params);
        OkGo.<String>get(URL)
                .tag(this)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        iCallListener.callSuccess(response.body());
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        iCallListener.onFinish();
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        iCallListener.callFailed();
                    }
                });
    }

 public void getRequestNetPUT(Context context, String url, Map<String, String> params, final ICallListener<String> iCallListener) {
//        String F_ID = PreferenceUtil.getString(MyApplication.getInstance(), BaseConstants.F_ID, "");
        String baseUrl = "http://" + PreferenceUtil.getString(context, "IP", "") + ":" + PreferenceUtil.getString(context, "DK", "") + "/";
        String URL = baseUrl + url;
        LogUtil.e(URL);
        LogUtil.e(params);
        OkGo.<String>put(URL)
                .tag(this)
                .params(params)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        iCallListener.callSuccess(response.body());
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        iCallListener.onFinish();
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        iCallListener.callFailed();
                    }
                });
    }



}
