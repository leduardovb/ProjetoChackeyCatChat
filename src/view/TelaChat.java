package view;

import entities.Client;
import java.util.ArrayList;
import javax.swing.*;
import model.bean.User;

public class TelaChat extends javax.swing.JFrame {
    User user;
    Client client;
    ArrayList<String> onlineChats;
    ArrayList<JTextArea> areas;
    ArrayList<JTextField> textBoxes;
    
    public TelaChat() {
        initComponents();
    }
    
    public TelaChat(User user , Client client) {
        this.user = user;
        this.client = client;
        initComponents();
    }
    
    public TelaChat(User user) {
        onlineChats = new ArrayList<>();
        areas = new ArrayList<>();
        textBoxes = new ArrayList<>();
        this.user = user;
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chatViews = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(chatViews, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(chatViews, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    public void createNewChat(String userChat) {
        if(!onlineChats.contains(userChat)) {
            JPanel chat = new JPanel();
            JPanel userPanel = new JPanel();
            JLabel userIcon = new JLabel();
            JLabel userNickName = new JLabel();
            JLabel userStatus = new JLabel();
            JTextField textBox = new JTextField();
            JTextArea chatScreen = new JTextArea();
            JScrollPane scrollChat = new JScrollPane();

            chat.setBackground(new java.awt.Color(0, 43, 46));

            userPanel.setBackground(new java.awt.Color(0, 47, 50));

            userIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8_user_50px_2.png"))); // NOI18N

            userNickName.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
            userNickName.setForeground(new java.awt.Color(240, 240, 240));
            userNickName.setText(userChat);

            userStatus.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
            userStatus.setForeground(new java.awt.Color(0, 204, 0));
            userStatus.setText("Online");

            javax.swing.GroupLayout userPanelLayout = new javax.swing.GroupLayout(userPanel);
            userPanel.setLayout(userPanelLayout);
            userPanelLayout.setHorizontalGroup(
                userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(userPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(userIcon)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(userNickName)
                        .addComponent(userStatus))
                    .addContainerGap(296, Short.MAX_VALUE))
            );
            userPanelLayout.setVerticalGroup(
                userPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(userIcon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(userPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(userNickName)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(userStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(16, Short.MAX_VALUE))
            );

            textBox.setBackground(new java.awt.Color(0, 43, 46));
            textBox.setFont(new java.awt.Font("Segoe UI Semibold", 0, 13));
            textBox.setForeground(new java.awt.Color(240, 240, 240));
            textBox.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 240, 240)));
            
            textBox.addKeyListener(new java.awt.event.KeyAdapter() {
                @Override
                public void keyPressed(java.awt.event.KeyEvent evt) {
                    textBoxKeyPressed(evt);
                }
            });

            chatScreen.setBackground(new java.awt.Color(0, 43, 46));
            chatScreen.setBorder(null);
            chatScreen.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14));
            chatScreen.setForeground(new java.awt.Color(240, 240, 240));
            scrollChat.setViewportView(chatScreen);

            javax.swing.GroupLayout chatLayout = new javax.swing.GroupLayout(chat);
            chat.setLayout(chatLayout);
            chatLayout.setHorizontalGroup(
                chatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(userPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, chatLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(chatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(scrollChat)
                        .addComponent(textBox))
                    .addContainerGap())
            );
            chatLayout.setVerticalGroup(
                chatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(chatLayout.createSequentialGroup()
                    .addComponent(userPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(scrollChat, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(textBox, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(18, Short.MAX_VALUE))
            );
            
            onlineChats.add(userChat);
            areas.add(chatScreen);
            textBoxes.add(textBox);
            chatViews.add(userChat , chat);
        }
    }
    
    private void textBoxKeyPressed(java.awt.event.KeyEvent evt) {
        if(evt.getKeyCode() == 10) {
            int pos = chatViews.getSelectedIndex();
            
            String msg = user.getUserNick() + ": "+ textBoxes.get(pos).getText() + "\n";
        
            areas.get(pos).append(msg);
            
            textBoxes.get(pos).setText("");
        }
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane chatViews;
    // End of variables declaration//GEN-END:variables
}
