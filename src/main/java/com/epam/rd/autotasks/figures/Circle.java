package com.epam.rd.autotasks.figures;

class Circle extends Figure{
    private final Point center;
    private final double radius;

    public Circle(Point center, double radius) {
        if (radius <= 0 || center == null){
            throw new IllegalArgumentException();
        }
        this.center = center;
        this.radius = radius;
    }
    @Override
    public Point centroid() {
        return center;
    }

    @Override
    public boolean isTheSame(Figure figure) {
        if (!(figure instanceof Circle)){
            return false;
        }
        Circle other = (Circle) figure;
        return areSamePoints(this.center, other.center) &&
                Math.abs(this.radius - other.radius) < 1e-6;
    }
    private boolean areSamePoints(Point p1, Point p2) {
        return Math.abs(p1.getX() - p2.getX()) < 1e-6 &&
                Math.abs(p1.getY() - p2.getY()) < 1e-6;
    }

}
