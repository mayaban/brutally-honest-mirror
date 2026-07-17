package com.brutallyhonestmirror.backend.repository;

import com.brutallyhonestmirror.backend.model.Reflection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReflectionRepository extends JpaRepository<Reflection, Long> {
}
