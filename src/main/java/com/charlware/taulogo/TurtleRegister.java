/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.charlware.taulogo;

import java.awt.Color;

import com.charlware.taulang.AbstractRegister;
import com.charlware.taulang.functions.GenericFunction0;
import com.charlware.taulang.functions.GenericFunction1;
import com.charlware.taulang.util.LinkedList;
import com.charlware.taulang.values.BooleanValue;
import com.charlware.taulang.values.DoubleValue;
import com.charlware.taulang.values.IntegerValue;
import com.charlware.taulang.values.ListValue;
import com.charlware.taulang.values.Value;

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
                turtle.forward(distance.asDouble());
                return distance;
            }
        });
        
        reg(new GenericFunction1("back", "distance") {
            @Override
            public Value execute(Value distance) throws Exception {
                turtle.back(distance.asDouble());
                return distance;
            }
        });
        
        reg(new GenericFunction1("left", "angle") {
            @Override
            public Value execute(Value angle) throws Exception {
                turtle.left(angle.asDouble());
                return angle;
            }
        });
        
        reg(new GenericFunction1("right", "angle") {
            @Override
            public Value execute(Value angle) throws Exception {
                turtle.right(angle.asDouble());
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
                return new DoubleValue(turtle.getDirection());
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
                return new DoubleValue((double) color.getRGB());
            }
        });
        
        reg(new GenericFunction0("backgroundColor") {
            @Override
            public Value execute() throws Exception {
                return new DoubleValue((double) turtleWorld.getBackground().getRGB());
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
        
        reg(new GenericFunction1("set_turtle_animation", "enabled") {
            @Override
            public Value execute(Value enabledVal) throws Exception {
                boolean enabled = enabledVal.asBoolean();
                turtle.setAnimateMovement(enabled);
                return BooleanValue.TRUE;
            }
        });
        
//        reg(new GenericFunction1("setpos", "coordinates") {
//            @Override
//            public Value execute(Value coordVal) throws Exception {
//                ListValue coordListVal = (ListValue) coordVal;
//                LinkedList coordList = coordListVal.getValue();
//                
//                return colorVal;
//            }
//        });
        
        reg("blue", new IntegerValue(Color.BLUE.getRGB()));
        reg("red", new IntegerValue(Color.RED.getRGB()));
        reg("green", new IntegerValue(Color.GREEN.getRGB()));
        reg("orange", new IntegerValue(Color.ORANGE.getRGB()));
        reg("yellow", new IntegerValue(Color.YELLOW.getRGB()));
        reg("black", new IntegerValue(Color.BLACK.getRGB()));
        reg("white", new IntegerValue(Color.WHITE.getRGB()));
        reg("gray", new IntegerValue(Color.GRAY.getRGB()));
        reg("lightgray", new IntegerValue(Color.LIGHT_GRAY.getRGB()));
        
        
        
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
