/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.charlware.taulogo;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.math.BigDecimal;

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
    private boolean animateMovement = false;
    private int animationSleep = 3;
    
    private TurtleListener listener = null;

    public Turtle right(double angle) {
        direction = (direction - angle) % 360;
        fireTurtleTurned();
        return this;
    }

    public Turtle left(double angle) {
        direction = (direction + angle) % 360;
        fireTurtleTurned();
        return this;
    }

    public Turtle forward(double distance) {
        double x = position.getX();
        double y = position.getY();
        double angle = Math.toRadians(direction);
        
//        System.out.println("Forward Direction= " + direction + "; angle=" + angle + "; x=" + x + "; y=" + y + "; newX=" + newX + "; newY=" + newY);
        if(animateMovement) {
        	BigDecimal d = new BigDecimal(distance);
        	int d_int = Math.abs(d.intValue());
        	int dir = distance < 0 ? -1 : 1;
        	for(int i = 0; i < d_int; i++) {
                double newX = dir * Math.cos((double) angle);
                double newY = -dir * Math.sin((double) angle);
        		position.setLocation(x + newX, y + newY);
        		x += newX;
        		y += newY;
        		fireTurtleMoved();
        		try {
					Thread.sleep(animationSleep);
				} catch (InterruptedException e) {
					// Just ignore for now
				}
        	}
        	double d_dec = d.subtract(new BigDecimal(dir * d_int)).doubleValue();
            double newX = d_dec * Math.cos((double) angle);
            double newY = -d_dec * Math.sin((double) angle);
    		position.setLocation(x + newX, y + newY);
    		fireTurtleMoved();
        }
        else {
        	double newX = distance * Math.cos((double) angle);
            double newY = -distance * Math.sin((double) angle);
	        position.setLocation(x + newX, y + newY);
	        fireTurtleMoved();
        }
        return this;
    }
    
    public Turtle back(double distance) {
    	return forward(-distance);
//        distance = -distance;
//        double x = position.getX();
//        double y = position.getY();
//        double newX = distance * Math.cos((double) Math.toRadians(direction));
//        double newY = -distance * Math.sin((double) Math.toRadians(direction));
//        position.setLocation(x + newX, y + newY);
//        fireTurtleMoved();
//        return this;
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

	public boolean isAnimateMovement() {
		return animateMovement;
	}

	public void setAnimateMovement(boolean animateMovement) {
		this.animateMovement = animateMovement;
	}

	public int getAnimationSleep() {
		return animationSleep;
	}

	public void setAnimationSleep(int animationSleep) {
		this.animationSleep = animationSleep;
	}
    
    
}
