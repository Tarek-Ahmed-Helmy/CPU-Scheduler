/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.schedularassignment;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.util.Queue;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 3afro
 */
public class SJFsimulation  extends javax.swing.JFrame{

    /**
     * Creates new form SJFsimulation
     */
    public SJFsimulation () {
        initComponents();
    }
    static ArrayList<ArrayList<String>> info;
    boolean preemptive;
    ArrayList<JLabel> processes = new ArrayList<>();
    ArrayList<JLabel> burstTimes = new ArrayList<>();
    ArrayList<JLabel> waitingTimes = new ArrayList<>();
    ArrayList<JProgressBar> bars = new ArrayList<>();
    int currtime = 0;
    int totaltime = 0;
    int trunaroundtime;
    int waitingTime;
    double totalwaitingTime = 0;
    double totalturnaroundTime = 0;
    double avgwaitingTime;
    double avgturnaroundTime;
    ArrayList<ArrayList<String>> arrived = new ArrayList<>();
    public SJFsimulation(ArrayList<ArrayList<String>> info, boolean p)  {
        initComponents();
        this.info = info;
        preemptive=p;
        GridLayout grid = new GridLayout(info.size() + 1,4);
        jPanel1.setLayout(grid);
        grid.setHgap(25);
        grid.setVgap(15);
        for(int i = 0 ;i< info.size(); i++){
            String [] row = {info.get(i).get(0), info.get(i).get(1), info.get(i).get(2)};
            DefaultTableModel tab=(DefaultTableModel)jTable1.getModel();
            tab.addRow(row);
            processes.add(new JLabel(info.get(i).get(0)));
            processes.get(i).setFont(new java.awt.Font("Segoe UI", 1, 14));
            processes.get(i).setHorizontalAlignment(JLabel.CENTER);
            jPanel1.add( processes.get(i));
            burstTimes.add(new JLabel(info.get(i).get(2)));
            burstTimes.get(i).setFont(new java.awt.Font("Segoe UI", 1, 14));
            burstTimes.get(i).setHorizontalAlignment(JLabel.CENTER);
            jPanel1.add( burstTimes.get(i));
            bars.add(new JProgressBar(0,Integer.parseInt(info.get(i).get(2))));
            bars.get(i).setSize(160, 20);
            jPanel1.add(bars.get(i));
            waitingTimes.add(new JLabel("0"));
            waitingTimes.get(i).setFont(new java.awt.Font("Segoe UI", 1, 14));
            waitingTimes.get(i).setHorizontalAlignment(JLabel.CENTER);
            jPanel1.add( waitingTimes.get(i));
        }
        for(int i = 0 ;i< info.size(); i++){
            totaltime += Integer.parseInt(info.get(i).get(2));
        }
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 102, 102));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Process", "Arrival Time", "Burrst Time"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Input");

        jLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 16)); // NOI18N
        jLabel2.setText("Remaining Burst Time");

        jLabel3.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 16)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Process");

        jLabel4.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 16)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Waiting Time");

        jLabel8.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 16)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Status Bar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(340, Short.MAX_VALUE))
        );

        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        jLabel5.setText("Average Turn Around Time:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        jLabel6.setText("Average Waiting Time:");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setText("Process Queue");

        jButton1.setText("Simulate");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextPane1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jScrollPane2.setViewportView(jTextPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGap(55, 55, 55)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(204, 204, 204)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        ArrayList<ArrayList<String>> sub = new ArrayList<ArrayList<String>>();
        for(ArrayList<String> st:info){
            sub.add((ArrayList<String>)st.clone());
        }
        new Thread(){
            public void run(){
                if(!preemptive){
                    Collections.sort(sub ,new Comparator<ArrayList<String>>(){
                        public int compare(ArrayList<String> p1, ArrayList<String> p2) {
                            if (p1.get(1).equals(p2.get(1))) {
                                return Integer.parseInt(p1.get(2)) - Integer.parseInt(p2.get(2));
                            } else {
                                return Integer.parseInt(p1.get(1)) - Integer.parseInt(p2.get(1));
                            }
                        }
                    });
                    while(currtime != Integer.parseInt(sub.get(0).get(1))){
                        currtime++;
                        totaltime++;
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(SJFsimulation.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    arrived.add(sub.get(0));
                    sub.remove(0);
                    while(currtime != totaltime){
                        int remainingBurstTime = Integer.parseInt(arrived.get(0).get(2));
                        int processid = arrived.get(0).get(0).charAt(1) - 49;
                        while(remainingBurstTime > 0){
                                jTextPane1.setText(jTextPane1.getText() + arrived.get(0).get(0) +"(" + String.valueOf(currtime)+")");
                                remainingBurstTime--;
                                burstTimes.get(processid).setText(String.valueOf(remainingBurstTime));
                                bars.get(processid).setValue(Integer.parseInt(info.get(processid).get(2)) - remainingBurstTime);
                                currtime++;
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException ex) {
                                            Logger.getLogger(SJFsimulation.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            trunaroundtime = currtime - Integer.parseInt(arrived.get(0).get(1));
                            totalturnaroundTime += trunaroundtime;
                            waitingTime = trunaroundtime - Integer.parseInt(arrived.get(0).get(2));
                            totalwaitingTime += waitingTime;
                            waitingTimes.get(processid).setText(String.valueOf(waitingTime));
                            arrived.remove(0);
                            for(int i = 0 ; i<sub.size();i++){
                                System.out.println(i);
                                if(Integer.parseInt(sub.get(i).get(1)) <= currtime){
                                    arrived.add(sub.get(i));
                                }
                            }
                            for(int i = 0 ; i<arrived.size();i++){
                                if(sub.contains(arrived.get(i))) sub.remove(arrived.get(i));
                            }
                            Collections.sort(arrived ,new Comparator<ArrayList<String>>(){
                                public int compare(ArrayList<String> p1, ArrayList<String> p2) {
                                    return Integer.parseInt(p1.get(2)) - Integer.parseInt(p2.get(2));
                                }
                            });
                    }
                    avgwaitingTime = totalwaitingTime / info.size();
                    jTextField2.setText(String.format("%.02f", avgwaitingTime));
                    avgturnaroundTime = totalturnaroundTime / info.size();
                    jTextField1.setText(String.format("%.02f", avgturnaroundTime));
                }
                else{
                    while(currtime != totaltime){
                        for(int i = 0 ; i<sub.size();i++){
                            if(currtime == Integer.parseInt(sub.get(i).get(1))){
                                arrived.add(sub.get(i));
                                Collections.sort(arrived ,new Comparator<ArrayList<String>>(){
                                    public int compare(ArrayList<String> p1, ArrayList<String> p2) {
                                        return Integer.parseInt(p1.get(2)) - Integer.parseInt(p2.get(2));
                                    }
                                });
                            }
                        }
                        for(int i = 0 ; i<arrived.size();i++){
                            if(sub.contains(arrived.get(i))) sub.remove(arrived.get(i));
                        }
                        if(!arrived.isEmpty()){
                            jTextPane1.setText(jTextPane1.getText() + arrived.get(0).get(0) +"(" + String.valueOf(currtime)+")");
                            int processid = arrived.get(0).get(0).charAt(1) - 49;
                            currtime++;
                            arrived.get(0).set(2, String.valueOf(Integer.parseInt(arrived.get(0).get(2)) - 1));
                            burstTimes.get(processid).setText(arrived.get(0).get(2));
                            bars.get(processid).setValue(Integer.parseInt(info.get(processid).get(2)) - Integer.parseInt(arrived.get(0).get(2)));
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(SJFsimulation.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            if(Integer.parseInt(arrived.get(0).get(2)) == 0){
                                trunaroundtime = currtime - Integer.parseInt(arrived.get(0).get(1));
                                totalturnaroundTime += trunaroundtime;
                                waitingTime = trunaroundtime - Integer.parseInt(info.get(processid).get(2));
                                totalwaitingTime += waitingTime;
                                waitingTimes.get(processid).setText(String.valueOf(waitingTime));
                                arrived.remove(0);
                            }
                            for(int i = 0 ; i<sub.size();i++){
                                if(currtime == Integer.parseInt(sub.get(i).get(1))){
                                    arrived.add(sub.get(i));
                                    Collections.sort(arrived ,new Comparator<ArrayList<String>>(){
                                        public int compare(ArrayList<String> p1, ArrayList<String> p2) {
                                            return Integer.parseInt(p1.get(2)) - Integer.parseInt(p2.get(2));
                                        }
                                    });
                                }
                            }
                            for(int i = 0 ; i<arrived.size();i++){
                                if(sub.contains(arrived.get(i))) sub.remove(arrived.get(i));
                            }
                        }
                        else{
                            currtime++;
                            totaltime++;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(SJFsimulation.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                    avgwaitingTime = totalwaitingTime / info.size();
                    jTextField2.setText(String.format("%.02f", avgwaitingTime));
                    avgturnaroundTime = totalturnaroundTime / info.size();
                    jTextField1.setText(String.format("%.02f", avgturnaroundTime));
                }
            }
        }.start();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(SJFsimulation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SJFsimulation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SJFsimulation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SJFsimulation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SJFsimulation().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private static javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables
}
