package com.dogsi.itil.handlers;
import java.time.Instant;

import com.dogsi.itil.domain.ErrorCode;

import lombok.Getter;

@Getter
public class ErrorResponse {
    
    private Instant timestamp;
    private ErrorCode code;
    private int codeId;
    private String description;

    public ErrorResponse(ErrorCode code, String description) {
        this.timestamp = Instant.now();
        this.code = code;
        this.codeId = code.getCodeId();
        this.description = description;
    }
}
