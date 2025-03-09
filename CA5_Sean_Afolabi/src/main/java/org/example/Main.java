package org.example;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        PlayerDAO playerDAO = new DAOImpl();

        Scanner kb = new Scanner(System.in);
        int menu_option_no = 0;
        String menuReturn = "Returning to the main menu...";


        while(menu_option_no != 14)
        {
            System.out.println("=============================================");
            System.out.println("Main Menu");
            System.out.println("=============================================");
            System.out.println("Input a question number from 1 - 20 (14 = Exit): ");
            menu_option_no = kb.nextInt();

            switch(menu_option_no)
            {
                case 1:
                    List<PlayerDTO> players = playerDAO.getAllPlayers();
                    players.forEach(System.out::println);
                    System.out.println("=============================================");
                    System.out.println(menuReturn);
                    break;
                case 2:
                    PlayerDTO player = playerDAO.getPlayerByID(1);
                    System.out.println(player);
                    System.out.println("=============================================");
                    System.out.println(menuReturn);
                    break;
                case 14:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid question number/input. Please enter a number within 1-21!");
                    System.out.println(menuReturn);
                    System.out.println("=============================================");
                    break;
            }
        }
    }
}

