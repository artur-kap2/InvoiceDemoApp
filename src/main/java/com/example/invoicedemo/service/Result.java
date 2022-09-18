package com.example.invoicedemo.service;

import lombok.Data;

@Data
public class Result<T> {

    private boolean ok;
    private String message;

    private T data;


    public Result(T data) {
        this.ok = true;
        this.message = "Operation is done";
        this.data = data;
    }

    public Result(T data, String message) {
        this.ok = false;
        this.data = data;

        this.message = message;
    }

}






