package model;
import java.util.*;

public class Layout {
    private String layout_id;
    private int row;
    private int col;
    private int[][] seats;

    public Layout(String layout_id, int row, int col, ArrayList<Integer> s) {
        this.layout_id = layout_id;
        this.row = row;
        this.col = col;
        this.seats = new int[row][col];
        for (int i = 0; i < row; i ++) {
            for (int j = 0; j < col; j ++) {
                this.seats[i][j] = s.get(col * i + j);
            }
        }
    }

    public String getLayout_id() {
        return layout_id;
    }

    public void viewLayout() {
        System.out.println("row : " + this.row + " col : " + this.col);
        System.out.print(" ");
        for (int i = 0; i < col; i ++) System.out.print(" " + i + " ");
        System.out.print("\n");
        for (int i = 0; i < row; i ++) {
            System.out.print((char)('A' + i));
            for (int j = 0; j < col; j ++) {
                if (this.seats[i][j] == 0) System.out.print("   ");
                else System.out.print("[ ]");
            }
            System.out.print("\n");
        }
    }
    
    
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int[][] getSeats() {
        return seats;
    }

    public void setSeats(int[][] seats) {
        this.seats = seats;
    }
}