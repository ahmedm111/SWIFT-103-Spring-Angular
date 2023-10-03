package com.example.swift_103;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

@SpringBootApplication
public class Swift103Application {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(Swift103Application.class, args);

    }

}
