package com.sl.yu.GMS.repository;

import com.sl.yu.GMS.model.maintable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BoardRepository extends JpaRepository<maintable,Long> {



    /*검색기능-1*/
    Page<maintable> findByVISITORContaining(String searchKeyword, Pageable pageable);
    Page<maintable> findByuserNameContaining(String searchKeyword, Pageable pageable);
    Page<maintable> findByvisitAssignContaining(String searchKeyword, Pageable pageable);
    Page<maintable> findByTITLEContaining(String searchKeyword, Pageable pageable);

    Page<maintable> findByStateID(String stateID, Pageable page);
    Page<maintable> findByStateIDAndDeDate(String stateID, String date, Pageable page);

    Page<maintable> findByStateIDAndDeDateLessThan (String stateID, String now, Pageable page);

}
