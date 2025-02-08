package com.example.template.common.exception.handler;

import com.example.template.common.exception.GeneralException;
import com.example.template.common.response.BaseErrorCode;

public class GeneralHandler extends GeneralException {
    public GeneralHandler(BaseErrorCode code) {
        super(code);
    }
}
