import java.io.IOException;
import java.util.Scanner;

public class TicTacToe 
{
    static int[][] gameBoard = new int[3][3];

    public static void main(String[] args)
    {        
        // welcome message here + copyright


        System.out.println("Welcome to Tic-Tac-Toe 2024! Please type 'play' to start.");
        System.out.println( "If you wish to quit the game, type 'exit'.");

        Scanner scan = new Scanner(System.in);

        String decision = scan.nextLine(); 
        decision = decision.toUpperCase();
        
        if(decision.equals("PLAY"))
        {
            initializeGameBoard();
            displayGameBoard();
        }
        else if(decision.equals("PLAY")) 
        {
            System.exit(0);
        }
        else 
        {
            System.out.println("Invalid Input!");
        }
    }

    static void initializeGameBoard() 
    {
        for(int i = 0; i < 3; i++) 
        {
            for(int j = 0; j < 3; j++) 
            {
                gameBoard[i][j] = 0;
            }
        }
    }

    static void displayGameBoard() 
    {
        clearConsole();
        System.out.println();

        for(int i = 0; i < 3; i++) 
        {
            for(int j = 0; j < 3; j++) 
            {
                System.out.print(gameBoard[i][j] + "\t\t\t\t\t");
            }
            
            System.out.println("\n\n\n");
        }
    }

    static boolean isGameOver() 
    {
        return true;
    }

    static void updateGameState() 
    {
        // clear screen

        // change selected slot value to either x or o depending on which player's turn it is.

        // display board again
    }

    static void displayMessage(String message) 
    {
        System.out.println(message);
    }

    public static void clearConsole() 
    {
        try 
        {
            if (System.getProperty("os.name").contains("Windows")) 
            {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            else 
            {
                System.out.print("\033\143");
            }
        } 
        catch (IOException | InterruptedException ex) 
        {

        }
    }
}
