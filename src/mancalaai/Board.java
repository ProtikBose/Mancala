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
public class Board {
     private int holeNumber;
    private int numOfStoneInHole;
    
    public Board(int holeNumber,int numOfStoneInHole){
        this.holeNumber=holeNumber;
        this.numOfStoneInHole=numOfStoneInHole;
    }

    /**
     * @return the holeNumber
     */
    public int getHoleNumber() {
        return holeNumber;
    }

    /**
     * @param holeNumber the holeNumber to set
     */
    public void setHoleNumber(int holeNumber) {
        this.holeNumber = holeNumber;
    }

    /**
     * @return the numOfStoneInHole
     */
    public int getNumOfStoneInHole() {
        return numOfStoneInHole;
    }

    /**
     * @param numOfStoneInHole the numOfStoneInHole to set
     */
    public void setNumOfStoneInHole(int numOfStoneInHole) {
        this.numOfStoneInHole = numOfStoneInHole;
    }
}
