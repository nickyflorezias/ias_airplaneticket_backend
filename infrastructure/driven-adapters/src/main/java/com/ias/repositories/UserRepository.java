package com.ias.repositories;

import com.ias.dbo.UserDBO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserDBO, Long> {
    Optional<UserDBO> findByUsername(String username);
    Optional<UserDBO> findByEmail(String email);
}
