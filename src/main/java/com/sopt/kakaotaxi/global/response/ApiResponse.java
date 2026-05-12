package com.sopt.kakaotaxi.global.response;

import org.springframework.http.HttpStatus;

import com.sopt.kakaotaxi.global.response.code.common.CommonSuccessCode;
import com.sopt.kakaotaxi.global.response.code.common.ErrorCode;
import com.sopt.kakaotaxi.global.response.code.common.SuccessCode;

public record ApiResponse<T>(
	int status,
	String code,
	String message,
	T data
) {

	public static <T> ApiResponse<T> success(T data) {
		SuccessCode defaultCode = CommonSuccessCode.OK;
		return new ApiResponse<>(
			defaultCode.getHttpStatus().value(),
			defaultCode.getCode(),
			defaultCode.getMessage(),
			data);
	}

	public static ApiResponse<Void> success() {
		return success(null);
	}

	public static <T> ApiResponse<T> success(SuccessCode code, T data) {
		return new ApiResponse<>(code.getHttpStatus().value(), code.getCode(), code.getMessage(), data);
	}

	public static ApiResponse<Void> fail(ErrorCode errorCode) {
		return fail(errorCode, errorCode.getMessage());
	}

	public static ApiResponse<Void> fail(ErrorCode errorCode, String message) {
		return new ApiResponse<>(
			errorCode.getHttpStatus().value(),
			errorCode.getCode(),
			message,
			null
		);
	}
}
