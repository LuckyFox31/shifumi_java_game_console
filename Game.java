import java.util.Random;
import java.util.Scanner;

public class Game {
    // -- Libraries --

    private static Scanner scanner = new Scanner(System.in);

    private static Random random = new Random();


    // -- Attributes --

    private String[] symbolsArray = {
            "Pierre", "Feuille", "Ciseaux"
    };

    private String askSentence = "[1] - Pierre\n[2] - Feuille\n[3] - Ciseaux";


    // -- Init --

    public static void main(String[] args) {
        Game game = new Game();

        System.out.println("Jeu du Shifumi");

        game.start();
    }


    // -- Methods --

    protected void start(){
        boolean isDraw = false;
        boolean isWin = false;

        do {
            String userInput = getUserInput(askSentence, new String[]{"1", "2", "3"});
            String userPlay = symbolsArray[Integer.parseInt(userInput) - 1];
            String aiPlay = symbolsArray[random.nextInt(symbolsArray.length)];

            System.out.println("Vous jouez "+ userPlay);
            System.out.println("L'IA joue "+ aiPlay);

            isDraw = aiPlay == userPlay;

            if (isDraw){
                System.out.println("Égalité !");
                continue;
            }

            switch (userPlay){
                case "Pierre":
                    isWin = aiPlay == "Ciseaux";
                    break;

                case "Feuille":
                    isWin = aiPlay == "Pierre";
                    break;

                case "Ciseaux":
                    isWin = aiPlay == "Feuille";
                    break;
            }

            if(isWin){
                System.out.println("Vous avez gagné !");
            } else {
                System.out.println("Vous avez perdu !");
            }
        } while (isDraw);
    }


    // -- Utils --

    public static String getUserInput(String message, String[] needToBeEqual){
        boolean restart = false;
        String userInput;

        do{
            System.out.println(message+"\n");

            userInput = scanner.nextLine();
            System.out.print("\u001b[2A\u001b[0J");

            for(String symbol: needToBeEqual){
                restart = !userInput.equals(symbol);

                if(!restart) break;
            }
        } while(restart);

        return userInput;
    }
}
