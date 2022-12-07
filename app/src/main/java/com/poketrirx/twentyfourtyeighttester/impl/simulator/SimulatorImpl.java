package com.poketrirx.twentyfourtyeighttester.impl.simulator;

import java.util.List;

import com.poketrirx.twentyfourtyeighttester.pub.models.Board;
import com.poketrirx.twentyfourtyeighttester.pub.models.ImmutableMatrix;
import com.poketrirx.twentyfourtyeighttester.pub.models.Move;
import com.poketrirx.twentyfourtyeighttester.pub.models.Space;
import com.poketrirx.twentyfourtyeighttester.pub.models.Summary;
import com.poketrirx.twentyfourtyeighttester.pub.simulation.Simulator;
import com.poketrirx.twentyfourtyeighttester.pub.strategy.Strategy;

public class SimulatorImpl implements Simulator {
    private static final ImmutableMatrix<Space> EMPTY_BOARD =
        new ImmutableMatrix<Space>(
            List.of(
                List.of(
                    Space.builder().build(),
                    Space.builder().build(),
                    Space.builder().build(),
                    Space.builder().build()
                ),
                List.of(
                    Space.builder().build(),
                    Space.builder().build(),
                    Space.builder().build(),
                    Space.builder().build()
                ),
                List.of(
                    Space.builder().build(),
                    Space.builder().build(),
                    Space.builder().build(),
                    Space.builder().build()
                ),
                List.of(
                    Space.builder().build(),
                    Space.builder().value(2).build(),
                    Space.builder().build(),
                    Space.builder().build()
                )
            )
        );

    @Override
    public Summary simulate(Strategy strategy) {
        Board board = runGame(strategy);

        return Summary.builder()
            .maxBlockSize(board.getCurrentLargestBlock())
            .maxScore(board.getCurrentScore())
            .build();
    }

    private Board runGame(Strategy strategy) {
        strategy.reset();

        Board board = Board.builder()
            .values(EMPTY_BOARD)
            .build();

        while (!board.isGameOver()) {
            Move move = strategy.next(board);

            if (!board.isMoveValid(move)) {
                break;
            }

            board = board.getBoardForNextMove(move);
        }

        return board;
    }
}
