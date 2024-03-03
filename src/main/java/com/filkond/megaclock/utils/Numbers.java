package com.filkond.megaclock.utils;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum Numbers {
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

    private final boolean[][] blocks;

    Numbers(boolean[][] blocks) {
        this.blocks = blocks;
    }
}
