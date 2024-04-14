package gamification.api;

public class GamificationFacade {
    // This class needs to be a singleton.
    // In order to achieve this, we create a static instance that can
    // be called by anyone.
    private static GamificationFacade instance = new GamificationFacade();
    public static GamificationFacade getInstance() {
        return instance;
    }

    // We render the constructor private, so that no other class may
    // call the constructor outside of this class.
    private GamificationFacade() {
    }

    public void setGameRule(GameRule rule, Class<? extends Task> task) {
    }

    public Object execute(Task task) throws FailedExecutionException {
        Object returned = task.execute();
        return returned;
    }
}
