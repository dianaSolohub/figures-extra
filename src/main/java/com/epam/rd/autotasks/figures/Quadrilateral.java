package com.epam.rd.autotasks.figures;

import java.util.*;

class Quadrilateral extends Figure{
    private final Point a;
    private final Point b;
    private final Point c;
    private final Point d;

    public Quadrilateral(Point a, Point b, Point c, Point d) {
        if (a == null || b == null || c == null || d == null || !isValidQuadrilateral(a, b, c, d)) {
            throw new IllegalArgumentException();
        }
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    private boolean isValidQuadrilateral(Point a, Point b, Point c, Point d) {
        // Check if the points form a convex quadrilateral
        double crossProductABxCD = crossProduct(a, b, c) * crossProduct(a, b, d);
        double crossProductBCxAD = crossProduct(b, c, a) * crossProduct(b, c, d);

        return crossProductABxCD > 0 && crossProductBCxAD > 0;
    }

    private double crossProduct(Point p1, Point p2, Point p3) {
        double vectorABx = p2.getX() - p1.getX();
        double vectorABy = p2.getY() - p1.getY();
        double vectorACx = p3.getX() - p1.getX();
        double vectorACy = p3.getY() - p1.getY();

        return vectorABx * vectorACy - vectorABy * vectorACx;
    }


    @Override
    public Point centroid() {
        Point centrA, centrB, centrC, centrD;
        centrA = new Triangle(b, c, d).centroid();
        centrB = new Triangle(a, c, d).centroid();
        centrC = new Triangle(a, b, d).centroid();
        centrD = new Triangle(a, b, c).centroid();
        return new Segment(centrA, centrC).intersection(new Segment(centrB, centrD));
    }

    private Point triangleCentroid(Point p1, Point p2, Point p3) {
        double centerX = (p1.getX() + p2.getX() + p3.getX()) / 3.0;
        double centerY = (p1.getY() + p2.getY() + p3.getY()) / 3.0;
        return new Point(centerX, centerY);
    }

    @Override
    public boolean isTheSame(Figure figure) {
        if (!(figure instanceof Quadrilateral)) {
            return false;
        }
        List<Point> thisVertices = this.getVertices();
        Quadrilateral quadrilateral = (Quadrilateral) figure;
        List<Point> otherVertices = quadrilateral.getVertices();

        if (thisVertices.size() != otherVertices.size()) {
            return false;
        }

        for (Point vertex : thisVertices) {
            boolean foundMatch = false;
            for (Point otherVertex : otherVertices) {
                if (areCloseEnough(vertex, otherVertex)) {
                    foundMatch = true;
                    break;
                }
            }
            if (!foundMatch) {
                return false;
            }
        }

        return true;
    }
    public List<Point> getVertices() {
        List<Point> points = new ArrayList<>();
        points.add(a);
        points.add(b);
        points.add(c);
        points.add(d);
        return points;
    }
    private boolean areCloseEnough(Point p1, Point p2) {
        double errorDelta = 0.00001; // Set the error delta based on your requirements
        return Math.abs(p1.getX() - p2.getX()) < errorDelta &&
                Math.abs(p1.getY() - p2.getY()) < errorDelta;
    }
}
