package model;
import java.util.*;

public class Layout {
    /**
     * layout if of Layout
     */
    private String layout_id;
    /**
     * row of layout
     */
    private int row;
    /**
     * column of layout
     */
    private int col;
    /**
     * array of seats
     */
    private int[][] seats;

    /**
     * constructor of Layout
     * @param layout_id
     * @param row
     * @param col
     * @param s
     */
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

    /**
     *
     * @return get layout id
     */
    public String getLayout_id() {
        return layout_id;
    }

    /**
     * print the layout
     */
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

    /**
     *
     * @return the number of rows
     */
    public int getRow() {
        return row;
    }

    /**
     *
     * @return the number of columns
     */
    public int getCol() {
        return col;
    }

    /**
     *
     * @return return seat array
     */
    public int[][] getSeats() {
        return seats;
    }
}