/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.charlware.taulogo;

import com.charlware.taulang.AbstractRegister;
import com.charlware.taulang.functions.GenericFunction0;
import com.charlware.taulang.functions.GenericFunction1;
import com.charlware.taulang.values.NumberValue;
import com.charlware.taulang.values.Value;
import java.awt.Color;

/**
 *
 * @author charlvj
 */
public class TurtleRegister extends AbstractRegister {

    private final Turtle turtle;
    private DrawingPane turtleWorld;
    
    public TurtleRegister(DrawingPane turtleWorld) {
        this.turtleWorld = turtleWorld;
        this.turtle = turtleWorld.getTurtle();
    }
    
    @Override
    public void registerAll() {
        reg(new GenericFunction1("forward", "distance") {
            @Override
            public Value execute(Value distance) throws Exception {
                turtle.forward(distance.asNumber());
                return distance;
            }
        });
        
        reg(new GenericFunction1("back", "distance") {
            @Override
            public Value execute(Value distance) throws Exception {
                turtle.back(distance.asNumber());
                return distance;
            }
        });
        
        reg(new GenericFunction1("left", "angle") {
            @Override
            public Value execute(Value angle) throws Exception {
                turtle.left(angle.asNumber());
                return angle;
            }
        });
        
        reg(new GenericFunction1("right", "angle") {
            @Override
            public Value execute(Value angle) throws Exception {
                turtle.right(angle.asNumber());
                return angle;
            }
        });
        
        reg(new GenericFunction0("clearscreen") {
            @Override
            public Value execute() throws Exception {
                turtleWorld.reset();
                return null;
            }
        });
        
        reg(new GenericFunction1("wait", "tenthOfASecond") {
            @Override
            public Value execute(Value tenthOfASecond) throws Exception {
                turtleWorld.repaint(10);
                Thread.sleep(tenthOfASecond.asInteger() * 10);
                return null;
            }
        });
        
        reg(new GenericFunction0("penup") {
            @Override
            public Value execute() throws Exception {
                turtle.setPenDown(false);
                return null;
            }
        });
        
        reg(new GenericFunction0("pendown") {
            @Override
            public Value execute() throws Exception {
                turtle.setPenDown(true);
                return null;
            }
        });
        
        reg(new GenericFunction0("hide") {
            @Override
            public Value execute() throws Exception {
                turtle.setHidden(true);
                return null;
            }
        });
        
        reg(new GenericFunction0("show") {
            @Override
            public Value execute() throws Exception {
                turtle.setHidden(false);
                return null;
            }
        });
        
        reg(new GenericFunction0("current_direction") {
            @Override
            public Value execute() throws Exception {
                return new NumberValue(turtle.getDirection());
            }
        });
        
        reg(new GenericFunction0("readline") {
            @Override
            public Value execute() throws Exception {
                System.out.println("readline not implemented yet for TauLogo.");
                return null;
            }
            
        });
        
        reg(new GenericFunction0("foregroundColor") {
            @Override
            public Value execute() throws Exception {
                Color color = turtle.getColor();
                return new NumberValue((double) color.getRGB());
            }
        });
        
        reg(new GenericFunction0("backgroundColor") {
            @Override
            public Value execute() throws Exception {
                return new NumberValue((double) turtleWorld.getBackground().getRGB());
            }
        });
        
        reg(new GenericFunction1("setforeground", "color") {
            @Override
            public Value execute(Value colorVal) throws Exception {
                Color color = new Color(colorVal.asInteger(), true);
                turtle.setColor(color);
                return colorVal;
            }
        });
        
        reg(new GenericFunction1("setbackground", "color") {
            @Override
            public Value execute(Value colorVal) throws Exception {
                Color color = new Color(colorVal.asInteger());
                turtleWorld.setBackground(color);
                return colorVal;
            }
        });
        
        reg("blue", new NumberValue(Color.BLUE.getRGB()));
        reg("red", new NumberValue(Color.RED.getRGB()));
        reg("green", new NumberValue(Color.GREEN.getRGB()));
        reg("orange", new NumberValue(Color.ORANGE.getRGB()));
        reg("yellow", new NumberValue(Color.YELLOW.getRGB()));
        reg("black", new NumberValue(Color.BLACK.getRGB()));
        reg("white", new NumberValue(Color.WHITE.getRGB()));
        reg("gray", new NumberValue(Color.GRAY.getRGB()));
        reg("lightgray", new NumberValue(Color.LIGHT_GRAY.getRGB()));
        
        
        
        try {
            alias("fd", "forward");
            alias("bk", "back");
            alias("rt", "right");
            alias("lt", "left");
        }
        catch(Exception e) {
            System.out.println("Error setting up aliases. Error: " + e);
            e.printStackTrace();
        }
    }
    
}
