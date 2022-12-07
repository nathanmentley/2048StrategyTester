package com.poketrirx.twentyfourtyeighttester.impl.strategy.leftdown;

import com.poketrirx.twentyfourtyeighttester.pub.models.Board;
import com.poketrirx.twentyfourtyeighttester.pub.models.Move;
import com.poketrirx.twentyfourtyeighttester.pub.simulation.GameLogic;
import com.poketrirx.twentyfourtyeighttester.pub.strategy.Strategy;

public class LeftDownStrategy implements Strategy {
    @Override
    public String getName() {
        //A unique name for the strategy so we know what to call it in reports.
        return "Left Down";
    }

    @Override
    public Move next(GameLogic gameLogic, Board board) {
        if (!gameLogic.isMoveValid(board, Move.Left) && gameLogic.isMoveValid(board, Move.Down)) {
            //if we can't move left, but can move down. lets move down.
            return Move.Down;
        }
        else if (gameLogic.isMoveValid(board, Move.Left) && !gameLogic.isMoveValid(board, Move.Down)) {
            //if we can't move down, but can move left. lets move left.
            return Move.Left;
        }
        else if (!gameLogic.isMoveValid(board, Move.Left) && !gameLogic.isMoveValid(board, Move.Down)) {
            //If we can't move either direction lets just try whatever we can move:
            return gameLogic.isMoveValid(board, Move.Right) ?
                Move.Right:
                Move.Up;
        }

        //Finally, since we can move either left or down, lets just try to move left on odd number rounds, and down on even.
        return board.getRound() % 2 == 1 ?
            Move.Left:
            Move.Down;
    }

    @Override
    public void reset() {
        //Since this strategy is dumb, we don't have any persisted data to keep track of during the game. If we did this method would clean it.
    }
}