package com.poketrirx.twentyfourtyeighttester.cli;

import picocli.CommandLine.Command;

@Command(
    name = "2048 Simulator",
    mixinStandardHelpOptions = true,
    version = "2048 Simulator 1.0.0",
    description = "Evaluates a 2048 Strategy.",
    subcommands = { SimulateCommand.class }
)
final class RootCommand {}