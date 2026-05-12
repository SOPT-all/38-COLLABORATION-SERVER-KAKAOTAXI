package com.sopt.kakaotaxi.global.response.code.common;

import org.springframework.http.HttpStatus;

public interface ResponseCode {
	HttpStatus getHttpStatus();
	String getCode();
	String getMessage();
}
