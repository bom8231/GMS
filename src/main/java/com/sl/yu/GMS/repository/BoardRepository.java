package com.sl.yu.GMS.repository;

import com.sl.yu.GMS.model.maintable;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public interface BoardRepository extends JpaRepository<maintable,Long> {


    Page<maintable> findByStateID(String stateID, Pageable page);
    Page<maintable> findByStateIDAndDeDate(String stateID, String date, Pageable page);

}
