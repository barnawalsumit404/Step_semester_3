package week4.practice_problems;

import java.util.Arrays;

public class BusRouteRankingEngine implements Comparable<BusRouteRankingEngine> {
    private final String routeCode;
    private final String routeName;
    private final int priority;

    public BusRouteRankingEngine(String routeCode, String routeName, int priority) {
        if (routeCode == null || routeCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Route code cannot be empty.");
        }
        if (routeName == null || routeName.trim().isEmpty()) {
            throw new IllegalArgumentException("Route name cannot be empty.");
        }

        this.routeCode = routeCode.trim();
        this.routeName = routeName.trim();
        this.priority = priority;
    }

    public BusRouteRankingEngine(String routeCode, String routeName) {
        this(routeCode, routeName, 1);
    }

    @Override
    public int compareTo(BusRouteRankingEngine other) {
        if (other == null) {
            return 1;
        }

        int byPriority = Integer.compare(other.priority, this.priority);
        if (byPriority != 0) {
            return byPriority;
        }

        int byCode = this.routeCode.compareToIgnoreCase(other.routeCode);
        if (byCode != 0) {
            return byCode;
        }

        return this.routeName.compareToIgnoreCase(other.routeName);
    }

    public static BusRouteRankingEngine[] rankRoutes(BusRouteRankingEngine[] routes) {
        if (routes == null) {
            return new BusRouteRankingEngine[0];
        }

        BusRouteRankingEngine[] sorted = Arrays.copyOf(routes, routes.length);
        for (int i = 1; i < sorted.length; i++) {
            BusRouteRankingEngine key = sorted[i];
            int j = i - 1;

            while (j >= 0 && sorted[j].compareTo(key) > 0) {
                sorted[j + 1] = sorted[j];
                j--;
            }

            sorted[j + 1] = key;
        }

        return sorted;
    }

    public String getRouteCode() {
        return routeCode;
    }

    public static void main(String[] args) {
        BusRouteRankingEngine[] routes = {
                new BusRouteRankingEngine("RT205L", "Airport Express", 3),
                new BusRouteRankingEngine("rt201j", "City Central", 4),
                new BusRouteRankingEngine("RT299T", "Night Service")
        };

        BusRouteRankingEngine[] ranked = rankRoutes(routes);
        for (BusRouteRankingEngine route : ranked) {
            System.out.println(route.getRouteCode());
        }
    }
}
