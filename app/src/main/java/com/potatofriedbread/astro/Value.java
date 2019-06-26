package com.potatofriedbread.astro;

import java.util.List;
import java.util.Arrays;

public class Value {
    static final int RED = 0;
    static final int YELLOW = 1;
    static final int BLUE = 2;
    static final int GREEN = 3;

    static final int AI = 0;
    static final int ONLINE_HUMAN = 1;
    static final int LOCAL_HUMAN = 2;
    static final int REPLAY = 3;

    static final int ONLINE_LAN = 0;
    static final int ONLINE_SERVER = 1;
    static final int LOCAL = 2;

    static final int STATE_ROLL = 0;
    static final int STATE_MOVE_CHESS = 1;
    static final int STATE_CANNOT_MOVE = 2;

    static final int ROLL_X = 17;
    static final int ROLL_Y = 17;

    static final int AI_TYPE[] = new int[]{0, 1, 2, 3};

    static final int TERMINAL = 56;
    static final int CONFLICT = 53; // 这个版本大跳跃还能撞上终点前的飞机吗
    static final int FLY_POINT = 18;
    static final List<Integer> JUMP_POINT = (List<Integer>)Arrays.asList(2, 6, 10, 14, /*18,*/ 22, 26, 30, 34, 38, 42, 46, 50);
    static final List<Integer> TAKE_OFF_NUM = (List<Integer>)Arrays.asList(6);
    static final List<Integer> COMBO_NUM = (List<Integer>)Arrays.asList(6);

    static final String[] PLAYER_COLOR = new String[]{"Red", "Yellow", "Blue", "Green"};

    static final int[] RED_PATH_X = new int[]{
            25,
            23, 24, 24, 23, 25, 27, 29,
            31, 32, 32, 32, 32, 32, 31,
            29, 27, 25, 23, 24, 24, 23,
            21, 19, 17, 15, 13,
            11, 10, 10, 11,  9,  7,  5,
             3,  2,  2,  2,  2,  2,  3,
             5,  7,  9, 11, 10, 10, 11,
            13, 15,
            17, 17, 17, 17, 17, 17, 17
    };
    static final int[] YELLOW_PATH_X = new int[]{
            33,
            31, 29, 27, 25, 23, 24, 24,
            23, 21, 19, 17, 15, 13, 11,
            10, 10, 11,  9,  7,  5,  3,
             2,  2,  2,  2,  2,
             3,  5,  7,  9, 11, 10, 10,
            11, 13, 15, 17, 19, 21, 23,
            24, 24, 23, 25, 27, 29, 31,
            32, 32,
            32, 29, 27, 25, 23, 21, 19
    };
    static final int[] BLUE_PATH_X = new int[]{
             9,
            11, 10, 10, 11,  9,  7,  5,
             3,  2,  2,  2,  2,  2,  3,
             5,  7,  9, 11, 10, 10, 11,
            13, 15, 17, 19, 21,
            23, 24, 24, 23, 25, 27, 29,
            31, 32, 32, 32, 32, 32, 31,
            29, 27, 25, 23, 24, 24, 23,
            21, 19,
            17, 17, 17, 17, 17, 17, 17
    };
    static final int[] GREEN_PATH_X = new int[]{
             1,
             3,  5,  7,  9, 11, 10, 10,
            11, 13, 15, 17, 19, 21, 23,
            24, 24, 23, 25, 27, 29, 31,
            32, 32, 32, 32, 32,
            31, 29, 27, 25, 23, 24, 24,
            23, 21, 19, 17, 15, 13, 11,
            10, 10, 11,  9,  7,  5,  3,
             2,  2,
             2,  5,  7,  9, 11, 13, 15
    };

    static final int[] RED_PATH_Y = new int[]{
             1,
             3,  5,  7,  9, 11, 10, 10,
            11, 13, 15, 17, 19, 21, 23,
            24, 24, 23, 25, 27, 29, 31,
            32, 32, 32, 32, 32,
            31, 29, 27, 25, 23, 24, 24,
            23, 21, 19, 17, 15, 13, 11,
            10, 10, 11,  9,  7,  5,  3,
             2,  2,
             2,  5,  7,  9, 11, 13, 15
    };
    static final int[] YELLOW_PATH_Y = new int[]{
            25,
            23, 24, 24, 23, 25, 27, 29,
            31, 32, 32, 32, 32, 32, 31,
            29, 27, 25, 23, 24, 24, 23,
            21, 19, 17, 15, 13,
            11, 10, 10, 11,  9,  7,  5,
             3,  2,  2,  2,  2,  2,  3,
             5,  7,  9, 11, 10, 10, 11,
            13, 15,
            17, 17, 17, 17, 17, 17, 17
    };
    static final int[] BLUE_PATH_Y = new int[]{
            33,
            31, 29, 27, 25, 23, 24, 24,
            23, 21, 19, 17, 15, 13, 11,
            10, 10, 11,  9,  7,  5,  3,
             2,  2,  2,  2,  2,
             3,  5,  7,  9, 11, 10, 10,
            11, 13, 15, 17, 19, 21, 23,
            24, 24, 23, 25, 27, 29, 31,
            32, 32,
            32, 29, 27, 25, 23, 21, 19
    };
    static final int[] GREEN_PATH_Y = new int[]{
             9,
            11, 10, 10, 11,  9,  7,  5,
             3,  2,  2,  2,  2,  2,  3,
             5,  7,  9, 11, 10, 10, 11,
            13, 15, 17, 19, 21,
            23, 24, 24, 23, 25, 27, 29,
            31, 32, 32, 32, 32, 32, 31,
            29, 27, 25, 23, 24, 24, 23,
            21, 19,
            17, 17, 17, 17, 17, 17, 17
    };

    static final int[][] PATHS_X = new int[][]{
            RED_PATH_X,
            YELLOW_PATH_X,
            BLUE_PATH_X,
            GREEN_PATH_X
    };

    static final int[][] PATHS_Y = new int[][]{
            RED_PATH_Y,
            YELLOW_PATH_Y,
            BLUE_PATH_Y,
            GREEN_PATH_Y
    };

    static final int[] RED_START_X = new int[]{
            29, 31, 29, 31
    };
    static final int[] YELLOW_START_X = new int[]{
            29, 31, 29, 31
    };
    static final int[] BLUE_START_X = new int[]{
            3, 5, 3, 5
    };
    static final int[] GREEN_START_X = new int[]{
            3, 5, 3, 5
    };

    static final int[] RED_START_Y = new int[]{
            3, 3, 5, 5
    };
    static final int[] YELLOW_START_Y = new int[]{
            29, 29, 31, 31
    };
    static final int[] BLUE_START_Y = new int[]{
            29, 29, 31, 31
    };
    static final int[] GREEN_START_Y = new int[]{
            3, 3, 5, 5
    };


    static final int[][] STARTS_X = new int[][]{
            RED_START_X,
            YELLOW_START_X,
            BLUE_START_X,
            GREEN_START_X
    };

    static final int[][] STARTS_Y = new int[][]{
            RED_START_Y,
            YELLOW_START_Y,
            BLUE_START_Y,
            GREEN_START_Y
    };
}
