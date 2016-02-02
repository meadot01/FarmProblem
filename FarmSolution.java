

import java.util.*;

/**
 * Solves the Farm problem - Programming Case Study - General Analytical Programming
 *
 * Created by Todd Meadows on 2/1/16.
 */
public class FarmSolution {

    private static final int MAX_X = 399;
    private static final int MAX_Y = 599;

    public static void main(String args[]) throws Exception {
        List<Rectangle> barrenLand = readRectanglesFromArgs(args);
        List<Set<Coordinate>> fertileCoordinateSets = new ArrayList<>();
        for (int x = 0; x <= MAX_X ; x++) {
            for (int y = 0; y <= MAX_Y; y++) {
                checkCoordinate(new Coordinate(x, y),null,fertileCoordinateSets, barrenLand );
            }
        }

        if (fertileCoordinateSets.size() == 0 ) {
            System.out.println("No fertile land");
        } else {
            System.out.print("Fertile land areas = ");
            for(Set<Coordinate> fertileCoordinates : fertileCoordinateSets) {
                System.out.print(fertileCoordinates.size() + "  ");
            }
            System.out.println();
        }
    }

    private static void checkCoordinate(Coordinate coordinate, Set<Coordinate> currentFertileSet, List<Set<Coordinate>> fertileSets, List<Rectangle> barrenRectangles) {
        // If the coordinate is already in fertileSets we already checked it - if it is in barrenRectangles we don't need to check neighbors.
        if (!coordinateInFertileSets(coordinate, fertileSets) && !coordinateInBarrenRectangles(coordinate, barrenRectangles)) {
            //This is a newly found fertile coordinate
            if (currentFertileSet == null) {
                // This is a new fertile area - create a new set for it.
                currentFertileSet = new HashSet<>();
                fertileSets.add(currentFertileSet);
            }
            currentFertileSet.add(coordinate);

            // Now we will recursively check the coordinates around this coordinate.  If any of them are also fertile it should be added
            // to the same fertile set.

            // Check coordinate above
            if (coordinate.getX() != MAX_X) {
                checkCoordinate(new Coordinate(coordinate.getX() + 1, coordinate.getY()), currentFertileSet, fertileSets, barrenRectangles );
            }
            // Check coordinate to the right
            if (coordinate.getY() != MAX_Y) {
                checkCoordinate(new Coordinate(coordinate.getX(), coordinate.getY() + 1), currentFertileSet, fertileSets, barrenRectangles);
            }
            // Check coordinate below
            if (coordinate.getX() != 0) {
                checkCoordinate(new Coordinate(coordinate.getX() - 1, coordinate.getY()), currentFertileSet, fertileSets, barrenRectangles );
            }
            // Check coordinate to the left
            if (coordinate.getY() != 0) {
                checkCoordinate(new Coordinate(coordinate.getX(), coordinate.getY() - 1), currentFertileSet, fertileSets, barrenRectangles );
            }        }
    }

    // Determine if passed coordinate is in any of the given sets of Coordinates
    private static boolean coordinateInFertileSets(Coordinate coordinate, List<Set<Coordinate>> fertileSets) {
        return fertileSets.stream()
                .filter(coordinateSet -> coordinateSet.contains(coordinate))
                .findAny()
                .isPresent();
    }

    // Determine is passed coordinate is in any of the list of barren rectangles
    private static boolean coordinateInBarrenRectangles(Coordinate coordinate, List<Rectangle> barrenRectangles) {
        return barrenRectangles.stream()
                .filter(rectangle -> rectangle.containsCoordinate(coordinate))
                .findAny()
                .isPresent();
    }

    private static List<Rectangle> readRectanglesFromArgs(String args[]) {
        List<Rectangle> barrenLand = new ArrayList<>();
        for( String currentArgument : args) {
            StringTokenizer argTokens = new StringTokenizer(currentArgument);
            if (argTokens.countTokens() != 4) {
                throw new RuntimeException("Improper arguments.  Each argument should contain 4 integers");
            }
            int blX, blY, trX, trY;
            try {
                blX = Integer.parseInt(argTokens.nextToken());
                blY = Integer.parseInt(argTokens.nextToken());
                trX = Integer.parseInt(argTokens.nextToken());
                trY = Integer.parseInt(argTokens.nextToken());
            } catch (Exception e) {
                throw new RuntimeException("Problem parsing integer argument", e);
            }
            barrenLand.add(new Rectangle(blX, blY, trX, trY));
        }
        return barrenLand;
    }


}
