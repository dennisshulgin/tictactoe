package org.tictactoe;

import java.io.BufferedReader;
import java.io.IOException;

public abstract class Player {
    protected Field field;
    protected Field.Symbol symbol;

    protected String playerName;

    public Player(String playerName, Field field, Field.Symbol symbol) {
        this.field = field;
        this.symbol = symbol;
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public Field.Symbol getSymbol() {
        return symbol;
    }

    public void writeCell(BufferedReader console) throws IOException{
        int point = selectCell(console);
        field.setCell(point, symbol);
    }

    public abstract int selectCell(BufferedReader console) throws IOException;
}
