package com.ggit.json;

import com.google.gson.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class GsonConfig {
    private static final int PRECISION = 2;

    public static Gson getIntance() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Double.class, doubleSerializer())
                .registerTypeAdapter(Float.class, doubleSerializer())
                .create();
    }

    private static JsonSerializer<Double> doubleSerializer() {
        return (number, _, _) -> {
            if (number == null) return JsonNull.INSTANCE;

            BigDecimal decimal = BigDecimal.valueOf(number);
            decimal = decimal.setScale(PRECISION, RoundingMode.HALF_UP);
            return new JsonPrimitive(decimal);
        };
    }
}
