import java.util.Scanner;
import java.util.ArrayList;

public class BitManager {
    public static void main(String[] args) {

        System.out.println("Welcome to Bitlandia!");
        System.out.print("Initial bit configuration: " + "\n\n");
        start();
    }

    static Scanner scan = new Scanner(System.in);

    // Check valid
    private static boolean isValid(String string) {
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (c != '0' && c != '1') {
                return false;
            }
        }
        return true;
    }

    // Start program
    private static void start() {

        ArrayList<String> confi = new ArrayList<String>();
        ArrayList<String> check = new ArrayList<String>();
        String num;
        check.add("");

        while (scan.hasNextLine()) {

            String configuration = scan.nextLine();
            if (isValid(configuration)) {
                confi.add(configuration);
                System.out.println("Please enter a command (check <string>, change <index> <state>, or exit):" + "\n");
                // System.out.println(confi);
                break;
            } else {
                System.out.println("Invalid input: Please enter a string of 1s and 0s.");
                System.out.print("Initial bit configuration: " + "\n");
                continue;
            }
        }
        while (true) {
            String command = scan.nextLine();
            String[] commandCheck = command.split(" "); // check 1011, change 1 naughty
            String numCheck;
            String con = confi.get(0);

            // Command Check
            if (commandCheck.length == 1) {
                if (commandCheck[0].equals("exit")) {
                    System.out.println("Exiting program. Goodbye!");
                    System.exit(0);
                }
            } else if (commandCheck.length == 2) {
                // Check command
                if (commandCheck[0].equals("check")) {
                    if (commandCheck[1].length() == con.length()) {
                        if (commandCheck.length > 1) {
                            numCheck = commandCheck[1];
                            if (isValid(numCheck)) {
                                check.set(0, numCheck);
                                // System.out.println(check);

                                ArrayList<Integer> index = new ArrayList<Integer>();
                                ArrayList<Integer> indexCheck = new ArrayList<Integer>();

                                for (int i = 0; i < con.length(); i++) {
                                    if (con.charAt(i) == '1') {
                                        index.add(i);
                                        // System.out.println(i);
                                    }
                                }

                                if (!check.isEmpty()) {
                                    num = check.get(0);
                                    for (int j = 0; j < num.length(); j++) {
                                        if (num.charAt(j) == '1') {
                                            indexCheck.add(j);
                                            // System.out.println(j);
                                        }
                                    }
                                }

                                // Naughty bits contain
                                boolean naughtyContain = false;
                                for (int i = 0; i < index.size(); i++) {
                                    for (int j = 0; j < indexCheck.size(); j++) {
                                        if (index.get(i).equals(indexCheck.get(j))) {
                                            naughtyContain = true;
                                            break;
                                        }
                                    }
                                }

                                if (naughtyContain) {
                                    System.out.println("Contains Naughty Bits" + "\n");
                                } else {
                                    System.out.println("No Naughty Bits Found" + "\n");
                                }
                            } else {
                                System.out.println("Invalid input: Please enter a string of 1s and 0s." + "\n");
                            }
                        }
                    } else {
                        System.out.println("Invalid input: Please enter a string of 1s and 0s." + "\n");
                    }
                }
            } else if (commandCheck.length == 3) {
                // Change command
                if (commandCheck[0].equals("change") && commandCheck[2].equals("naughty")
                        || commandCheck[2].equals("good")) {
                    if (commandCheck.length > 2) {
                        int changeIndex = Integer.parseInt(commandCheck[1]);
                        char[] arrayNum = con.toCharArray();
                        if (commandCheck[2].equals("naughty")) {
                            if (changeIndex >= 0 && changeIndex < con.length()) {
                                arrayNum[(con.length() - 1) - changeIndex] = '1';
                                System.out.println("Bit at index " + changeIndex + " marked as Naughty" + "\n");
                            } else {
                                System.out.println("Invalid index: Index out of bounds or not a number." + "\n");
                            }
                        } else if (commandCheck[2].equals("good")) {
                            if (changeIndex >= 0 && changeIndex < con.length()) {
                                arrayNum[(con.length() - 1) - changeIndex] = '0';
                                System.out.println("Bit at index " + changeIndex + " marked as Good" + "\n");
                            } else {
                                System.out.println("Invalid index: Index out of bounds or not a number." + "\n");
                            }
                            // System.out.print(arrayNum);
                            // change char array to string
                            String conNew = String.valueOf(arrayNum);
                            confi.set(0, conNew);
                            // System.out.println(confi);
                        } else {
                            System.out.println("Invalid index: Index out of bounds or not a number." + "\n");
                        }
                    } else {
                        System.out.println("Invalid argument: For change command, specify 'naughty' or 'good'." + "\n");
                    }

                } else {
                    System.out.println("Invalid argument: For change command, specify 'naughty' or 'good'." + "\n");
                }
            }
        }
    }
}
