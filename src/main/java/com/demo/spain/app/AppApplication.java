package com.demo.spain.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppApplication {


    public static void main(String[] args)  {
        SpringApplication.run(AppApplication.class, args);
        String fileName = args[0];
        String searchType = args[1];
        String searchWord = args[2];
        FileProcessor reader = new FileProcessor(searchWord, searchType);
        reader.processFile(fileName);

    }

}

