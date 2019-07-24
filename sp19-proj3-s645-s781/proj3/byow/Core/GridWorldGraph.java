package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import byow.hw4.AStarGraph;
import byow.hw4.WeightedEdge;
import byow.proj2ab.Point;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GridWorldGraph implements AStarGraph<Point> {
    private HashMap<Point, TETile> pointType;

    public GridWorldGraph(HashMap<Point, TETile> pointType) {
        this.pointType = pointType;
    }

    @Override
    public List<WeightedEdge<Point>> neighbors(Point v) {
        ArrayList<WeightedEdge<Point>> result = new ArrayList<>();
        Point upper = PointUtils.upperPoint(v);
        Point lower = PointUtils.lowerPoint(v);
        Point left = PointUtils.leftPoint(v);
        Point right = PointUtils.rightPoint(v);

        addToNeighbors(v, result, upper, lower);

        addToNeighbors(v, result, left, right);
        return result;
    }

    private void addToNeighbors(Point v, ArrayList<WeightedEdge<Point>> result,
                                Point left, Point right) {
        if (PointUtils.insideWindow(left)) {
            result.add(new WeightedEdge<>(v, left, generateWeight(left)));
        }
        if (PointUtils.insideWindow(right)) {
            result.add(new WeightedEdge<>(v, right, generateWeight(right)));
        }
    }

    private double generateWeight(Point v) {
        if (pointType.get(v).equals(Tileset.NOTHING)) {
            return 1.0;
        }
        if (pointType.get(v).equals(Tileset.WALL)) {
            return 1.0;
        }
        if (pointType.get(v).equals(Tileset.FLOOR)) {
            return 1.0;
        }
        throw new IllegalArgumentException("v must be either nothing, wall or floor!");
    }

    @Override
    public double estimatedDistanceToGoal(Point s, Point goal) {
//        return Math.sqrt(Math.pow(s.getX() - goal.getX(), 2) +
//        Math.pow(s.getY() - goal.getY(), 2));
        return 0;
    }
}
