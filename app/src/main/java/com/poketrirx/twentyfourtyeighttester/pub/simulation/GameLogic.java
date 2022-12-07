package com.poketrirx.twentyfourtyeighttester.pub.simulation;

import com.poketrirx.twentyfourtyeighttester.pub.models.Board;
import com.poketrirx.twentyfourtyeighttester.pub.models.Move;

public interface GameLogic {
    boolean isMoveValid(Board board, Move move);

    boolean isGameOver(Board board);

    Board getBoardForNextMove(Board board, Move move);
}
