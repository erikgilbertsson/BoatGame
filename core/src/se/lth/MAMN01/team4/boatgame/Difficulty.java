package se.lth.MAMN01.team4.boatgame;

public enum Difficulty {
    D0 (10, 0, 0, false),
    D1 (15, 1, 0, false),
    D2 (15, 2, 1, false),
    D3 (16, 3, 1, true),
    D4 (16, 3, (float)1.5, false),
    D5 (17, 3, (float)1.5, false),
    D6 (17, 4, 2, true),
    D7 (18, 4, 2, false),
    D8 (18, 4, (float)2.3, true),
    D9 (19, 5, (float)2.3, false),
    D10 (20, 5, -1, true); // -1 indicating continual increase decided in director

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
