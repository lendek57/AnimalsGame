package com.ggit.json;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Logger {
    private final static String DATE_FORMAT = "yyyy-MM-dd";
    private final static String DIR_NAME = "logs";

    static {
        Path dirPath = path().getParent();
        if (!Files.exists(dirPath)) {
            try {
                Files.createDirectory(dirPath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void log(String message) {
        try {
            Files.writeString(path(), formatMessage(message), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String formatMessage(String message) {
        return String.format("[%s] %s\n", Instant.now().toString(), message);
    }

    private static String filename() {
        return String.format("simulation.%s.log", LocalDate.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT)));
    }

    private static Path path() {
        return Path.of(String.format("./%s/%s", DIR_NAME, filename()));
    }
}
