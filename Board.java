//These are changes made in the frame full branch. Will they show up?
package pkg2048;

/**
 *
 * @author erinengle
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Board extends JPanel 
{
    private final int BOARD_SIZE=4;
    private int[][] board = new int[4][4];
    private JButton[][] buttons = new JButton[4][4];
    private boolean[][] changed = new boolean[4][4];
    private Random r = new Random();
    private boolean winner;
    
    
    public Board(){
        winner=false;
        initializeBoard();
        setBackground(Color.BLACK);
        LayoutManager grid=new GridLayout(4, 4, 1, 1);
        setLayout(grid);
       
        //setFocusable(true);
        
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
               // System.err.println (e);
                switch (e.getKeyCode ()){
                    case KeyEvent.VK_DOWN: moveDown(); break;
                    case KeyEvent.VK_UP: moveUp(); break;
                    case KeyEvent.VK_LEFT: moveLeft();break;
                    case KeyEvent.VK_RIGHT: moveRight(); break;
                    default: break;                       
                }
                repaint();
            }
        });
        
    }
    
    private void initializeBoard(){
        for(int row=0; row<board.length; row++){
            for(int col=0; col<board[row].length; col++){
                board[row][col]=0;
                //StringBuilder s = new StringBuilder();
                //s.append(board[row][col]);
                buttons[row][col] = new JButton();
                buttons [row] [col].setFont (new Font ("Arial", Font.BOLD, 50));
                buttons [row] [col].setBackground (Color.BLACK); 
                //buttons[row][col].setText ( s.toString());
                add(buttons[row][col]).setFocusable(false); 
            }
        }
        addRandomNum();
        
        
    }
    //Arrays.fill doesnt worl with booleans?
    private void setChanged(){
        for(int row=0; row<changed.length; row++){
            for(int col=0; col<changed[row].length; col++){
                changed[row][col]=false;
            }
        }
            
    }
    
    private void addRandomNum(){
        int randRow = r.nextInt(4);
        int randCol = r.nextInt(4);
        
        do{
        randRow = r.nextInt(4);
        randCol = r.nextInt(4);
        }while(board[randRow][randCol]!=0);
        
        int startingNum = r.nextInt(2);
        switch (startingNum){
            case 0:
                board[randRow][randCol]=2;
                buttons[randRow][randCol].setText("2");
                break;
            case 1:
                board[randRow][randCol]=4;
                buttons[randRow][randCol].setText("4");
                break;
            default:
                break;
        }
        
    }
    
    public void moveUp(){
        for(int row=0; row<BOARD_SIZE; row++){
            for(int col=0; col<BOARD_SIZE; col++){
                try{
                    
                    int tempRow=row;
                    while(tempRow>=0){
                        try{
                    if(board[tempRow-1][col]==0){
                        
                        board[tempRow-1][col]=board[tempRow][col];
                        
                        if(board[tempRow-1][col]!=0){
                        StringBuilder newNum = new StringBuilder();
                        newNum.append(board[tempRow-1][col]);
                        buttons[tempRow-1][col].setText(newNum.toString());
                    }
                        
                        board[tempRow][col]=0;
                        buttons[tempRow][col].setText("");
                    }
                    else if(board[tempRow-1][col]==board[tempRow][col] 
                            && changed[tempRow-1][col]==false && changed[tempRow][col]==false){
                        board[tempRow-1][col]=board[tempRow-1][col]+board[tempRow][col];
                        changed[tempRow-1][col]=true;
                        
                        StringBuilder newNum = new StringBuilder();
                        newNum.append(board[tempRow-1][col]);
                        if(board[tempRow-1][col]!=0){
                        buttons[tempRow-1][col].setText(newNum.toString());
                        }
                        board[tempRow][col]=0;
                        buttons[tempRow][col].setText("");
                    }
                    }catch(ArrayIndexOutOfBoundsException e){
                    //Edge of board
                    //do nothing
                }
                    tempRow--;
                    
                    }
                
                if(board[row][col]==2048){
                    winGame();
                }
                }catch(ArrayIndexOutOfBoundsException e){
                    //Edge of board
                    //do nothing
                }
                
            }
        }
        setChanged();
        addRandomNum();
    }
    
    public void moveRight(){
        for(int row=0; row<BOARD_SIZE; row++){
            for(int col=BOARD_SIZE; col>=0; col--){
                try{
                    int tempCol = col;
                    while(tempCol>=0){
                        try{
                    if(board[row][tempCol+1]==0){
                        
                        board[row][tempCol+1]=board[row][tempCol];
                        
                        if(board[row][tempCol+1]!=0){
                        StringBuilder newNum = new StringBuilder();
                        newNum.append(board[row][tempCol+1]);
                        buttons[row][tempCol+1].setText(newNum.toString());
                        }
                        
                        board[row][tempCol]=0;
                        buttons[row][tempCol].setText("");
                    }
                    else if(board[row][tempCol+1]==board[row][tempCol] && 
                            changed[row][tempCol+1]==false && changed[row][tempCol]==false){
                        
                        board[row][tempCol+1]=board[row][tempCol+1]+board[row][tempCol];
                        changed[row][tempCol+1]=true;
                        
                        StringBuilder newNum = new StringBuilder();
                        newNum.append(board[row][tempCol+1]);
                        if(board[row][tempCol+1]!=0){
                        buttons[row][tempCol+1].setText(newNum.toString());
                        }
                        
                        board[row][tempCol]=0;
                        buttons[row][tempCol].setText("");
                    }
                    }catch(ArrayIndexOutOfBoundsException e){
                    //Edge of board
                    //do nothing
                }
                    tempCol--;
                    }
                
                if(board[row][col]==2048){
                    winGame();
                }
                }catch(ArrayIndexOutOfBoundsException e){
                    //Edge of board
                    //do nothing
                }
            }
        }
        setChanged();
        addRandomNum();
        
    }
    public void moveDown(){
        for(int row=BOARD_SIZE; row>=0; row--){
            for(int col=0; col<BOARD_SIZE; col++){
                try{
                    int tempRow = row;
                    while(tempRow>=0){
                        try{
                    if(board[tempRow+1][col]==0){
                        board[tempRow+1][col]=board[tempRow][col];
                        
                        if(board[tempRow+1][col]!=0){
                        StringBuilder newNum = new StringBuilder();
                        newNum.append(board[tempRow+1][col]);
                        buttons[tempRow+1][col].setText(newNum.toString());
                        }
                        
                        board[tempRow][col]=0;
                        buttons[tempRow][col].setText("");
                    }
                    else if(board[tempRow+1][col]==board[tempRow][col] 
                            && changed[tempRow+1][col]==false && changed[tempRow][col]==false){
                        board[tempRow+1][col]=board[tempRow+1][col]+board[tempRow][col];
                        changed[tempRow+1][col]=true;
                             
                        StringBuilder newNum = new StringBuilder();
                        newNum.append(board[tempRow+1][col]);
                        if(board[tempRow+1][col]!=0){
                        buttons[tempRow+1][col].setText(newNum.toString());
                        }
                        board[tempRow][col]=0;
                        buttons[tempRow][col].setText("");
                    }
                    }catch(ArrayIndexOutOfBoundsException e){
                    //Edge of board
                    //do nothing
                }
                    tempRow--;
                    }
                        
                
                if(board[row][col]==2048){
                    winGame();
                }
                }catch(ArrayIndexOutOfBoundsException e){
                    //Edge of board
                    //do nothing
                }
            }
        }
        setChanged();
        addRandomNum();
        
    }
    public void moveLeft(){
        for(int row=0; row<BOARD_SIZE; row++){
            for(int col=0; col<BOARD_SIZE; col++){
                try{
                    int tempCol= col;
                    while(tempCol >= 0){
                    try{
                        if(board[row][tempCol-1]==0){
                        board[row][tempCol-1]=board[row][tempCol];
                        
                        if(board[row][tempCol-1]!=0){
                        StringBuilder newNum = new StringBuilder();
                        newNum.append(board[row][tempCol-1]);
                        buttons[row][tempCol-1].setText(newNum.toString());
                        }
                        
                        board[row][tempCol]=0;
                        buttons[row][tempCol].setText("");
                    }
                    else if(board[row][tempCol-1]==board[row][tempCol] 
                            && changed[row][tempCol-1]==false && changed[row][tempCol]==false){
                        board[row][tempCol-1]=board[row][tempCol-1]+board[row][tempCol];
                        changed[row][tempCol-1]=true;
                        
                        StringBuilder newNum = new StringBuilder();
                        newNum.append(board[row][tempCol-1]);
                        if(board[row][tempCol-1]!=0){
                        buttons[row][tempCol-1].setText(newNum.toString());
                        }
                        board[row][tempCol]=0;
                        buttons[row][tempCol].setText("");
                    }
                    }catch(ArrayIndexOutOfBoundsException e){
                    //Edge of board
                    //do nothing
                }
                    tempCol--;
                    }
                
                
                if(board[row][col]==2048){
                    winGame();
                }
                }catch(ArrayIndexOutOfBoundsException e){
                    //Edge of board
                    //do nothing
                }
            }
        }
        setChanged();
        addRandomNum();
        
    }
    
    public void winGame(){
        winner=true;
        JFrame frame = new JFrame("You Win!");
        
        frame.setSize(200,100);
        frame.setLocationRelativeTo(null);
        JButton label = new JButton("Winner Winner");
        label.setFont (new Font ("Arial", Font.BOLD, 20));
        label.setBackground (Color.BLACK); 
        label.setText ( "You win!");
        frame.add(label);
        frame.setVisible ( true);
    }
    public boolean isWinner(){
        return winner;
    }
    public void setWinner(){
        winner = false;
    }
    
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
          for(int row=0; row<board.length; row++){
            for(int col=0; col<board[row].length; col++){
                s.append ( "\t"+board[row][col]);
            }
            s.append("\n");
        }
          return s.toString();
    }
    
}
