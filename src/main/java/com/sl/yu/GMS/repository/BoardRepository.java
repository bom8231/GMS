package com.sl.yu.GMS.repository;

import com.sl.yu.GMS.model.maintable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;


public interface BoardRepository extends JpaRepository<maintable,Long> {


    /*전체 리스트 검색기능-1*/
    Page<maintable> findByVISITORContainingAndDeDateLessThanEqualAndDeDateGreaterThanEqual(String searchKeyword, String endDate, String startDate, Pageable pageable);
    Page<maintable> findByuserNameContainingAndDeDateLessThanEqualAndDeDateGreaterThanEqual(String searchKeyword, String endDate, String startDate, Pageable pageable);
    Page<maintable> findByvisitAssignContainingAndDeDateLessThanEqualAndDeDateGreaterThanEqual(String searchKeyword, String endDate, String startDate, Pageable pageable);
    Page<maintable> findByTITLEContainingAndDeDateLessThanEqualAndDeDateGreaterThanEqual(String searchKeyword, String endDate, String startDate, Pageable pageable);
    Page<maintable> findByDeDateLessThanEqualAndDeDateGreaterThanEqual(String endDate, String startDate, Pageable pageable);
    Page<maintable> findByStateID(String stateID, Pageable page);
    Page<maintable> findByStateIDAndDeDate(String stateID, String date, Pageable page);

    //check in
    Page<maintable> findByVISITORContainingAndStateIDAndDeDate(String searchKeyword, String stateID, String date, Pageable page);
    Page<maintable> findByuserNameContainingAndStateIDAndDeDate(String searchKeyword, String stateID, String date, Pageable page);
    Page<maintable> findByvisitAssignContainingAndStateIDAndDeDate(String searchKeyword, String stateID, String date, Pageable page);
    Page<maintable> findByTITLEContainingAndStateIDAndDeDate(String searchKeyword, String stateID, String date, Pageable page);
    //check out
    Page<maintable> findByVISITORContainingAndStateID(String searchKeyword,String stateID, Pageable page);
    Page<maintable> findByuserNameContainingAndStateID(String searchKeyword,String stateID, Pageable page);
    Page<maintable> findByvisitAssignContainingAndStateID(String searchKeyword,String stateID, Pageable page);
    Page<maintable> findByTITLEContainingAndStateID(String searchKeyword,String stateID, Pageable page);

    Page<maintable> findByStateIDAndDeDateLessThan (String stateID, String now, Pageable page);


}
