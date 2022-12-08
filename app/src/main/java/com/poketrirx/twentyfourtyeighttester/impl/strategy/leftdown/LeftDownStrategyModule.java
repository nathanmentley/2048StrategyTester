package com.poketrirx.twentyfourtyeighttester.impl.strategy.leftdown;

import com.google.inject.AbstractModule;

import com.poketrirx.twentyfourtyeighttester.pub.strategy.Strategy;

public final class LeftDownStrategyModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Strategy.class).to(LeftDownStrategy.class);
    }
}
