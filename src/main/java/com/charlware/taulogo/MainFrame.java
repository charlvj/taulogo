/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.charlware.taulogo;

import com.charlware.taulang.ui.TauEditorPane;
import com.wordpress.tips4java.MessageConsole;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JFileChooser;

/**
 *
 * @author charlvj
 */
public class MainFrame extends javax.swing.JFrame {

//    private List<String> history = new ArrayList<>();
    private List<String> history = new ArrayList();
    private int historyPosition = 0;
    private MessageConsole messageConsole;

    private List<File> files = new ArrayList();
    private Map<String, JComponent> tabs = new HashMap();
    private int unnamedCount = 1;
    
    private Executor exec = Executors.newSingleThreadExecutor();
    private TauExecutor tauExecutor;
    
    
    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        messageConsole = new MessageConsole(txOutput);
        messageConsole.redirectOut();
        messageConsole.redirectErr(Color.red, null);
        tauExecutor = new TauExecutor(turtleWorld);   
        
        pnSplitPane.setDividerLocation(0.8);
        pnSplitPane.setResizeWeight(0.75);
        
        validate();
        setVisible(true);
    }
    
    protected void addOutputLine(String line) {
        txOutput.append(line + "\n");
    }

    private int addClosableTab(String title, JComponent component) {
        // First see if we can find one already...
        JComponent cur = tabs.get(title);
        if(cur != null) {
            // Remove it so it can be replaced by the new one...
            tabs.remove(title);
            pnTabs.remove(cur);
        }
        pnTabs.addTab(title, component);
        int idx = pnTabs.indexOfComponent(component);
        pnTabs.setTabComponentAt(idx, new ButtonTabComponent(pnTabs));
        tabs.put(title, component);
        pack();
        return idx;
    }
    
    public List<File> getFiles() {
        List<File> files = new ArrayList();
        for(JComponent comp: tabs.values()) {
            TauEditorPane p = (TauEditorPane) comp;
            try {
                p.saveFile();
            } catch (FileNotFoundException ex) {
                System.out.println("Trouble loading a file: " + ex);
            }
            files.add(p.getFile());
        }
        return files;
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileChooser = new javax.swing.JFileChooser();
        jToolBar1 = new javax.swing.JToolBar();
        btNew = new javax.swing.JButton();
        btOpen = new javax.swing.JButton();
        btSave = new javax.swing.JButton();
        pnSplitPane = new javax.swing.JSplitPane();
        pnTabs = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        turtleWorld = new com.charlware.taulogo.DrawingPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txOutput = new javax.swing.JTextArea();
        txCode = new javax.swing.JTextField();

        fileChooser.setDialogTitle("Open File");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tau Logo");
        getContentPane().setLayout(new java.awt.BorderLayout());

        jToolBar1.setRollover(true);

        btNew.setText("New");
        btNew.setFocusable(false);
        btNew.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btNew.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNewActionPerformed(evt);
            }
        });
        jToolBar1.add(btNew);

        btOpen.setText("Open...");
        btOpen.setFocusable(false);
        btOpen.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btOpen.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btOpenActionPerformed(evt);
            }
        });
        jToolBar1.add(btOpen);

        btSave.setText("Save");
        btSave.setFocusable(false);
        btSave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btSave.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSaveActionPerformed(evt);
            }
        });
        jToolBar1.add(btSave);

        getContentPane().add(jToolBar1, java.awt.BorderLayout.NORTH);

        pnSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        turtleWorld.setMinimumSize(new java.awt.Dimension(100, 100));
        turtleWorld.setName(""); // NOI18N

        javax.swing.GroupLayout turtleWorldLayout = new javax.swing.GroupLayout(turtleWorld);
        turtleWorld.setLayout(turtleWorldLayout);
        turtleWorldLayout.setHorizontalGroup(
            turtleWorldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 583, Short.MAX_VALUE)
        );
        turtleWorldLayout.setVerticalGroup(
            turtleWorldLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jScrollPane2.setViewportView(turtleWorld);

        pnTabs.addTab("Canvas", jScrollPane2);

        pnSplitPane.setLeftComponent(pnTabs);

        jPanel1.setLayout(new java.awt.BorderLayout());

        txOutput.setEditable(false);
        txOutput.setColumns(20);
        txOutput.setRows(5);
        jScrollPane1.setViewportView(txOutput);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pnSplitPane.setRightComponent(jPanel1);

        getContentPane().add(pnSplitPane, java.awt.BorderLayout.CENTER);

        txCode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txCodeActionPerformed(evt);
            }
        });
        txCode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txCodeKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txCodeKeyPressed(evt);
            }
        });
        getContentPane().add(txCode, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txCodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txCodeActionPerformed
        try {
            String code = txCode.getText();
            if (!code.isEmpty()) {
                tauExecutor.bootstrap(getFiles());
                txCode.setText("");
                exec.execute(() -> tauExecutor.run(code));
                history.add(code);
                historyPosition = history.size();
            }
        } catch (Exception ex) {
            System.err.println("Error: " + ex);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_txCodeActionPerformed

    private void txCodeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txCodeKeyTyped
    }//GEN-LAST:event_txCodeKeyTyped

    private void txCodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txCodeKeyPressed
        switch (evt.getExtendedKeyCode()) {
            case KeyEvent.VK_UP:
                if (historyPosition > 0) {
                    txCode.setText(history.get(--historyPosition));
                }
                break;
            case KeyEvent.VK_DOWN:
                if (historyPosition < history.size() - 1) {
                    txCode.setText(history.get(++historyPosition));
                } else {
                    txCode.setText("");
                }
                break;
        }
    }//GEN-LAST:event_txCodeKeyPressed

    private void btNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNewActionPerformed
        TauEditorPane editor = new TauEditorPane();
        editor.setFileChooser(fileChooser);
        String filename = "Unnamed" + unnamedCount++;
        editor.newFile(filename);
        addClosableTab(filename, editor);
    }//GEN-LAST:event_btNewActionPerformed

    private void btOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btOpenActionPerformed
        int res = fileChooser.showOpenDialog(this);
        if(res == JFileChooser.APPROVE_OPTION)  {
            File f = fileChooser.getSelectedFile();
            TauEditorPane editor = new TauEditorPane();
            try {
                editor.openFile(f);
                addClosableTab(f.getName(), editor);
                pnTabs.setSelectedComponent(editor);
            } catch (IOException ex) {
                System.err.println("Error while opening file: " + ex);
            }
        }
    }//GEN-LAST:event_btOpenActionPerformed

    private void btSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSaveActionPerformed
        TauEditorPane editor = (TauEditorPane) pnTabs.getSelectedComponent();
        int index = pnTabs.getSelectedIndex();
        String fname = pnTabs.getTitleAt(index);
        if(editor.isNew()) {
            int res = fileChooser.showSaveDialog(this);

            if(res != JFileChooser.APPROVE_OPTION) {
                return;
            }
            File f = fileChooser.getSelectedFile();
            editor.setFile(f);
            pnTabs.setTitleAt(index, f.getName());
        }
        try {
            editor.saveFile();
            pnTabs.setTitleAt(index, editor.getFile().getName());
        } catch (FileNotFoundException ex) {
            System.err.println("Error saving file: " + ex);
        }
    }//GEN-LAST:event_btSaveActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
//        System.out.println("Starting...");
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                System.out.println("Err");
                new MainFrame().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btNew;
    private javax.swing.JButton btOpen;
    private javax.swing.JButton btSave;
    private javax.swing.JFileChooser fileChooser;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JSplitPane pnSplitPane;
    private javax.swing.JTabbedPane pnTabs;
    private com.charlware.taulogo.DrawingPane turtleWorld;
    private javax.swing.JTextField txCode;
    private javax.swing.JTextArea txOutput;
    // End of variables declaration//GEN-END:variables

}
