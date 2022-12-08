package com.poketrirx.twentyfourtyeighttester.impl.simulator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.poketrirx.twentyfourtyeighttester.pub.models.Board;
import com.poketrirx.twentyfourtyeighttester.pub.models.ImmutableMatrix;
import com.poketrirx.twentyfourtyeighttester.pub.models.Move;
import com.poketrirx.twentyfourtyeighttester.pub.models.Space;
import com.poketrirx.twentyfourtyeighttester.pub.simulation.GameLogic;

 final class GameLogicImpl implements GameLogic {
    private static final Space EMPTY_SPACE =
        Space.builder()
            .value(0)
            .build();

    public boolean isMoveValid(Board board, Move move) {
        Board moved = getBoardForNextMove(board, move);

        return moved.getCurrentScore() != board.getCurrentScore();
    }

    public boolean isGameOver(Board board) {
        return !isMoveValid(board, Move.Down) &&
            !isMoveValid(board, Move.Up) &&
            !isMoveValid(board, Move.Left) &&
            !isMoveValid(board, Move.Right);
    }

    public Board getBoardForNextMove(Board board, Move move) {
        return board.toBuilder()
            .values(getValues(board, move))
            .round(board.getRound() + 1)
            .build();
    }

    private ImmutableMatrix<Space> getValues(Board board, Move move) {
        switch(move) {
            case Left:
                return moveLeft(board);
            case Up:
                return moveUp(board);
            case Right:
                return moveRight(board);
            case Down:
                return moveDown(board);
            default:
                return moveDown(board);
        }
    }

    private ImmutableMatrix<Space> moveLeft(Board board) {
        ImmutableMatrix<Space> rotatedBoard = board.getValues().rotateRight().rotateRight().rotateRight();

        ImmutableMatrix<Space> movedBoard =  moveValues(board, rotatedBoard);

        return movedBoard.rotateRight();
    }

    private ImmutableMatrix<Space> moveRight(Board board) {
        ImmutableMatrix<Space> rotatedBoard = board.getValues().rotateRight();

        ImmutableMatrix<Space> movedBoard =  moveValues(board, rotatedBoard);
        
        return movedBoard.rotateRight().rotateRight().rotateRight();
    }

    private ImmutableMatrix<Space> moveUp(Board board) {
        ImmutableMatrix<Space> rotatedBoard = board.getValues().rotateRight().rotateRight();

        ImmutableMatrix<Space> movedBoard =  moveValues(board, rotatedBoard);
        
        return movedBoard.rotateRight().rotateRight();
    }

    private ImmutableMatrix<Space> moveDown(Board board) {
        return moveValues(board, board.getValues());
    }

    private ImmutableMatrix<Space> moveValues(Board board, ImmutableMatrix<Space> values) {
        List<List<Space>> newValues = values.toMutable();

        //Move all items down
        for (int x = 0; x < board.getSize(); x++)
        for(int y = board.getSize() - 1; y >= 0; y--) {
            if (newValues.get(x).get(y).getValue() == 0) {
                Collections.swap(newValues.get(x), 0, y);
            }
        }

        //Merge an tiles that need merging.
        for (int x = 0; x < board.getSize(); x++)
        for(int y = board.getSize() - 1; y > 0; y--) {
            int target = y - 1;

            if (newValues.get(x).get(y).getValue() == newValues.get(x).get(target).getValue()) {
                newValues.get(x).remove(y);
                newValues.get(x).add(y, Space.builder().value(newValues.get(x).get(target).getValue() * 2).build());
                newValues.get(x).remove(target);
                newValues.get(x).add(0, EMPTY_SPACE);
            }
        }

        //Populate new tiles
        List<Integer> newTileOptions = new ArrayList<Integer>();
        for (int x = 0; x < board.getSize(); x++) {
            if (newValues.get(x).get(0).getValue() == 0) {
                newTileOptions.add(x);
            }
        }
        if (!newTileOptions.isEmpty()) {
            Random rand = new Random();
            int randomElementIndex = rand.nextInt(newTileOptions.size());

            newValues.get(randomElementIndex).remove(0);
            newValues.get(randomElementIndex).add(0, Space.builder().value(rand.nextInt(1) % 2 == 0 ? 2 : 4).build());
        }

        //Move all items down
        for (int x = 0; x < board.getSize(); x++)
        for(int y = board.getSize() - 1; y >= 0; y--) {
            if (newValues.get(x).get(y).getValue() == 0) {
                Collections.swap(newValues.get(x), 0, y);
            }
        }

        return new ImmutableMatrix<Space>(newValues);
    }
}
