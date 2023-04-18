package com.sl.yu.GMS.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Data
@Getter

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
    @Column(insertable = false)
    private String REQ_DATE = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    @PrePersist
    public void prePersist() {
        this.REQ_DATE =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
    @Column(insertable = false)
    private String STATE_ID ="0";
    private String TEL;
    private String TITLE;
    private String USER_ID;

    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "VISIT_ASSIGN")
    private String visitAssign;
    private String VISIT_CLASS;
    private String VISIT_PURPOSE;
    private String VISIT_TIME;
    private String VISITOR;
    private String VISITOR_EMAIL;
    private String VISITOR_PHONE;

}