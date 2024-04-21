package gamification.conditions;

import gamification.api.GameCondition;
import gamification.user.UserRegistry;

public class PointsCollected implements GameCondition {
    private int minimumNoOfPoints;

    public PointsCollected(int minimumNoOfPoints) {
        this.minimumNoOfPoints = minimumNoOfPoints;
    }

    @Override
    public boolean evaluate() {
        return UserRegistry.getCurrentUser().getPoints() >= minimumNoOfPoints;
    }

    @Override
    public boolean persistent() {
        return true;
    }
}
