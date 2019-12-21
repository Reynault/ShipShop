package model.informations;

import model.constant.GridConstant;

public final class Review {
    private final boolean dataSet;

    private final boolean end;

    private final int xPlayer, yPlayer, xEnnemy, yEnnemy;

    private final GridConstant player, ennemy;

    public Review(boolean dataSet, boolean end, int xPlayer, int yPlayer, int xEnnemy, int yEnnemy, GridConstant player, GridConstant ennemy) {
        this.dataSet = dataSet;
        this.end = end;
        this.xPlayer = xPlayer;
        this.yPlayer = yPlayer;
        this.xEnnemy = xEnnemy;
        this.yEnnemy = yEnnemy;
        this.player = player;
        this.ennemy = ennemy;
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

    public boolean isDataSet() {
        return dataSet;
    }
}
