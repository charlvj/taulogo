/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.charlware.taulogo;

import java.awt.Color;
import java.awt.geom.Point2D;

/**
 *
 * @author charlvj
 */
public class Turtle {

    private double direction = 90.0;
    private final Point2D position = new Point2D.Double(0, 0);
    private boolean penDown = true;
    private boolean hidden = false;
    private Color penColor = Color.BLACK;
    
    private TurtleListener listener = null;

    public Turtle right(double angle) {
        direction = (direction - angle) % 360;
        fireTurtleTurned();
        return this;
    }

    public Turtle left(double angle) {
        direction = Math.abs((direction + angle) % 360);
        fireTurtleTurned();
        return this;
    }

    public Turtle forward(double distance) {
        double x = position.getX();
        double y = position.getY();
        double angle = Math.toRadians(direction);
        double newX = distance * Math.cos((double) angle);
        double newY = -distance * Math.sin((double) angle);
//        System.out.println("Forward Direction= " + direction + "; angle=" + angle + "; x=" + x + "; y=" + y + "; newX=" + newX + "; newY=" + newY);
        position.setLocation(x + newX, y + newY);
        fireTurtleMoved();
        return this;
    }
    
    public Turtle back(double distance) {
        distance = -distance;
        double x = position.getX();
        double y = position.getY();
        double newX = distance * Math.cos((double) Math.toRadians(direction));
        double newY = -distance * Math.sin((double) Math.toRadians(direction));
        position.setLocation(x + newX, y + newY);
        fireTurtleMoved();
        return this;
    }
    
    public void fireTurtleMoved() {
        if(listener != null) listener.turtleMoved(this);
    }
    
    public void fireTurtleTurned() {
        if(listener != null) listener.turtleTurned(this);
    }
    
    public boolean isPenDown() {
        return penDown;
    }
    
    public void setPenDown(boolean penDown) {
        if(penDown != this.penDown) {
            this.penDown = penDown;
            if(listener != null) listener.penStateChanged(this);
        }
    }
    
    public boolean isHidden() {
        return hidden;
    }
    
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
    
    public Point2D getPosition() {
        return position;
    }
    
    public void setPosition(double x, double y) {
        position.setLocation(x, y);
    }
    
    public double getDirection() {
        return direction;
    }
    
    public void setDirection(double direction) {
        this.direction = direction;
    }
    
    public TurtleListener getListener() {
        return listener;
    }
    
    public void setTurtleListener(TurtleListener listener) {
        this.listener = listener;
    }
    
    public void setColor(Color color) {
        this.penColor = color;
        if(listener != null) listener.penColorChanged(this);
    }
    
    public Color getColor() {
        return penColor;
    }
}
