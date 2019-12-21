package model.informations;

import model.constant.GridConstant;

public final class Review {
    private final boolean canAttack;

    private final boolean alreadySunk;

    private final boolean dataSet;

    private final boolean end;

    private final int xPlayer, yPlayer, xEnnemy, yEnnemy;

    private final GridConstant player, ennemy;

    public Review(boolean canAttack, boolean alreadySunk,boolean dataSet, boolean end, int xPlayer, int yPlayer, int xEnnemy, int yEnnemy, GridConstant player, GridConstant ennemy) {
        this.canAttack = canAttack;
        this.alreadySunk = alreadySunk;
        this.dataSet = dataSet;
        this.end = end;
        this.xPlayer = xPlayer;
        this.yPlayer = yPlayer;
        this.xEnnemy = xEnnemy;
        this.yEnnemy = yEnnemy;
        this.player = player;
        this.ennemy = ennemy;
    }

    public boolean hasAlreadySunk() {
        return alreadySunk;
    }

    public boolean isEnd() {
        return end;
    }

    public int getxPlayer() {
        return xPlayer;
    }

    public int getyPlayer() {
        return yPlayer;
    }

    public int getxEnnemy() {
        return xEnnemy;
    }

    public int getyEnnemy() {
        return yEnnemy;
    }

    public GridConstant getPlayer() {
        return player;
    }

    public GridConstant getEnnemy() {
        return ennemy;
    }

    public boolean ifCanAttack() {
        return canAttack;
    }

    public boolean isDataSet() {
        return dataSet;
    }
}
