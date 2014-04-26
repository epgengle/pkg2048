/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkg2048;

import java.util.Scanner;
import javax.swing.JFrame;

/**
 *
 * @author erinengle
 */
public class Main
{

    public static boolean interact=false;
    /**
     * @param args the command line arguments
     */
    public static void main ( String[] args ) 
    {
        Scanner in = new Scanner(System.in);
        Board board = new Board();
        System.out.println(board.toString());
        
        
//        Listener1 l = new Listener1(board);
//        board.addKeyListener ( l);
        
        JFrame frame = new JFrame("2048");
        
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(board);
        board.setFocusable(true);
        frame.setVisible (true);
        
        
        if(interact){
        String move;
        boolean keepGoing= true;
        while(keepGoing){
        while(!board.isWinner()){
            move=in.next ();
            switch(move){
                case "^":
                    board.moveUp ();
                    break;
                case ">":
                    board.moveRight ();
                    break;
                case "v":
                case "V":
                    board.moveDown();
                    break;
                case "<":
                    board.moveLeft ();
                    break;
                default:
                    break;
                }
            
        System.out.println(board.toString());
        
            }
        System.out.print ( "You win! Keep going?");
        String kg = in.next ();
        switch(kg){
            case "yes":
                board.setWinner();
                break;
            case "no":
                keepGoing = false;
        }
        
        }
        }
        }
    }
