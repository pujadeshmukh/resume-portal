package io.pujaDeshmukh.resumeportal;

import io.pujaDeshmukh.resumeportal.models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserInfo,Integer> {
    Optional<UserInfo> findByUsername(String username);
}
