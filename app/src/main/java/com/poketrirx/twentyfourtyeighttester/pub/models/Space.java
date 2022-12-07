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
public class Space {
    @Getter
    @Builder.Default
    private final long value = 0;

    public boolean isEmpty() {
        return value == 0;
    }
}