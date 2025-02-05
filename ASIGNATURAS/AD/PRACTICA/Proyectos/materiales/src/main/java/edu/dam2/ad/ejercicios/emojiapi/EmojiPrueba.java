package edu.dam2.ad.ejercicios.emojiapi;

import java.util.List;
import java.util.Scanner;

/**
 * Interactive program to test the connection to the API and see the different
 * content one can extract from it.
 */
public class EmojiPrueba {
    public static void main(String[] args) {
        System.out.print("""
                EMOJI'S CATEGORIES:
                0 - Smileys & Emotion
                1 - Food & Drink
                2 - Animals & Nature
                3 - Travel & Places
                4 - Activities
                5 - Objects
                6 - Symbols
                7 - Flags
                8 - EXIT
                ---------------------------
                """);
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                int input_categoryNum = Integer.parseInt(sc.nextLine());
                if (input_categoryNum == 8) {
                    break;
                }
                String input_category = EmojiApp.CATEGORIES[input_categoryNum]; // Reuses the array for categories from the App
                List<Emoji> emojis = EmojiAPI.fetchEmojisByCategory(input_category);
                int counter = 0;
                for (Emoji emoji : emojis) {
                    if (counter == 10) { // Change this to alter the number of columns
                        System.out.println();
                        counter = 0;
                    }
                    System.out.print(emoji.toString());
                    counter++;
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Error reading the option selected.");
                System.out.println("Please enter a valid option.");
            } finally {
                System.out.println("\n---------------------------");
            }
        }
    }
}
