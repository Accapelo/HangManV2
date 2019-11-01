package lexicon;
import com.sun.deploy.util.StringUtils;

import java.util.Random;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
import java.lang.*;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        Random rand = new Random();
        Scanner in = new Scanner(System.in);
        String[] possibleWords = {"switzerland", "octagon", "awkward", "bagpipes", "banjo", "dwarves", "fjord",
                "haphazard", "jukebox", "memento", "oxygen", "sphinx", "zealous", "blizzard", "beekeeper", "bookworm",
                "buffalo", "espionage", "keyhole", "kilobyte", "luxury", "quartz", "rhubarb", "scratch", "megahertz",
                "stronghold", "strength", "transcript", "vaporize", "whiskey", "xylophone", "youth"};
        String input = new String();
        final String[] bokstaver = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "w", "v", "x", "y", "z"};
        char inputSave;
        int bokPos;

        //Initierar ord
        String rightAnswer = possibleWords[rand.nextInt(possibleWords.length)];
        char[] korrektGissning = new char[rightAnswer.length()];
        for (int i = 0; i < rightAnswer.length(); i++) {
            korrektGissning[i] = '_';
        }

        int antalGissningar = 0;
        StringBuilder gissningar = new StringBuilder();
        int sPos = 0;
        boolean win = false;

        while (antalGissningar < 8 && !win) {
            System.out.print("Guess a letter or the whole word.\nWord: ");
            for (char ord : korrektGissning) {
                System.out.print(ord + " ");
            }
            System.out.println("\nGuessed letters: " + gissningar.toString());
            System.out.println("Guesses left = "+(8-antalGissningar));

            //input för gissning
            input = in.nextLine().toLowerCase();
            // om input är en bokstav
            if (input.length()==1){
                inputSave = input.charAt(0);
                if (0 <= compareLetter(inputSave, gissningar.toString())) {
                    System.out.println("Can't guess on the same letter twice");
                } else {
                    //kolla om gissning stämmer
                    if (0 <= compareLetter(inputSave, rightAnswer)) {
                        System.out.println("Correct guess");
                        korrektGissning[compareLetter(inputSave, rightAnswer)] = inputSave;

                        bokPos = compareLetter(inputSave, rightAnswer) + 1;
                        //kollar om det finns fler utav bokstaven i ordet
                        while (rightAnswer.length() > bokPos) {
                            if (bokPos <= compareLetter(inputSave, bokPos, rightAnswer)) {
                                korrektGissning[compareLetter(inputSave, bokPos, rightAnswer)] = inputSave;
                                bokPos++;
                            } else {
                                bokPos++;
                            }
                        }
                    } else {
                        System.out.println("Wrong guess");
                        antalGissningar++;
                    }
                    gissningar.append(inputSave);
                }
            }
            //om input är ett ord
            else{
                if(input.equalsIgnoreCase(rightAnswer)){
                    System.out.println("Right answer");
                    win = true;
                }else{
                    System.out.println("Wrong answer");
                    antalGissningar++;
                }
            }

        }

        in.close();

        if(antalGissningar<8){
            System.out.println("You won in "+antalGissningar+" guesses.");
        }else{
            System.out.println("You lose\nThe word was "+rightAnswer);
        }

    }

    public static int compareLetter(char a,int c, String b){
        for (int i = c; c < b.length(); c++){
            if (b.charAt(c)==a){
                return c;
            }
        }
        return -1;
    }

    public static int compareLetter(char a, String b){
        for (int i = 0; i < b.length(); i++){
            if (b.charAt(i)==a){
                return i;
            }
        }
        return -1;
    }

    public static boolean compareLetter(String a, String[] b){
        for (int i = 0; i < b.length; i++){
            if (b[i].equalsIgnoreCase(a)){
                return true;
            }
        }
        return false;
    }
}
