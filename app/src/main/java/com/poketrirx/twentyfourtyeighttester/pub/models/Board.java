package com.poketrirx.twentyfourtyeighttester.pub.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Builder(toBuilder = true)
@EqualsAndHashCode
@ToString
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {
    private static final Space EMPTY_SPACE =
        Space.builder()
            .value(0)
            .build();

    @Getter
    @Builder.Default
    private final int size = 4;

    @Getter
    @Builder.Default
    private final long round = 0;

    @NonNull
    @Getter
    private final ImmutableMatrix<Space> values;

    public long getCurrentLargestBlock() {
        long largestBlock = 0;

        for(int x = 0; x < size; x++) 
        for (int y = 0; y < size; y++) {
            if (values.get(x, y).getValue() > largestBlock) {
                largestBlock = values.get(x, y).getValue(); 
            }
        }

        return largestBlock;
    }

    public long getCurrentScore() {
        int score = 0;

        for(int x = 0; x < size; x++)
        for (int y = 0; y < size; y++) {
            score += values.get(x, y).getValue(); 
        }

        return score;
    }

    public boolean isMoveValid(Move move) {
        Board moved = getBoardForNextMove(move);

        return moved.getCurrentScore() != getCurrentScore();
    }

    public boolean isGameOver() {
        return !isMoveValid(Move.Down) &&
            !isMoveValid(Move.Up) &&
            !isMoveValid(Move.Left) &&
            !isMoveValid(Move.Right);
    }

    public Board getBoardForNextMove(Move move) {
        return toBuilder()
            .values(getValues(move, values))
            .round(round + 1)
            .build();
    }

    private ImmutableMatrix<Space> getValues(Move move, ImmutableMatrix<Space> values) {
        switch(move) {
            case Left:
                return moveLeft(values);
            case Up:
                return moveUp(values);
            case Right:
                return moveRight(values);
            case Down:
                return moveDown(values);
            default:
                return moveDown(values);
        }
    }

    private ImmutableMatrix<Space> moveLeft(ImmutableMatrix<Space> values) {
        ImmutableMatrix<Space> rotatedBoard = values.rotateRight().rotateRight().rotateRight();

        ImmutableMatrix<Space> movedBoard =  moveValues(rotatedBoard);

        return movedBoard.rotateRight();
    }

    private ImmutableMatrix<Space> moveRight(ImmutableMatrix<Space> values) {
        ImmutableMatrix<Space> rotatedBoard = values.rotateRight();

        ImmutableMatrix<Space> movedBoard =  moveValues(rotatedBoard);
        
        return movedBoard.rotateRight().rotateRight().rotateRight();
    }

    private ImmutableMatrix<Space> moveUp(ImmutableMatrix<Space> values) {
        ImmutableMatrix<Space> rotatedBoard = values.rotateRight().rotateRight();

        ImmutableMatrix<Space> movedBoard =  moveValues(rotatedBoard);
        
        return movedBoard.rotateRight().rotateRight();
    }

    private ImmutableMatrix<Space> moveDown(ImmutableMatrix<Space> values) {
        return moveValues(values);
    }

    private ImmutableMatrix<Space> moveValues(ImmutableMatrix<Space> values) {
        List<List<Space>> newValues = values.toMutable();

        //Move all items down
        for (int x = 0; x < size; x++)
        for(int y = size - 1; y >= 0; y--) {
            if (newValues.get(x).get(y).getValue() == 0) {
                Collections.swap(newValues.get(x), 0, y);
            }
        }

        //Merge an tiles that need merging.
        for (int x = 0; x < size; x++)
        for(int y = size - 1; y > 0; y--) {
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
        for (int x = 0; x < size; x++) {
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
        for (int x = 0; x < size; x++)
        for(int y = size - 1; y >= 0; y--) {
            if (newValues.get(x).get(y).getValue() == 0) {
                Collections.swap(newValues.get(x), 0, y);
            }
        }

        return new ImmutableMatrix<Space>(newValues);
    }
}