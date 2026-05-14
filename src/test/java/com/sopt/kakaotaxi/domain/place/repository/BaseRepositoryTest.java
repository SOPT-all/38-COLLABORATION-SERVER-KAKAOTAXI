package com.sopt.kakaotaxi.domain.place.repository;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import com.sopt.kakaotaxi.global.config.QueryDslConfig;

@DataJpaTest
@Import(QueryDslConfig.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public abstract class BaseRepositoryTest {
}
