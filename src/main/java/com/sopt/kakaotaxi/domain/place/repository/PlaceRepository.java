package com.sopt.kakaotaxi.domain.place.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sopt.kakaotaxi.domain.place.entity.Place;

public interface PlaceRepository extends JpaRepository<Place, Long>, PlaceRepositoryCustom {
}
