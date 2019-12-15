package com.service.consumption.fuel.responses;

import lombok.Getter;

@Getter
public class MessageOnlyResponse {

    public final String message;

    public MessageOnlyResponse(String message) {
        super();
        this.message = message;
    }
}
