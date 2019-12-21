package model.constant;

import java.util.Random;

public enum DirectionConstant {
    UP, LEFT, RIGHT, DOWN;

    public static DirectionConstant getRandomDirection(){
        Random rand = new Random();
        DirectionConstant res;
        switch (rand.nextInt(4)){
            case 1:
                res = UP;
                break;
            case 2:
                res = DOWN;
                break;
            case 3:
                res = RIGHT;
                break;
            case 4:
                res = LEFT;
                break;
            default:
                res = UP;
                break;
        }
        return res;
    }
}
