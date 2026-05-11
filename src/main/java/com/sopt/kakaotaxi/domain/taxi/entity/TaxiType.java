package com.sopt.kakaotaxi.domain.taxi.entity;

public enum TaxiType {
	SAFE_TAXI("안심택시"),
	REGULAR_TAXI("일반택시"),
	PREMIUM_TAXI("프리미엄택시"),
	LARGE_TAXI("대형택시");

	private final String displayName;

	TaxiType(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}
