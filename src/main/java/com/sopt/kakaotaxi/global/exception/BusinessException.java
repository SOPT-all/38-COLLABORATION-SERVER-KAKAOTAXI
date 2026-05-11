package com.sopt.kakaotaxi.global.exception;

import java.util.Objects;
import org.springframework.http.HttpStatus;

// 현재는 HTTP 상태와 메시지만 있으나 추후 작업에서 ErrorCode 기반으로 변경 예정
public class BusinessException extends RuntimeException {

	private final HttpStatus status;

	public BusinessException(HttpStatus status, String message) {
		super(message);
		this.status = Objects.requireNonNull(status);
	}

	public HttpStatus getStatus() {
		return status;
	}
}
