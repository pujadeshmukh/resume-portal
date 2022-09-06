package io.pujaDeshmukh.resumeportal;

import io.pujaDeshmukh.resumeportal.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUserName(String userName);
}
