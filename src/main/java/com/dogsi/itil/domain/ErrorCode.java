package com.dogsi.itil.domain;

import lombok.Getter;

@Getter
public enum ErrorCode {
    
    VALIDATION_ERROR(-1),
    NOT_FOUND(-404);

    private int codeId;

    ErrorCode(int code){
        codeId= code;
    }

}
