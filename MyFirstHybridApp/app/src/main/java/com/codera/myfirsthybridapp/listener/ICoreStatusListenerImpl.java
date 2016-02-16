package com.codera.myfirsthybridapp.listener;

import io.dcloud.common.DHInterface.ICore;
import io.dcloud.feature.internal.sdk.SDK;

/**
 * 监听5+内核的运行事件
 * Created by Administrator on 2016/2/15.
 */
public class ICoreStatusListenerImpl implements ICore.ICoreStatusListener {

    /**
     * 5+内核开始初始化时触发，通常在这个方法初始化5+SDK
     * @param iCore 事件接口？
     */
    @Override
    public void onCoreReady(ICore iCore) {
        // 调用SDK初始化接口，初始化5+SDK
        SDK.initSDK(iCore);
        // 设置当前的应用可以使用5+ API
        SDK.requestAllFeature();
    }

    /**
     * 5+内核初始化完成时触发，在其完成之后才可以通过接口启动指定目录下的5+WebApp
     * @param iCore
     */
    @Override
    public void onCoreInitEnd(ICore iCore) {

    }

    /**
     * 5+内核关闭时触发
     * @return
     */
    @Override
    public boolean onCoreStop() {
        return false;
    }

}
