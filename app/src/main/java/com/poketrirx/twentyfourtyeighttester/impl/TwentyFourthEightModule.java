package com.poketrirx.twentyfourtyeighttester.impl;

import com.google.inject.AbstractModule;

import com.poketrirx.twentyfourtyeighttester.impl.simulator.SimulatorModule;
import com.poketrirx.twentyfourtyeighttester.impl.strategy.leftdown.LeftDownStrategyModule;

public class TwentyFourthEightModule extends AbstractModule {
    @Override
    protected void configure() {
        install(new SimulatorModule());
        install(new LeftDownStrategyModule());
    }
}
