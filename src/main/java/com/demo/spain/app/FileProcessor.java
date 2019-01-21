package com.demo.spain.app;

import com.demo.spain.app.strategy.formatting.FormatDecoderStrategy;
import com.demo.spain.app.strategy.filtering.FilteringStrategy;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Slf4j
@Component
public class FileProcessor {


    private final List<FormatDecoderStrategy> startLineDecoderStrategies;
    private final List<FilteringStrategy> filteringStrategies;
    private LineIterator fileContent;
    private String searchType;
    private String searchString;

    @Autowired
    public FileProcessor(List<FormatDecoderStrategy> startLineDecoderStrategies, List<FilteringStrategy> filteringStrategies) {
        this.startLineDecoderStrategies = startLineDecoderStrategies;
        this.filteringStrategies = filteringStrategies;
    }


    @SneakyThrows
    public void processFile(String[] args) {
        String filename = args[0];
        searchType = args[1];
        searchString = args[2];
        Instant startTime = Instant.now();
        loadFile(filename);
        List<String> result = processEachLine();
        Instant finish = Instant.now();
        printUnrepeatedResults(result);
        System.out.println("==========> Time: " + Duration.between(startTime,finish).toMillis() * 0.001);
        fileContent.close();
    }

    private List<String> processEachLine() {
        Formatting readingFormat = null;
        List<String> result = new ArrayList<>();
        processingNextLine:
        while (fileContent.hasNext()) {
            String line = fileContent.nextLine();
            for (FormatDecoderStrategy strategy : startLineDecoderStrategies) {
                if(strategy.isApplicable(line)){
                    readingFormat = strategy.setCurrentProcessing();
                    continue processingNextLine;
                }
            }
            if(line.startsWith("D")) {
                if (readingFormat == Formatting.TYPE_1) {
                    processLine(result, line, Formatting.TYPE_1.getSeparator());
                }
                if (readingFormat == Formatting.TYPE_2) {
                    processLine(result, line, Formatting.TYPE_2.getSeparator());
                }
                continue processingNextLine;
            }
            System.out.println("This line \"" + line + "\" has been ignored due to wrong format");
        }
        return result;
    }

    private void loadFile(String filename) {
        try {
            fileContent = FileUtils.lineIterator(new File(filename), StandardCharsets.UTF_8.name());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printUnrepeatedResults(List<String> result) {
        new HashSet<>(result).forEach(System.out::println);
    }

    private void processLine(List<String> result, String line, String separator) {
        String[] splittedLine = line.split(separator);
        DataHolder output = DataHolder.builder()
            .name(splittedLine[0].substring(splittedLine[0].indexOf(" ") + 1))
            .city(StringUtils.deleteWhitespace(splittedLine[1]))
            .id(StringUtils.deleteWhitespace(splittedLine[2].replace("-", "")))
            .build();

        filteringStrategies.stream()
            .filter(strategy -> strategy.isApplicable(searchType, searchString, output))
            .map(strategy -> strategy.filter(output))
            .forEach(result::add);
    }
}

