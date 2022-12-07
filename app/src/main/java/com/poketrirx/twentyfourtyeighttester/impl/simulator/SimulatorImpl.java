package com.poketrirx.twentyfourtyeighttester.impl.simulator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import com.poketrirx.twentyfourtyeighttester.pub.models.Board;
import com.poketrirx.twentyfourtyeighttester.pub.models.ImmutableMatrix;
import com.poketrirx.twentyfourtyeighttester.pub.models.Move;
import com.poketrirx.twentyfourtyeighttester.pub.models.Space;
import com.poketrirx.twentyfourtyeighttester.pub.models.Summary;
import com.poketrirx.twentyfourtyeighttester.pub.simulation.GameLogic;
import com.poketrirx.twentyfourtyeighttester.pub.simulation.Simulator;
import com.poketrirx.twentyfourtyeighttester.pub.strategy.Strategy;

public class SimulatorImpl implements Simulator {
    private static final Space EMPTY_SPACE =
        Space.builder().value(0).build();

    private final GameLogic gameLogic;

    public SimulatorImpl(GameLogic gameLogic) {
        this.gameLogic = Objects.requireNonNull(gameLogic);
    }

    @Override
    public Summary simulate(Strategy strategy, int size) {
        Board board = runGame(strategy, size);

        return Summary.builder()
            .maxBlockSize(board.getCurrentLargestBlock())
            .maxScore(board.getCurrentScore())
            .totalRounds(board.getRound())
            .build();
    }

    private Board runGame(Strategy strategy, int size) {
        strategy.reset();

        Board board = Board.builder()
            .values(getInitialBoardForSize(size))
            .size(size)
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

    private ImmutableMatrix<Space> getInitialBoardForSize(int size) {
        List<List<Space>> board = new ArrayList<List<Space>>(size);

        for (int x = 0; x < size; x++) {
            List<Space> row = new ArrayList<Space>(size);

            for (int y = 0; y < size; y++) {
                row.add(EMPTY_SPACE);
            }

            board.add(row);
        }

        Random rand = new Random();

        int x = rand.nextInt(size);
        int y = rand.nextInt(size);

        board.get(x).set(y, Space.builder().value(2).build());

        return new ImmutableMatrix<Space>(board);
    }
}
