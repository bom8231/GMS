package com.sl.yu.GMS.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import jakarta.persistence.Id;

@Entity
@Data
public class maintable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long VISIT_ID;
    private String TITLE;
    private String TEL;
    private String CONTENT_VISIT;
    private String EMAIL;

    private String PLANT;
    private String DEPT_NAME;
    private String ATTACH;
    private String DE_DATE;

    private String CAR_NUMBER;
    private String VISIT_ASSIGN;
    private String VISIT_PURPOSE;
    private String VISIT_CLASS;
    private String VISITOR_EMAIL;

}
