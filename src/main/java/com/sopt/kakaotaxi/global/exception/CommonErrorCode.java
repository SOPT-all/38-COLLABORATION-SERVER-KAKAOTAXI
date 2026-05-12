package com.sopt.kakaotaxi.global.exception;

import org.springframework.http.HttpStatus;

import com.sopt.kakaotaxi.global.response.code.common.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommonErrorCode implements ErrorCode {
	INVALID_MAPPING_PARAMETER(HttpStatus.BAD_REQUEST, "COMMON_400_001", "매핑할 수 없는 값입니다."),
	INVALID_REQUEST_VARIABLE(HttpStatus.BAD_REQUEST, "COMMON_400_002", "엔드포인트에 잘못된 요청값이 있습니다."),

	UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "COMMON_401_001", "인증에 실패했습니다."),

	FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON_403_001", "접근 권한이 없습니다."),

	RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "COMMON_404_001", "존재하지 않는 리소스입니다."),

	INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON_500_001", "서버 내부 오류가 발생했습니다.");

	private final HttpStatus httpStatus;
	private final String code;
	private final String message;
}
