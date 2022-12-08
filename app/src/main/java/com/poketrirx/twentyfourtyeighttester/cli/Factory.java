package com.poketrirx.twentyfourtyeighttester.cli;

import java.util.Objects;

import com.google.inject.Guice;
import com.google.inject.Injector;
import picocli.CommandLine.IFactory;

import com.poketrirx.twentyfourtyeighttester.impl.TwentyFourthEightModule;

final class Factory implements IFactory {
    private static final Injector INJECTOR = Guice.createInjector(new TwentyFourthEightModule());

    @Override
    public <Type> Type create(Class<Type> aClass) throws Exception {
        return INJECTOR.getInstance(Objects.requireNonNull(aClass));
    }
}
