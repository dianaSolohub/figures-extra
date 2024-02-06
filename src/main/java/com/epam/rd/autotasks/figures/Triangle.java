package com.epam.rd.autotasks.figures;

class Triangle extends Figure{
    private final Point a;
    private final Point b;
    private final Point c;

    public Triangle(Point a, Point b, Point c) {
        if (a == null || b == null || c == null || areCollinear(a,b,c)){
            throw new IllegalArgumentException();
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public Point centroid() {
        double x = (a.getX() + b.getX() + c.getX()) /3;
        double y = (a.getY() + b.getY() + c.getY()) /3;
        return new Point(x, y);
    }
    private boolean areCollinear(Point p1, Point p2, Point p3) {
        double area = 0.5 * ((p2.getX() - p1.getX()) * (p3.getY() - p1.getY()) -
                (p3.getX() - p1.getX()) * (p2.getY() - p1.getY()));
        return Math.abs(area) < 1e-6;
    }
    @Override
    public boolean isTheSame(Figure figure) {
        return figure instanceof Triangle;
    }
}
