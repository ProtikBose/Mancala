/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mancalaai;

import java.util.Vector;

/**
 *
 * @author PRANTO
 */
public class Player {

    Board board;
    int playerNum;
    Vector<Integer> field;
    int initialHoleNumber;

    public Player(Board board, int playerNum) {
        this.board = board;
        this.playerNum = playerNum;
        field = new Vector<>();
        initialHoleNumber = board.getHoleNumber();
        initialBoard();
    }
    
    public Player sendtoCreateNode(){
        return new Player(board,playerNum);
    }
    
    public void printBoard() {

       // System.out.println("Player "+playerNum+" has given his turn");
        
        System.out.print("---");
        for (int i = initialHoleNumber; i > 0; i--) {
            System.out.print("---");
        }
        System.out.println("---");
        System.out.print("|  |");
        for (int i = (2 * initialHoleNumber); i > initialHoleNumber; i--) {

            System.out.print(field.get(i));
            if (field.get(i) < 10) {
                System.out.print(" |");
            } else {
                System.out.print("|");
            }
        }
        System.out.println("  |");
        //line 4
        System.out.print("|" + field.get(2 * initialHoleNumber + 1));
        if (field.get(2 * initialHoleNumber + 1) < 10) {
            System.out.print(" |");
        } else {
            System.out.print("|");
        }
        for (int i = initialHoleNumber; i > 1; i--) {
            System.out.print("--+");
        }
        System.out.print("--| " + field.get(initialHoleNumber));
        if (field.get(initialHoleNumber) < 10) {
            System.out.print("|");
        } else {
            System.out.print("|");
        }
        System.out.println("");
        //line 5
        System.out.print("|  |");
        for (int i = 0; i < initialHoleNumber; i++) {
            System.out.print(field.get(i));
            if (field.get(i) < 10) {
                System.out.print(" |");
            } else {
                System.out.print("|");
            }
        }
        System.out.println("  |");
        System.out.print("---");
        for (int i = initialHoleNumber; i > 0; i--) {
            System.out.print("---");
        }
        System.out.println("---");
    }

    public void initialBoard() {
        for (int i = 0; i <= board.getHoleNumber() * 2 + 1; i++) {
            field.add(board.getNumOfStoneInHole());
        }
        field.set(board.getHoleNumber(), 0);
        field.set(board.getHoleNumber() * 2 + 1, 0);
    }
}
