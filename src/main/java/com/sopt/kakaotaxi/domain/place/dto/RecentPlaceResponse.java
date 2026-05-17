package com.sopt.kakaotaxi.domain.place.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sopt.kakaotaxi.domain.place.entity.Place;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "최근 방문 장소 응답")
public record RecentPlaceResponse(
	@Schema(description = "장소 ID", example = "1")
	Long id,
	@Schema(description = "장소 이름", example = "한사랑병원")
	String name,
	@Schema(description = "최근 방문 일시", example = "2026.05.09T164600")
	@JsonFormat(pattern = "yyyy.MM.dd'T'HHmmss")
	LocalDateTime last_visited_at,
	@Schema(description = "장소 주소", example = "서울시 송파구")
	String address
) {
	public static RecentPlaceResponse from(Place place) {
		return new RecentPlaceResponse(
			place.getId(),
			place.getName(),
			place.getLastVisitedAt(),
			place.getAddress()
		);
	}
}
