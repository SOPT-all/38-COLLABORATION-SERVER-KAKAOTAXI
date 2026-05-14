package com.sopt.kakaotaxi.domain.place.repository;

import java.util.List;
import com.sopt.kakaotaxi.domain.place.entity.Place;

public interface PlaceRepositoryCustom {
	List<Place> findFavoritePlaces(Long userId);
}
