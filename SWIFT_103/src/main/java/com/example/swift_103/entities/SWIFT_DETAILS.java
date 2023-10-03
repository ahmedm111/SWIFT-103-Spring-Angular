package com.example.swift_103.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SWIFT_DETAILS implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    private String TABLEID;
    private Integer Ordre;
    private String TAG;
    private String Valeur;
 //   @OneToOne
   // private SWIFT_DETAILS swift_details;
}
