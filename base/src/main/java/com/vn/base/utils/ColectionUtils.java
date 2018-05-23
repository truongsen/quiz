package com.vn.base.utils;

import java.util.List;

/**
 * Created by SenTH on 5/23/2018.
 */

public class ColectionUtils {
    public static <T> boolean isListEmpty(List<T> list) {
        return list == null || list.isEmpty();
    }
}
