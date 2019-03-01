/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.charlware.taulogo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;
import javax.swing.JPanel;

/**
 *
 * @author charlvj
 */
public class DrawingPane extends JPanel implements TurtleListener {

    private Turtle turtle = new Turtle();
    private Point origin = new Point();
    private Deque<TurtleMovement> shapes = new ConcurrentLinkedDeque();

    public DrawingPane() {
        reset();
        turtle.setTurtleListener(this);
        setDoubleBuffered(true);
        repaint();
    }

    public Turtle getTurtle() {
        return turtle;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics;
        g.translate(origin.x, origin.y);
        g.setColor(Color.BLACK);
        shapes.descendingIterator().forEachRemaining(movement -> {
            Path2D path = movement.getPath();
            Color color = movement.getColor();
            g.setColor(color);
            g.draw(path);
            });
        
        paintTurtle(g);
    }

    protected void paintTurtle(Graphics2D g) {
        if (!turtle.isHidden()) {
            Dimension size = getSize();
            origin.setLocation(size.width / 2, size.height / 2);
            if (turtle.isPenDown()) {
                g.setColor(Color.BLUE);
            } else {
                g.setColor(Color.RED);
            }
            Point2D p = turtle.getPosition();
            Shape arrow = createArrowShape(p, turtle.getDirection());
            g.draw(arrow);
        }
    }

    public static Shape createArrowShape(Point2D fromPt, double direction) {
        Polygon arrowPolygon = new Polygon();
        arrowPolygon.addPoint(0, -6);
        arrowPolygon.addPoint(-20, 0);
        arrowPolygon.addPoint(0, 6);

        double angle = Math.toRadians(direction - 180);
        AffineTransform transform = new AffineTransform();
        transform.translate(fromPt.getX(), fromPt.getY());
        transform.rotate(-angle);

        return transform.createTransformedShape(arrowPolygon);
    }

    @Override
    public void turtleMoved(Turtle turtle) {
        Point2D p = turtle.getPosition();
        if (turtle.isPenDown()) {
            shapes.getFirst().getPath().lineTo(p.getX(), p.getY());
        } else {
            shapes.getFirst().getPath().moveTo(p.getX(), p.getY());
        }
        repaint();
    }

    @Override
    public void turtleTurned(Turtle turtle) {
        repaint();
    }

    @Override
    public void penStateChanged(Turtle turtle) {
        if (!turtle.isPenDown()) {
            TurtleMovement movement = new TurtleMovement(turtle);
            shapes.push(movement);
            repaint();
        }
    }
    
    @Override
    public void penColorChanged(Turtle turtle) {
        TurtleMovement movement = new TurtleMovement(turtle);
        shapes.push(movement);
        repaint();
    }

    public void reset() {
        shapes.clear();
        turtle.setPosition(0, 0);
        turtle.setDirection(90);
        TurtleMovement movement = new TurtleMovement(turtle);
        shapes.push(movement);
        repaint();
    }
}
