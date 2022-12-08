package com.poketrirx.twentyfourtyeighttester.pub.models;

import java.util.Objects;
import java.util.Optional;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class Board {
    private Optional<Long> currentLargestBlock = Optional.empty();
    private Optional<Long> currentScore = Optional.empty();

    @Getter
    private final int size;

    @Getter
    private final long round;

    @Getter
    private final ImmutableMatrix<Space> values;

    @Builder(toBuilder = true)
    protected Board(int size, long round, @NonNull ImmutableMatrix<Space> values) {
        this.size = size;
        this.round = round;
        this.values = Objects.requireNonNull(values);
    }

    public long getCurrentLargestBlock() {
        if (currentLargestBlock.isEmpty()) {
            long largestBlock = 0;

            for(int x = 0; x < size; x++) 
            for (int y = 0; y < size; y++) {
                if (values.get(x, y).getValue() > largestBlock) {
                    largestBlock = values.get(x, y).getValue(); 
                }
            }

            currentLargestBlock = Optional.of(largestBlock);
        }

        return currentLargestBlock.get();
    }

    public long getCurrentScore() {
        if (currentScore.isEmpty()) {
            long score = 0;

            for(int x = 0; x < size; x++)
            for (int y = 0; y < size; y++) {
                score += values.get(x, y).getValue(); 
            }

            currentScore = Optional.of(score);
        }

        return currentScore.get();
    }
}