package com.demo.spain.app;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Slf4j
public class FileProcessor {

    private final String searchString;
    private final String searchType;
    private LineIterator fileContent;

    public FileProcessor(String searchString, String searchType) {
        this.searchString = searchString;
        this.searchType = searchType;
    }

    @SneakyThrows
    public void processFile(String filename) {
        Instant startTime = Instant.now();
        try {
            fileContent = FileUtils.lineIterator(new File(filename), StandardCharsets.UTF_8.name());
        } catch (IOException e) {
            e.printStackTrace();
        }
        State readingFormat = null;
        List<String> result = new ArrayList<>();

        while (fileContent.hasNext()) {
            String line = fileContent.nextLine();
            if (line.equalsIgnoreCase("f1")) {
                readingFormat = State.READ_FORMAT1;
                continue;
            }
            if (line.equalsIgnoreCase("f2")) {
                readingFormat = State.READ_FORMAT2;
                continue;
            }
            if(line.startsWith("D")) {
                if (readingFormat == State.READ_FORMAT1) {
                        String[] lineCutted = line.split(",");
                    Output output = Output.builder()
                        .name(lineCutted[0].substring(lineCutted[0].indexOf(" ") + 1))
                        .city(StringUtils.deleteWhitespace(lineCutted[1]))
                        .id(StringUtils.deleteWhitespace(lineCutted[2].replace("-", "")))
                        .build();
                    if (searchType.equals("CITY")) {
                        if (output.getCity().equalsIgnoreCase(searchString.toLowerCase())) {
                            result.add(output.getName() + " " + output.getId());
                        }
                    }
                    if (searchType.equals("ID")) {
                        if(output.getId().equals(searchString)){
                            result.add(output.getCity().toUpperCase());
                        }
                    }
                }

                if (readingFormat == State.READ_FORMAT2) {
                    String[] lineCutted = line.split(" ; ");
                    Output output = Output.builder()
                        .name(lineCutted[0].substring(lineCutted[0].indexOf(" ") + 1))
                        .city(StringUtils.deleteWhitespace(lineCutted[1]))
                        .id(StringUtils.deleteWhitespace(lineCutted[2].replace("-", "")))
                        .build();
                    if (searchType.equals("CITY")) {
                        if (output.getCity().equalsIgnoreCase(searchString.toLowerCase())) {
                            result.add(output.getName() + " " + output.getId());
                        }
                    }
                    if (searchType.equals("ID")) {
                        if(output.getId().equals(searchString)){
                            result.add(output.getCity().toUpperCase());
                        }
                    }
                }
                continue;
            }
            System.out.println("This line \"" + line + "\" has been ignored due to wrong format");
        }
        Instant finish = Instant.now();
        new HashSet<>(result).forEach(System.out::println);
        System.out.println("==========> Time: " + Duration.between(startTime,finish).toMillis() * 0.001);
        fileContent.close();
    }
}

