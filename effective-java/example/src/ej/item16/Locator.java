package ej.item16;

public class Locator {
    private static class Location {
        public double x;
        public double y;
    }

    public Location getLocation() {
        Location location = new Location();
        location.x = 10;
        location.y = 20;
        return location;
    }
}
