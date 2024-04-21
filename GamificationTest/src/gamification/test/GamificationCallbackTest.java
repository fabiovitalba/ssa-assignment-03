package gamification.test;

import gamification.api.*;
import gamification.conditions.BadgeCollected;
import gamification.conditions.PointsCollected;
import gamification.conditions.PointsWon;
import gamification.listeners.ChangePoints;
import gamification.rules.AnnoyingPerson;
import gamification.rules.RecordPoints;
import gamification.user.User;
import gamification.user.UserRegistry;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GamificationCallbackTest {
    @AfterEach
    public void afterEachTest() {
        GamificationFacade.getInstance().clearRules();
        GamificationFacade.getInstance().clearCallbacks();
    }

    @Test
    @DisplayName("Add bonus points based on GameCondition")
    public void addBonusPointsBasedOnGameCondition() throws FailedExecutionException {
        // Configure the user
        User user = new User("Tester");
        UserRegistry.setCurrentUser(user);

        // Configure the rule
        GameRule gameRule = new RecordPoints(10);
        GamificationFacade.getInstance().setGameRule(gameRule, DummyTask.class);

        GameCondition gameCondition = new PointsWon(10);
        GamificationListener gamificationListener = new ChangePoints(2);
        GamificationFacade.getInstance().setCallback(gameCondition,gamificationListener);

        // Execute the task
        GamificationFacade.getInstance().execute(new DummyTask());
        GamificationFacade.getInstance().execute(new DummyTask());

        // Verify the points
        assertEquals(22, user.getPoints());
        assertEquals(10, user.getLastPointsGot());
    }

    @Test
    @DisplayName("Add bonus points when User receives Badge")
    public void addBonusPointsWhenUserReceivesBadge() throws FailedExecutionException {
        // Configure the user
        User user = new User("Tester");
        UserRegistry.setCurrentUser(user);

        // Configure the rule
        GameRule gameRule = new AnnoyingPerson();
        GamificationFacade.getInstance().setGameRule(gameRule, DummyTask.class);

        GameCondition gameCondition = new BadgeCollected("Annoying");
        GamificationListener gamificationListener = new ChangePoints(-5);
        GamificationFacade.getInstance().setCallback(gameCondition,gamificationListener);

        // Execute the task
        try {
            GamificationFacade.getInstance().execute(new DummyTask(true));
            fail();
        } catch(FailedExecutionException e) {
        }


        // Verify the points
        assertTrue(user.getBadges().contains("Annoying"));
        assertEquals("Annoying", UserRegistry.getCurrentUser().getLastBadgeGot());
        assertEquals(-5,user.getLastPointsGot());
    }

    @Test
    @DisplayName("Always add bonus points based on GameCondition")
    public void alwaysAddBonusPointsBasedOnGameCondition() throws FailedExecutionException {
        // Configure the user
        User user = new User("Tester");
        UserRegistry.setCurrentUser(user);

        // Configure the rule
        GameRule gameRule = new RecordPoints(10);
        GamificationFacade.getInstance().setGameRule(gameRule, DummyTask.class);

        GameCondition gameCondition = new PointsCollected(11);
        GamificationListener gamificationListener = new ChangePoints(2);
        GamificationFacade.getInstance().setCallback(gameCondition,gamificationListener);

        // Execute the task
        GamificationFacade.getInstance().execute(new DummyTask());
        GamificationFacade.getInstance().execute(new DummyTask());
        GamificationFacade.getInstance().execute(new DummyTask());
        GamificationFacade.getInstance().execute(new DummyTask());

        // Verify the points
        assertEquals(46, user.getPoints());
        assertEquals(2, user.getLastPointsGot());
    }
}
