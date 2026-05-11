package com.sopt.kakaotaxi.global.exception;

import java.util.Objects;
import org.springframework.http.HttpStatus;

import com.sopt.kakaotaxi.global.response.code.common.ErrorCode;

public class BusinessException extends RuntimeException {

	private final ErrorCode errorCode;

	public BusinessException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public HttpStatus getStatus() {
		return HttpStatus.valueOf(errorCode.getStatus());
	}
}
