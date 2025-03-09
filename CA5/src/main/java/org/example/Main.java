package org.example;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PlayerDAO playerDAO = new DAOImpl();
        Scanner kb = new Scanner(System.in);
        int menu_option_no = 0;
        String menuReturn = "Returning to the main menu...";

        while (menu_option_no != 14) {
            System.out.println("=============================================");
            System.out.println("Main Menu");
            System.out.println("=============================================");
            System.out.println("Input a question number from 1 - 20 (14 = Exit): ");
            menu_option_no = kb.nextInt();
            kb.nextLine(); 

            switch (menu_option_no) {
                case 1: // Get all players
                    List<PlayerDTO> players = playerDAO.getAllPlayers();
                    players.forEach(System.out::println);
                    System.out.println("=============================================");
                    System.out.println(menuReturn);
                    break;

                case 2: // Get player by ID
                    System.out.print("Enter Player ID: ");
                    int searchId = kb.nextInt();
                    PlayerDTO player = playerDAO.getPlayerByID(searchId);
                    if (player != null) {
                        System.out.println(player);
                    } else {
                        System.out.println("Player with ID " + searchId + " not found.");
                    }
                    System.out.println("=============================================");
                    System.out.println(menuReturn);
                    break;

                case 3: // Insert a new player
                    System.out.print("Enter player name: ");
                    String name = kb.nextLine();
                    System.out.print("Enter player age: ");
                    int age = kb.nextInt();
                    System.out.print("Enter player rating: ");
                    float rating = kb.nextFloat();

                    PlayerDTO newPlayer = new PlayerDTO(0, name, age, rating);
                    PlayerDTO insertedPlayer = playerDAO.insertPlayer(newPlayer);
                    System.out.println("Inserted Player: " + insertedPlayer);
                    System.out.println("=============================================");
                    System.out.println(menuReturn);
                    break;

                case 4: // Delete player by ID
                    System.out.print("Enter Player ID to delete: ");
                    int deleteId = kb.nextInt();
                    playerDAO.deletePlayerById(deleteId);
                    System.out.println("=============================================");
                    System.out.println(menuReturn);
                    break;

                case 14: // Exit
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid question number/input. Please enter a number within 1-20!");
                    System.out.println(menuReturn);
                    System.out.println("=============================================");
                    break;
            }
        }
        kb.close();
    }
}
