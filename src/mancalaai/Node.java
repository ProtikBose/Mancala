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
public class Node {

    Node prev;
    Player playerOne;
    Player playerTwo;
    int playerTurn;
    int move;
    ArrayList<Integer> choice;
    ArrayList<Node> children;

    Node(Node prev, Player playerOne, Player playerTwo, int move) {
        this.prev = prev;
        this.playerOne = playerOne.sendtoCreateNode();
        this.playerTwo = playerTwo.sendtoCreateNode();
        // this.playerTurn=playerTurn;
        this.move = move;
        choice = new ArrayList<>();
        children = new ArrayList<>();
    }
    
    Node(Node prev, Player playerOne, Player playerTwo, int move,boolean tf) {
        this.prev = prev;
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        // this.playerTurn=playerTurn;
        this.move = move;
        choice = new ArrayList<>();
        children = new ArrayList<>();
    }

    public void makeChoiceList(int playerturn) {
        if (playerturn == 1) {
            for (int i = 1; i <= 6; i++) {
                if (playerOne.field.get(i - 1) != 0) {
                    choice.add(i);
                }
            }
        } else {
            for (int i = 8; i <= 13; i++) {
                if (playerTwo.field.get(i - 1) != 0) {
                    choice.add(i);
                }
            }
        }
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public int getChoice() {
        return move;
    }

    public void movePerform(int move, int playerTrn) {
        this.playerTurn = playerTrn;
        this.move = move;
        System.out.println("Player Number " + playerTurn);
        System.out.println("Move number : " + move);
       

        if (playerTurn == 1) {
            int num = playerOne.field.get(move - 1);
            int temp = num;
            playerOne.field.set(move - 1, 0);
            playerTwo.field.set(move - 1, 0);
            while (num > 0) {
                if (move == 13 && temp != num) {
                    move = 0;
                }
                // if(choice ==14) choice=0;
                playerOne.field.set(move, playerOne.field.get(move) + 1);
                playerTwo.field.set(move, playerTwo.field.get(move) + 1);
                //System.out.println(choice);
                //System.out.println("Element :"+pOne.field.get(choice));
                move++;
                num--;

            }

            if (((move - 1) == 6)) {
                playerTurn = 1;
            } else if (playerOne.field.get(move - 1) == 1 && move - 1 < 6) {
                playerOne.field.set(6, playerOne.field.get(move - 1) + playerOne.field.get(12 - move + 1) + playerOne.field.get(6));
                playerTwo.field.set(6, playerTwo.field.get(move - 1) + playerTwo.field.get(12 - move + 1) + playerTwo.field.get(6));
                playerOne.field.set(move - 1, 0);
                playerOne.field.set(12 - move + 1, 0);
                playerTwo.field.set(move - 1, 0);
                playerTwo.field.set(12 - move + 1, 0);
                //System.out.println("ok");
                playerTurn = 2;
            } else {
                playerTurn = 2;
            }
            // playerOne.printBoard();
        } else {
            int num = playerTwo.field.get(move - 1);
            int temp = num;
            playerTwo.field.set(move - 1, 0);
            playerOne.field.set(move - 1, 0);
            while (num > 0) {
                if (move == 6 && temp != num) {
                    move = 7;
                }
                if (move == 14) {
                    move = 0;
                }
                playerOne.field.set(move, playerOne.field.get(move) + 1);
                playerTwo.field.set(move, playerTwo.field.get(move) + 1);
                move++;

                num--;

            }
            // System.out.println(choice);

            if (((move - 1) == 13)) {
                playerTurn = 2;
            } else if (playerTwo.field.get(move - 1) == 1 && move - 1 < 13 && move - 1 > 6) {
                playerOne.field.set(13, playerOne.field.get(move - 1) + playerOne.field.get(12 - move + 1) + playerOne.field.get(13));
                playerTwo.field.set(13, playerTwo.field.get(move - 1) + playerTwo.field.get(12 - move + 1) + playerTwo.field.get(13));
                playerOne.field.set(move - 1, 0);
                playerOne.field.set(12 - move + 1, 0);
                playerTwo.field.set(move - 1, 0);
                playerTwo.field.set(12 - move + 1, 0);
                playerTurn = 1;
            } else {
                playerTurn = 1;
            }
            // playerTwo.printBoard();
        }
        System.out.println("Next Player Turn : "+getPlayerTurn());
       // playerOne.printBoard();
         System.out.println(" ");
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public void makeChildren(int playerTurn) {
        makeChoiceList(playerTurn);

        for (int i = 0; i < choice.size(); i++) {
            Node newChild = new Node(this, playerOne, playerTwo, choice.get(i));
            children.add(newChild);
            children.get(i).movePerform(choice.get(i), playerTurn);
        }

    }
    
    public void makeChildrenAlgo(int playerTurn) {
        makeChoiceList(playerTurn);

        for (int i = 0; i < choice.size(); i++) {
            Node newChild = new Node(this, this.playerOne, this.playerTwo, choice.get(i),true);
            children.add(newChild);
            children.get(i).movePerform(choice.get(i), playerTurn);
        }

    }

    public int getHuristicValue(Node root) {
        int val = 0;
        if (root.getPlayerTurn() == 1) {
            val = playerOne.field.get(6) - playerOne.field.get(13);
        } else {
            val = playerOne.field.get(13) - playerOne.field.get(6);
        }
        return val;
    }

    public int alphaBetaALgorithm(Node root, int depth, int alpha, int beta, int playerTrn) {
        if (depth == 0 || root == null) {
            //Check if the game is done
            // return root.getBoard().getMancalaDifference(root.getisPlayer1Max());
            return root.getHuristicValue(root);
        }

        if (root.getPlayerTurn() == 1) {
            root.makeChildren(root.getPlayerTurn());
            int value;
            for (Node aNode : root.getChildren()) {
                value = alphaBetaALgorithm(aNode, depth - 1, alpha, beta, aNode.getPlayerTurn());
                alpha = Math.max(alpha, value);
                if (alpha >= beta) {
                    break;
                }
            }
            return alpha;
        } else {
            root.makeChildren(root.getPlayerTurn());
            int value;
            for (Node aNode : root.getChildren()) {
                value = alphaBetaALgorithm(aNode, depth - 1, alpha, beta, aNode.getPlayerTurn());
                beta = Math.min(beta, value);
                if (alpha >= beta) {
                    break;
                }
            }
            return beta;
        }

    }

    public int getSelectedPosition(Node node) {
        int position = 0;

        int max = -99999999;
     /*   for (Node aNode : node.getChildren()) {
            System.out.println("ok");
            int checkVal = (alphaBetaALgorithm(aNode, 1, Integer.MIN_VALUE, Integer.MAX_VALUE, aNode.getPlayerTurn()));
            if (checkVal >= max) {
                max = aNode.getChoice();
            }
        }

        position = max; */
        
        node.getChildren().get(0).makeChildrenAlgo(node.getPlayerTurn());
        System.out.println(node.getChildren().get(0).getPlayerTurn());
        System.out.println(node.getChildren().get(0).getChildren().get(0).getPlayerTurn());
        node.getChildren().get(0).getChildren().get(0).playerOne.printBoard();
     
        //position=node.getChildren().get(3).getChoice();

        return position;
    }
}
