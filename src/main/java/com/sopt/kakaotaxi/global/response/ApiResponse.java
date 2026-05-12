package com.sopt.kakaotaxi.global.response;

import org.springframework.http.HttpStatus;

import com.sopt.kakaotaxi.global.response.code.common.ErrorCode;
import com.sopt.kakaotaxi.global.response.code.common.SuccessCode;

public record ApiResponse<T>(
	int status,
	String code,
	String message,
	T data
) {

	private static final String SUCCESS_CODE = "SUCCESS";
	private static final String SUCCESS_MESSAGE = "요청에 성공했습니다.";

	public static <T> ApiResponse<T> success(T data) {
		return new ApiResponse<>(HttpStatus.OK.value(), SUCCESS_CODE, SUCCESS_MESSAGE, data);
	}

	public static ApiResponse<Void> success() {
		return new ApiResponse<>(HttpStatus.OK.value(), SUCCESS_CODE, SUCCESS_MESSAGE, null);
	}

	public static <T> ApiResponse<T> success(SuccessCode code, T data) {
		return new ApiResponse<>(code.getHttpStatus().value(), code.getCode(), code.getMessage(), data);
	}

	public static ApiResponse<Void> fail(ErrorCode errorCode) {
		return new ApiResponse<>(errorCode.getHttpStatus().value(), errorCode.getCode(), errorCode.getMessage(), null);
	}

	public static ApiResponse<Void> fail(int status, String code, String message) {
		return new ApiResponse<>(status, code, message, null);
	}
}
