package com.lamandys.xposedtest;

import android.util.Log;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by lamandys on 2019-09-15 17:00.
 */
public class HookMain implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if (!lpparam.packageName.equals("com.yuanwofei.cardemulator.pro")) {
            return;
        }
        Class<?> clazz = XposedHelpers.findClass("com.yuanwofei.cardemulator.c.h.a", lpparam.classLoader);
        if (clazz == null) {
            return;
        }
        Log.d("tag","clazz => " + clazz);
        XposedHelpers.findAndHookMethod("com.yuanwofei.cardemulator.c.e", lpparam.classLoader,
                "a", String.class, clazz, new XC_MethodHook() {

                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        Log.d("tag","before hook");
                        Log.d("tag","params[0] is  " + param.args[0]);
                        param.args[0] = "";
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        Log.d("tag","after hook");
                        Log.d("tag","params[0] is  " + param.args[0]);
                        param.args[0] = "";
                    }
                });

        XposedHelpers.findAndHookConstructor("com.yuanwofei.cardemulator.c.g", lpparam.classLoader,
                String.class, clazz, new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        Log.d("tag","Constructor before hook");
                        Log.d("tag","params[0] is  " + param.args[0]);
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        Log.d("tag","Constructor after hook");
                        Log.d("tag","params[0] is  " + param.args[0]);
                    }
                });
    }
}
