package com.poketrirx.twentyfourtyeighttester.impl.simulator;

import java.util.List;
import java.util.Objects;

import com.poketrirx.twentyfourtyeighttester.pub.models.Board;
import com.poketrirx.twentyfourtyeighttester.pub.models.ImmutableMatrix;
import com.poketrirx.twentyfourtyeighttester.pub.models.Move;
import com.poketrirx.twentyfourtyeighttester.pub.models.Space;
import com.poketrirx.twentyfourtyeighttester.pub.models.Summary;
import com.poketrirx.twentyfourtyeighttester.pub.simulation.GameLogic;
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

    private final GameLogic gameLogic;

    public SimulatorImpl(GameLogic gameLogic) {
        this.gameLogic = Objects.requireNonNull(gameLogic);
    }

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

        while (!gameLogic.isGameOver(board)) {
            Move move = strategy.next(gameLogic, board);

            if (!gameLogic.isMoveValid(board, move)) {
                break;
            }

            board = gameLogic.getBoardForNextMove(board, move);
        }

        return board;
    }
}
