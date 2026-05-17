// 택시 호출 후보 조회용 Ride 저장소 동작을 검증하는 테스트
package com.sopt.kakaotaxi.domain.ride.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.sopt.kakaotaxi.domain.place.entity.Place;
import com.sopt.kakaotaxi.domain.place.repository.BaseRepositoryTest;
import com.sopt.kakaotaxi.domain.place.repository.PlaceRepository;
import com.sopt.kakaotaxi.domain.ride.entity.Ride;
import com.sopt.kakaotaxi.domain.taxi.entity.Taxi;
import com.sopt.kakaotaxi.domain.taxi.entity.TaxiType;
import com.sopt.kakaotaxi.domain.user.entity.User;
import com.sopt.kakaotaxi.domain.user.repository.UserRepository;

class RideRepositoryTest extends BaseRepositoryTest {

	@Autowired
	private RideRepository rideRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PlaceRepository placeRepository;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	@DisplayName("사용자와 목적지에 맞는 택시 호출 후보를 조회한다")
	void findAllByUserIdAndDestinationId_success() {
		User user = userRepository.save(User.create("김솝트"));
		User otherUser = userRepository.save(User.create("이솝트"));

		Place destination = placeRepository.save(
			Place.createEtc("회사", "서울 중구", 10, user)
		);
		Place otherDestination = placeRepository.save(
			Place.createEtc("카페", "서울 마포구", 5, user)
		);

		Taxi regularTaxi = entityManager.persistAndFlush(
			Taxi.create("12가1234", "김기사", TaxiType.REGULAR_TAXI, "쏘나타", "검정")
		);
		Taxi largeTaxi = entityManager.persistAndFlush(
			Taxi.create("34나5678", "박기사", TaxiType.LARGE_TAXI, "스타리아", "흰색")
		);
		Taxi premiumTaxi = entityManager.persistAndFlush(
			Taxi.create("56다9012", "최기사", TaxiType.PREMIUM_TAXI, "K9", "검정")
		);

		rideRepository.save(
			Ride.create(new BigDecimal("12000"), user, regularTaxi, destination)
		);
		rideRepository.save(
			Ride.create(new BigDecimal("18000"), user, largeTaxi, destination)
		);
		rideRepository.save(
			Ride.create(new BigDecimal("25000"), user, premiumTaxi, otherDestination)
		);
		rideRepository.save(
			Ride.create(new BigDecimal("9000"), otherUser, regularTaxi, destination)
		);

		List<Ride> result = rideRepository.findAllByUserIdAndDestinationId(
			user.getId(),
			destination.getId()
		);

		assertThat(result)
			.extracting(Ride::getFare)
			.containsExactlyInAnyOrder(
				new BigDecimal("12000"),
				new BigDecimal("18000")
			);
		assertThat(result)
			.extracting(ride -> ride.getTaxi().getType())
			.containsExactlyInAnyOrder(
				TaxiType.REGULAR_TAXI,
				TaxiType.LARGE_TAXI
			);
	}

	@Test
	@DisplayName("사용자와 택시에 맞는 택시 호출 정보를 조회한다")
	void findFirstByUserIdAndTaxiId_success() {
		User user = userRepository.save(User.create("김솝트"));
		User otherUser = userRepository.save(User.create("이솝트"));

		Place destination = placeRepository.save(
			Place.createEtc("회사", "서울 중구", 10, user)
		);

		Taxi taxi = entityManager.persistAndFlush(
			Taxi.create("12가1234", "김기사", TaxiType.REGULAR_TAXI, "쏘나타", "검정")
		);

		rideRepository.save(
			Ride.create(new BigDecimal("12000"), user, taxi, destination)
		);
		rideRepository.save(
			Ride.create(new BigDecimal("9000"), otherUser, taxi, destination)
		);

		Optional<Ride> result = rideRepository.findFirstByUserIdAndTaxiId(
			user.getId(),
			taxi.getId()
		);

		assertThat(result).isPresent();
		assertThat(result.get().getUser().getId()).isEqualTo(user.getId());
		assertThat(result.get().getTaxi().getPlateNumber()).isEqualTo("12가1234");
	}
}
