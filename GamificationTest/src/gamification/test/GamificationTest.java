package gamification.test;

import gamification.api.FailedExecutionException;
import gamification.api.GameRule;
import gamification.api.GamificationFacade;
import gamification.rules.RecordPoints;
import gamification.rules.TrueFalseReturn;
import gamification.user.User;
import gamification.user.UserRegistry;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GamificationTest {
    @AfterEach
    public void afterEachTest() {
        GamificationFacade.getInstance().clearRules();
    }

    @Test
    @DisplayName("Add points before executing task")
    public void basicFrameworkUsage() throws FailedExecutionException {
        // Configure the user
        User user = new User("Tester");
        UserRegistry.setCurrentUser(user);

        // Configure the rule
        GameRule gameRule = new RecordPoints(10);
        // We assign the Game Rule to DummyTask.class
        // In the setGameRule class, we specified that we can only assign rules to Task-implementing classes
        GamificationFacade.getInstance().setGameRule(gameRule, DummyTask.class);

        // Execute the task
        GamificationFacade.getInstance().execute(new DummyTask());
        GamificationFacade.getInstance().execute(new DummyTask());

        // Verify the points
        assertEquals(20, user.getPoints());
        assertEquals(10, user.getLastPointsGot());
    }

    @Test
    @DisplayName("Add points depending on output")
    public void ruleThatDependsOnReturn() throws FailedExecutionException {
        // Configure the user
        User user = new User("Tester");
        UserRegistry.setCurrentUser(user);

        // Configure the rule
        GameRule gameRule = new TrueFalseReturn(20,-5);
        GamificationFacade.getInstance().setGameRule(gameRule, DummyTaskTrueFalse.class);

        // Execute the task
        GamificationFacade.getInstance().execute(new DummyTaskTrueFalse(true));
        GamificationFacade.getInstance().execute(new DummyTaskTrueFalse(false));
        GamificationFacade.getInstance().execute(new DummyTaskTrueFalse(true));

        // Verify the points
        assertEquals(35, user.getPoints());
        assertEquals(20, user.getLastPointsGot());
    }

    @Test
    @DisplayName("Add points based on multiple rules")
    public void moreThanOneRule() throws FailedExecutionException {
        // Configure the user
        User user = new User("Tester");
        UserRegistry.setCurrentUser(user);

        // Configure the rules
        GameRule gameRule1 = new TrueFalseReturn(20,-5);
        GamificationFacade.getInstance().setGameRule(gameRule1, DummyTaskTrueFalse.class);

        GameRule gameRule2 = new RecordPoints(20);
        GamificationFacade.getInstance().setGameRule(gameRule2, DummyTaskTrueFalse.class);

        // Execute the task
        // First execution we expect to receive 20 + 20
        GamificationFacade.getInstance().execute(new DummyTaskTrueFalse(true));
        // For the second execution we expect to receive -5 + 20
        GamificationFacade.getInstance().execute(new DummyTaskTrueFalse(false));

        // Verify the points
        assertEquals(55, user.getPoints());
    }
}
