package com.haixiajiemei.member.Api.Basic;

public class ApiException extends Exception {
    private final int status;
    private final String reason;
    private final String body;
    private final ErrorBody message;

    public ApiException(int status, String reason, String body,ErrorBody message) {
        this.status = status;
        this.reason = reason;
        this.body = body;
        this.message=message;
    }

    public int getStatus() {
        return this.status;
    }

    public String getReason() {
        return this.reason;
    }

    public ErrorBody getErrorBody() {
        return this.message;
    }

    public String getBody() {
        return this.body;
    }
}
