package com.mycompany.schedularassignment;

import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.table.DefaultTableModel;
import javax.swing.Timer;
import java.util.Queue;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class RRsimulation extends javax.swing.JFrame {

    /**
     * Creates new form RRsimulation
     */
    public RRsimulation() {
        initComponents();
    }
    
    static ArrayList<ArrayList<String>> info;
    boolean preemptive;
    ArrayList<JLabel> processes = new ArrayList<>();
    ArrayList<JLabel> burstTimes = new ArrayList<>();
    ArrayList<JLabel> waitingTimes = new ArrayList<>();
    ArrayList<JProgressBar> bars = new ArrayList<>();
    int currTime = 0;
    int totaltime = 0;
    int turnaroundTime;
    int waitingTime;
    double totalWaitingTime = 0;
    double totalTurnaroundTime = 0;
    double avgWaitingTime;
    double avgTurnaroundTime;
    boolean key = false;
    int cnt;
    GridLayout grid;
    Queue<ArrayList<String>> arrivedProcesses; // Queue to hold the arrived processes
    int quantumTime;
    Timer timer;
    ArrayList<String> currentProcess = null;
    ArrayList<String> startingBurstTimes = new ArrayList<>();

    public RRsimulation(ArrayList<ArrayList<String>> info, int quantumTime) {
        initComponents();
        home.setEnabled(false);
        this.quantumTime = quantumTime;
        this.info = info;
        cnt = info.size();
        grid = new GridLayout(info.size() + 1, 4);
        mainpanel.setLayout(grid);
        grid.setHgap(25);
        grid.setVgap(15);
        for (int i = 0; i < info.size(); i++) {
            String[] row = { info.get(i).get(0), info.get(i).get(1), info.get(i).get(2) };
            DefaultTableModel tab = (DefaultTableModel) table.getModel();
            tab.addRow(row);
            processes.add(new JLabel(info.get(i).get(0)));
            processes.get(i).setFont(new java.awt.Font("Segoe UI", 1, 14));
            processes.get(i).setHorizontalAlignment(JLabel.CENTER);
            mainpanel.add(processes.get(i));
            burstTimes.add(new JLabel(info.get(i).get(2)));
            burstTimes.get(i).setFont(new java.awt.Font("Segoe UI", 1, 14));
            burstTimes.get(i).setHorizontalAlignment(JLabel.CENTER);
            mainpanel.add(burstTimes.get(i));
            bars.add(new JProgressBar(0, Integer.parseInt(info.get(i).get(2))));
            bars.get(i).setSize(160, 20);
            mainpanel.add(bars.get(i));
            waitingTimes.add(new JLabel("0"));
            waitingTimes.get(i).setFont(new java.awt.Font("Segoe UI", 1, 14));
            waitingTimes.get(i).setHorizontalAlignment(JLabel.CENTER);
            mainpanel.add(waitingTimes.get(i));
            
            startingBurstTimes.add(info.get(i).get(2));
        }
        for (int i = 0; i < info.size(); i++) {
            totaltime += Integer.parseInt(info.get(i).get(2));
        }
        // Initialize the arrivedProcesses queue with the initial set of processes
        arrivedProcesses = new LinkedList<>(info);
    }
    public void clear(){
        arrivaltime.setText("");
        bursttime.setText("");
    }

    
    private void scheduleNextProcess() {
        if (arrivedProcesses.isEmpty() && currentProcess == null) {
            // All processes have been completed, stop the simulation
            timer.stop();
            return;
        }
        // Get the next process from the queue
        currentProcess = arrivedProcesses.poll();
        if(Integer.parseInt(currentProcess.get(1))<=currTime){
            int burstTime = Integer.parseInt(currentProcess.get(2));
            int processIndex = info.indexOf(currentProcess);
            int startingBurstTime = Integer.parseInt(startingBurstTimes.get(processIndex));
            JProgressBar progressBar = bars.get(processIndex);
            JLabel waitingTimeLabel = waitingTimes.get(processIndex);
            // Update the progress bar
            int executionTime = Math.min(quantumTime, burstTime); // Execute for quantum time or remaining burst time, whichever is smaller
            progressBar.setValue(progressBar.getValue() + executionTime);
            burstTimes.get(processIndex).setText(String.valueOf(Integer.parseInt(burstTimes.get(processIndex).getText())-executionTime));
            int count = 0; 
            while(count!=executionTime){
                jTextPane1.setText(jTextPane1.getText() + currentProcess.get(0) +"(" + String.valueOf(currTime+count)+")");
                count++;
            }
            if (burstTime > executionTime) {
                // The process is not completed, update the remaining burst time and add it back to the queue
                burstTime -= executionTime;
                currentProcess.set(2, String.valueOf(burstTime));
                arrivedProcesses.offer(currentProcess);
            }else{
                // The process is completed, calculate turnaround time and waiting time
                int startTime = Integer.parseInt(currentProcess.get(1));
                int endTime = currTime + executionTime;
                int turnaroundTime = endTime - startTime;
                //int turnaroundTime = currTime - startTime + executionTime;
                int waitingTime = turnaroundTime - startingBurstTime;
                totalTurnaroundTime += turnaroundTime;
                totalWaitingTime += waitingTime;
                // Update the waiting time label
                waitingTimeLabel.setText(String.valueOf(waitingTime));
                currentProcess = null;
            }
            // Update the current time
            currTime += executionTime;
            cnt = info.size();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(SJFsimulation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            cnt--;
            if(cnt==0){
                while(currTime != Integer.parseInt(currentProcess.get(1))){
                    currTime++;
                    totaltime++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SJFsimulation.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            arrivedProcesses.offer(currentProcess);
        }
    }

    private void startSimulation() {
        timer = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scheduleNextProcess();
                if (currTime >= totaltime) {
                    // All processes have been executed, calculate average times
                    avgTurnaroundTime = totalTurnaroundTime / info.size();
                    avgWaitingTime = totalWaitingTime / info.size();
                    // Display average times or perform any required actions
                    averagewaitingtime.setText(String.format("%.02f", avgWaitingTime));
                    averageturnaroundtime.setText(String.format("%.02f", avgTurnaroundTime));
                    add.setEnabled(false);
                    home.setEnabled(true);
                    key = true;    
                    timer.stop();
                }
            }
        });
        timer.start();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        simulate = new javax.swing.JButton();
        averagewaitingtime = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        averageturnaroundtime = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        mainpanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        arrivaltime = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        bursttime = new javax.swing.JTextField();
        add = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        home = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        jLabel6.setText("Average Waiting Time:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setText("Process Queue");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Process", "Arrival Time", "Burrst Time"
            }
        ));
        jScrollPane1.setViewportView(table);

        simulate.setText("Simulate");
        simulate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simulateActionPerformed(evt);
            }
        });

        averagewaitingtime.setBackground(new java.awt.Color(255, 255, 255));
        averagewaitingtime.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        averagewaitingtime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        averagewaitingtime.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jTextPane1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jScrollPane2.setViewportView(jTextPane1);

        averageturnaroundtime.setBackground(new java.awt.Color(255, 255, 255));
        averageturnaroundtime.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        averageturnaroundtime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        averageturnaroundtime.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Input");

        mainpanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

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

        javax.swing.GroupLayout mainpanelLayout = new javax.swing.GroupLayout(mainpanel);
        mainpanel.setLayout(mainpanelLayout);
        mainpanelLayout.setHorizontalGroup(
            mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainpanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );
        mainpanelLayout.setVerticalGroup(
            mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(mainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(388, Short.MAX_VALUE))
        );

        jLabel9.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel9.setText("Arrival Time:");

        jLabel10.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel10.setText("Burst Time:");

        add.setText("Add ");
        add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addMouseClicked(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel12.setText("Add Dynamically:");

        home.setText("Home");
        home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(arrivaltime, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(251, 251, 251))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bursttime, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(home, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(add, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(arrivaltime, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bursttime, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(add)
                .addGap(5, 5, 5)
                .addComponent(home)
                .addContainerGap())
        );

        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        jLabel5.setText("Average Turn Around Time:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(simulate, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addComponent(mainpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(180, 180, 180)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(averagewaitingtime, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(152, 152, 152)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(averageturnaroundtime, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(simulate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(mainpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(averagewaitingtime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(averageturnaroundtime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void simulateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simulateActionPerformed
        // TODO add your handling code here:
        if(key){}
        simulate.setEnabled(false);
        startSimulation();
    }//GEN-LAST:event_simulateActionPerformed

    private void addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addMouseClicked
        try{
            if(key){
            }else if(arrivaltime.getText().isEmpty()||bursttime.getText().isEmpty()){
                JOptionPane.showMessageDialog(this,"All text fields must be filled!");
            }
            else if(Integer.parseInt(arrivaltime.getText())>=currTime&&Integer.parseInt(bursttime.getText())>=1){
                ArrayList<String> data= new ArrayList<String>();
                data.add("P"+Integer.toString(info.size()+1));
                data.add(arrivaltime.getText());
                data.add(bursttime.getText());
                String [] row = {"P"+Integer.toString(info.size()+1), arrivaltime.getText(), bursttime.getText()};
                DefaultTableModel tab=(DefaultTableModel)table.getModel();
                tab.addRow(row);
                info.add(data);
                cnt++;
                arrivedProcesses.add(data);
                startingBurstTimes.add(info.get(info.size()-1).get(2));
                totaltime += Integer.parseInt(info.get(info.size() - 1).get(2));
                grid.setRows(info.size()+1);
                grid.setColumns(4);
                mainpanel.setLayout(grid);
                grid.setHgap(25);
                grid.setVgap(15);
                processes.add(new JLabel(info.get(info.size() - 1).get(0)));
                processes.get(info.size() - 1).setFont(new java.awt.Font("Segoe UI", 1, 14));
                processes.get(info.size() - 1).setHorizontalAlignment(JLabel.CENTER);
                mainpanel.add( processes.get(info.size() - 1));
                burstTimes.add(new JLabel(info.get(info.size() - 1).get(2)));
                burstTimes.get(info.size() - 1).setFont(new java.awt.Font("Segoe UI", 1, 14));
                burstTimes.get(info.size() - 1).setHorizontalAlignment(JLabel.CENTER);
                mainpanel.add( burstTimes.get(info.size() - 1));
                bars.add(new JProgressBar(0,Integer.parseInt(info.get(info.size() - 1).get(2))));
                bars.get(info.size() - 1).setSize(160, 20);
                mainpanel.add(bars.get(info.size() - 1));
                waitingTimes.add(new JLabel("0"));
                waitingTimes.get(info.size() - 1).setFont(new java.awt.Font("Segoe UI", 1, 14));
                waitingTimes.get(info.size() - 1).setHorizontalAlignment(JLabel.CENTER);
                mainpanel.add( waitingTimes.get(info.size() - 1));
                clear();
            }else{
                JOptionPane.showMessageDialog(this,"Arrival time must be >= current time and Burst time must be >= 1");
            }
        }
        catch(NumberFormatException e){
            JOptionPane.showMessageDialog(this,"Time must be integer!");
        }
    }//GEN-LAST:event_addMouseClicked

    private void homeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeMouseClicked
        if(key){
            new StartWindow().setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_homeMouseClicked

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
            java.util.logging.Logger.getLogger(RRsimulation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RRsimulation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RRsimulation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RRsimulation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RRsimulation().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JTextField arrivaltime;
    private javax.swing.JLabel averageturnaroundtime;
    private javax.swing.JLabel averagewaitingtime;
    private javax.swing.JTextField bursttime;
    private javax.swing.JButton home;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JPanel mainpanel;
    private javax.swing.JButton simulate;
    private static javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}