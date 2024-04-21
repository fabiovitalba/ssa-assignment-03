package gamification.conditions;

import gamification.api.GameCondition;

public class PointsWon implements GameCondition {
    private int minimumNoOfPoints;

    public PointsWon(int minimumNoOfPoints) {
        this.minimumNoOfPoints = minimumNoOfPoints;
    }

    @Override
    public boolean evaluate() {
        return false;
    }
}
