package model;
/**
 * enumeration of 5 movie type
 */
public enum MovieType {
    /**
     * 2D movie
     */
    TwoD {
        @Override
        public String toString() {
            return "2D";
        }
    },
    /**
     * 3D movie
     */
    ThreeD {
        @Override
        public String toString() {
            return "3D";
        }
    },
    /**
     * IMAX movie
     */
    IMAX,
    /**
     * Blockbuster movie
     */
    Blockbuster,
    /**
     * Other movie
     */
    OTHERS
}
