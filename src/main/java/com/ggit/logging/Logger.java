package com.ggit.logging;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static final Path path = Path.of(getPath());
    private static final String PREFIX = "app.";
    private static final String SUFFIX = ".log";
    private static final String DATE_FORMAT = "yyyy-MM-dd";

    static {
        Path dir = path.getParent();
        IOUtils.toUnchecked(() -> {
            if (!Files.exists(dir)) Files.createDirectory(dir);
            else cleanUp(dir);
        });
    }

    public static void log(String msg) {
        IOUtils.toUnchecked(() ->
                Files.writeString(path, formatMessage(msg),
                        StandardOpenOption.CREATE, StandardOpenOption.APPEND, StandardOpenOption.WRITE));
    }

    private static String formatMessage(String msg) {
        return String.format("[%s] %s\n", Instant.now().toString(), msg);
    }

    private static String getPath() {
        return String.format("./logs/%s%s%s", PREFIX, LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), SUFFIX);
    }

    private static void cleanUp(Path dir) {
        LocalDate cleanUpDate = LocalDate.now().minusDays(7);
        try (var filesList = Files.list(dir)) {
            filesList.forEach(child -> {
                LocalDate logDate = retrieveDateFromFilename(child.getFileName().toString());
                if (logDate.isBefore(cleanUpDate)) safeDeleteFile(child);
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static LocalDate retrieveDateFromFilename(String filename) {
        int startIndex = PREFIX.length();
        int stopIndex = startIndex + DATE_FORMAT.length();
        return LocalDate.parse(filename.substring(startIndex, stopIndex));
    }

    private static void safeDeleteFile(Path file) {
        IOUtils.toUnchecked(() -> Files.delete(path));
    }
}
