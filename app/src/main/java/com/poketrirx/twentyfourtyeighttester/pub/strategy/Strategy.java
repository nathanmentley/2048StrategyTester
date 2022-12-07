package com.poketrirx.twentyfourtyeighttester.pub.strategy;

import com.poketrirx.twentyfourtyeighttester.pub.models.Board;
import com.poketrirx.twentyfourtyeighttester.pub.models.Move;
import com.poketrirx.twentyfourtyeighttester.pub.simulation.GameLogic;

/**
 * Encapsulates the logic of a 2048 strategy.
 */
public interface Strategy {
    /**
     * Fetches the name of the strategy
     * 
     * @return The strategy name as a string.
     */
    String getName();

    /**
     * Determines the next move to be made by the strategy.
     * 
     * @param board A pojo that contains the current state of the game to analyze when making a move.
     * @return An enum that is either Up, Down, Left, Right depending on the desired move to make.
     */
    Move next(GameLogic gameLogic, Board board);

    /**
     * Resets any information kepted in this instances that'd we want cleared to reuse this strategy instance for multiple games.
     */
    void reset();
}