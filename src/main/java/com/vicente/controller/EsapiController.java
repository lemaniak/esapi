package com.vicente.controller;

import com.vicente.dto.request.RQSendEmail;
import com.vicente.exception.EsapiException;
import com.vicente.services.decl.EsapiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by vicente on 27/01/17.
 */
@RestController
@RequestMapping(value = "api/v1/esapi/")
public class EsapiController {

    @Autowired
    private EsapiService esapiService;

    @RequestMapping(value = "send", method = RequestMethod.POST)
    private ResponseEntity create(@Validated @RequestBody RQSendEmail rqSendEmail) throws EsapiException {
        esapiService.sendEmail(rqSendEmail);
        return new ResponseEntity(HttpStatus.OK);
    }
}
