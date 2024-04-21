package gamification.listeners;

import gamification.api.GamificationListener;

public class CongratulateUserInConsole implements GamificationListener {
    private String message;

    public CongratulateUserInConsole(String message) {
        this.message = message;
    }

    @Override
    public void execute() {
        System.out.println(message);
    }
}
