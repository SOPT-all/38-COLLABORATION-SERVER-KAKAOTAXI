package com.sopt.kakaotaxi.domain.place.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sopt.kakaotaxi.domain.place.entity.Place;
import com.sopt.kakaotaxi.domain.place.entity.PlaceType;

public interface PlaceRepository extends JpaRepository<Place, Long>, PlaceRepositoryCustom {
	List<Place> findByUserIdOrderByLastVisitedAtDesc(Long userId);
}
