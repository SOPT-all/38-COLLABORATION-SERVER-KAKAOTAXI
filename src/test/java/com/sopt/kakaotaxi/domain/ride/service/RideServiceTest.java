// 택시 호출 후보 조회 서비스의 응답 변환을 검증하는 테스트
package com.sopt.kakaotaxi.domain.ride.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;
import static org.mockito.BDDMockito.given;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import com.sopt.kakaotaxi.domain.place.entity.Place;
import com.sopt.kakaotaxi.domain.ride.dto.RideTaxiResponse;
import com.sopt.kakaotaxi.domain.ride.entity.Ride;
import com.sopt.kakaotaxi.domain.ride.repository.RideRepository;
import com.sopt.kakaotaxi.domain.taxi.entity.Taxi;
import com.sopt.kakaotaxi.domain.taxi.entity.TaxiType;
import com.sopt.kakaotaxi.domain.user.entity.User;

@ExtendWith(MockitoExtension.class)
class RideServiceTest {

	@InjectMocks
	private RideService rideService;

	@Mock
	private RideRepository rideRepository;

	@Test
	@DisplayName("Ride의 택시 정보와 예상 요금을 응답으로 변환한다")
	void getRideTaxis_success() {
		User user = User.create("김솝트");
		Place destination = Place.createEtc("회사", "서울 중구", 10, user);

		Taxi regularTaxi = Taxi.create("12가1234", "김기사", TaxiType.REGULAR_TAXI);
		ReflectionTestUtils.setField(regularTaxi, "id", 1L);

		Taxi largeTaxi = Taxi.create("34나5678", "박기사", TaxiType.LARGE_TAXI);
		ReflectionTestUtils.setField(largeTaxi, "id", 2L);

		given(rideRepository.findAllByUserIdAndDestinationId(1L, 10L))
			.willReturn(List.of(
				Ride.create(new BigDecimal("12000"), user, regularTaxi, destination),
				Ride.create(new BigDecimal("18000"), user, largeTaxi, destination)
			));

		List<RideTaxiResponse> result = rideService.getRideTaxis(1L, 10L);

		assertThat(result)
			.extracting(
				RideTaxiResponse::taxiId,
				RideTaxiResponse::taxiType,
				RideTaxiResponse::estimatedFare
			)
			.containsExactly(
				tuple(1L, "일반택시", "12000"),
				tuple(2L, "대형택시", "18000")
			);
	}
}
