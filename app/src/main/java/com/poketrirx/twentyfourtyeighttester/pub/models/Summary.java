package com.poketrirx.twentyfourtyeighttester.pub.models;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder(toBuilder = true)
@EqualsAndHashCode
@ToString
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Summary {
    @Getter
    @Builder.Default
    private final double maxScore = 0;

    @Getter
    @Builder.Default
    private final double maxBlockSize = 0;

    @Getter
    @Builder.Default
    private final double totalRounds = 0;
}
