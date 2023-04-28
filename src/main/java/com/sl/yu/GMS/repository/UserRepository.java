package com.sl.yu.GMS.repository;

import com.sl.yu.GMS.model.maintable;
import com.sl.yu.GMS.model.usertable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<usertable,Long> {

    List<usertable> findRoleByUserID(String userID);
}

