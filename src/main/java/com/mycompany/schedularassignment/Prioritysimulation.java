package com.mycompany.schedularassignment;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.table.DefaultTableModel;

public class Prioritysimulation extends javax.swing.JFrame {

    /**
     * Creates new form
     */
    public Prioritysimulation() {
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
    int remainingBurstTime;
    double totalwaitingTime = 0;
    double totalturnaroundTime = 0;
    double avgwaitingTime;
    double avgturnaroundTime;
    boolean key = false;
    GridLayout grid;
    ArrayList<ArrayList<String>> sub;
    ArrayList<ArrayList<String>> arrived = new ArrayList<>();
    public Prioritysimulation(ArrayList<ArrayList<String>> info, boolean p) {
        initComponents();
        preemptive=p;
        home.setEnabled(false);
        this.info = info;
        preemptive=p;
        grid = new GridLayout(info.size() + 1,4);
        mainpanel.setLayout(grid);
        grid.setHgap(25);
        grid.setVgap(15);
        for(int i = 0 ;i< info.size(); i++){
            String [] row = {info.get(i).get(0), info.get(i).get(1), info.get(i).get(2) , info.get(i).get(3)};
            DefaultTableModel tab=(DefaultTableModel)table.getModel();
            tab.addRow(row);
            processes.add(new JLabel(info.get(i).get(0)));
            processes.get(i).setFont(new java.awt.Font("Segoe UI", 1, 14));
            processes.get(i).setHorizontalAlignment(JLabel.CENTER);
            mainpanel.add( processes.get(i));
            burstTimes.add(new JLabel(info.get(i).get(2)));
            burstTimes.get(i).setFont(new java.awt.Font("Segoe UI", 1, 14));
            burstTimes.get(i).setHorizontalAlignment(JLabel.CENTER);
            mainpanel.add( burstTimes.get(i));
            bars.add(new JProgressBar(0,Integer.parseInt(info.get(i).get(2))));
            bars.get(i).setSize(160, 20);
            mainpanel.add(bars.get(i));
            waitingTimes.add(new JLabel("0"));
            waitingTimes.get(i).setFont(new java.awt.Font("Segoe UI", 1, 14));
            waitingTimes.get(i).setHorizontalAlignment(JLabel.CENTER);
            mainpanel.add( waitingTimes.get(i));
        }
        for(int i = 0 ;i< info.size(); i++){
            totaltime += Integer.parseInt(info.get(i).get(2));
        }
    }
    public void clear(){
        arrivaltime.setText("");
        bursttime.setText("");
        priority.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        arrivaltime = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        bursttime = new javax.swing.JTextField();
        add = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        home = new javax.swing.JButton();
        priority = new javax.swing.JTextField();
        label = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        simulate = new javax.swing.JButton();
        mainpanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        averagewaitingtime = new javax.swing.JLabel();
        averageturnaroundtime = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        label.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        label.setText("Priority:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(home, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(add, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(label)
                                .addGap(52, 52, 52)
                                .addComponent(priority))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(18, 18, 18))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(bursttime)
                                    .addComponent(arrivaltime, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE))))
                        .addGap(251, 251, 251))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(arrivaltime, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bursttime, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(priority, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(add)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(home)
                .addGap(12, 12, 12))
        );

        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        jLabel5.setText("Average Turn Around Time:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 3, 16)); // NOI18N
        jLabel6.setText("Average Waiting Time:");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Process", "Arrival Time", "Burrst Time", "Priority"
            }
        ));
        jScrollPane1.setViewportView(table);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setText("Process Queue");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Input");

        simulate.setText("Simulate");
        simulate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simulateActionPerformed(evt);
            }
        });

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
                .addContainerGap(362, Short.MAX_VALUE))
        );

        jTextPane1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jScrollPane2.setViewportView(jTextPane1);

        averagewaitingtime.setBackground(new java.awt.Color(255, 255, 255));
        averagewaitingtime.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        averagewaitingtime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        averagewaitingtime.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        averageturnaroundtime.setBackground(new java.awt.Color(255, 255, 255));
        averageturnaroundtime.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        averageturnaroundtime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        averageturnaroundtime.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(simulate, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addComponent(mainpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(180, 180, 180)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(averagewaitingtime, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(152, 152, 152)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(averageturnaroundtime, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(simulate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(mainpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(averagewaitingtime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(averageturnaroundtime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void addMouseClicked(java.awt.event.MouseEvent evt) {
        try{
            if(key){
            }else if(arrivaltime.getText().isEmpty()||bursttime.getText().isEmpty()||priority.getText().isEmpty()){
                JOptionPane.showMessageDialog(this,"All text fields must be filled!");
            }else if(Integer.parseInt(arrivaltime.getText())>=currtime&&Integer.parseInt(bursttime.getText())>=1&&Integer.parseInt(priority.getText())>=1){
                ArrayList<String> data= new ArrayList<String>();
                ArrayList<String> datacpy= new ArrayList<String>();
                data.add("P"+Integer.toString(info.size()+1));
                data.add(arrivaltime.getText());
                data.add(bursttime.getText());
                data.add(priority.getText());
                datacpy.add("P"+Integer.toString(info.size()+1));
                datacpy.add(arrivaltime.getText());
                datacpy.add(bursttime.getText());
                datacpy.add(priority.getText());
                String [] row = {"P"+Integer.toString(info.size()+1), arrivaltime.getText(), bursttime.getText(), priority.getText()};
                DefaultTableModel tab=(DefaultTableModel)table.getModel();
                tab.addRow(row);
                info.add(data);
                sub.add(datacpy);
                Collections.sort(sub ,new Comparator<ArrayList<String>>(){
                    public int compare(ArrayList<String> p1, ArrayList<String> p2) {
                        if (p1.get(1).equals(p2.get(1))) {
                            return Integer.parseInt(p1.get(3)) - Integer.parseInt(p2.get(3));
                        } else {
                            return Integer.parseInt(p1.get(1)) - Integer.parseInt(p2.get(1));
                        }
                    }
                });
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
    }

    private void homeMouseClicked(java.awt.event.MouseEvent evt) {
        if(key){
            new StartWindow().setVisible(true);
            this.dispose();
        }
    }

    private void simulateActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        sub = new ArrayList<ArrayList<String>>();
        for(ArrayList<String> st:info){
            sub.add((ArrayList<String>)st.clone());
        }
        simulate.setEnabled(false);
        if(key){}
        new Thread(){
            public void run(){
                if(!preemptive){
                    Collections.sort(sub ,new Comparator<ArrayList<String>>(){
                        public int compare(ArrayList<String> p1, ArrayList<String> p2) {
                            if (p1.get(1).equals(p2.get(1))) {
                                return Integer.parseInt(p1.get(3)) - Integer.parseInt(p2.get(3));
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
                    while(currtime != totaltime){
                        if(arrived.isEmpty()){
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
                        }
                        remainingBurstTime = Integer.parseInt(arrived.get(0).get(2));
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
                            if(Integer.parseInt(sub.get(i).get(1)) <= currtime){
                                arrived.add(sub.get(i));
                            }
                        }
                        for(int i = 0 ; i<arrived.size();i++){
                            if(sub.contains(arrived.get(i))) sub.remove(arrived.get(i));
                        }
                        Collections.sort(arrived ,new Comparator<ArrayList<String>>(){
                            public int compare(ArrayList<String> p1, ArrayList<String> p2) {
                                return Integer.parseInt(p1.get(3)) - Integer.parseInt(p2.get(3));
                            }
                        });
                    }
                    avgwaitingTime = totalwaitingTime / info.size();
                    averagewaitingtime.setText(String.format("%.02f", avgwaitingTime));
                    avgturnaroundTime = totalturnaroundTime / info.size();
                    averageturnaroundtime.setText(String.format("%.02f", avgturnaroundTime));
                    add.setEnabled(false);
                    home.setEnabled(true);
                    key = true;
                }
                else{
                    while(currtime != totaltime){
                        for(int i = 0 ; i<sub.size();i++){
                            if(currtime == Integer.parseInt(sub.get(i).get(1))){
                                arrived.add(sub.get(i));
                                Collections.sort(arrived ,new Comparator<ArrayList<String>>(){
                                    public int compare(ArrayList<String> p1, ArrayList<String> p2) {
                                        return Integer.parseInt(p1.get(3)) - Integer.parseInt(p2.get(3));
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
                            remainingBurstTime = Integer.parseInt(arrived.get(0).get(2)) - 1;
                            arrived.get(0).set(2, String.valueOf(remainingBurstTime));
                            burstTimes.get(processid).setText(arrived.get(0).get(2));
                            bars.get(processid).setValue(Integer.parseInt(info.get(processid).get(2)) - remainingBurstTime);
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
                                            return Integer.parseInt(p1.get(3)) - Integer.parseInt(p2.get(3));
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
                    averagewaitingtime.setText(String.format("%.02f", avgwaitingTime));
                    avgturnaroundTime = totalturnaroundTime / info.size();
                    averageturnaroundtime.setText(String.format("%.02f", avgturnaroundTime));
                    add.setEnabled(false);
                    home.setEnabled(true);
                    key = true;
                }
            }
        }.start();
    }

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
            java.util.logging.Logger.getLogger(Prioritysimulation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Prioritysimulation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Prioritysimulation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Prioritysimulation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Prioritysimulation().setVisible(true);
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JLabel label;
    private javax.swing.JPanel mainpanel;
    private javax.swing.JTextField priority;
    private javax.swing.JButton simulate;
    private static javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
/*

                    avgwaitingTime = totalwaitingTime / info.size();
                    averagewaitingtime.setText(String.format("%.02f", avgwaitingTime));
                    avgturnaroundTime = totalturnaroundTime / info.size();
                    averageturnaroundtime.setText(String.format("%.02f", avgturnaroundTime));


*/