package com.sopt.kakaotaxi.domain.place.repository;

import static com.sopt.kakaotaxi.domain.place.entity.QPlace.*;
import java.util.List;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sopt.kakaotaxi.domain.place.entity.Place;
import com.sopt.kakaotaxi.domain.place.entity.PlaceType;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PlaceRepositoryImpl implements PlaceRepositoryCustom { // Custom만 구현!

	private final JPAQueryFactory queryFactory;

	@Override
	public List<Place> findFavoritePlaces(Long userId) {
		return queryFactory
			.selectFrom(place)
			.where(place.user.id.eq(userId))
			.orderBy(
				homeFirst(),
				place.visitCount.desc()
			)
			.fetch();
	}

	private OrderSpecifier homeFirst() {
		return new CaseBuilder()
			.when(place.type.eq(PlaceType.HOME)).then(0)
			.otherwise(1).asc();
	}
}
