package com.example.swift_103.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SWIFT_RECUS implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    private String TABLEID;
    private String SwiftType;
    private String Sens;
    private String BanRec;
    private String BanEme;
    private LocalDate DateRec;
    private LocalTime TimeRec;
    //   @OneToOne(mappedBy = "swift_details")
    // private SWIFT_DETAILS swift_recus;
}