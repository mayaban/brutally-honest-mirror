package com.brutallyhonestmirror.backend.repository;

import com.brutallyhonestmirror.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository <User, Long> {
}
