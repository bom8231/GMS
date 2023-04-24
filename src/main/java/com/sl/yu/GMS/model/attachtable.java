package com.sl.yu.GMS.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Entity
@Data
@Getter
public class attachtable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FILE_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VISIT_ID")
    private maintable maintable;

    @Column(name = "ORIGIN_NAME")
    private String originName;

    @Column(name = "SAVED_NAME")
    private String savedName;

    @Column(name = "SAVED_PATH")
    private String savedPath;

}
