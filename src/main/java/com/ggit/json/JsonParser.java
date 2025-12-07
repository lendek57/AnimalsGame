package com.ggit.json;

import com.ggit.simulation.SimulationStatistics;
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonParser {
    private static final Gson gson = new Gson();
    private static final String STATS_PATH = "./stats.json";
    private static final String CONFIG_PATH = "./config.json";

    public static void dumpStatsToFile(SimulationStatistics statistics) {
        try (FileWriter writer = new FileWriter(STATS_PATH, true)) {
            gson.toJson(statistics, writer);
        } catch (IOException e) {
            Logger.log(e.getMessage());
        }
    }

    public static SimulationParams readConfig() {
        try {
            return gson.fromJson(Files.readString(Path.of(CONFIG_PATH)), SimulationParams.class);
        } catch (IOException e) {
            Logger.log(e.getMessage());
            return new SimulationParams();
        }
    }
}
