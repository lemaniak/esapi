package com.vicente.mapping.converters;

import com.vicente.utils.Utils;
import org.dozer.CustomConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by vicente on 10/11/16.
 */
@Component
public class DateConverter implements CustomConverter {
    @Autowired
    private Utils utils;

    @Override
    public Object convert(Object destination, Object source, Class destClass, Class sourceClass) {
        if (source == null) {
            return null;
        }

        Date date = (Date) source;
        return utils.dateToSimpleString(date);

    }

}
