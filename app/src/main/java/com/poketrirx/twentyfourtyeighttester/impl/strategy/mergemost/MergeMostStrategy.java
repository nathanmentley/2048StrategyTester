package com.poketrirx.twentyfourtyeighttester.impl.strategy.mergemost;

import com.poketrirx.twentyfourtyeighttester.pub.models.Board;
import com.poketrirx.twentyfourtyeighttester.pub.models.Move;
import com.poketrirx.twentyfourtyeighttester.pub.simulation.GameLogic;
import com.poketrirx.twentyfourtyeighttester.pub.strategy.Strategy;

public class MergeMostStrategy implements Strategy {
    @Override
    public String getName() {
        return "Merge Most";
    }

    @Override
    public Move next(GameLogic gameLogic, Board board) {
        return Move.Up;
    }

    @Override
    public void reset() {}
}