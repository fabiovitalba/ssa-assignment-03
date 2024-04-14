package gamification.user;

import java.util.HashSet;
import java.util.Set;

public class User {
    private String name;
    private int points = 0;
    private Set<String> badges = new HashSet<String>();
    private String lastBadgeGot;
    private int lastPointsGot;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public Set<String> getBadges() {
        return badges;
    }

    public String getLastBadgeGot() {
        return lastBadgeGot;
    }

    public int getLastPointsGot() {
        return lastPointsGot;
    }

    public void changePointsBy(int nrOfPoints) {
        points += nrOfPoints;
        lastPointsGot = nrOfPoints;
    }

    public void addBadge(String badgeName) {
        badges.add(badgeName);
        lastBadgeGot = badgeName;
    }

    public void removeBadge(String badgeName) {
        badges.remove(badgeName);
    }
}
