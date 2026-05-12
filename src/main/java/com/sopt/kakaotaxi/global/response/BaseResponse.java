package com.sopt.kakaotaxi.global.response;

import com.sopt.kakaotaxi.global.response.code.common.CommonSuccessCode;
import com.sopt.kakaotaxi.global.response.code.common.ErrorCode;
import com.sopt.kakaotaxi.global.response.code.common.SuccessCode;

public record BaseResponse<T>(
	int status,
	String code,
	String message,
	T data
) {

	public static <T> BaseResponse<T> success(T data) {
		SuccessCode defaultCode = CommonSuccessCode.OK;
		return new BaseResponse<>(
			defaultCode.getHttpStatus().value(),
			defaultCode.getCode(),
			defaultCode.getMessage(),
			data);
	}

	public static BaseResponse<Void> success() {
		return success(null);
	}

	public static <T> BaseResponse<T> success(SuccessCode code, T data) {
		return new BaseResponse<>(code.getHttpStatus().value(), code.getCode(), code.getMessage(), data);
	}

	public static BaseResponse<Void> fail(ErrorCode errorCode) {
		return fail(errorCode, errorCode.getMessage());
	}

	public static BaseResponse<Void> fail(ErrorCode errorCode, String message) {
		return new BaseResponse<>(
			errorCode.getHttpStatus().value(),
			errorCode.getCode(),
			message,
			null
		);
	}
}
