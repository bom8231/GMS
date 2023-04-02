package com.sl.yu.GMS.repository;

import com.sl.yu.GMS.model.maintable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<maintable,Long> {

}
