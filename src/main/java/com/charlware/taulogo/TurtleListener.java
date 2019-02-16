/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.charlware.taulogo;

/**
 *
 * @author charlvj
 */
public interface TurtleListener {
    public void turtleMoved(Turtle turtle);
    public void turtleTurned(Turtle turtle);
    public void penStateChanged(Turtle turtle);
    public void penColorChanged(Turtle turtle);
}
