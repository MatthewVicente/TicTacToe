/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

/**
 *
 * @author mathe
 */
import java.util.Scanner;
public class TicTacToe {

    /**
     * @param args the command line arguments
     */
    //Scanner read = new Scanner(System.in);
    public static Scanner read = new Scanner(System.in);
    public static void main(String[] args) {
        // TODO code application logic here   
        game();
    }
    
    public static void game(){
        //Print matriz
        char boardgame[][], player = 'x';
        int line, col, result = -1, tradePlayer = 0;
        boolean step;
        String namePlayer1, namePlayer2;
        
        boardgame = initialize();
        System.out.println("BEM-VINDO! ESTE É SEU TABULEIRO: ");
        System.out.println();
        print(boardgame);
        
        System.out.println();
        System.out.printf("Jogador número 1: ");
        namePlayer1 = read.nextLine();
        
        System.out.printf("Jogador número 2: ");
        namePlayer2 = read.nextLine();
        
        while(result == -1) {
            //System.out.println("Jogador[O]: " + gamer1);
            System.out.println();
            if(tradePlayer % 2 == 0) {
                System.out.println("Jogador " + namePlayer1);
            } else {
                System.out.println("Jogador " + namePlayer2);
            }
            System.out.printf("Linha: ");
            line = read.nextInt() - 1;
            System.out.printf("Coluna: ");
            col = read.nextInt() - 1;
            System.out.println();
            
            //Troca o jogador
            if (tradePlayer % 2 == 0) {
                player = 'o';
            } else {
                player = 'x';
            }
            
            tradePlayer += 1;
            
            //Verifica se a posição escolhida pode ser preenchida
            step = step(boardgame, line, col, player);
            
            if(step == true){
                //Preenche posição informada
                boardgame[line][col] = player;
            } else {
                //Em caso de posição já preenchida
                System.out.println("Posição ocupada!");
                System.out.println();
                print(boardgame);
                //Caso o usuario erre, pode digitar a posição correta de novo
                while (step = step(boardgame, line, col, player)) {
                    System.out.println();
                    System.out.printf("Linha: ");
                    line = read.nextInt() - 1;
                    System.out.printf("Coluna: ");
                    col = read.nextInt() - 1;
                    step = step(boardgame, line, col, player);
                }
            }
            
            //Print win/draw
            int status = status(boardgame, player, namePlayer1, namePlayer2);
            if(status == 1) {
                System.out.println();
                System.out.println("O jogador " + namePlayer1 + " venceu!");
                result = 1;
            } else if (status == 2) {
                System.out.println();
                System.out.println("O jogador " + namePlayer2 + " venceu!");
                result = 2;
            } else if (status == 0) {
                System.out.println("Deu empate!");
                result = 0;
            }
        }
        
    }
    
    public static char [][]initialize() {       
        char[][] board = new char [3][3];
        
        for(int line = 0; line < 3; line++){
            for(int col = 0; col < 3; col++){
                board[line][col] = '-'; 
            }
        }
        
        return board;
    }
    
    public static void print(char M[][]) {
        String empty = "";
        
        for(int line = 0; line < 3; line++){
            for(int col = 0; col < 3; col++){
                empty = empty + "[" + M[line][col] + "]";
                if(col == 2){
                    System.out.println(empty);                   
                }
            }
            empty = "";
        }
    }
    
    public static boolean step (char M[ ][ ], int lin, int col, char gamer){
        if(M[lin][col] != 'x' && M[lin][col] != 'o'){            
            M[lin][col] = gamer;

            print(M);
            
            return true;
        } else {
            return false;
        }
    }
    
    public static int status (char M[ ][ ], char player, String namePlayer1, String namePlayer2){
        int empate = 0,cont = 0;
        
        //Checa possibilidade de vitória na horizontal
        for(int line = 0; line < 3; line++){
            for(int col = 0; col < 1; col++){
              if(M[line][col] == 'o' && M[line][col+1] == 'o' && M[line][col+2] == 'o') {
                return 1;
              } else if(M[line][col] == 'x' && M[line][col+1] == 'x' && M[line][col+2] == 'x') {
                return 2;
              }  
            }
        }
        
        //Checa possibilidade de vitória na vertical
        for(int col = 0; col < 3; col++){
            for(int line = 0; line < 1; line++){
              if(M[line][col] == 'o' && M[line+1][col] == 'o' && M[line+2][col] == 'o') {
                return 1;
              } else if(M[line][col] == 'x' && M[line+1][col] == 'x' && M[line+2][col] == 'x') {
                return 2;
              }  
            }
        }
        
        //Checar possibilidade de vitória na diagonal
        for(int line = 0; line < 1; line++){
            for(int col = 0; col < 1; col++){
              if(M[line][col] == 'o' && M[line+1][col+1] == 'o' && M[line+2][col+2] == 'o' || 
              M[line][col+2] == 'o' && M[line+1][col+1] == 'o' && M[line+2][col] == 'o') {
                return 1;
              } else if(M[line][col] == 'x' && M[line+1][col+1] == 'x' && M[line+2][col+2] == 'x' || 
               M[line][col+2] == 'x' && M[line+1][col+1] == 'x' && M[line+2][col] == 'x') {
                return 2;
              }  
            }
        }
        
        //Checar possibilidade de empate
        for(int line = 0; line < 3; line++){
            for(int col = 0; col < 3; col++){
                if (M[line][col] == '-') {
                    cont += 1;
                }
            }
        }
        
        if (cont == 0) {
           return 0;
        }

      return -1;
    }
}
