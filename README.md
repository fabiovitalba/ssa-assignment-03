##### ssa-assignment-03
# Gamification Framework
A framework designed to apply concepts of gamification to your Java Applications.

# Index
1. Framework Description
2. Class Diagram
3. Provided Features / Implementations
4. Simple "Hello World" example
* Examples of framework usage


# 1. Framework Description
The Gamification Framework helps developers to add game-elements to their applications, providing a more entertaining user expirience.
The main idea of the Framework is to keep a score for each user and track special achievements through badges that the user can be awarded with.
The framework provides the tooling to extend the execution of tasks with game related rules.

The singleton class `GamificationFacade` handles the execution flow of tasks with connected rules and/or conditions.
The execution flow of a task is extended in this way:

```mermaid
flowchart TD
    A[GamificationFacade.execute] --> B{Retrieve beforeExecution-GameRules}
    B --> |Rules found| C(Execute beforeExecution-GameRules)
    C --> D
    B --> |No Rules found|D{Execute task}
    D -->|Task executed successfully| E(Retrieve whenTaskReturns-GameRules)
    D -->|Task threw exception| F(Retrieve whenTaskThrowsException-GameRules)
    E -->G(Execute rules)
    F -->H(Execute rules)
    G -->I{Retrieve GameConditions}
    H -->I
    I -->|Conditions found|J{Evaluate Condition}
    J -->|Condition true|K(Execute GamificationListener)
    K -->L[Return Object/Exception]
    I -->|No Conditions found|L
    J -->|Condition false|L
```

The framework allows the creation of:
## 1.1 `User`
The `User` class stores information about the user that is currently using the application. The `User`-class must be instantiated and provided to the `UserRegistry` singleton-class in order to work properly.
The `UserRegistry` tracks who's the current user of the thread that the application is executed in.
A `User` has points and badges that can be read or altered using the provided methods.

## 1.2 `Task`
A `Task` is something that is executed by the `GamificationFacade` singleton-class. It may return an Object or throw a `FailedExecutionException`.

## 1.3 `GameRule`
A `GameRule` allows the creation of Rules that are connected to the task execution flow.
The rule can execute code `beforeExecution`, `whenTaskReturns` or `whenTaskThrowsException`, so whenever a task is executed, a Rule can be executed before or after a Task execution.

## 1.4 `GameCondition` and `GamificationListener`
The `GameCondition` and `GamificationListener` classes work together in the framework to allow the creation of more sophisticated rules.
After a task is executed, the `GamificationFacade` iterates over all `GameCondition`s to see if any of them is true.
If a `GameCondition` is actually true, the connected `GamificationListener` is executed.
If a `GameCondition` is not `persistent`, then it is removed from the list of conditions that the `GamificationFacade` tracks, once it was evaluated to true. This essentially means that any listeners connected to this `GameCondition` are only executed once.

# 2. Class Diagram
The following diagram shows the Class Diagram of the `gamification.api`-package.
```mermaid
classDiagram
    namespace api {
        class Task
        class GameRule
        class GameCondition
        class GamificationListener
        class GamificationFacade

        class Task {
            Object execute()
        }

        class GameRule {
            beforeExecution(Task task)
            whenTaskReturns(Task task, Object returned)
            whenTaskThrowsException(Task task, FailedExecutionException exception)
        }

        class GameCondition {
            boolean evaluate()
        }

        class GamificationListener {
            execute()
        }

        class GamificationFacade {

            setGameRule(GameRule rule, Class taskClass)
            setCallback(GameCondition condition, GamificationListener listener)
            Object execute(Task task)
            clearRules()
            clearCallbacks()
        }
    }

    namespace user {
        class User
        class UserRegistry

        class User {
            -name
            -points
            -badges
            -lastBadgeGot
            -lastPointsGot
            changePointsBy(int noOfPoints)
            addBadge(String badgeName)
        }

        class UserRegistry {
            -currentUser
            setCurrentUser(User user)
            getCurrentUser()
        }
    }

    <<Interface>> Task
    <<Interface>> GameRule
    <<Interface>> GameCondition
    <<Interface>> GamificationListener
    GamificationFacade --o Task : Executes
    GamificationFacade --> "*" GameRule : Executes
    GamificationFacade --> "*" GameCondition : Evaluates
    GamificationFacade --> "*" GamificationListener : Executes
    GameCondition -- "*" GamificationListener
    UserRegistry "1" --> "1" User : Tracks
```

# 3. Provided Features / Implementations
This framework includes some example rules and listeners. Here's a small explanation of what you can expect to find.
## 3.1 `GameRule`
### 3.1.1 `AnnoyingPerson`
This rule awards the current User with the `"Annoying"` badge if they run into an exception while executing a task.

### 3.1.2 `BadgeCollector`
This rule awards the current User with the `"Badge Collector"` if they manage to collect a minimum number of badges.

### 3.1.3 `RecordPoints`
This rule awards the current User with points for each task execution, but before the task is executed.

### 3.1.4 `Roguelike`
This rule removes all points and badges from the User if they run into an exception while executing a task. 

### 3.1.5 `TrueFalseReturn`
This rule awards the current User with points based on the return value of the executed Task.
The returned Object of the Task is cast to `Boolean` and if the cast-object is true, then `pointsIfTrue` are awarded, otherwise `pointsIfFalse` are awarded instead.

## 3.2 `GameCondition`
### 3.2.1 `BadgeCollected`
This condition compares the last gained badge with the provided `badgeName`. The condition returns true only if they match.
This condition is not persistent.

### 3.2.2 `PointsWon`
This condition compares the last gained points with the provided `minimumNoOfPoints`. The condition returns true if the last gained points are greater or equal to `minimumNoOfPoints`.
This condition is not persistent.

### 3.2.3 `PointsCollected`
This condition compares the current User's points with the provided `minimumNoOfPoints`. The condition returns true if the current User's points are greater or equal to `minimumNoOfPoints`.

## 3.3 `Gamificationlistener`
### 3.3.1 `ChangePoints`
This listener changes the points of the current User. The provided `pointsToAdd` are added to the current Users points.

### 3.3.1 `CongratulateUserInConsole`
This listener prints a `message` in the console.

# 4. Simple "Hello World" example
```java

```
