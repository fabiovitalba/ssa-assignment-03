package gamification.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GamificationFacade {
    // ------------- Singleton settings ------------- //
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


    // ------------- Functionality ------------- //
    private Map<Class<? extends Task>, List<GameRule>> classRules = new HashMap<>();
    private Map<GameCondition, List<GamificationListener>> gameConditionListeners = new HashMap<>();

    public void setGameRule(GameRule rule, Class<? extends Task> taskClass) {
        if (!classRules.containsKey(taskClass)) {
            classRules.put(taskClass, new ArrayList<>());
        }
        classRules.get(taskClass).add(rule);
    }

    public Object execute(Task task) throws FailedExecutionException {
        // Execute Rules before Task execution
        classRules.get(task.getClass()).forEach(rule -> rule.beforeExecution(task));

        try {
            Object returned = task.execute();
            // Execute Rules based on Return Value
            classRules.get(task.getClass()).forEach(rule -> rule.whenTaskReturns(task, returned));
            
            executeListeners();
            
            return returned;
        } catch (FailedExecutionException fee) {
            classRules.get(task.getClass()).forEach(rule -> rule.whenTaskThrowsException(task,fee));
            
            executeListeners();
            
            throw fee;
        }
    }
    
    private void executeListeners() {
        List<GameCondition> conditionsToRemove = new ArrayList<>();
        gameConditionListeners.keySet().forEach(condition -> {
        	if (condition.evaluate()) {
                gameConditionListeners.get(condition).forEach(listener -> {
                    listener.execute();
                });
                if (!condition.persistent())
                    conditionsToRemove.add(condition);
            }
        });
        conditionsToRemove.forEach(condition -> {
            gameConditionListeners.remove(condition);
        });
    }

    public void clearRules() {
        classRules = new HashMap<>();
    }
    
    public void setCallback(GameCondition condition, GamificationListener listener) {
    	if (!gameConditionListeners.containsKey(condition)) {
    		gameConditionListeners.put(condition, new ArrayList<>());
    	}
    	gameConditionListeners.get(condition).add(listener);
    }
    
    public void clearCallbacks() {
    	gameConditionListeners = new HashMap<>();
    }
}
