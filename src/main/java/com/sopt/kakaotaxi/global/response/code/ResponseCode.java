package com.sopt.kakaotaxi.global.response.code;

import org.springframework.http.HttpStatus;

public interface ResponseCode {
	HttpStatus getHttpStatus();
	String getMessage();
}
