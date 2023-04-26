package com.sl.yu.GMS.repository;

import com.sl.yu.GMS.model.attachtable;
import com.sl.yu.GMS.model.maintable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileRepository extends JpaRepository<attachtable,Long>{
    List<attachtable> findByMaintable (maintable maintable);
}

