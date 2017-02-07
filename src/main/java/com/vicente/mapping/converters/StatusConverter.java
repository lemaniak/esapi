package com.vicente.mapping.converters;

import com.vicente.model.Status;
import org.dozer.CustomConverter;
import org.springframework.stereotype.Component;

/**
 * Created by vicente on 10/11/16.
 */
@Component
public class StatusConverter implements CustomConverter {

    @Override
    public Object convert(Object destination, Object source, Class destClass, Class sourceClass){
        if (source == null) {
            return null;
        }
        if(source instanceof String){
            return Status.fromValue((String) source);
        }else if(source instanceof Status){
            return ((Status) source).getValue();
        }
        return null;
    }
}
