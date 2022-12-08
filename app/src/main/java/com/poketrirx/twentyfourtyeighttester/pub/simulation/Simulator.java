package com.poketrirx.twentyfourtyeighttester.pub.simulation;

import com.poketrirx.twentyfourtyeighttester.pub.models.GameSummary;
import com.poketrirx.twentyfourtyeighttester.pub.strategy.Strategy;

public interface Simulator {
    GameSummary simulate(Strategy strategy, int size);
}