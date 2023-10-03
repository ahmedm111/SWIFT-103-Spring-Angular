package com.example.swift_103.service;


import com.example.swift_103.entities.SWIFT_DETAILS;
import com.example.swift_103.entities.SWIFT_RECUS;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public interface IProjetService {
        public StringBuilder readSwiftMessage();
        public void processAndInsertSWIFTRECUS();
        public String extractSessionNumber(String swiftMessage);
        public String extractSequenceNumber(String swiftMessage);
        public String extractType(String swiftMessage);
        public String extractSense(String swiftMessage);
        public String extractBanqueRec(String swiftMessage);
        public String extractBanqueEme(String swiftMessage);
        public LocalDate extractDateRec(String swiftMessage);
        public LocalTime extractTimeRec(String swiftMessage);
        public void processAndInsertSWIFTDETAILS();
        public String extractField(String swiftMessage, String tag);
        public void DeleteSWIFTRECU(Integer tableid);
        public void DeleteSWIFTDETAILS(Integer tableid);
        public List<SWIFT_RECUS> getSwiftRecuByTABLEID(String tableID);
        public List<SWIFT_DETAILS> getSwiftDetailsByTABLEID(String tableID);
        public List<SWIFT_DETAILS> getAllSwiftDetails();
        public List<SWIFT_RECUS> getAllSwiftRecu();
        public SWIFT_RECUS updateSwiftRecu(SWIFT_RECUS updatedSR);
        public SWIFT_DETAILS updateSwiftDetails(SWIFT_DETAILS updatedSD);




}
