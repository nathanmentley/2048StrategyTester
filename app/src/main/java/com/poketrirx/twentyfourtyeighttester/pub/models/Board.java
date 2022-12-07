package com.poketrirx.twentyfourtyeighttester.pub.models;

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
}