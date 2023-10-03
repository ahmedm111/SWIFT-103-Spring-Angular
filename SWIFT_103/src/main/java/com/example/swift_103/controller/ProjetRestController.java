package com.example.swift_103.controller;


import com.example.swift_103.entities.SWIFT_DETAILS;
import com.example.swift_103.entities.SWIFT_RECUS;
import com.example.swift_103.service.ProjetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/projet")
public class ProjetRestController {

    @Autowired
    ProjetServiceImpl projetService;

    @GetMapping("/retrieve-swiftdetails/{swiftid}")
    public List<SWIFT_DETAILS> retrieveSwiftDetails(@PathVariable("swiftid") String tableID) {
        List<SWIFT_DETAILS> lswiftdetails = projetService.getSwiftDetailsByTABLEID(tableID);
        return lswiftdetails;
    }
    @GetMapping("/retrieve-swiftrecu/{swiftid}")
    public List<SWIFT_RECUS> retrieveSwiftRecu(@PathVariable("swiftid") String tableID) {
        List<SWIFT_RECUS> lswiftrecu = projetService.getSwiftRecuByTABLEID(tableID);
        return lswiftrecu;
    }

    @GetMapping("/retrieve-swiftrecu")
    public List<SWIFT_RECUS> retrieveSwiftRecu() {
        List<SWIFT_RECUS> lswiftrecu = projetService.getAllSwiftRecu();
        return lswiftrecu;
    }
    @GetMapping("/retrieve-swiftdetails")
    public List<SWIFT_DETAILS> retrieveSwiftDetails() {
        List<SWIFT_DETAILS> lswiftrecu = projetService.getAllSwiftDetails();
        return lswiftrecu;
    }

    @PostMapping("/add-SWIFTRECU")
    public void addSWIFTRECU() {
        projetService.processAndInsertSWIFTRECUS();
    }
    @PostMapping("/add-SWIFTDETAILS")
    public void addSWIFTDETAILS() {
        projetService.processAndInsertSWIFTDETAILS();
    }

    @DeleteMapping("/remove-swiftdetails/{swiftdetails-id2}")
    public void removeSwiftDetails(@PathVariable("swiftdetails-id2") Integer id2) {
        projetService.DeleteSWIFTDETAILS(id2);
    }
    @DeleteMapping("/remove-swiftrecu/{swiftrecu-id2}")
    public void removeSwiftRecu(@PathVariable("swiftrecu-id2") Integer id2) {
        projetService.DeleteSWIFTRECU(id2);
    }

    @PutMapping("/update-swiftdetails")
    public SWIFT_DETAILS updateSwiftDetails(@RequestBody SWIFT_DETAILS SD){
        return projetService.updateSwiftDetails(SD);
    }
    @PutMapping("/update-swiftrecu")
    public SWIFT_RECUS updateSwiftRecu(@RequestBody SWIFT_RECUS SR){
        return projetService.updateSwiftRecu(SR);
    }

}
