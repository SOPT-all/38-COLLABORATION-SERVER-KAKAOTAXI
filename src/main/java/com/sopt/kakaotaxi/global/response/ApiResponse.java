package com.sopt.kakaotaxi.global.response;

import org.springframework.http.HttpStatus;

public record ApiResponse<T>(
	int status,
	String message,
	T data
) {

	private static final String SUCCESS_MESSAGE = "요청에 성공했습니다.";

	public static <T> ApiResponse<T> success(T data) {
		return new ApiResponse<>(HttpStatus.OK.value(), SUCCESS_MESSAGE, data);
	}

	public static ApiResponse<Void> success() {
		return new ApiResponse<>(HttpStatus.OK.value(), SUCCESS_MESSAGE, null);
	}

	public static ApiResponse<Void> fail(HttpStatus status, String message) {
		return new ApiResponse<>(status.value(), message, null);
	}
}
