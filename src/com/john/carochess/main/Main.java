package com.john.carochess.main;

import java.util.Scanner;

import com.john.carochess.chesstable.ChessTable;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ChessTable ct = new ChessTable("Red", "John", "Rosie");
		char type = '-';
		
		for (int i = 0; i < 80; i++) {
			System.out.println("\nPlace chess: ");
			int x = sc.nextInt();
			int y = sc.nextInt();
			
			if (type=='-') {
				type = ct.getPlayer1Type();
			}
			type = ct.placeChess(x, y, type);
			ct.checkVictory();
		}
		sc.close();
	}
}
