package com.ggit.logging;

import java.io.IOException;

public class IOUtils {
    public static void toUnchecked(IORunnable runnable) {
        try {
            runnable.run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FunctionalInterface
    public interface IORunnable {
        void run() throws IOException;
    }
}
