import java.io.IOException;
import java.util.Scanner;

public class TicTacToe 
{
    static String[][] gameBoard = new String[3][3];
    static boolean isPlayer1Turn = true;
    static String playersCurrentLetter;
    static int player1Score = 0;
    static int player2Score = 0;

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
            updateGameState();
            scan.close();
        }
        else if(decision.equals("EXIT")) 
        {
            scan.close();
            System.exit(0);            
        }
        else 
        {
            System.out.println("Invalid Input!");
            scan.close();
            System.exit(0);  // TO-DO: add exception handling          
        }

        scan.close();
    }

    static void initializeGameBoard() 
    {
        int z = 1;
        for(int i = 0; i < 3; i++) 
        {
            for(int j = 0; j < 3; j++) 
            {
                gameBoard[i][j] = Integer.toString(z);
                z += 1;
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
                System.out.print(gameBoard[i][j] + " \t\t | \t\t");
            }
            
            System.out.println("\n");
            System.out.println("----------------------------------------------------------------------------------"); // find more efficient method to implement this
            System.out.println("\n");
        }
    }

    static boolean gameIsOver() 
    {
        // if 3 in a row, col, or diagonal of the same character then game over and print game winner
        
        // diagonal check
        if(gameBoard[0][0] == playersCurrentLetter && (gameBoard[0][0].equals(gameBoard[1][1]) && gameBoard[0][0].equals(gameBoard[2][2])))
        {
            return true;    
        }
        // anti-diagonal check
        if(gameBoard[0][2] == playersCurrentLetter && (gameBoard[0][2].equals(gameBoard[1][1]) && gameBoard[0][2].equals(gameBoard[2][0])))
        {
            return true;
        }

        // row check
        for(int row = 0; row < 3; row++) 
        {
            if(gameBoard[row][0] == gameBoard[row][1] && gameBoard[row][1] == gameBoard[row][2]) 
            {
                return true;
            }
        }

        // column check
        for(int col = 0; col < 3; col++) 
        {
            if(gameBoard[0][col] == gameBoard[1][col] && gameBoard[1][col] == gameBoard[2][col]) 
            {
                return true;
            }
        }

        return false;
    }

    static void updateGameState() 
    {
        // clear screen
        clearConsole();
        // display the board
        displayGameBoard();

        playerTurn();

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

    static void playerTurn() 
    {
        displayMessage("Select a number 1-9");
        Scanner scan = new Scanner(System.in);
        int playerChoice = scan.nextInt();
        
        if(isPlayer1Turn) 
        {
            playersCurrentLetter = "X";
        }
        else if(isPlayer1Turn == false) // when player 2 turn 
        {
            playersCurrentLetter = "O";
        }

        switch(playerChoice) 
        {
            case 1:
                gameBoard[0][0] = playersCurrentLetter;
                break;
            case 2:
                gameBoard[0][1] = playersCurrentLetter;
                break;
            case 3:
                gameBoard[0][2] = playersCurrentLetter;
                break;
            case 4:
                gameBoard[1][0] = playersCurrentLetter;
                break;
            case 5:
                gameBoard[1][1] = playersCurrentLetter;
                break;
            case 6:
                gameBoard[1][2] = playersCurrentLetter;
                break;
            case 7:
                gameBoard[2][0] = playersCurrentLetter;
                break;
            case 8:
                gameBoard[2][1] = playersCurrentLetter;
                break;
            case 9:
                gameBoard[2][2] = playersCurrentLetter;
                break;
        }

        if(gameIsOver()) 
        {             
            if(isPlayer1Turn) 
            {
                clearConsole();
                displayGameBoard();

                displayMessage("Player 1 Wins!");

                // add one point for player 1
                player1Score += 1;
                displayMessage("Player 1's Score is: " + Integer.toString(player1Score));
                System.exit(0);
            }
            else 
            {
                clearConsole();
                displayGameBoard();

                displayMessage("Player 2 Wins!");

                // add one point for player 1
                player2Score += 1;
                displayMessage("Player 2's Score is: " + Integer.toString(player2Score));
                System.exit(0);
            }
        }

        isPlayer1Turn = !isPlayer1Turn;

        updateGameState();
    }
}
