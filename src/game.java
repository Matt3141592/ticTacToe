import java.util.*;

public class game {
    public static void main(String[] args) throws InterruptedException {
        board a = new board();
        Random rand = new Random();
        Scanner sc = new Scanner(System.in);

        int player, computer;
        int tomove = 0;

        a.print();

        if (rand.nextBoolean()) {
            System.out.println("The computer is X and they are starting");
            Thread.sleep(1500);
            a.move("b2", 1);
            computer = 1;
            player = 2;
        } else {
            while (true) {
                player = 1;
                computer = 2;
                tomove = 1;
                System.out.println("You are X and you are starting. Please select a move.");
                String in = sc.nextLine();
                if (a.move(in, player))
                    break;
            }
        }

        a.print();
        for (int i = 1; i < 9; i++) {
            if (tomove == 0) {
                while (true) {
                    System.out.println("Player's turn to move.");
                    String in = sc.nextLine();
                    if (a.move(in, player))
                        break;
                }
            } else {
                System.out.println("The computer is moving");
                Thread.sleep(1500);
                a.aicomp(computer);
            }

            a.print();
            tomove = tomove ^ 1;
            int x = a.gameover(player);

            if (x > 0) {
                if (x == player) {
                    System.out.println("The player wins");
                    System.exit(0);
                }
                System.out.println("The computer wins");
                System.exit(0);
            }
        }
        System.out.println("No one wins");
    }

}
