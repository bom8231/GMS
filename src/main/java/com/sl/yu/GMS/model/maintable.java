package com.sl.yu.GMS.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class maintable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "VISIT_ID")
    private Long id;

    private String ATTACH;
    private String BACK_TIME;
    private String CAR_NUMBER;
    private String CARD_ID;
    private String COMPLETE;
    private String CONTENT_VISIT;

    private String DE_DATE;
    private String DEPT_ID;
    private String DEPT_NAME;
    private String EMAIL;

    private Date END_DATE;
    private String FILE_ID;
    private String PLANT;
    private Date REPLY_DATE;
    private String REQ_DATE;
    private String STATE_ID;
    private String TEL;
    private String TITLE;
    private String USER_ID;
    private String USER_NAME;

    private String VISIT_ASSIGN;
    private String VISIT_CLASS;
    private String VISIT_PURPOSE;
    private String VISIT_TIME;
    private String VISITOR;
    private String VISITOR_EMAIL;
    @Builder
    public maintable(String ATTACH, String BACK_TIME, String CAR_NUMBER, String CARD_ID, String COMPLETE, String CONTENT_VISIT, String DE_DATE, String DEPT_ID, String DEPT_NAME, String EMAIL, Date END_DATE, String FILE_ID, String PLANT, Date REPLY_DATE, String REQ_DATE, String STATE_ID, String TEL, String TITLE, String USER_ID, String USER_NAME, String VISIT_ASSIGN, String VISIT_CLASS, String VISIT_PURPOSE, String VISIT_TIME, String VISITOR, String VISITOR_EMAIL) {
        this.ATTACH = ATTACH;
        this.BACK_TIME = BACK_TIME;
        this.CAR_NUMBER = CAR_NUMBER;
        this.CARD_ID = CARD_ID;
        this.COMPLETE = COMPLETE;
        this.CONTENT_VISIT = CONTENT_VISIT;
        this.DE_DATE = DE_DATE;
        this.DEPT_ID = DEPT_ID;
        this.DEPT_NAME = DEPT_NAME;
        this.EMAIL = EMAIL;
        this.END_DATE = END_DATE;
        this.FILE_ID = FILE_ID;
        this.PLANT = PLANT;
        this.REPLY_DATE = REPLY_DATE;
        this.REQ_DATE = REQ_DATE;
        this.STATE_ID = STATE_ID;
        this.TEL = TEL;
        this.TITLE = TITLE;
        this.USER_ID = USER_ID;
        this.USER_NAME = USER_NAME;
        this.VISIT_ASSIGN = VISIT_ASSIGN;
        this.VISIT_CLASS = VISIT_CLASS;
        this.VISIT_PURPOSE = VISIT_PURPOSE;
        this.VISIT_TIME = VISIT_TIME;
        this.VISITOR = VISITOR;
        this.VISITOR_EMAIL = VISITOR_EMAIL;
    }

}
