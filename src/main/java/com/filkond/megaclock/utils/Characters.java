package com.filkond.megaclock.utils;

public enum Characters {
    COLON(new boolean[][]{
            {true},
            {false},
            {true}
    }),
    ONE(new boolean[][]{
            {false, true, false},
            {true, true, false},
            {false, true, false},
            {false, true, false},
            {true, true, true}
    }),
    TWO(new boolean[][]{
            {true, true, true},
            {false, false, true},
            {true, true, true},
            {true, false, false},
            {true, true, true},
    })

    ;

    public boolean[][] getBlocks() {
        return blocks;
    }

    private final boolean[][] blocks;

    Characters(boolean[][] blocks) {
        this.blocks = blocks;
    }
}
