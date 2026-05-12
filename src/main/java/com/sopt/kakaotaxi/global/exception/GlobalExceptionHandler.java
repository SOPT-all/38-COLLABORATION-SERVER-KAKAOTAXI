package com.sopt.kakaotaxi.global.exception;

import com.sopt.kakaotaxi.global.response.ApiResponse;
import com.sopt.kakaotaxi.global.response.code.common.ErrorCode;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final String DEFAULT_VALIDATION_MESSAGE = "잘못된 요청입니다.";

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ApiResponse<Void>> handleBusinessException(BusinessException exception) {
		ErrorCode errorCode = exception.getErrorCode();

		return ResponseEntity
			.status(errorCode.getHttpStatus())
			.body(ApiResponse.fail(errorCode));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<Void>> handleMethodArgumentNotValidException(
		MethodArgumentNotValidException exception
	) {
		ErrorCode errorCode = CommonErrorCode.INVALID_MAPPING_PARAMETER;
		String message = extractValidationMessage(exception);

		return ResponseEntity
			.status(errorCode.getHttpStatus())
			.body(ApiResponse.fail(errorCode, message));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Void>> handleException(Exception exception) {
		log.error("Unhandled exception occurred", exception);

		ErrorCode errorCode = CommonErrorCode.INTERNAL_SERVER_ERROR;

		return ResponseEntity
			.status(errorCode.getHttpStatus())
			.body(ApiResponse.fail(errorCode));
	}

	private String extractValidationMessage(MethodArgumentNotValidException exception) {
		return exception.getBindingResult()
			.getAllErrors()
			.stream()
			.findFirst()
			.map(DefaultMessageSourceResolvable::getDefaultMessage)
			.orElse(DEFAULT_VALIDATION_MESSAGE);
	}
}
