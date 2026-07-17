package com.brutallyhonestmirror.backend.repository;

import com.brutallyhonestmirror.backend.model.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryRepository extends JpaRepository<Entry, Long> {

}
