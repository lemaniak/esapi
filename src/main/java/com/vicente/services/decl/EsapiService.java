package com.vicente.services.decl;

import com.vicente.dto.request.RQSendEmail;
import com.vicente.exception.EsapiException;

/**
 * Created by vicente on 12/12/16.
 */
public interface EsapiService {

     void sendEmail(RQSendEmail rqSendEmail) throws EsapiException;
}
