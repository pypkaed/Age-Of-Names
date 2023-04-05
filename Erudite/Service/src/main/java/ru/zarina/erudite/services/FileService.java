package ru.zarina.erudite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zarina.erudite.dtos.HumanDto;
import ru.zarina.erudite.exceptions.FileServiceException;
import ru.zarina.erudite.exceptions.FileServiceExceptionHandler;
import ru.zarina.erudite.exceptions.HumanServiceException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class FileService {
    private static final String ENTRY_PATTERN = "^[A-Za-z]+_\\d+$";
    private final HumanService humanService;
    @Autowired
    public FileService(HumanService humanService) {
        this.humanService = humanService;
    }

    public HumanDto parseLine(String line) throws FileServiceException, IOException {
            if (!Pattern.matches(ENTRY_PATTERN, line)) {
                throw new IllegalArgumentException("The entry " + line + " should match the pattern " + ENTRY_PATTERN);
            }
            HumanDto humanEntry;

            String[] fields = line.split("_");

            try {
                humanEntry = humanService.addHuman(fields[0], Integer.parseInt(fields[1]));
            }
            catch (HumanServiceException e) {
                throw FileServiceExceptionHandler.handleHumanServiceException(e);
            }
        return humanEntry;
    }
}
