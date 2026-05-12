package com.sopt.kakaotaxi.global.response.code.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum CommonSuccessCode implements SuccessCode {
	OK(HttpStatus.OK, "COMMON_200_001", "요청에 성공했습니다."),

	CREATED(HttpStatus.CREATED, "COMMON_201_001", "리소스가 성공적으로 생성되었습니다.");

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;
}
