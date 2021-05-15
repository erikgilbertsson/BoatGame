package se.lth.MAMN01.team4.boatgame;

public enum Difficulty {
    D0 (10, 0, 0, false),
    D1 (10, 1, 0, false),
    D2 (12, 2, 1, false),
    D3 (13, 2, 1, true),
    D4 (14, 3, 1, false),
    D5 (15, 3, 1, false),
    D6 (16, 4, (float)1.5, false),
    D7 (17, 4, (float)1.5, false),
    D8 (18, 4, (float)1.5, true),
    D9 (18, 4, 2, false);

    private final float ySpeed;
    private final int nbrOfCliffs;
    private final float windStrength;
    private final boolean clouds;

    Difficulty(float ySpeed, int nbrOfCliffs, float windStrength, boolean clouds) {
        this.ySpeed = ySpeed;
        this.nbrOfCliffs = nbrOfCliffs;
        this.windStrength = windStrength;
        this.clouds = clouds;
    }

    public Difficulty getNext() {
        Difficulty[] ies = this.getClass().getEnumConstants();
        return this.ordinal() != ies.length - 1 ? ies[this.ordinal() + 1] : this;
    }

    public float getYSpeed() {
        return ySpeed;
    }

    public double getNbrOfCliffs() {
        return nbrOfCliffs;
    }

    public float getWindStrength() {
        return windStrength;
    }

    public boolean isClouds() {
        return clouds;
    }
}
