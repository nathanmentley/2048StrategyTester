package com.poketrirx.twentyfourtyeighttester.impl.simulator;

import com.google.inject.AbstractModule;

import com.poketrirx.twentyfourtyeighttester.pub.simulation.GameLogic;
import com.poketrirx.twentyfourtyeighttester.pub.simulation.Simulator;

public final class SimulatorModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(GameLogic.class).to(GameLogicImpl.class);
        bind(Simulator.class).to(SimulatorImpl.class);
    }
}
