/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mancalaai;

/**
 *
 * @author PRANTO
 */
public class MancalaAI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Board brd=new Board(6,4);
        Player playerOne=new Player(brd, 1);
        System.out.println("Player One enters !!!");
        Player playerTwo=new Player(brd, 2);
        System.out.println("Player two enters !!!");
        System.out.println("");
        gamePlay gp=new gamePlay(playerOne, playerTwo);
        gp.play();
    }
    
}
