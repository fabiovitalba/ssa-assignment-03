package gamification.user;

// This is like a "Singleton" per thread. Where the scope is the thread.
public class UserRegistry {
    // This ThreadLocal will only store ONE user per thread
    // This will help us get the user of the current thread.
    private static ThreadLocal<User> currentUser;

    public static void setCurrentUser(User user) {
        currentUser.set(user);
    }
    public static User getCurrentUser() {
        return currentUser.get();
    }
}
