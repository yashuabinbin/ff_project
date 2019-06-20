package com.lbb.utils;

import org.springframework.cglib.beans.BeanCopier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeanUtils {

    private static final Map<String, BeanCopier> CONVERT_MAP = new HashMap<>();

    public static <SourceClass, TargetClass> TargetClass convert(SourceClass sourceClass, Class targetClass) {
        try {
            String convertName = sourceClass.getClass().getName() + targetClass.getName();

            BeanCopier beanCopier = CONVERT_MAP.get(convertName);
            if (beanCopier == null) {
                beanCopier = BeanCopier.create(sourceClass.getClass(), targetClass, false);
                CONVERT_MAP.put(convertName, beanCopier);
            }

//            MyConverter converter = new MyConverter();
            TargetClass targetInstance = (TargetClass) targetClass.newInstance();
            beanCopier.copy(sourceClass, targetInstance, null);

            return targetInstance;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }


    public static <SourceClass, TargetClass> List<TargetClass> convertList(List<SourceClass> sourceClassList, Class targetClass) {
        List<TargetClass> tcList = new ArrayList<>();
        for (SourceClass sc : sourceClassList) {
            tcList.add(convert(sc, targetClass));
        }
        return tcList;
    }

}
