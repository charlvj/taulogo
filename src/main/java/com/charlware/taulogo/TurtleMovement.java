/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.charlware.taulogo;

import java.awt.Color;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

/**
 *
 * @author charlvj
 */
public class TurtleMovement {
    private final Path2D path;
    private final Color color;
    
    public TurtleMovement(Turtle turtle) {
        path = new Path2D.Double();
        path.moveTo(turtle.getPosition().getX(), turtle.getPosition().getY());
        color = turtle.getColor();
    }

    public Path2D getPath() {
        return path;
    }

    public Color getColor() {
        return color;
    }
    
    
}
