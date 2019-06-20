package com.lbb.utils;

import java.util.*;

public class MyCollectionUtils {

    public static <T, K extends Collection<? extends T>> List<T> intersection(Collection<K> datas) {
        List<T> result = new ArrayList<>();
        Iterator<K> it = datas.iterator();
        if (it.hasNext()) {
            K copy = it.next();
            if (copy != null) {
                result = new ArrayList<>(copy);
                for (K k : datas) {
                    result.retainAll(k);
                }
            }
        }
        return result;
    }

    public static <T, K extends Collection<? extends T>> List<T> intersection(K... datas) {
        return intersection(Arrays.asList(datas));
    }

}
