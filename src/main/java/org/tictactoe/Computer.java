package org.tictactoe;

import java.io.BufferedReader;
import java.io.IOException;

public final class Computer extends Player {
    public Computer(String playerName, Field field, Field.Symbol symbol) {
        super(playerName, field, symbol);
    }

    @Override
    public int selectCell(BufferedReader console) throws IOException {
        return -1;
    }
}
