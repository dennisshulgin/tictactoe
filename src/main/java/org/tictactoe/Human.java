package org.tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class Human extends Player {
    public Human(String playerName, Field field, Field.Symbol symbol) {
        super(playerName, field, symbol);
    }

    @Override
    public int selectCell(BufferedReader console) throws IOException{
        return Integer.parseInt(console.readLine());
    }
}
