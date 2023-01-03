package com.john.carochess.chesstable;

import java.util.Comparator;

import com.john.carochess.chesspiece.ChessPiece;
import com.john.carochess.player.Player;

public class ChessTable {
	public static final int ROW = 10;
	public static final int COLUMN = 10;
	public static final int SIZE = 50;
	private static final String RESET = "0:0";
	private String score;
	private String color;
	private Player player1;
	private Player player2;

	public ChessTable(String color, String playerName1, String playerName2) {
		this.color = color;
		player1 = new Player(playerName1, ChessPiece.X);
		player2 = new Player(playerName2, ChessPiece.O);
		score = RESET;
	}

	public void drawTable() {
		for (int i = 0; i < ROW; i++) {
			for (int j = 0; j < COLUMN; j++) {
				int x = j * SIZE + SIZE / 2;
				int y = i * SIZE + SIZE / 2;
				ChessPiece cp = new ChessPiece(x, y, '-');
				if (player1.getListChessPiece().indexOf(cp) >= 0) {
					System.out.print("|x");
				} else if (player2.getListChessPiece().indexOf(cp) >= 0) {
					System.out.print("|o");
				} else {
					System.out.print("| ");
				}
			}
			System.out.println("|");
		}
	}

	public char placeChess(int x, int y, char type) {
		if (type == player1.getType()) {
			if (player1.placeChess(x, y, player2)) {
				drawTable();
				return player2.getType();
			}
		} else {
			if (player2.placeChess(x, y, player1)) {
				drawTable();
				return player1.getType();
			}
		}

		return type;
	}

	public void deleteTable() {
		player1.deleteChess();
		player2.deleteChess();
	}

	public void playAgain() {
		deleteTable();
		score = RESET;
	}

	public char getPlayer1Type() {
		return player1.getType();
	}

	public void checkVictory() {
		int countChess = player1.getListChessPiece().size() + player2.getListChessPiece().size();
		if (countChess < 9) {
			return;
		}

		if (checkPlayerHorizontalWin(player1) || checkPlayerVerticalWin(player1) || checkPlayerCrossWin(player1)) {
			String item[] = score.split(":");
			score = Integer.parseInt(item[0] + 1) + ":" + item[1];
			System.out.println("Player 1 is victory!");
			System.out.println("Score: " + score);

			deleteTable();
			return;
		} else if (checkPlayerHorizontalWin(player2) || checkPlayerVerticalWin(player2)
				|| checkPlayerCrossWin(player2)) {
			String item[] = score.split(":");
			score = item[0] + ":" + Integer.parseInt(item[0] + 1);
			System.out.println("Player 2 is victory!");
			System.out.println("Score: " + score);

			deleteTable();
			return;
		}
	}

	private Comparator<ChessPiece> sortHorizontal = new Comparator<ChessPiece>() {
		@Override
		public int compare(ChessPiece o1, ChessPiece o2) {
			return o1.getX() - o2.getX();
		}
	};

	private Comparator<ChessPiece> sortVertical = new Comparator<ChessPiece>() {
		@Override
		public int compare(ChessPiece o1, ChessPiece o2) {
			return o1.getY() - o2.getY();
		}
	};

	private boolean checkPlayerHorizontalWin(Player player) {
		player.getListChessPiece().sort(sortHorizontal);
		for (int i = 0; i < player.getListChessPiece().size(); i++) {
			int count = 1;
			ChessPiece previousChess = player.getListChessPiece().get(i);
			for (int j = i + 1; j < player.getListChessPiece().size(); j++) {
				ChessPiece nextChess = player.getListChessPiece().get(j);
				if (nextChess.getX() == previousChess.getX() + SIZE && nextChess.getY() == previousChess.getY()) {
					count++;
					previousChess = nextChess;
				}
				if (count >= 5) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean checkPlayerVerticalWin(Player player) {
		player.getListChessPiece().sort(sortVertical);
		for (int j = 0; j < player.getListChessPiece().size(); j++) {
			int count = 1;
			ChessPiece previousChess = player.getListChessPiece().get(j);
			for (int i = j + 1; i < player.getListChessPiece().size(); i++) {
				ChessPiece nextChess = player.getListChessPiece().get(i);
				if (nextChess.getX() == previousChess.getX() && nextChess.getY() == previousChess.getY() + SIZE) {
					count++;
					previousChess = nextChess;
				}
				if (count >= 5) {
					return true;
				}
			}
		}
		return false;
	}

	private boolean checkPlayerCrossWin(Player player) {
		player.getListChessPiece().sort(sortHorizontal);
		player.getListChessPiece().sort(sortVertical);

		for (int i = 0; i < player.getListChessPiece().size(); i++) {
			int count = 1;
			ChessPiece previousChess = player.getListChessPiece().get(i);
			for (int j = i + 1; j < player.getListChessPiece().size(); j++) {
				ChessPiece nextChess = player.getListChessPiece().get(j);
				if ((nextChess.getX() == previousChess.getX() + SIZE && nextChess.getY() == previousChess.getY() + SIZE)
						|| (nextChess.getX() == previousChess.getX() - SIZE
								&& nextChess.getY() == previousChess.getY() + SIZE)) {
					count++;
					previousChess = nextChess;
				}
				if (count >= 5) {
					return true;
				}
			}
		}
		return false;
	}
}
