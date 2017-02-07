package com.vicente.utils;

import com.fasterxml.uuid.Generators;
import com.vicente.dto.response.RSMessage;
import com.vicente.dto.response.RSMessageType;
import com.vicente.exception.EsapiException;
import com.vicente.exception.EsapiValidationException;
import com.vicente.model.Account;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import javax.lang.model.type.ErrorType;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by vicente on 10/11/16.
 */
@Component
public class Utils {

    private static  final SimpleDateFormat format = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
    private static  final SimpleDateFormat simpleFormat = new SimpleDateFormat("dd-M-yyyy");

    public  String dateToString(Date date){
        return format.format(date);
    }

    public  String dateToSimpleString(Date date){
        return simpleFormat.format(date);
    }

    public  Date stringToDate(String date) throws EsapiException {
        try {
            return format.parse(date);
        } catch (ParseException e) {
            throw new EsapiException("esapi.validation.internal.error",e.getCause());
        }
    }


    public  String generateUniqueId() {
        UUID uuid = Generators.randomBasedGenerator().generate();
        return uuid.toString().replaceAll("-", "").toUpperCase();
    }

    public static RSMessage errorResponse(String message){
        if(!message.contains("|")){
            RSMessage response= new RSMessage( RSMessageType.ERROR,message,999);
            return response;
        }else{
            int index = message.indexOf("|");
            String code= message.substring(index+1);
            message=message.substring(0,index-1);
            RSMessage response= new RSMessage(RSMessageType.ERROR,message,Integer.parseInt(code.trim()));
            return response;
        }
    }

    public String readFile(String file)throws IOException {
        String data = "";
        ClassPathResource cpr = new ClassPathResource(file);
        try {
            byte[] bdata = FileCopyUtils.copyToByteArray(cpr.getInputStream());
            data = new String(bdata, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("IOException"+ e.toString());
        }
        return data;
    }

}
