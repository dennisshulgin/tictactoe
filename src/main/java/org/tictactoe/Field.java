package org.tictactoe;

import java.util.*;

public final class Field {

    private final Symbol[][] field;
    private final int size;
    private final Map<Integer, int[]> coordinates;

    private final Set<Integer> freeCoordinates;

    private int freeCellsCount;

    public Field(int size) {
        this.size = size;
        validateFieldSize(size);
        this.field = new Symbol[size][size];
        this.coordinates = new HashMap<>(size * size);
        this.freeCoordinates = new HashSet<>(size * size);
        initField();
    }

    private void initField() {
        for(int i = 0; i < this.size; i++) {
            for(int j = 0; j < this.size; j++) {
                field[i][j] = Symbol.N;
                coordinates.put(i * size + j + 1, new int[] {i, j});
                freeCoordinates.add(i * size + j + 1);
            }
        }
        freeCellsCount = this.size * this.size;
    }

    public void setCell(int number, Symbol symbol) {
        if (symbol == null) {
            throw new IllegalStateException("The symbol should not be null");
        }

        int[] coordinate = coordinates.get(number);
        int x = coordinate[0];
        int y = coordinate[1];

        if (this.field[x][y] != Symbol.N) {
            throw new IllegalStateException("The current cell is already filled");
        }
        this.freeCellsCount--;
        this.freeCoordinates.remove(number);
        this.field[x][y] = symbol;
    }

    public Symbol getCell(int number) {
        int[] coordinate = coordinates.get(number);
        int x = coordinate[0];
        int y = coordinate[1];
        return this.field[x][y];
    }

    public void clearCell(int number) {
        int[] coordinate = coordinates.get(number);
        int x = coordinate[0];
        int y = coordinate[1];
        this.freeCoordinates.add(number);
        this.freeCellsCount++;
        this.field[x][y] = Symbol.N;
    }

    public int getSize() {
        return this.size;
    }

    public int getFreeCellsCount() {
        return this.freeCellsCount;
    }

    public Collection<Integer> getFreeCoordinates() {
        return new HashSet<>(freeCoordinates);
    }

    public boolean isWinnerExists() {
        for (int i = 0; i < size; i++) {
            if (checkRow(i) || checkColumn(i)) {
                return true;
            }
        }
        return checkLeftDiag() || checkRightDiag();
    }

    private boolean checkRow(int row) {
        for (int i = 1; i < size; i++) {
            if (field[row][i] != field[row][i - 1]) {
                return false;
            }
        }
        return field[row][0] != Symbol.N;
    }

    private boolean checkColumn(int column) {
        for (int i = 1; i < size; i++) {
            if (field[i][column] != field[i - 1][column]) {
                return false;
            }
        }
        return field[0][column] != Symbol.N;
    }

    private boolean checkLeftDiag() {
        for (int i = 1; i < size; i++) {
            if (field[i][i] != field[i - 1][i - 1]) {
                return false;
            }
        }
        return field[0][0] != Symbol.N;
    }

    private boolean checkRightDiag() {
        int x = 1;
        int y = size - 2;

        for (int i = 1; i < size; i++) {
            if (field[x][y] != field[x - 1][y + 1]) {
                return false;
            }
            x++;
            y--;
        }
        return field[0][size - 1] != Symbol.N;
    }

    private void validateFieldSize(int size) {
        if (size < 3) {
            throw new IllegalStateException("Field size should be equals or greater 3");
        }
    }

    public void printField() {
        StringJoiner tireRow = new StringJoiner("|");
        for (int i = 0; i < this.size; i++) {
            tireRow.add("———");
        }

        StringJoiner full = new StringJoiner(String.format("\n%s\n", tireRow));

        for (int i = 0; i < this.size; i++) {
            StringJoiner row = new StringJoiner("|");

            for (int j = 0; j < this.size; j++) {
                row.add(String.format(" %s ", field[i][j] == Symbol.N ?
                        String.valueOf(i * size + j + 1) : field[i][j]));
            }

            full.add(row.toString());
        }
        System.out.println(full);
    }

    public enum Symbol {
        X,
        O,
        N;

        public static Symbol getSymbolByIndex(int idx) {
            switch (idx) {
                case 1 -> {
                    return X;
                }
                case 2 -> {
                    return O;
                }
            }
            return N;
        }
    }
}
