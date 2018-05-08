/*
 * Homework Assignment #6: "8-Puzzle"
 *
 *  - Board class for solving "8-Puzzle" Programming Assignment
 *
 *  Compilation:  javac Board.java Queue.java
 *
 * @ Student ID :
 * @ Name       :
 **/

import java.io.File;
import java.util.Scanner;

public class Board {

    private int[][] tiles;
    private int N;
    /*********************************
     * YOU CAN ADD MORE HERE
     *********************************/

    // construct a board from an N-by-N array of blocks
    // (where blocks[i][j] = block in row i, column j)
    public Board(int[][] blocks) {
        if (blocks == null)
            throw new java.lang.NullPointerException();

        N = blocks.length;
        if (N < 2 || N > 128)
            throw new IllegalArgumentException("N must be <= 128");

        tiles = new int[N][N];
        for (int i = 0; i < N; i++)
            System.arraycopy(blocks[i], 0, tiles[i], 0, blocks[i].length);
    }

    // board dimension N
    public int dimension() {
        return N;
    }

    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        int manhattan = 0;
		int R, i, j, iR, jR;
		for(i=0;i<N;i++){
			for(j=0;j<N;j++){
				R=tiles[i][j];
				if(R!=(i*N+(j+1))){
					R/N=iR;
					R%N-1=jR;
				}
			}
		}			
        return manhattan;   // TODO
    }

    // is this board the goal board?
    public boolean isGoal() {
		int i, j, val=1;
		for(i=0;i<N;i++){
			for(j=0;j<N;j++){
				if(tiles[i][j]!=val)
					return false;
			}
		}
        return true;   // TODO
    }

    private void swap(int[][] blocks, int r1, int c1, int r2, int c2) {
        if (r1 < 0 || c1 < 0 || r2 < 0 || c2 < 0)
            throw new IndexOutOfBoundsException("row/col index < 0");
        if (r1 >= N || c1 >= N || r2 >= N || c2 >= N)
            throw new IndexOutOfBoundsException("row/col index >= N");

        // swap blocks
    	int temp;
		temp=blocks[r1][c1];
		blocks[r1][c1]=blocks[c2][r2];
		blocks[r2][c2]=temp;
	}

    // a board that is obtained by exchanging two adjacent blocks in the same row
    public Board twin() {
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            System.arraycopy(tiles[i], 0, blocks[i], 0, tiles[i].length);
		Random rand=new Random();
		int r1,c1,r2,c2;

		r1=r2=nextInt(N)+1;
		do{
			c1=nextInt(N)+1;
			c2=c1+1;
		}while(c2>=N+1);//swap adjacent block at the same row
		swap(blocks,r1,c1,r2,c2);
     
        return new Board(blocks);
    }

    // does this board equal y?
    public boolean equals(Object y) {

    	if(y instanceof Board){
			return this==y;
 
        return false;    // TODO
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {

        Queue<Board> nbrs = new Queue<Board>();
		
		int zR,zC;
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				if(tiles[i][j]==0){
					zR=i;	
					zC=j;
					break;
				}
			}
		}

		nbrs.offer(//
        // put all neighbor boards into the queue

		
        return nbrs;
    }

    // string representation of this board (in the output format specified below)
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tiles[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    // unit tests (DO NOT MODIFY)
    public static void main(String[] args) {
        // read the input file
        Scanner in;
        try {
            in = new Scanner(new File(args[0]), "UTF-8");
        } catch (Exception e) {
            System.out.println("invalid or no input file ");
            return;
        }

        // create initial board from file
        int N = in.nextInt();
        int[][] blocks = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                blocks[i][j] = (int)in.nextInt();
                if (blocks[i][j] >= N*N)
                    throw new IllegalArgumentException("value must be < N^2");
                if (blocks[i][j] < 0)
                    throw new IllegalArgumentException("value must be >= 0");
            }
        }

        Board initial = new Board(blocks);

        System.out.println("\n=== Initial board ===");
        System.out.println(" - manhattan = " + initial.manhattan());
        System.out.println(initial.toString());

        Board twin = initial.twin();

        System.out.println("\n=== Twin board ===");
        System.out.println(" - manhattan = " + twin.manhattan());
        System.out.println(" - equals = " + initial.equals(twin));
        System.out.println(twin.toString());

        System.out.println("\n=== Neighbors ===");
        for (Board board : initial.neighbors())
            System.out.println(board);
    }
}

