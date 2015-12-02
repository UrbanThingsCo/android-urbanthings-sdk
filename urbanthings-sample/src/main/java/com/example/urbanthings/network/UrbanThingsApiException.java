package com.example.urbanthings.network;

public class UrbanThingsApiException extends Exception {
    public UrbanThingsApiException() {
        super();
    }

    public UrbanThingsApiException(String detailMessage) {
        super(detailMessage);
    }

    public UrbanThingsApiException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public UrbanThingsApiException(Throwable throwable) {
        super(throwable);
    }
}
