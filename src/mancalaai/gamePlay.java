/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mancalaai;

import java.util.ArrayList;

/**
 *
 * @author PRANTO
 */
public class gamePlay {
    
    Player playerOne;
    Player playerTwo;
    ArrayList<Integer>choice;
    int playerOneTurn;
    
    
    gamePlay(Player playerOne,Player playerTwo){
        this.playerOne=playerOne;
        this.playerTwo=playerTwo;
        choice=new ArrayList<>();
        playerOneTurn=1;
    }
    
   
    
    
    
    public void play(){
        //makeChoiceList();
       // playerOne.printBoard();
       Node parent=new Node(null, playerOne, playerTwo, 0);
       parent.makeChildren(playerOneTurn);
       
       int movePos=parent.getSelectedPosition(parent);
        System.out.println("The selected Move is : "+movePos);
    }
}
