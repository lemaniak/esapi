package com.vicente;

import com.vicente.mapping.converters.DateConverter;
import com.vicente.mapping.converters.StatusConverter;
import com.vicente.utils.*;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.dozer.CustomConverter;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import javax.jms.ConnectionFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
@SpringBootApplication//enables class scanning and autoconfiguration features
public class App 
{

    @Autowired
    private EsapiConfig esapiConfig;

    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);//fires spring boot
    }


    @Bean
    public ConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory(esapiConfig.getBrokerURL());
    }

    @Bean(name = "messageSource")
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageBundle = new ReloadableResourceBundleMessageSource();
        messageBundle.setBasename("classpath:messages/messages");
        messageBundle.setDefaultEncoding("UTF-8");
        return messageBundle;
    }


    @Bean(name = "dozerBeanMapper")
    public DozerBeanMapper dozerBeanMapper(){
        List<String> mappingFiles = new ArrayList<>();
        mappingFiles.add("dozer-bean-mappings.xml");
        List<CustomConverter> customConverters = new ArrayList<>();
        customConverters.add(dateConverter());
        customConverters.add(statusConverter());
        DozerBeanMapper mapper = new DozerBeanMapper();
        mapper.setCustomConverters(customConverters);
        mapper.setMappingFiles(mappingFiles);
        return mapper;
    }

    @Bean(name = "cryptoUtils")
    public CryptoUtils cryptoUtils(){
        return new CryptoUtils();
    }

    @Bean(name = "utils")
    public Utils utils(){
        return new Utils();
    }

    @Bean(name = "tokenUtils")
    public TokenUtils tokenUtils(){
        return new TokenUtils();
    }

    @Bean(name = "emailUtils")
    public EmailSender emailUtils(){
        return new EmailSender();
    }


    @Bean(name = "dateConverter")
    public DateConverter dateConverter(){
        return new DateConverter();
    }

    @Bean(name = "statusConverter")
    public StatusConverter statusConverter(){
        return new StatusConverter();
    }


}
