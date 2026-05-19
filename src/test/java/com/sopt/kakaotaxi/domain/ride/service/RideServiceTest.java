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
import com.sopt.kakaotaxi.domain.ride.dto.RideTaxiDetailResponse;
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
		User otherUser = User.create("이솝트");
		Place destination = Place.createEtc("회사", "서울 중구", 10, user);
		Place otherDestination = Place.createEtc("카페", "서울 마포구", 5, user);
		Place otherUserDestination = Place.createEtc("집", "서울 강남구", 3, otherUser);

		Taxi regularTaxi = Taxi.create(
			"12가1234",
			"김기사",
			TaxiType.REGULAR_TAXI,
			"쏘나타",
			"검정"
		);
		ReflectionTestUtils.setField(regularTaxi, "id", 1L);

		Taxi largeTaxi = Taxi.create(
			"34나5678",
			"박기사",
			TaxiType.LARGE_TAXI,
			"스타리아",
			"흰색"
		);
		ReflectionTestUtils.setField(largeTaxi, "id", 2L);

		Taxi premiumTaxi = Taxi.create(
			"56다9012",
			"최기사",
			TaxiType.PREMIUM_TAXI,
			"K9",
			"검정"
		);
		ReflectionTestUtils.setField(premiumTaxi, "id", 3L);

		given(rideRepository.findAll())
			.willReturn(List.of(
				Ride.create(new BigDecimal("7800"), user, regularTaxi, destination),
				Ride.create(new BigDecimal("18000"), user, largeTaxi, otherDestination),
				Ride.create(new BigDecimal("25000"), otherUser, premiumTaxi, otherUserDestination)
			));

		List<RideTaxiResponse> result = rideService.getRideTaxis();

		assertThat(result)
			.extracting(
				RideTaxiResponse::taxiId,
				RideTaxiResponse::taxiType,
				RideTaxiResponse::estimatedFare
			)
			.containsExactly(
				tuple(1L, "일반택시", "7,800"),
				tuple(2L, "대형택시", "18,000"),
				tuple(3L, "프리미엄택시", "25,000")
			);
	}

	@Test
	@DisplayName("선택한 택시의 상세 정보를 응답으로 변환한다")
	void getRideTaxiDetail_success() {
		User user = User.create("김솝트");
		Place destination = Place.createEtc("회사", "서울 중구", 10, user);

		Taxi taxi = Taxi.create(
			"12가1234",
			"김기사",
			TaxiType.REGULAR_TAXI,
			"쏘나타",
			"검정"
		);
		ReflectionTestUtils.setField(taxi, "id", 1L);

		given(rideRepository.findFirstByUserIdAndTaxiId(1L, 1L))
			.willReturn(java.util.Optional.of(
				Ride.create(new BigDecimal("12000"), user, taxi, destination)
			));

		RideTaxiDetailResponse result = rideService.getRideTaxiDetail(1L, 1L);

		assertThat(result.taxiId()).isEqualTo(1L);
		assertThat(result.taxiType()).isEqualTo("일반택시");
		assertThat(result.modelName()).isEqualTo("쏘나타");
		assertThat(result.modelColor()).isEqualTo("검정");
		assertThat(result.plateNumber()).isEqualTo("12가1234");
		assertThat(result.driverName()).isEqualTo("김기사");
	}
}
