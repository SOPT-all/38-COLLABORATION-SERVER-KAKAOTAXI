package com.sopt.kakaotaxi.global.exception;

import com.sopt.kakaotaxi.global.response.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final String DEFAULT_VALIDATION_MESSAGE = "잘못된 요청입니다.";
	private static final String INTERNAL_SERVER_ERROR_MESSAGE = "서버 내부 오류가 발생했습니다.";

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<ApiResponse<Void>> handleBusinessException(BusinessException exception) {
		HttpStatus status = exception.getStatus();

		return ResponseEntity
			.status(status)
			.body(ApiResponse.fail(status, exception.getMessage()));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<Void>> handleMethodArgumentNotValidException(
		MethodArgumentNotValidException exception
	) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String message = extractValidationMessage(exception);

		return ResponseEntity
			.status(status)
			.body(ApiResponse.fail(status, message));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Void>> handleException(Exception exception) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		log.error("Unhandled exception occurred", exception);

		return ResponseEntity
			.status(status)
			.body(ApiResponse.fail(status, INTERNAL_SERVER_ERROR_MESSAGE));
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
