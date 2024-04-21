package gamification.conditions;

import gamification.api.GameCondition;
import gamification.user.UserRegistry;

public class BadgeCollected implements GameCondition {
    private String badgeName;

    public BadgeCollected(String badgeName) {
        this.badgeName = badgeName;
    }

    @Override
    public boolean evaluate() {
        return UserRegistry.getCurrentUser().getLastBadgeGot().equals(badgeName);
    }
}
