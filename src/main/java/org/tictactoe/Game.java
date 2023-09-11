package org.tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class Game {

    private Field field;
    private GameMode gameMode;
    private Player firstPlayer;
    private Player secondPlayer;

    private BufferedReader console;
    public Game() {
        try {
            initGame();
        } catch (IOException e) {
            try {
                console.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    private void initGame() throws IOException{
        this.console = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Choose a game mode:");
        System.out.println("1. One player");
        System.out.println("2. Two players");
        System.out.print("Input mode: ");
        GameMode mode = GameMode.getModeByIndex(Integer.parseInt(console.readLine()));

        System.out.print("Input field size: ");

        int filedSize = Integer.parseInt(console.readLine());

        this.field = new Field(filedSize);

        switch (mode) {
            case ONE_PLAYER -> initModeOnePlayer(field, console);
            case TWO_PLAYER -> initModeTwoPlayer(field, console);
        }
    }

    private void initModeOnePlayer(Field field, BufferedReader console) throws IOException{
        System.out.print("Input your name: ");
        String playerName = console.readLine();

        System.out.println("Choose your symbol: ");
        System.out.println("1. X");
        System.out.println("2. 0");
        System.out.print("Input symbol: ");
        Field.Symbol symbol = Field.Symbol.getSymbolByIndex(Integer.parseInt(console.readLine()));

        firstPlayer = new Human(playerName, field, symbol);

        Field.Symbol computerSymbol = firstPlayer.getSymbol() == Field.Symbol.X ? Field.Symbol.O : Field.Symbol.X;
        secondPlayer = new Computer("Computer", field, computerSymbol);
    }

    private void initModeTwoPlayer(Field field, BufferedReader console) throws IOException{
        System.out.print("Input first player name: ");
        String firstPlayerName = console.readLine();

        System.out.println("Choose your symbol: ");
        System.out.println("1. X");
        System.out.println("2. 0");
        System.out.print("Input symbol: ");
        Field.Symbol firstPlayerSymbol = Field.Symbol.
                getSymbolByIndex(Integer.parseInt(console.readLine()));

        firstPlayer = new Human(firstPlayerName, field, firstPlayerSymbol);

        Field.Symbol secondPlayerSymbol = firstPlayer.getSymbol() == Field.Symbol.X ?
                Field.Symbol.O : Field.Symbol.X;

        System.out.print("Input second player name: ");
        String secondPlayerName = console.readLine();
        secondPlayer = new Human(secondPlayerName, field, secondPlayerSymbol);
    }

    public void startGame() {
        Player currentPlayer = null;

        do {
            field.printField();
            currentPlayer = currentPlayer == secondPlayer  || currentPlayer == null ?
                    firstPlayer : secondPlayer;

            try {
                System.out.printf("%s, choose cell: ", currentPlayer.getPlayerName());
                currentPlayer.writeCell(console);
            } catch (IOException e) {
                try {
                    console.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        } while (!field.isWinnerExists() && field.getFreeCellsCount() > 0);

        field.printField();

        if (field.isWinnerExists()) {
            System.out.printf("%s is winner!", currentPlayer.getPlayerName());
        } else {
            System.out.println("Friendly won!");
        }
    }

    public enum GameMode {
        ONE_PLAYER(1),
        TWO_PLAYER(2);

        final int index;

        GameMode(int index) {
            this.index = index;
        }

        public static GameMode getModeByIndex(int idx) {
            switch (idx) {
                case 1 -> {
                    return ONE_PLAYER;
                }
                case 2 -> {
                    return TWO_PLAYER;
                }
            }
            return ONE_PLAYER;
        }
    }
}
