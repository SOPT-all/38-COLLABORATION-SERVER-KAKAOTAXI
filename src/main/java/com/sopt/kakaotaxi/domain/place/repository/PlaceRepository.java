package com.sopt.kakaotaxi.domain.place.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sopt.kakaotaxi.domain.place.entity.Place;

public interface PlaceRepository extends JpaRepository<Place, Long>, PlaceRepositoryCustom {
	List<Place> findTop4ByUserIdOrderByLastVisitedAtDesc(Long userId);
}
