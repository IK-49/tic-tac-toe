import java.io.IOException;
import java.util.Scanner;

public class TicTacToe 
{
    public static void main(String[] args) 
    {
        System.out.println("------------------------------------------------------------------------------------------------");
        System.out.println("TicTacToe 2024 is the intellectual property of Aakash Basarkar and Izad Khokar. All rights reserved.\nThis includes but is not limited to the game design, codebase, graphics, and any associated media.\nNo part of this game may be reproduced, distributed, or transmitted in any form or by any means,\nelectronic or mechanical, without the prior written permission of the copyright holders.\nUnauthorized use or reproduction of TicTacToe 2024 may result in legal action.\nFor inquiries regarding licensing or permissions, please contact the copyright holders.");
        System.out.println("------------------------------------------------------------------------------------------------");
        System.out.println("Welcome to Tic-Tac-Toe 2024! Please type 'play' to start.");
        System.out.println("If you wish to quit the game, type 'exit'.");

        Scanner scan = new Scanner(System.in);
        String decision = scan.nextLine().toUpperCase();

        if (decision.equals("PLAY")) 
        {
            Game game = new Game();
            game.startGame();
        } 
        else if (decision.equals("EXIT")) 
        {
            System.exit(0);
        } 
        else 
        {
            System.out.println("Invalid Input!");
            System.exit(0);
        }
    }
}

class Game 
{
    private String[][] gameBoard = new String[3][3];
    private boolean isPlayer1Turn = true;
    private String playersCurrentLetter;
    private int player1Score = 0;
    private int player2Score = 0;

    public void startGame() 
    {
        initializeGameBoard();
        updateGameState();
    }

    private void initializeGameBoard() 
    {
        int z = 1;
        for (int i = 0; i < 3; i++) 
        {
            for (int j = 0; j < 3; j++) 
            {
                gameBoard[i][j] = Integer.toString(z);
                z += 1;
            }
        }
    }

    private void displayGameBoard() 
    {
        clearConsole();
        System.out.println("┌───┬───┬───┐");
        for (int i = 0; i < 3; i++) 
        {
            System.out.print("│ ");
            for (int j = 0; j < 3; j++) 
            {
                System.out.print(gameBoard[i][j] + " │ ");
            }
            System.out.println("\n├───┼───┼───┤");
        }
        System.out.println();
        System.out.println("Current score - Player 1: " + player1Score + " Player 2: " + player2Score);
    }

    private boolean gameIsOver() 
    {
        // Check for a winner
        if (checkForWinner(playersCurrentLetter)) 
        {
            return true;
        }

        // Check for a draw
        for (int i = 0; i < 3; i++) 
        {
            for (int j = 0; j < 3; j++) 
            {
                if (!gameBoard[i][j].equals("X") && !gameBoard[i][j].equals("O")) 
                {
                    return false; // Game is not over yet
                }
            }
        }
        return true; // It's a draw
    }

    private boolean checkForWinner(String player) 
    {
        // Diagonal check
        if (gameBoard[0][0].equals(player) && gameBoard[1][1].equals(player) && gameBoard[2][2].equals(player)) 
        {
            return true;
        }
        if (gameBoard[0][2].equals(player) && gameBoard[1][1].equals(player) && gameBoard[2][0].equals(player)) 
        {
            return true;
        }

        // Row and column check
        for (int i = 0; i < 3; i++) 
        {
            if ((gameBoard[i][0].equals(player) && gameBoard[i][1].equals(player) && gameBoard[i][2].equals(player)) || (gameBoard[0][i].equals(player) && gameBoard[1][i].equals(player) && gameBoard[2][i].equals(player))) 
            {
                return true;
            }
        }
        return false;
    }

    private void updateGameState() 
    {
        while (true) 
        {
            displayGameBoard();
            playerTurn();
            if (gameIsOver()) 
            {
                displayGameBoard();
                if (checkForWinner("X")) 
                {
                    player1Score++;
                    System.out.println("Player 1 wins! Player 1's score: " + player1Score);
                } 
                else if (checkForWinner("O")) 
                {
                    player2Score++;
                    System.out.println("Player 2 wins! Player 2's score: " + player2Score);
                } 
                else 
                {
                    System.out.println("It's a draw!");
                }
                if (player1Score >= 5 || player2Score >= 5) 
                {
                    if (player1Score >= 5) 
                    {
                        System.out.println("Player 1 wins the game!");
                    } 
                    else 
                    {
                        System.out.println("Player 2 wins the game!");
                    }
                    
                    break;
                }
                initializeGameBoard();
            }
        }
    }

    private void playerTurn() 
    {
        Scanner scan = new Scanner(System.in);
        int playerChoice;
        if (isPlayer1Turn) 
        {
            System.out.println("Player 1's turn (X). Choose a number (1-9): ");
            playersCurrentLetter = "X";
        } 
        else 
        {
            System.out.println("Player 2's turn (O). Choose a number (1-9): ");
            playersCurrentLetter = "O";
        }
        playerChoice = scan.nextInt();
        if (playerChoice < 1 || playerChoice > 9) 
        {
            System.out.println("Invalid input! Choose a number between 1 and 9.");
            playerTurn();
            return;
        }
        int row = (playerChoice - 1) / 3;
        int col = (playerChoice - 1) % 3;
        if (!gameBoard[row][col].equals("X") && !gameBoard[row][col].equals("O")) 
        {
            gameBoard[row][col] = playersCurrentLetter;
            isPlayer1Turn = !isPlayer1Turn;
        } 
        else 
        {
            System.out.println("That cell is already occupied! Choose another.");
            playerTurn();
        }
        clearConsole();
    }

    private void clearConsole() 
    {
        try 
        {
            if (System.getProperty("os.name").contains("Windows")) 
            {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } 
            else {
                System.out.print("\033\143");
            }
        } 
        catch (IOException | InterruptedException ex) 
        {
            System.out.println(ex.getMessage());
        }
    }
}
