package com.vicente.services.decl;

import com.vicente.dto.request.RQEmail;

/**
 * Created by vicente on 12/12/16.
 */
public interface EmailListener {

    void processEmail(RQEmail rqEmail);
}
