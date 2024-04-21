package gamification.listeners;

import gamification.api.GamificationListener;
import gamification.user.UserRegistry;

public class ChangePoints implements GamificationListener {
    private int pointsToAdd;

    public ChangePoints(int pointsToAdd) {
        this.pointsToAdd = pointsToAdd;
    }

    @Override
    public void execute() {
        UserRegistry.getCurrentUser().changePointsBy(pointsToAdd);
    }
}
