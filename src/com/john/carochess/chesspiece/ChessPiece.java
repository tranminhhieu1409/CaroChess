package com.john.carochess.chesspiece;

public class ChessPiece {
	public static final char X = 'X';
	public static final char O = 'O';
	
	private int x, y;
	private char type;	
	public ChessPiece(int x, int y, char type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public char getType() {
		return type;
	}	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ChessPiece) {
			ChessPiece cp2 = (ChessPiece) obj;
			return x == cp2.x && y == cp2.y;			
		}
		return super.equals(obj);
	}
}
