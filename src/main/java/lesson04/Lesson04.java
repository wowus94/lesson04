package lesson04;

import java.util.Random;
import java.util.Scanner;

public class Lesson04 {
    static final int SIZE = 5;
    static final char DOT_EMPTY = '•';
    static final char DOT_X = 'X';
    static final char DOT_O = 'O';
    static char[][] field;
    static final Scanner sc = new Scanner(System.in);
    static final Random rand = new Random();

    public static void main(String[] args) {
        initField();
        printField();
        while (true) {
            humanTurn();
            printField();
            if (checkWin(DOT_X)) {
                System.out.println("Победил человек");
                break;
            }
            aiTurn();
            printField();
            if (checkWin(DOT_O)) {
                System.out.println("Победил компьютер");
                break;
            }
            if (isFieldFull()) {
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Игра окончена");

    }

    static boolean checkWin(char symbol) {

        for (int i = 0; i < SIZE; i++) {
            boolean vertical = true, horizontal = true;
            for (int j = 0; j < SIZE; j++) {
                vertical = vertical & (field[j][i]) == symbol;
                horizontal = horizontal & (field[i][j]) == symbol;
            }
            if (vertical || horizontal) return true;

        }
        boolean toright = true, toleft = true;
        for (int i = 0; i < SIZE; i++) {

            toright = toright & (field[i][i] == symbol);
            toleft = toleft & (field[SIZE - i - 1][i] == symbol);
        }

        if (toright || toleft) return true;

        return false;
    }


    static boolean isFieldFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (field[i][j] == DOT_EMPTY)
                    return false;
            }
        }

        return true;
    }

    static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты в формате X Y через пробел.");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (isCellValid(x, y));
        field[y][x] = DOT_X;
    }

    static void aiTurn() {
        int x, y;
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (isCellValid(x, y));
        System.out.println("Компьютер выбрал точку " + (x + 1) + " " + (y + 1));
        field[y][x] = DOT_O;
    }

    static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return true;
        if (field[y][x] == DOT_EMPTY) return false;
        return true;
    }

    static void initField() {
        field = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                field[i][j] = DOT_EMPTY;
            }
        }
    }

    static void printField() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }

        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}