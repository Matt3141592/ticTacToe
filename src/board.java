import java.util.*;

public class board {
    private int[][] table = new int[3][3];

    public boolean move(String pos, int player) {
        if (pos.length() != 2)
            return false;

        int x, y;

        switch (Character.toLowerCase(pos.charAt(0))) {
            case 'a':
                x = 0;
                break;
            case 'b':
                x = 1;
                break;
            case 'c':
                x = 2;
                break;
            default:
                return false;
        }
        try {
            y = Integer.parseInt(pos.substring(1, 2));
        } catch (NumberFormatException nfe) {
            return false;
        }
        y--;

        if (table[y][x] != 0) {
            return false;
        } else {
            table[y][x] = player;
        }
        return true;
    }

    public void print() {

        System.out.println("   A   B   C");
        for (int i = 0; i < 3; i++) {
            System.out.printf("%d  ", (i + 1));
            for (int j = 0; j < 3; j++) {
                System.out.printf("%c", board.xo(table[i][j]));
                if (j != 2)
                    System.out.printf(" | ");
            }
            if (i != 2) {
                System.out.println();
                System.out.println("   ---------");
            }
        }
        System.out.println();
    }

    public static char xo(int i) {
        if (i == 0)
            return ' ';
        else if (i == 1)
            return 'X';
        return 'O';
    }

    public void comp(int player) {
        Random rand = new Random();

        while (true) {
            int x = rand.nextInt(3);
            int y = rand.nextInt(3);
            if (table[x][y] == 0) {
                table[x][y] = player;
                return;
            }
        }

    }

    public int gameover(int player) {
        if (table[0][0] != 0) {
            if (table[0][0] == table[0][1] && table[0][0] == table[0][2]) {
                if (player == table[0][0]) {
                    return player;
                }
                return 3;
            }

            if (table[0][0] == table[1][0] && table[0][0] == table[2][0]) {
                if (player == table[0][0]) {
                    return player;
                }
                return 3;
            }

            if (table[0][0] == table[1][1] && table[0][0] == table[2][2]) {
                if (player == table[0][0]) {
                    return player;
                }
                return 3;
            }
        }

        if (table[2][0] != 0) {
            if (table[2][0] == table[2][1] && table[2][0] == table[2][2]) {
                if (player == table[2][0]) {
                    return player;
                }
                return 3;
            }

            if (table[2][0] == table[1][1] && table[2][0] == table[0][2]) {
                if (player == table[2][0]) {
                    return player;
                }
                return 3;
            }
        }
        if (table[1][1] != 0) {
            if (table[1][0] == table[1][1] && table[1][1] == table[1][2]) {
                if (player == table[1][0]) {
                    return player;
                }
                return 3;
            }

            if (table[0][1] == table[1][1] && table[1][1] == table[2][1]) {
                if (player == table[1][0]) {
                    return player;
                }
                return 3;
            }
        }

        if (table[0][2] != 0) {
            if (table[0][2] == table[1][2] && table[0][2] == table[2][2]) {
                if (player == table[0][2]) {
                    return player;
                }
                return 3;
            }
        }
        return 0;
    }

    public void aicomp(int player) {
        int other = ((player - 1) ^ 1) + 1;

        if (win(other, player)) return;
        if (block(other, player)) return;

        if (table[1][1] == 0) {
            table[1][1] = player;
            return;
        }
        if (corner(player)) return;
        comp(player);
    }

    public boolean block(int player, int computer) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (table[i][j] == 0) {
                    table[i][j] = player;
                    if (gameover(player) != 0) {
                        table[i][j] = computer;
                        return true;
                    }
                    table[i][j] = 0;
                }
            }
        }
        return false;
    }

    public boolean win(int player, int computer) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (table[i][j] == 0) {
                    table[i][j] = computer;
                    if (gameover(player) != 0) {
                        return true;
                    }
                    table[i][j] = 0;
                }
            }
        }
        return false;
    }

    public boolean corner(int player) {
        for (int i = 0; i < 3; i += 2)
            for (int j = 0; j < 3; j += 2)
                if (table[i][j] == 0) {
                    table[i][j] = player;
                    return true;
                }
        return false;
    }
}
