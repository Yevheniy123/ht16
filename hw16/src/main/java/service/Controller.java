package service;

import dto.Computer;
import dto.DependencyTable;
import dto.Player;

import java.util.Scanner;

import static service.Beautify.lineDelimiter;
import static service.PrintStatistics.printStats;
import static service.ToolDefinition.defineTool;
import static service.WinDefinition.defineWin;

public class Controller extends DependencyTable {
    private static final int DASHES = 70;
    private Player player;
    private Computer computer;
    private int countGames;

    public void runGame() {
        computer = new Computer();
        boolean nextGame = true;
        Scanner scanner = new Scanner(System.in);

        System.out.print("Hello! What's your name? \n-> ");
        String name = scanner.nextLine();

        player = new Player(name);

        System.out.println("Nice to meet you, " + player.getName() + " :)");
        System.out.print("How many games do you want to play? \n-> ");
        int plays = Integer.parseInt(scanner.nextLine());
        System.out.println("Nice :) \nLET'S PLAY!");

        try {
            do {
                System.out.println(lineDelimiter(DASHES));
                System.out.print("""
                            Tools:\s
                             [R] - Rock\s
                             [P] - Paper\s
                             [S] - Scissors\s
                            Input:\040""");
                String toolChoice = scanner.nextLine();
                String computerChoice = computer.getComputerDecision();

                player.setTool(toolChoice);
                computer.setTool(computerChoice);

                System.out.println(lineDelimiter(DASHES));

                System.out.println("Result: ");
                String resultChoice = defineWin(player, computer,
                        defineTool(toolChoice), defineTool(computerChoice));

                System.out.println(resultChoice);
                System.out.println(lineDelimiter(DASHES));

                countGames = player.getWin() + player.getLose() + player.getTie();
                if (countGames == plays) break;

                System.out.print("""
                            Do you want to play again? [N] - to exit.\s
                            Press any [button] to continue.\s
                            Input:\040""");

                String buffer = scanner.nextLine();
                if (buffer.equalsIgnoreCase("N")) nextGame = false;

                } while (nextGame);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.err.println("This tool is not existing!");
            } catch (NumberFormatException e) {
                System.err.println("Wrong argument! >:(");
            } finally {
                printStats(player, computer);
            }
    }
}
