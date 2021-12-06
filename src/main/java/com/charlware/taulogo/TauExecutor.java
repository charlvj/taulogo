/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.charlware.taulogo;

import com.charlware.taulang.Interpreter;
import com.charlware.taulang.values.Value;
import java.io.File;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author charlvj
 */
public class TauExecutor {

    private final com.charlware.taulang.Runtime tauRuntime;
    private final Interpreter tau;
    private final DrawingPane turtleWorld;
    
    private PrintStream out = System.out;
    private PrintStream err = System.err;
    private InputStream in = System.in;

    public TauExecutor(DrawingPane turtleWorld) {
        this.turtleWorld = turtleWorld;
        tauRuntime = new com.charlware.taulang.Runtime();
        tauRuntime.getFlags().setEnableTracer(false);
        tauRuntime.getSearchPath().add(Value.of("src/main/tau"));
        tauRuntime.initialize();     
        
        // Register this separately so that we can register in a new scope. 
        TurtleRegister turtleRegister = new TurtleRegister(turtleWorld);
        turtleRegister.setRuntime(tauRuntime);
        turtleRegister.registerAll();
        tauRuntime.getMemory().pushScope();
        
        tau = new Interpreter(tauRuntime);
        tauRuntime.setInterpreter(tau);
        try {
            tau.interpret("import \"logo.tau\" \"\"");
        } catch (Exception ex) {
            System.out.println("Error initializing logo.tau: " + ex);
        }
    }

    public void runFresh(String code, List<File> imports) {
        if(bootstrap(imports)) {
            run(code);
        }
    }
    
    public void run(String code) {
        try {
            out.println("? " + code);
            Value result = tau.interpret(code);
            out.println(""+result);
        } catch (Exception ex) {
            err.println("Error: " + ex);
        }
    }
    
    public boolean bootstrap(Collection<File> imports) {
        StringBuilder bootstrap = new StringBuilder();
//        bootstrap.append("import \"logo.tau\" \"\" ");
        imports.forEach(f -> bootstrap.append("print \"Importing " + f.getAbsolutePath() + "...\" printline ")
                .append("import \"")
                .append(f.getAbsolutePath())
                .append("\"")
                .append(" \"\" "));
        String s = bootstrap.toString();
        if(!s.isEmpty())
            try {
                tau.interpret(bootstrap.toString());
                return true;
            } catch (Exception ex) {
                err.println("An error occurred while bootstrapping the Tau runtime: " + ex);
                return false;
            }
        else
            return true;
    }
    
    public void reset() {
        tauRuntime.clearMemory();
    }
    
    public void setOut(PrintStream out) {
        this.out = out;
        tauRuntime.stdout = out;
    }

    public void setErr(PrintStream err) {
        this.err = err;
    }

    public void setIn(InputStream in) {
        this.in = in;
        tauRuntime.stdin = in;
    }

    
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        File f = new File("/home/cvanjaarsveldt/lang/taulang/logo.tau");
        sb.append("import \"").append(f.getAbsoluteFile()).append("\"");
        System.out.println(sb);
    }
}
