package com.poketrirx.twentyfourtyeighttester.pub.simulation;

import com.poketrirx.twentyfourtyeighttester.pub.models.Summary;
import com.poketrirx.twentyfourtyeighttester.pub.strategy.Strategy;

public interface Simulator {
    Summary simulate(Strategy strategy);
}