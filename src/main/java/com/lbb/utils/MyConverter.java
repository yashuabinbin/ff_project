package com.lbb.utils;


import org.springframework.cglib.core.Converter;

public class MyConverter implements Converter {

    @Override
    public Object convert(Object sourceObj, Class targetObj, Object context) {
        if (sourceObj instanceof String) {
            switch (targetObj.getName()) {
                case "java.lang.Integer":
                    return Integer.parseInt((String) sourceObj);

                case "java.lang.Boolean":
                    return Boolean.parseBoolean((String) sourceObj);

                case "java.lang.Long":
                    return Long.parseLong((String) sourceObj);

                case "java.lang.Short":
                    return Short.parseShort((String) sourceObj);

                case "java.lang.Float":
                    return Float.parseFloat((String) sourceObj);

                case "java.lang.Double":
                    return Double.parseDouble((String) sourceObj);
                default:
                    if (targetObj.isEnum()) {
                        return Enum.valueOf(targetObj, sourceObj.toString());
                    }
            }
        }
        return context;
    }
}
