package com.poketrirx.twentyfourtyeighttester.cli;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.stream.IntStream;

import javax.inject.Inject;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import com.poketrirx.twentyfourtyeighttester.pub.models.GameSummary;
import com.poketrirx.twentyfourtyeighttester.pub.models.Summary;
import com.poketrirx.twentyfourtyeighttester.pub.simulation.Simulator;
import com.poketrirx.twentyfourtyeighttester.pub.strategy.Strategy;

@Command(name = "simulate")
final class SimulateCommand implements Callable<Integer> {
    private final Simulator simulator;
    private final Strategy strategy;

    @Option(names = "-s", description = "The 2048 board size")
    private int size = 4;

    @Option(names = "-i", description = "The number of simulations")
    private int totalSimulations = 100;

    @Inject
    public SimulateCommand(Simulator simulator, Strategy strategy) {
        this.simulator = Objects.requireNonNull(simulator);
        this.strategy = Objects.requireNonNull(strategy);
    }

    @Override
    public Integer call() throws Exception {
        List<GameSummary> summaries = runSimulations(simulator, strategy, size, totalSimulations);

        Summary results = buildSummary(summaries);

        printSummary(strategy.getName(), results, size, totalSimulations);

        return 0;
    }

    private static List<GameSummary> runSimulations(Simulator simulator, Strategy strategy, int size, int totalSimulations) {
        List<GameSummary> summaries = new ArrayList<GameSummary>();

        IntStream.range(0, totalSimulations).forEachOrdered(n -> {
            GameSummary summary = simulator.simulate(strategy, size);

            summaries.add(summary);
        });

        return summaries;
    }

    private static Summary buildSummary(List<GameSummary> summaries) {
        double maxBlockSize = summaries.stream().mapToDouble(summary -> summary.getMaxBlockSize()).average().orElse(0);
        double maxScore = summaries.stream().mapToDouble(summary -> summary.getMaxScore()).average().orElse(0);
        double totalRounds = summaries.stream().mapToDouble(summary -> summary.getTotalRounds()).average().orElse(0);

        return Summary.builder()
            .maxBlockSize(maxBlockSize)
            .maxScore(maxScore)
            .totalRounds(totalRounds)
            .build();
    }

    private static void printSummary(String name, Summary results, int size, int totalSimulations) {
        String summary = String.format(
            "Strategy Name: %s\n" +
            "Max Block Size: %s\n" +
            "Max Score: %s\n" +
            "Total Rounds: %s\n" +
            "Board Size: %s\n" +
            "Total Simulations: %s\n",
            name,
            results.getMaxBlockSize(),
            results.getMaxScore(),
            results.getTotalRounds(),
            size,
            totalSimulations
        );

        System.out.println(summary);
    }
}
