package gamification.api;

public interface GameCondition {
	boolean evaluate();
	boolean persistent();
}
