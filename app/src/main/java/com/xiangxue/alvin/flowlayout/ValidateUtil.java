package com.xiangxue.alvin.flowlayout;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 用于进行通用的校验判断
 *
 * @author zhuliang 2019/03/a2
 */
public class ValidateUtil {

    /**
     * 校验是否为空字符串
     *
     * @param validateStr 待校验的字符串
     * @return boolean
     */
    public static boolean isEmptyString(String validateStr) {
        return null == validateStr || StringConstans.STR_EMPTY.equals(validateStr)
                || StringConstans.STR_NULL_EN.equals(validateStr) || validateStr.length() == 0;
    }

    /**
     * 校验是否不为空字符串
     *
     * @param validateStr 待校验的字符串
     * @return boolean
     */
    public static boolean isNotEmptyString(String validateStr) {
        return !isEmptyString(validateStr);
    }

    /**
     * 校验是否为空json对象
     *
     * @param jo 待校验的json对象
     */
    public static boolean isEmptyOrgJsonObj(JSONObject jo) {
        return null == jo || jo.equals("[]") || StringConstans.STR_EMPTY.equals(jo);
    }

    /**
     * 校验是否不为空json对象
     *
     * @param jo 待校验的json对象
     */
    public static boolean isNotEmptyOrgJsonObj(JSONObject jo) {
        return !isEmptyOrgJsonObj(jo);
    }

    /**
     * 校验: 为空对象或者空字符串
     *
     * @param validateObj 待校验的对象
     * @return boolean
     */
    public static boolean isEmptyObjectOrString(Object validateObj) {
        return null == validateObj || StringConstans.STR_EMPTY.equals(validateObj.toString().trim());
    }

    /**
     * 校验: 不为为空对象或者空字符串
     *
     * @param validateObj 待校验的对象
     * @return boolean
     */
    public static boolean isNotEmptyObjectOrString(Object validateObj) {
        return !isEmptyObjectOrString(validateObj);
    }

    /**
     * 校验是否为空collection
     *
     * @param validateColl 待校验的collection
     * @return boolean
     */
    public static boolean isEmptyCollection(Collection<?> validateColl) {
        return null == validateColl || validateColl.size() == 0;
    }

    /**
     * 校验是否不为空collection
     *
     * @param validateColl 待校验的collection
     * @return boolean
     */
    public static boolean isNotEmptyCollection(Collection<?> validateColl) {
        return !isEmptyCollection(validateColl);
    }

    /**
     * 校验：为空JSONArray
     *
     * @param validateJSONArray 待校验的JSONArray
     * @return boolean
     */
    public static boolean isEmptyJSONArray(JSONArray validateJSONArray) {
        return null == validateJSONArray || validateJSONArray.length() == 0;
    }

    /**
     * 校验：不为空JSONArray
     *
     * @param validateJSONArray 待校验的collection
     * @return boolean
     */
    public static boolean isNotEmptyJSONArray(JSONArray validateJSONArray) {
        return !isEmptyJSONArray(validateJSONArray);
    }

    /**
     * 校验：为空Map
     *
     * @param validateMap 待校验的Map
     * @return boolean
     */
    public static boolean isEmptyMap(Map<?, ?> validateMap) {
        return null == validateMap || 0 == validateMap.entrySet().size() || validateMap.size() == 0;
    }

    /**
     * 校验: 不为空Map
     *
     * @param validateMap 待校验的Map
     * @return boolean
     */
    public static boolean isNotEmptyMap(Map<?, ?> validateMap) {
        return !isEmptyMap(validateMap);
    }

    /**
     * 判断任何对象的数组是否为空数组
     *
     * @param arrayObj 　数组对象
     * @return　boolean
     */
    public static boolean isEmptyArray(Object arrayObj) {
        return null == arrayObj || Array.getLength(arrayObj) == 0;
    }

    /**
     * 判断任何对象的数组是否不为空数组
     *
     * @param arrayObj 　数组对象
     * @return　boolean
     */
    public static boolean isNotEmptyArray(Object arrayObj) {
        return !isEmptyArray(arrayObj);
    }

    /**
     * 判断当前应用程序是否退到后台运行
     */
    public static boolean isApplicationBroughtToBackground(final Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;
    }


    /**
     * 判断网络连接是否可用
     *
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        boolean result = false;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            // 如果仅仅是用来判断网络连接
            // 则可以使用 cm.getActiveNetworkInfo().isAvailable();
            @SuppressLint("MissingPermission") NetworkInfo[] info = cm.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        result = true;
                        break;
                    }
                }
            }
        }

        return result;
    }


    private static long lastClickTime;

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        // 两次点击间隔小于500毫秒
        if (time - lastClickTime < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }



}
