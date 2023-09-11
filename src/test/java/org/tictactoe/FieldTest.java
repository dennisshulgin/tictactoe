package org.tictactoe;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class FieldTest {
    private static int size;
    private static Field field;

    @BeforeClass
    public static void setFiled() {
        size = 3;
    }

    @Before
    public void newField() {
        field = new Field(size);
    }

    @Test
    public void setCellCorrect() {
        field.setCell(1, Field.Symbol.O);
        assertEquals(field.getCell(1), Field.Symbol.O);
    }

    @Test
    public void setCellIncorrect() {
        assertThrows(IllegalStateException.class, () -> field.setCell(1, null));
    }

    @Test
    public void printFieldTest() {
        field.printField();
    }

    @Test
    public void testWinRow() {
        field.setCell(7, Field.Symbol.X);
        field.setCell(8, Field.Symbol.X);
        field.setCell(9, Field.Symbol.X);
        assertTrue(field.isWinnerExists());
    }

    @Test
    public void testNoWinRow() {
        field.setCell(7, Field.Symbol.X);
        field.setCell(8, Field.Symbol.X);
        assertFalse(field.isWinnerExists());
    }

    @Test
    public void testWinColumn() {
        field.setCell(1, Field.Symbol.X);
        field.setCell(4, Field.Symbol.X);
        field.setCell(7, Field.Symbol.X);
        assertTrue(field.isWinnerExists());
    }

    @Test
    public void testNoWinColumn() {
        field.setCell(4, Field.Symbol.X);
        field.setCell(7, Field.Symbol.X);
        assertFalse(field.isWinnerExists());
    }

    @Test
    public void testWinLeftDiag() {
        field.setCell(1, Field.Symbol.X);
        field.setCell(5, Field.Symbol.X);
        field.setCell(9, Field.Symbol.X);
        assertTrue(field.isWinnerExists());
    }

    @Test
    public void testNoWinLeftDiag() {
        field.setCell(1, Field.Symbol.X);
        field.setCell(9, Field.Symbol.X);
        assertFalse(field.isWinnerExists());
    }

    @Test
    public void testWinRightDiag() {
        field.setCell(3, Field.Symbol.X);
        field.setCell(5, Field.Symbol.X);
        field.setCell(7, Field.Symbol.X);
        assertTrue(field.isWinnerExists());
    }

    @Test
    public void testNoWinRightDiag() {
        field.setCell(3, Field.Symbol.X);
        field.setCell(5, Field.Symbol.X);
        assertFalse(field.isWinnerExists());
    }

    @Test
    public void testWinEmptyField() {
        assertFalse(field.isWinnerExists());
    }

    @Test
    public void testInputFieldSizeLess3() {
        Assert.assertThrows(IllegalStateException.class, () -> new Field(2));
    }

}