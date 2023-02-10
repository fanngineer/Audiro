package com.example.socket;

import lombok.Builder;

import java.io.Serializable;

@Builder
public class ClientDTO implements Serializable {

    private String message;
}
