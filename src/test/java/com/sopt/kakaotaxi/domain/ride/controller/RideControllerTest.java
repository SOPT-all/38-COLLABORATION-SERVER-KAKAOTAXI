// 택시 호출 후보 조회 API의 웹 계층 동작을 검증하는 테스트
package com.sopt.kakaotaxi.domain.ride.controller;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.sopt.kakaotaxi.domain.ride.dto.RideTaxiDetailResponse;
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

		given(rideService.getRideTaxis())
			.willReturn(response);

		mockMvc.perform(
				post("/v1/rides")
					.header("X-User-Id", 1L)
			)
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data[0].taxiId").value(1L))
			.andExpect(jsonPath("$.data[0].taxiType").value("일반택시"))
			.andExpect(jsonPath("$.data[0].estimatedFare").value("12000"))
			.andExpect(jsonPath("$.data[1].taxiId").value(2L))
			.andExpect(jsonPath("$.data[1].taxiType").value("대형택시"))
			.andExpect(jsonPath("$.data[1].estimatedFare").value("18000"));

		then(rideService).should().getRideTaxis();
	}

	@Test
	@DisplayName("택시 상세 조회에 성공한다")
	void getRideTaxiDetail_success() throws Exception {
		RideTaxiDetailResponse response = new RideTaxiDetailResponse(
			1L,
			"일반택시",
			"쏘나타",
			"검정",
			"12가1234",
			"김기사"
		);

		given(rideService.getRideTaxiDetail(1L, 1L))
			.willReturn(response);

		mockMvc.perform(
				get("/v1/rides/{taxi_id}", 1L)
					.header("X-User-Id", 1L)
			)
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.data.taxi_id").value(1L))
			.andExpect(jsonPath("$.data.taxiType").value("일반택시"))
			.andExpect(jsonPath("$.data.modelName").value("쏘나타"))
			.andExpect(jsonPath("$.data.modelColor").value("검정"))
			.andExpect(jsonPath("$.data.plateNumber").value("12가1234"))
			.andExpect(jsonPath("$.data.driverName").value("김기사"));
	}
}
