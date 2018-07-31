package com.vn.core.utils;

import java.util.List;

/**
 * Created by SenTH on 5/23/2018.
 */

public class CollectionUtils {
    public static <T> boolean isListEmpty(List<T> list) {
        return list == null || list.isEmpty();
    }
}
