package com.sopt.kakaotaxi.domain.place.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import com.sopt.kakaotaxi.domain.place.entity.Place;
import com.sopt.kakaotaxi.domain.user.entity.User;
import com.sopt.kakaotaxi.domain.user.repository.UserRepository;
import com.sopt.kakaotaxi.global.config.QueryDslConfig;

@Import(QueryDslConfig.class)
class PlaceRepositoryTest extends BaseRepositoryTest {

	@Autowired
	private PlaceRepository placeRepository;

	@Autowired
	private UserRepository userRepository;

	@Nested
	@DisplayName("즐겨찾기 장소 조회")
	class FindFavoritePlaces {

		@Test
		@DisplayName("HOME이 가장 먼저 조회되고 나머지는 방문 횟수 내림차순으로 정렬된다")
		void success() {
			// given
			User user = userRepository.save(
				User.create("김솝트")
			);

			placeRepository.save(
				Place.createHome(
					"우리집",
					"경기도 남양주시",
					user
				)
			);

			placeRepository.save(
				Place.createEtc(
					"자주가는 카페",
					"서울 중랑구",
					100,
					user
				)
			);

			placeRepository.save(
				Place.createEtc(
					"가끔가는 병원",
					"서울 광진구",
					5,
					user
				)
			);

			// when
			List<Place> result =
				placeRepository.findFavoritePlaces(user.getId());

			// then
			assertThat(result)
				.extracting(Place::getName)
				.containsExactly(
					"우리집",
					"자주가는 카페",
					"가끔가는 병원"
				);
		}

		@Test
		@DisplayName("다른 사용자의 장소는 조회되지 않는다")
		void exclude_other_user_places() {
			// given
			User user1 = userRepository.save(
				User.create("김솝트")
			);

			User user2 = userRepository.save(
				User.create("이솝트")
			);

			placeRepository.save(
				Place.createHome(
					"우리집",
					"경기도 남양주시",
					user1
				)
			);

			placeRepository.save(
				Place.createHome(
					"다른 사람 집",
					"경기도 안양시",
					user2
				)
			);

			// when
			List<Place> result =
				placeRepository.findFavoritePlaces(user1.getId());

			// then
			assertThat(result).hasSize(1);

			assertThat(result.get(0).getName())
				.isEqualTo("우리집");
		}

		@Test
		@DisplayName("방문 횟수가 같으면 최근 방문한 장소가 먼저 조회된다")
		void same_visit_count_ordered_by_last_visited_at() {
			// given
			User user = userRepository.save(
				User.create("김솝트")
			);

			placeRepository.save(
				Place.createEtc(
					"오래된 카페",
					"서울 중랑구",
					50,
					LocalDateTime.of(2023, 1, 1, 0, 0),
					user
				)
			);

			placeRepository.save(
				Place.createEtc(
					"최근 카페",
					"서울 광진구",
					50,
					LocalDateTime.of(2025, 5, 1, 0, 0),
					user
				)
			);

			// when
			List<Place> result =
				placeRepository.findFavoritePlaces(user.getId());

			// then
			assertThat(result)
				.extracting(Place::getName)
				.containsExactly(
					"최근 카페",
					"오래된 카페"
				);
		}
	}
}
