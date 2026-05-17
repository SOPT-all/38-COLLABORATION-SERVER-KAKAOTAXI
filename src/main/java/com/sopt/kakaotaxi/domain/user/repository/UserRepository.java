package com.sopt.kakaotaxi.domain.user.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.sopt.kakaotaxi.domain.user.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
