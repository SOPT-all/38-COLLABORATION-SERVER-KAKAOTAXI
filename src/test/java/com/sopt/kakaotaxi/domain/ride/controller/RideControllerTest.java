// 택시 호출 후보 조회 API의 웹 계층 동작을 검증하는 테스트
package com.sopt.kakaotaxi.domain.ride.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import com.sopt.kakaotaxi.domain.ride.dto.RideTaxiResponse;
import com.sopt.kakaotaxi.domain.ride.service.RideService;

@WebMvcTest(RideController.class)
class RideControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private RideService rideService;

	@Test
	@DisplayName("택시 호출 후보 조회에 성공한다")
	void getRideTaxis_success() throws Exception {
		List<RideTaxiResponse> response = List.of(
			new RideTaxiResponse(1L, "일반택시", "12000"),
			new RideTaxiResponse(2L, "대형택시", "18000")
		);

		given(rideService.getRideTaxis(1L, 10L))
			.willReturn(response);

		mockMvc.perform(
				post("/v1/rides")
					.header("X-User-Id", 1L)
					.contentType(MediaType.APPLICATION_JSON)
					.content("""
						{
						  "placeId": 10
						}
						""")
			)
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data[0].taxiId").value(1L))
			.andExpect(jsonPath("$.data[0].taxiType").value("일반택시"))
			.andExpect(jsonPath("$.data[0].estimatedFare").value("12000"))
			.andExpect(jsonPath("$.data[1].taxiId").value(2L))
			.andExpect(jsonPath("$.data[1].taxiType").value("대형택시"))
			.andExpect(jsonPath("$.data[1].estimatedFare").value("18000"));
	}
}
