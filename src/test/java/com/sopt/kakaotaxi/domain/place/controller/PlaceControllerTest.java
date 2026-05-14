package com.sopt.kakaotaxi.domain.place.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.sopt.kakaotaxi.domain.place.dto.PlaceResponseDto;
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
		PlaceResponseDto response = new PlaceResponseDto(
			List.of("우리집", "자주가는 카페")
		);

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
}
