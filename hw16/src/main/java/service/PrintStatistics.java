package service;

import dto.Computer;
import dto.Player;

import static service.Beautify.lineDelimiter;

public class PrintStatistics {
    private static final int DASHES = 70;
    protected static void printStats(Player player, Computer computer) {
        int winsPlayer = player.getWin();
        int losesPlayer = player.getLose();
        int tiesPlayer = player.getTie();

        int winsComputer = computer.getWin();
        int losesComputer = computer.getLose();
        int tiesComputer = computer.getTie();

        int countGames = player.getWin() + player.getLose() + player.getTie();

        System.out.println("Result".toUpperCase());

        System.out.println(lineDelimiter(DASHES));

        System.out.println("Player".toUpperCase());

        System.out.println(lineDelimiter(DASHES));

        System.out.println("Wins: " + winsPlayer);
        System.out.println("Loses: " + losesPlayer);
        System.out.println("Ties: " + tiesPlayer);

        System.out.println();
        System.out.println(lineDelimiter(DASHES));

        System.out.println("Computer".toUpperCase());

        System.out.println(lineDelimiter(DASHES));

        System.out.println("Wins: " + winsComputer);
        System.out.println("Loses: " + losesComputer);
        System.out.println("Ties: " + tiesComputer);

        try {
            player.setWinRate((double) winsPlayer / (winsPlayer + losesPlayer) * 100);
            System.out.println(lineDelimiter(DASHES));
            System.out.println("Player " + player.getName() + " win rate: "
                    + String.format("%.2f", player.getWinRate()) + "%");

            computer.setWinRate((double) winsComputer / (winsComputer + losesComputer) * 100);
            System.out.println("Computer win rate: " + String.format("%.2f", computer.getWinRate()) + "%");

        } catch (ArithmeticException e) {
            System.err.println("Statistics cannot be displayed.");
        } finally {
            System.out.println("Number of games: " + countGames);
            StatsRecord.recordStats(player, computer);
            System.out.println(lineDelimiter(DASHES));
        }
    }
}
