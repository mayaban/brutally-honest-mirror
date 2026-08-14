package com.brutallyhonestmirror.backend.repository;

import com.brutallyhonestmirror.backend.model.Reflection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReflectionRepository extends JpaRepository<Reflection, Long> {

    List<Reflection> findByEntry_User_IdOrderByCreatedAtDesc(Long userId);
}
