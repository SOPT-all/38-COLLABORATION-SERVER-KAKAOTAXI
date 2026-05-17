package com.sopt.kakaotaxi.domain.place.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.sopt.kakaotaxi.domain.place.dto.RecentPlaceResponse;
import com.sopt.kakaotaxi.domain.place.service.PlaceService;

@WebMvcTest(PlaceController.class)
class PlaceControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private PlaceService placeService;

	@Test
	@DisplayName("즐겨찾기 장소 조회에 성공한다")
	void getFavoritePlaces_success() throws Exception {
		List<String> response = List.of("우리집", "자주가는 카페");

		given(placeService.getFavoritePlaces(1L))
			.willReturn(response);

		mockMvc.perform(
				get("/v1/places/favorite")
					.header("X-User-Id", 1L)
			)
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data[0]").value("우리집"))
			.andExpect(jsonPath("$.data[1]").value("자주가는 카페"));
	}

	@Test
	@DisplayName("최근 방문 장소 조회에 성공한다")
	void getRecentPlaces_success() throws Exception {
		List<RecentPlaceResponse> response = List.of(
			new RecentPlaceResponse(1L, "한사랑병원", LocalDateTime.of(2026, 5, 9, 16, 46, 0), "서울시 송파구"),
			new RecentPlaceResponse(2L, "강남구 보건소", LocalDateTime.of(2026, 5, 9, 14, 0, 0), "서울시 강남구")
		);

		given(placeService.getRecentPlaces(1L))
			.willReturn(response);

		mockMvc.perform(
				get("/v1/places/recent")
					.header("X-User-Id", 1L)
			)
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data[0].id").value(1L))
			.andExpect(jsonPath("$.data[0].name").value("한사랑병원"))
			.andExpect(jsonPath("$.data[0].address").value("서울시 송파구"))
			.andExpect(jsonPath("$.data[1].id").value(2L))
			.andExpect(jsonPath("$.data[1].name").value("강남구 보건소"));
	}
}
