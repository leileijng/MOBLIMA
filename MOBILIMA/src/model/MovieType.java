package model;

public enum MovieType {
    TwoD {
        @Override
        public String toString() {
            return "2D";
        }
    },
    ThreeD {
        @Override
        public String toString() {
            return "3D";
        }
    },
    IMAX,
    Blockbuster,
    OTHERS
}
