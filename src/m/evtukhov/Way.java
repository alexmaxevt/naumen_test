package m.evtukhov;

public class Way {
    private final char[][] map;
    private final RouteFinder routeFinder = new Route();
    Way(char[]... array){
        map = array;
    }

    char[][] GetWay(){
        return routeFinder.findRoute(map);
    }
}
