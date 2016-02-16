package com.codera.myfirsthybridapp.listener;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.webkit.WebView;
import android.widget.LinearLayout;

import io.dcloud.common.DHInterface.ICore;
import io.dcloud.common.DHInterface.IWebview;
import io.dcloud.common.DHInterface.IWebviewStateListener;
import io.dcloud.feature.internal.sdk.SDK;

/**
 * 创建webview并且调用webview
 * Created by Administrator on 2016/2/15.
 */
public class WebviewModeImpl implements ICore.ICoreStatusListener {

    IWebview mWebview = null;
    LinearLayout mBtns = null;
    Activity mActivity = null;
    ViewGroup mRootView = null;


    public WebviewModeImpl(Activity activity, ViewGroup rootView) {

        this.mActivity = activity;
        this.mRootView = rootView;
        mBtns = new LinearLayout(activity);
        mRootView.setBackgroundColor(Color.rgb(255, 255, 255));
        mRootView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        mWebview.onRootViewGlobalLayout(mRootView);
                    }
                }
        );
    }


    @Override
    public void onCoreReady(ICore iCore) {

        SDK.initSDK(iCore);
        SDK.requestAllFeature();
    }

    @Override
    public void onCoreInitEnd(ICore iCore) {

        // 设置单页面集成的appid
        String appId = "test1";
        // 单页面集成时的加载页面路径，可以使本地文件路径，也可以是网络路径
        String url = "file:///android_asset/apps/H5Plugin/www/index.html";
        mWebview = SDK.createWebview(mActivity, url, appId,
                new IWebviewStateListener() {
                    @Override
                    public Object onCallBack(int i, Object o) {
                        switch (i) {
                            case IWebviewStateListener.ON_WEBVIEW_READY:
                                // 准备完毕之后添加webview到显示父View中，设置排版不显示状态
                                // 避免显示webbiew时，html内容排版错乱问题
                                ((IWebview) o).obtainFrameView().obtainMainView().setVisibility(View.INVISIBLE);
                                SDK.attach(mRootView, (IWebview) o);
                                break;
                            case IWebviewStateListener.ON_PAGE_STARTED:
                                break;
                            case IWebviewStateListener.ON_PROGRESS_CHANGED:
                                break;
                            case IWebviewStateListener.ON_PAGE_FINISHED:
                                // 页面加载完毕，设置显示webview
                                mWebview.obtainFrameView().obtainMainView().setVisibility(View.VISIBLE);
                                break;
                        }
                        return null;
                    }
                }
        );

        final WebView webViewInstance = mWebview.obtainWebview();
        // 监听返回键
        webViewInstance.setOnKeyListener(
                new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if (keyCode == KeyEvent.KEYCODE_BACK) {
                            if (webViewInstance.canGoBack()) {
                                webViewInstance.goBack();
                                return true;
                            }
                        }
                        return false;
                    }
                }
        );
    }

    @Override
    public boolean onCoreStop() {
        return false;
    }
}
