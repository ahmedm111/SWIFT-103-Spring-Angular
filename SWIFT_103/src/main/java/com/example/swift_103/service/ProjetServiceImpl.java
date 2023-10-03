package com.example.swift_103.service;
import com.example.swift_103.entities.SWIFT_DETAILS;
import com.example.swift_103.entities.SWIFT_RECUS;
import com.example.swift_103.repository.SWIFT_DETAILSRepo;
import com.example.swift_103.repository.SWIFT_RECUSRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ProjetServiceImpl implements IProjetService {
    @Autowired
    SWIFT_RECUSRepo swift_recusRepo;
    @Autowired
    SWIFT_DETAILSRepo swift_detailsRepo;
    @Autowired
    public StringBuilder readSwiftMessage() {
        Resource resource = new ClassPathResource("test.txt");
        StringBuilder content = new StringBuilder();

        try (InputStream inputStream = resource.getInputStream()) {
            try (Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name())) {
                while (scanner.hasNextLine()) {
                    content.append(scanner.nextLine()).append("\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }
    @Override
    public void processAndInsertSWIFTRECUS() {
        String swiftMessage = String.valueOf(readSwiftMessage());
        String sessionNumber = extractSessionNumber(swiftMessage);
        String sequenceNumber = extractSequenceNumber(swiftMessage);
        String sens = extractSense(swiftMessage);
        String type = extractType(swiftMessage);
        String BanqueEm = extractBanqueEme(swiftMessage);
        String BanqueRe = extractBanqueRec(swiftMessage);
        LocalDate  Date = extractDateRec(swiftMessage);
        LocalTime time = extractTimeRec(swiftMessage);

        SWIFT_RECUS swiftRecus = new SWIFT_RECUS();
        swiftRecus.setTABLEID(sessionNumber+ sequenceNumber);
        swiftRecus.setSwiftType(type);
        swiftRecus.setSens(sens);
        swiftRecus.setBanRec(BanqueRe);
        swiftRecus.setBanEme(BanqueEm);
        swiftRecus.setDateRec(Date);
        swiftRecus.setTimeRec(time);
        swift_recusRepo.save(swiftRecus);
    }
    @Override
    public void processAndInsertSWIFTDETAILS() {
        int j=0;
        String swiftMessage = String.valueOf(readSwiftMessage());
        String sessionNumber = extractSessionNumber(swiftMessage);
        String sequenceNumber = extractSequenceNumber(swiftMessage);
        List<String> tagsToExtract = new ArrayList<>();
        Pattern pattern = Pattern.compile(":([0-9A-Za-z]+):");
        Matcher matcher = pattern.matcher(swiftMessage);
        while (matcher.find()) {
            tagsToExtract.add(matcher.group(1));
        }
        for (String tag : tagsToExtract) {
            SWIFT_DETAILS details = new SWIFT_DETAILS();
            String valeur = extractField(swiftMessage, tag);
            j+=1;
            details.setTABLEID(sessionNumber+ sequenceNumber);
            details.setOrdre(j);
            details.setTAG(tag);
            details.setValeur(valeur);
            swift_detailsRepo.save(details);
        }
    }

    @Override
    public String extractField(String swiftMessage, String tag) {
        int startIndex = swiftMessage.indexOf(tag+":") + tag.length()+1;
        int endIndex;
        for (int i = startIndex; i < swiftMessage.length(); i++){

        }
        if(swiftMessage.indexOf("}", startIndex)<swiftMessage.indexOf(":", startIndex)){
            endIndex=swiftMessage.indexOf("}", startIndex);
        }
        else {
            endIndex = swiftMessage.indexOf(":", startIndex);
        }
        return swiftMessage.substring(startIndex, endIndex);
    }
    @Transactional
    @Override
    public void DeleteSWIFTRECU(Integer id2) {
        swift_recusRepo.deleteByTABLEID(id2);
    }
    @Transactional
    @Override
    public void DeleteSWIFTDETAILS(Integer id2) {
        swift_detailsRepo.deleteByTABLEID(id2);
    }

    @Override
    public List<SWIFT_RECUS> getSwiftRecuByTABLEID(String tableID) {

        return swift_recusRepo.findByTABLEID(tableID);
    }

    @Override
    public List<SWIFT_DETAILS> getSwiftDetailsByTABLEID(String tableID) {
        return swift_detailsRepo.findByTABLEID(tableID);
    }

    @Override
    public List<SWIFT_DETAILS> getAllSwiftDetails() {
        return swift_detailsRepo.findAll();
    }

    @Override
    public List<SWIFT_RECUS> getAllSwiftRecu() {
        return swift_recusRepo.findAll();
    }

    @Override
    public SWIFT_RECUS updateSwiftRecu(SWIFT_RECUS updatedSR) {
        SWIFT_RECUS existingSwiftRecu = swift_recusRepo.findById(updatedSR.getID()).get();
        if (updatedSR.getTABLEID() != null) {
            existingSwiftRecu.setTABLEID(updatedSR.getTABLEID());
        }
        if (updatedSR.getSens() != null) {
            existingSwiftRecu.setSens(updatedSR.getSens());
        }
        if (updatedSR.getSwiftType() != null) {
            existingSwiftRecu.setSwiftType(updatedSR.getSwiftType());
        }
        if (updatedSR.getBanRec() != null) {
            existingSwiftRecu.setBanRec(updatedSR.getBanRec());
        }
        if (updatedSR.getBanEme() != null) {
            existingSwiftRecu.setBanEme(updatedSR.getBanEme());
        }
        if (updatedSR.getTimeRec() != null) {
            existingSwiftRecu.setTimeRec(updatedSR.getTimeRec());
        }
        if (updatedSR.getDateRec() != null) {
            existingSwiftRecu.setDateRec(updatedSR.getDateRec());
        }
        return swift_recusRepo.save(existingSwiftRecu);
    }

    @Override
    public SWIFT_DETAILS updateSwiftDetails(SWIFT_DETAILS updatedSD) {
        SWIFT_DETAILS existingSwiftDetails = swift_detailsRepo.findById(updatedSD.getID()).get();
        if (updatedSD.getTABLEID() != null) {
            existingSwiftDetails.setTABLEID(updatedSD.getTABLEID());
        }
        if (updatedSD.getOrdre() != null) {
            existingSwiftDetails.setOrdre(updatedSD.getOrdre());
        }
        if (updatedSD.getTAG() != null) {
            existingSwiftDetails.setTAG(updatedSD.getTAG());
        }
        if (updatedSD.getValeur() != null) {
            existingSwiftDetails.setValeur(updatedSD.getValeur());
        }
        return swift_detailsRepo.save(existingSwiftDetails);
    }

    //    @Autowired
  //  public String writeSessionNumber() {
    //    String swiftMessage = String.valueOf(readSwiftMessage());
      //  String sessionNumber = extractSessionNumber(swiftMessage);
        //String sequenceNumber = extractSequenceNumber(swiftMessage);
//        String sens = extractSense(swiftMessage);
  //      String type = extractType(swiftMessage);
    //    String Date = extractDateRec(swiftMessage);
      //  String time = extractTimeRec(swiftMessage);
        //System.out.println("Extracted Session Number: " + sessionNumber + sequenceNumber + type+sens+Date+time);
        //return swiftMessage;
    //}
    @Override
    public String extractSessionNumber(String swiftMessage) {
        int startIndex = swiftMessage.indexOf("{1:") + 18;
        int endIndex = startIndex + 4;
        String sessionnumber=swiftMessage.substring(startIndex, endIndex);
        return sessionnumber;
    }
    @Override
    public String extractSequenceNumber(String swiftMessage) {
        int startIndex = swiftMessage.indexOf("{1") + 22;
        int endIndex = startIndex + 6;
        String sequencenumber=swiftMessage.substring(startIndex, endIndex);
        return sequencenumber;
    }
    @Override
    public String extractType(String swiftMessage) {
        int startIndex = swiftMessage.indexOf("{2") + 17;
        int endIndex = startIndex + 12;
        String type=swiftMessage.substring(startIndex, endIndex);
        return type;
    }
    @Override
    public String extractSense(String swiftMessage) {
        int startIndex = swiftMessage.indexOf("{2") + 3;
        int endIndex = startIndex + 1;
        String sens=swiftMessage.substring(startIndex, endIndex);
        return sens;
    }
    @Override
    public LocalDate extractDateRec(String swiftMessage) {
        int startIndex = swiftMessage.indexOf("{2") + 39;
        int endIndex = startIndex + 6;
        String DateStr=swiftMessage.substring(startIndex, endIndex);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
        LocalDate DateRec = LocalDate.parse(DateStr, formatter);
        return DateRec;
    }
    @Override
    public LocalTime extractTimeRec(String swiftMessage) {
        int startIndex = swiftMessage.indexOf("{2") + 45;
        int endIndex = startIndex + 4;
        String timeStr=swiftMessage.substring(startIndex, endIndex);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        LocalTime time = LocalTime.parse(timeStr, formatter);
        return time;
    }


    @Override
    public String extractBanqueRec(String swiftMessage) {
        int startIndex = swiftMessage.indexOf("{1") + 6;
        int endIndex = startIndex + 12;
        String BanqueRec=swiftMessage.substring(startIndex, endIndex);
        return BanqueRec;
    }

    @Override
    public String extractBanqueEme(String swiftMessage) {
        int startIndex = swiftMessage.indexOf("{2") + 17;
        int endIndex = startIndex + 12;
        String BanqueEme=swiftMessage.substring(startIndex, endIndex);
        return BanqueEme;
    }

}

