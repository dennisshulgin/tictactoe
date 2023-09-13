package org.tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collection;

public final class Computer extends Player {
    public Computer(String playerName, Field field, Field.Symbol symbol) {
        super(playerName, field, symbol);
    }

    @Override
    public int selectCell(BufferedReader console) throws IOException {
        int cell = computingCell();
        System.out.println(cell);
        return cell;
    }

    private int computingCell() {
        int max = Integer.MIN_VALUE;
        int res = -1;
        Collection<Integer> cells = field.getFreeCoordinates();
        for (int cell : cells) {
            field.setCell(cell, symbol);
            int r = recursive(symbol == Field.Symbol.X ? Field.Symbol.O : Field.Symbol.X);
            if (max < r) {
                res = cell;
                max = r;
            }
            field.clearCell(cell);
        }

        return res;
    }

    private int recursive(Field.Symbol currSymbol) {
        if (field.isWinnerExists()) {
            if (currSymbol != symbol) {
                return 1;
            } else {
                return -1;
            }
        }
        if (field.getFreeCellsCount() == 0) {
            return 0;
        }

        boolean isOurAct = currSymbol == symbol;

        int result = 0;

        if (isOurAct) {
            result = Integer.MIN_VALUE;

            for (int cell : field.getFreeCoordinates()) {
                field.setCell(cell, currSymbol);
                Field.Symbol userSymbol = currSymbol == Field.Symbol.X ? Field.Symbol.O : Field.Symbol.X;
                result = Math.max(result, recursive(userSymbol));
                field.clearCell(cell);
            }
        } else {
            result = Integer.MAX_VALUE;
            for (int cell : field.getFreeCoordinates()) {
                field.setCell(cell, currSymbol);
                Field.Symbol userSymbol = currSymbol == Field.Symbol.X ? Field.Symbol.O : Field.Symbol.X;
                result = Math.min(result, recursive(userSymbol));
                field.clearCell(cell);
            }
        }

        return result;
    }
}
