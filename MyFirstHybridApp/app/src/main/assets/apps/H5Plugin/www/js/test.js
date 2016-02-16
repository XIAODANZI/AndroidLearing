document.addEventListener( "plusready",  function()
{
    // 1.声明的JS“扩展插件别名”，别名在assets\data\properties.xml中声明
    var _BARCODE = 'plugintest',
    B = window.plus.bridge;
    var plugintest = 
    {
        // 2.1 声明异步返回方法
        PluginTestFunction : function (Argus1, Argus2, Argus3, Argus4, successCallback, errorCallback )
        {
            var success = typeof successCallback !== 'function' ? null : function(args)
            {
                successCallback(args);
            },
            fail = typeof errorCallback !== 'function' ? null : function(code)
            {
                errorCallback(code);
            };
            callbackID = B.callbackId(success, fail);

            // 2.2 通知Native层plugintest扩展插件运行“PluginTestFunction”方法
            return B.exec(_BARCODE, "PluginTestFunction", [callbackID, Argus1, Argus2, Argus3, Argus4]);
        },
        PluginTestFunctionArrayArgu : function (Argus, successCallback, errorCallback )
        {
            var success = typeof successCallback !== 'function' ? null : function(args)
            {
                successCallback(args);
            },
            fail = typeof errorCallback !== 'function' ? null : function(code)
            {
            errorCallback(code);
            };
            callbackID = B.callbackId(success, fail);
            // 异步返回JS扩展方法实现
            // void plus.bridge.exec( String service, String action, Array<String> args );
            // service：插件类的别名
            // action：调用Native层插件方法的名称
            // args：参数列表
            return B.exec(_BARCODE, "PluginTestFunctionArrayArgu", [callbackID, Argus]);
        },
        // 2.3 声明同步返回方法
        PluginTestFunctionSync : function (Argus1, Argus2, Argus3, Argus4) 
        {
            // 2.4 通知Native层plugintest扩展插件运行“PluginTestFunctionSync”方法并同步返回结果
            return B.execSync(_BARCODE, "PluginTestFunctionSync", [Argus1, Argus2, Argus3, Argus4]);
        },
        PluginTestFunctionSyncArrayArgu : function (Argus) 
        {
            // 同步扩展方法，可同步获取native插件返回的运行结果
            // service：插件类的别名
            // action：调用Native层插件方法的名称
            // args：参数列表
            // plus.bridge.execSync( String service, String action, Array<String> args );
            return B.execSync(_BARCODE, "PluginTestFunctionSyncArrayArgu", [Argus]);
        }
    };
    window.plus.plugintest = plugintest;
}, true );