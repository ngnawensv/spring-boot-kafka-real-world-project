package com.belrose.repository;

import com.belrose.model.WikimediaData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WikimediaDataRepository  extends JpaRepository<WikimediaData,Long> {
}
