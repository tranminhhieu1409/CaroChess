package com.john.carochess.player;

import java.util.ArrayList;
import com.john.carochess.chesspiece.ChessPiece;
import com.john.carochess.chesstable.ChessTable;

public class Player {
	private char type;
	private ArrayList<ChessPiece> listChessPiece;
	private String name;
	
	public Player(String name, char type) {
		this.name = name;
		this.type = type;
		listChessPiece = new ArrayList<ChessPiece>();
	}
	
	public String getName() {
		return name;
	}
	public char getType() {
		return type;
	}
	public ArrayList<ChessPiece> getListChessPiece() {
		return listChessPiece;
	}

	public boolean placeChess(int x, int y, Player player2) {
		// Validate x, y
		if (x < 0 || x > (ChessTable.COLUMN * ChessTable.SIZE) || y < 0 || y > (ChessTable.ROW * ChessTable.SIZE)) {
			System.out.println("Wrong location!");
			return false;
		}
		// Assign x,y in center of square
		x = (x / ChessTable.SIZE) * ChessTable.SIZE + ChessTable.SIZE/2;
		y = (y / ChessTable.SIZE) * ChessTable.SIZE + ChessTable.SIZE/2;
		// Create new ChessPiece and check in listChessPiece
		ChessPiece cp = new ChessPiece(x, y, type);
		if (listChessPiece.contains(cp)) {	
			System.out.printf("Error, existed chess piece in (%d,%d)",x,y);
			return false;
		}
		
		if (player2.listChessPiece.contains(cp)) {	
			System.out.printf("Error, existed chess piece in (%d,%d)",x,y);
			return false;
		}
		
		listChessPiece.add(cp);	
		return true;
	}

	public void deleteChess() {
		listChessPiece.clear();
	}
}
