package io.pujaDeshmukh.resumeportal;

import io.pujaDeshmukh.resumeportal.models.USERINFO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<USERINFO,Integer> {
    Optional<USERINFO> findByUsername(String username);
}
