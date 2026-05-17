// 택시 호출 후보 조회에 필요한 Ride 데이터를 조회하는 JPA 저장소
package com.sopt.kakaotaxi.domain.ride.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sopt.kakaotaxi.domain.ride.entity.Ride;

public interface RideRepository extends JpaRepository<Ride, Long> {

	@EntityGraph(attributePaths = "taxi")
	List<Ride> findAllByUserIdAndDestinationId(Long userId, Long destinationId);

	@EntityGraph(attributePaths = "taxi")
	Optional<Ride> findFirstByUserIdAndTaxiId(Long userId, Long taxiId);
}
