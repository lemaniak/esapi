package com.vicente.services.decl;

import com.vicente.dto.request.RQEmail;
import com.vicente.exception.EsapiException;

/**
 * Created by vicente on 12/12/16.
 */
public interface EmailService {

     void sendEmail(RQEmail rqEmail) throws EsapiException;
}
