package com.example.boxuegu.utils;

import android.util.Log;

public class LogUtils {
    static String className;//类名
    static String methodName;//方法名
    static int lineNumber;//行数
    static boolean showClass = false;//是否显示参数类型
    static boolean IS_DEBUG = true;

    private static String createLog(Object... log) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(methodName + "() : " + lineNumber + "行 : ");
        for (Object item : log) {
            if (item == null && showClass) buffer.append("null类型 --- null   ");
            else {
                if (showClass)
                    buffer.append(item.getClass().getSimpleName() + "类型 --- " + item + "   ");
                else buffer.append(item + "   ");
            }
        }
        return buffer.toString();
    }

    private static void getMethodNames(StackTraceElement[] elements) {
        className = elements[1].getFileName();
        methodName = elements[1].getMethodName();
        lineNumber = elements[1].getLineNumber();
    }


    public static void e(Object message) {
        if (IS_DEBUG) {
            // Throwable instance must be created before any methods
            getMethodNames(new Throwable().getStackTrace());
            Log.e(className, createLog(message));
        }
    }


    public static void i(Object... message) {
        if (IS_DEBUG) {
            getMethodNames(new Throwable().getStackTrace());
            Log.i(className, createLog(message));
        }
    }

    public static void d(Object message) {
        if (IS_DEBUG) {
            getMethodNames(new Throwable().getStackTrace());
            Log.d(className, createLog(message));
        }
    }

    public static void v(Object message) {
        if (IS_DEBUG) {
            getMethodNames(new Throwable().getStackTrace());
            Log.v(className, createLog(message));
        }
    }

    public static void w(Object message) {
        if (IS_DEBUG) {
            getMethodNames(new Throwable().getStackTrace());
            Log.w(className, createLog(message));
        }
    }

    public static void wtf(Object message) {
        if (IS_DEBUG) {
            getMethodNames(new Throwable().getStackTrace());
            Log.wtf(className, createLog(message));
        }
    }

    public static void json(String json) {
        if (IS_DEBUG) {
            getMethodNames(new Throwable().getStackTrace());
            Log.i(className, createLog(formatJson(json)));
        }
    }

    /**
     * @return 格式化后的json数据
     */
    private static String formatJson(String jsonStr) {
        if (null == jsonStr || "".equals(jsonStr))
            return "";
        StringBuffer sb = new StringBuffer();
        char last = '\0';
        char current = '\0';
        int indent = 0;
        boolean isInQuotationMarks = false;
        for (int i = 0; i < jsonStr.length(); i++) {
            last = current;
            current = jsonStr.charAt(i);
            switch (current) {
                case '"':
                    if (last != '\\') {
                        isInQuotationMarks = !isInQuotationMarks;
                    }
                    sb.append(current);
                    break;
                case '{':
                case '[':
                    sb.append(current);
                    if (!isInQuotationMarks) {
                        sb.append('\n');
                        indent++;
                        addIndentBlank(sb, indent);
                    }
                    break;
                case '}':
                case ']':
                    if (!isInQuotationMarks) {
                        sb.append('\n');
                        indent--;
                        addIndentBlank(sb, indent);
                    }
                    sb.append(current);
                    break;
                case ',':
                    sb.append(current);
                    if (last != '\\' && !isInQuotationMarks) {
                        sb.append('\n');
                        addIndentBlank(sb, indent);
                    }
                    break;
                default:
                    sb.append(current);
            }
        }

        return sb.toString();
    }


    /**
     * 在 StringBuilder指定位置添加 space
     *
     * @param sb     字符集
     * @param indent 添加位置
     */
    private static void addIndentBlank(StringBuffer sb, int indent) {
        for (int i = 0; i < indent; i++) {
            sb.append('\t');
        }
    }
}