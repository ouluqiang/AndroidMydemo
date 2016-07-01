package imbmob.test.utils;

import android.text.TextUtils;

/**
 * Created by Administrator on 2016/6/12 0012.
 */
public class StringUtils {

    public static boolean isNull(CharSequence text) {
        return TextUtils.isEmpty(text);
    }

    public static boolean isEquals(CharSequence text, CharSequence text1) {
        return !TextUtils.equals(text, text1);
    }

    public static boolean isLengthMin(CharSequence text, int length) {
        return text.length() >= length ? true : false;
    }

    public static boolean isLengthMax(CharSequence text, int length) {
        return text.length() < length ? true : false;
    }

    public static boolean isLengthMax(CharSequence text, int start, int end) {
        return text.length() < end && text.length() > start ? true : false;
    }

}
