package com.poketrirx.twentyfourtyeighttester.cli;

import java.util.Objects;

import picocli.CommandLine;
import picocli.CommandLine.IFactory;

public class App {
    public static void main(String[] args) {
        Objects.requireNonNull(args);

        IFactory factory = new Factory();

        CommandLine commandLine = new CommandLine(RootCommand.class, factory);

        int result = commandLine.execute(args);

        System.exit(result);
    }
}