package com.oleszl.springshop.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ExceptionResponse {

    private Date timestamp;
    private String message;
}

