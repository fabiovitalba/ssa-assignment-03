package gamification.test;

import gamification.api.GameRule;
import gamification.api.GamificationFacade;
import gamification.rules.RecordPoints;
import gamification.user.User;

import gamification.user.UserRegistry;
import org.junit.jupiter.api.Test;

public class GamificationTest {
    @Test
    void basicFrameworkUsage() {
        // Configure the user
        User user = new User("Tester");
        UserRegistry.setCurrentUser(user);

        // Configure the rule
        GameRule gameRule = new RecordPoints(10);
        // We assign the Game Rule to DummyTask.class
        // In the setGameRule class, we specified that we can only assign rules to Task-implementing classes
        GamificationFacade.getInstance().setGameRule(gameRule, DummyTask.class);
    }
}
