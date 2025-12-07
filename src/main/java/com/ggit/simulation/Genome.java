package com.ggit.simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Genome {
    private static final int GENOME_LENGTH = 32;
    private static final int MIN_SPLIT = 1;
    private static final int MAX_SPLIT = 30;
    private static final Random random = new Random();

    private final List<MapDirection> genes;

    public Genome() {
        genes = Stream.generate(MapDirection::random).limit(GENOME_LENGTH)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public Genome(Genome mather, Genome father) {
        int split = random.nextInt(MIN_SPLIT, MAX_SPLIT);
        genes = new ArrayList<>(GENOME_LENGTH);
        genes.addAll(mather.genes.subList(0, split));
        genes.addAll(split, father.genes.subList(split, GENOME_LENGTH));
    }

    public MapDirection randomGene() {
        return genes.get(random.nextInt(GENOME_LENGTH));
    }
}
