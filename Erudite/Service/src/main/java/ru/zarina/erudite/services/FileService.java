package ru.zarina.erudite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zarina.erudite.dtos.HumanDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class FileService {
    private static final String ENTRY_PATTERN = "^[A-Za-z]+_[\\d]+$";
    private final HumanService humanService;
    @Autowired
    public FileService(HumanService humanService) {
        this.humanService = humanService;
    }

    public List<HumanDto> parseFile(InputStream inputStream) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            List<HumanDto> entries = new ArrayList<>();
            String line;

            while ((line = reader.readLine()) != null) {
                try {
                    if (!Pattern.matches(ENTRY_PATTERN, line)) {
                        throw new IllegalArgumentException("The entry " + line + " should match the pattern " + ENTRY_PATTERN);
                    }
                    String[] fields = line.split("_");


                    try {
                        entries.add(humanService.addHuman(fields[0], Integer.parseInt(fields[1])));
                    }
                    catch (Exception e) {
                        System.err.println("Error: " + e.getMessage() + " Skipping over.");
                    }
                } catch (Exception e) {
                    System.err.println("Error occurred while parsing line: " + line + ". Error: " + e.getMessage());
                }
            }
            return entries;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
