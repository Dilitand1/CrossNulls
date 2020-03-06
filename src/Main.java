import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static final Object[][] DESK = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}};
    static List<List<Integer>> victory = new ArrayList<>();

    static {
        victory.add(Arrays.asList(0, 1, 2));
        victory.add(Arrays.asList(3, 4, 5));
        victory.add(Arrays.asList(6, 7, 8));
        victory.add(Arrays.asList(0, 3, 6));
        victory.add(Arrays.asList(1, 4, 7));
        victory.add(Arrays.asList(2, 5, 8));
        victory.add(Arrays.asList(0, 4, 8));
        victory.add(Arrays.asList(2, 4, 6));
    }

    public static void main(String[] args) {
        Player player1 = new Player(TypePlayer.X);
        Player player2 = new Player(TypePlayer.O);
        int tmp = 0;

        while (true) {
            printArray();
            if (tmp % 2 == 0) {
                makeMove(player1);
            } else {
                makeMove(player2);
            }
            tmp++;
        }
    }

    static void makeMove(Player player) {
        System.out.println("Введите номер ячейки, ходит игрок: " + player);
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            int tmp = scanner.nextInt();
            if (!place(player, tmp)) {
                System.out.println("Введите свободную ячейку в диапазоне");
                makeMove(player);
            }
        } else {
            System.out.println("Введите номер ячейки в числовом формате");
            makeMove(player);
        }
        endGame(player);
    }

    static boolean place(Player player, int x) {
        for (int i = 0; i < DESK.length; i++) {
            for (int j = 0; j < DESK[i].length; j++) {
                if (DESK[i][j] instanceof Player) {
                    continue;
                }
                if ((int) DESK[i][j] == x) {
                    DESK[i][j] = player;
                    return true;
                }
            }
        }
        return false;
    }

    static void printArray() {
        for (Object[] i : DESK) {
            for (Object j : i) {
                System.out.print("[" + j.toString() + "]" + " ");
            }
            System.out.println();
        }
    }

    static void endGame(Player player) {
        int x = 0;
        List<Integer> list = new ArrayList<>();

        for (Object[] i : DESK) {
            for (Object j : i) {
                if (j.toString().equals(player.toString()))
                    list.add(x);
                x++;
            }
        }

        for (List list1 : victory) {
            if (list.containsAll(list1)) {
                System.out.println("Game over!"+ " Победил игрок " + player.toString());
                printArray();

                System.exit(0);
            }
        }
    }
}

enum TypePlayer {
    X, O;

    @Override
    public String toString() {
        return this.name();
    }
}

class Player {
    TypePlayer p;

    Player(TypePlayer p) {
        this.p = p;
    }

    @Override
    public String toString() {
        return p.name();
    }
}