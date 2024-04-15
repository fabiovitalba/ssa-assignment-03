package gamification.rules;

import gamification.api.FailedExecutionException;
import gamification.api.GameRule;
import gamification.api.Task;
import gamification.user.User;
import gamification.user.UserRegistry;

public class BadgeCollector implements GameRule {
	
	private int minAmountOfBadges;
	
	public BadgeCollector(int minAmountOfBadges) {
		this.minAmountOfBadges = minAmountOfBadges;
	}

	@Override
	public void beforeExecution(Task task) {
	}

	@Override
	public void whenTaskReturns(Task task, Object returned) {
		User user = UserRegistry.getCurrentUser();
		if (user.getBadges().size() >= minAmountOfBadges)
			user.addBadge("Badge Collector");
	}

	@Override
	public void whenTaskThrowsException(Task task, FailedExecutionException exception) {
	}

}
