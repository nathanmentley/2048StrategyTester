package com.poketrirx.twentyfourtyeighttester.impl.strategy.mergemost;

import com.poketrirx.twentyfourtyeighttester.pub.models.Board;
import com.poketrirx.twentyfourtyeighttester.pub.models.Move;
import com.poketrirx.twentyfourtyeighttester.pub.strategy.Strategy;

public class MergeMostStrategy implements Strategy {
    @Override
    public String getName() {
        return "Merge Most";
    }

    @Override
    public Move next(Board board) {
        if (!board.isMoveValid(Move.Left) && board.isMoveValid(Move.Down)) {
            return Move.Down;
        }

        if (board.isMoveValid(Move.Left) && !board.isMoveValid(Move.Down)) {
            return Move.Left;
        }

        Move next = board.getRound() % 2 == 1 ?
            Move.Left:
            Move.Down;

        if (board.isMoveValid(next)) {
            return next;
        }

        return board.isMoveValid(Move.Right) ?
            Move.Right:
            Move.Up;
    }

    @Override
    public void reset() {}
}