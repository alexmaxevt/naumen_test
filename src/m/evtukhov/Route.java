package m.evtukhov;

import java.util.*;

public class Route implements RouteFinder{

    private final List<Points> list = new ArrayList<>();
    private final List<Points> finalList = new ArrayList<>();

    private int[][] WeightMap = new int[][]
    {
        {0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0}
    };

    @Override
    public char[][] findRoute(char[][] map) {
        WeightMap = ResizeWeightMap(map);
        int weight = 0;
        int rows = map.length - 1;
        int columns = map[0].length - 1;
        for (int i = rows; i >= 0; i--)
        {
            for (int j = 0; j <= columns; j++)
            {
                if (IsFinish(i, j, map))
                {
                    list.add(new Points(j, i, weight));
                    list.get(0).IsFinish();
                    GetWeight(list.get(0).getX(), list.get(0).getY(), list.get(0).getWeight(), map);
                    WeightMap[list.get(0).getY()][list.get(0).getX()] = list.get(0).getWeight();
                    break;
                }
            }
        }
        for (int i = 1; ; i++)
        {
            try
            {
                weight = GetWeight(list.get(i).getX(), list.get(i).getY(), list.get(i).getWeight(), map);
                if (weight == -2)
                {
                    break;
                }
            }
            catch (Exception e)
            {
                return null;
            }
        }
        return FindBacktrace(map);
    }

    private char[][] FindBacktrace(char[][] map) {
        int weight = 0;
        for (int i = list.size() - 1; i >= 0; i--)
        {
            if (IsStart(list.get(i).getY(), list.get(i).getX(), map))
            {
                finalList.add(list.get(i));
                weight = list.get(i).getWeight() - 1;
            }
            else if (IsFinish(list.get(i).getY(), list.get(i).getX(), map))
            {
                finalList.add(list.get(i));
                break;
            }
            else if (list.get(i).getWeight() == weight && Math.abs((finalList.get(finalList.size() - 1).getY() - list.get(i).getY())) <= 1 && Math.abs((finalList.get(finalList.size() - 1).getX() - list.get(i).getX())) <= 1)
            {
                finalList.add(list.get(i));
                map[list.get(i).getY()][list.get(i).getX()] = '+';
                weight--;
            }
        }
        return map;
    }

    private int[][] ResizeWeightMap(char[][] map) {
        int[][] newMap;
        if (map.length > WeightMap.length || map[0].length > WeightMap[0].length)
        {
            newMap = new int[map.length][map[0].length];
            for (int i = 0; i < newMap.length; i++)
            {
                for (int j = 0; j < newMap[0].length; j++)
                {
                    newMap[i][j] = 0;
                }
            }
            return newMap;
        }
        return WeightMap;
    }

    private int GetWeight(int x, int y, int weight, char[][] map) {
        int newWeight;
        newWeight = weight + 1;
        int up = y - 1;
        int left = x - 1;
        int down = y + 1;
        int right = x + 1;

        if (GetPoint(x, up, newWeight, map) == -2)
        {
            return -2;
        }
        if (GetPoint(left, y, newWeight, map) == -2)
        {
            return -2;
        }
        if (GetPoint(x, down, newWeight, map) == -2)
        {
            return -2;
        }
        if (GetPoint(right, y, newWeight, map) == -2)
        {
            return -2;
        }

        return newWeight;
    }

    private int GetPoint(int x, int y, int newWeight, char[][] map) {
        if (IsExist(y, x, map) && GetWeight(y, x) == 0 && !IsFinish(y, x, map))
        {
            if (IsWall(y, x, map))
            {
                WeightMap[y][x] = -1;
                return newWeight;
            }
            else
            {
                Points point = new Points(x, y, newWeight);
                list.add(point);
                WeightMap[y][x] = newWeight;
                if (IsStart(y, x, map))
                {
                    list.get(list.size() - 1).IsStart();
                    return -2;
                }
                return newWeight;
            }
        }
        return newWeight;
    }


    private boolean IsWall(int i, int j, char[][] map) {
        return map[i][j] == '#';
    }
    private boolean IsExist(int i, int j, char[][] map) {
        return i < map.length && j < map[0].length && i >= 0 && j >= 0;
    }
    private boolean IsFinish(int i, int j, char[][] map) {
        return map[i][j] == 'X';
    }
    private boolean IsStart(int i, int j, char[][] map) {
        return map[i][j] == '@';
    }
    private int GetWeight(int i, int j) {
        return WeightMap[i][j];
    }
}
