/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.genreapp2;

/**
 *
 * @author David Constantin Bulugea
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;


public class GenreApp2 extends JFrame {
    
    //Declare Variables
    private JButton likeButton;
    private JButton unlikeButton; 
    private JButton genreOne; 
    private JButton genreTwo;
    private JButton genreOneRemove;
    private JButton genreTwoRemove;
    private CardLayout cardLayout;
    private JLabel messageLabel;
    private JPanel mainPanel;
    private JPanel panel1, panel2, panelGenre1, panelGenre2;
    private JLabel songCount1, songCount2, songCount3, songCount4;
    
    
    //Constructor
    public GenreApp2() {
        //Properties
        setTitle("Genre App Project");
        setSize(1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        
        //GUI 
        messageLabel = new JLabel();
        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);
        
        //Buttons on side panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS)); 
        
        likeButton = new JButton("Like️");
        genreOneRemove = new JButton("Remove from rock");
        genreTwoRemove = new JButton("Remove from rap");
        genreOneRemove.setVisible(false); //making buttons invisible initially
        genreTwoRemove.setVisible(false);
       
        //Panels - Layout, Size
        panel1 = createPanel("<html><h1>Home</h1></html>", Color.WHITE);
        panel2 = createPanel("<html><h1>Liked Songs</h1></html>", Color.WHITE);
        songCount1 = (JLabel)panel1.getComponent(2);
        songCount2 = (JLabel)panel2.getComponent(2);
        
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        panel1.setPreferredSize(new Dimension(1000, 1000));
        panel2.setPreferredSize(new Dimension(1000, 1000));
        
        mainPanel.add(panel1, "panel1");
        mainPanel.add(panel2, "panel2");
        addSongs(panel1);
        add(mainPanel);
    
        //Home Button
        JButton homeButton = new JButton("Home");
        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "panel1");
                unlikeButton.setVisible(false); 
                genreOne.setVisible(false);
                genreTwo.setVisible(false);
                likeButton.setVisible(true);
                genreOneRemove.setVisible(false);
                genreTwoRemove.setVisible(false);
            }
        });
        
        //Liked songs button
        JButton likedSongs = new JButton("Liked Songs");
        likedSongs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "panel2");
                likeButton.setVisible(false);
                unlikeButton.setVisible(true); 
                genreOne.setVisible(true);
                genreTwo.setVisible(true);
                genreOneRemove.setVisible(false);
                genreTwoRemove.setVisible(false);
            }
        });
        
        //Unlike button
        unlikeButton = new JButton("Remove from liked");
        unlikeButton.setVisible(false); 
        unlikeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                unlikeSong(); 
            }
        });

        //Entering playlist 1
        JButton genreButton = new JButton("Rock"); 
        genreButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "panelGenre1");
                unlikeButton.setVisible(false);
                genreOne.setVisible(false);
                genreTwo.setVisible(false);
                likeButton.setVisible(false);
                genreOneRemove.setVisible(true);
                genreTwoRemove.setVisible(false);
            }
        });
        
                //Entering genre 2 playlist
        JButton genreButton2 = new JButton("Rap");
        genreButton2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                cardLayout.show(mainPanel, "panelGenre2");
                genreOne.setVisible(false);
                genreTwo.setVisible(false);
                unlikeButton.setVisible(false);
                likeButton.setVisible(false);
                genreOneRemove.setVisible(false);
                genreTwoRemove.setVisible(true);

            }
        });

        
        //Like button ActionListener
        likeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                likeSong();
            }
        });
        
                // Adding to genre 1 playlist
        genreOne = new JButton("Add to Rock");
        genreOne.setVisible(false);
        genreOne.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                addToGenre1();
            }
        });

        //Adding to genre 2 playlist
        genreTwo = new JButton("Add to Rap");
        genreTwo.setVisible(false);
        genreTwo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                addToGenre2();
            }
        });
       
        add(buttonPanel, BorderLayout.NORTH);
        add(messageLabel);

        //Adding buttons

        buttonPanel.add(homeButton);
        buttonPanel.add(likeButton);
        buttonPanel.add(likedSongs);
        buttonPanel.add(unlikeButton);
        buttonPanel.add(genreOne);
        buttonPanel.add(genreTwo);
        buttonPanel.add(genreButton);         
        buttonPanel.add(genreButton2);
        buttonPanel.add(genreOneRemove);
        buttonPanel.add(genreTwoRemove);
        
    //Calling methods to remove songs from genre
        genreOneRemove.addActionListener(e -> {
            removeFromGenre1();
    });

        genreTwoRemove.addActionListener(e -> {
            removeFromGenre2();
    }); 
        

        //Genre playlist panels
        panelGenre1 = createPanel("<html><h1>Rock</h1></html>", Color.WHITE);
        panelGenre2 = createPanel("<html><h1>Rap</h1></html>", Color.WHITE);
        
        songCount3 = (JLabel)panelGenre1.getComponent(2);
        songCount4 = (JLabel)panelGenre2.getComponent(2);
        
        mainPanel.add(panelGenre1, "panelGenre1");
        mainPanel.add(panelGenre2, "panelGenre2");
        
        panelGenre1.setPreferredSize(new Dimension(1000, 1000));
        panelGenre2.setPreferredSize(new Dimension(1000, 1000));
        
        panelGenre1.setVisible(false); //make them invisible initially
        panelGenre1.setVisible(false);
    }
    
    

    // Create Panel Method
    private JPanel createPanel(String text, Color color) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(color);
        
        // Search Bar
        JLabel label = new JLabel(text);
        panel.add(label);
        
        JTextField searchBar = new JTextField("Search Here");
        searchBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, searchBar.getPreferredSize().height));
        panel.add(searchBar);
        
        searchBar.addActionListener(e -> filterSongList(panel, searchBar.getText()));
        
        // Song Count 
        JLabel songCountLabel = new JLabel("Songs: 0");
        panel.add(songCountLabel);
        return panel;
    }
    
    
 
    //Random Songs List
    private void addSongs(JPanel panel) {
        
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        //Loop
        Random random = new Random();
        for (int i = 1; i <= 10; i++) {
            JLabel songLabel = new JLabel("Song " + i);

            // Adding a mouse listener to each song label
            songLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JLabel clickedLabel = (JLabel) e.getSource();
                    
                    // Toggle selection (change background color)
                    if (clickedLabel.getBackground().equals(Color.WHITE)) {
                        clickedLabel.setBackground(Color.CYAN);
                    } else {
                        clickedLabel.setBackground(Color.WHITE);
                    }
                }
            });
            
            

            // Customising the labels
            songLabel.setOpaque(true);
            songLabel.setBackground(Color.WHITE);
            songLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            
            // Adding the label to the panel
            panel.add(songLabel);
        }
    }

    // Moves songs when liked
    private void likeSong() {
        // Get all components in panel 1
        Component[] components = panel1.getComponents();
        List<JLabel> selectedLabels = new ArrayList<>();

        // Look for selected labels
        for (Component component : components) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                if (label.getBackground().equals(Color.CYAN)) {
                    selectedLabels.add(label);
                }
            }
        }
        // Updating the songs count
        updateSongCounts();
        // Move selected labels to panel 2
        for (JLabel label : selectedLabels) {
            panel1.remove(label);
            panel2.add(label);
        }
        panel1.revalidate();
        panel1.repaint();
        panel2.revalidate();
        panel2.repaint();
    }
    
    // Move songs when unliked
    private void unlikeSong() {
        // Get all components in panel 2
        Component[] components = panel2.getComponents();
        List<JLabel> selectedLabels = new ArrayList<>();

        // Find labels
        for (Component component : components) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                if (label.getBackground().equals(Color.CYAN)) {
                    selectedLabels.add(label);
                }
            }
        }
        // Update song count
        updateSongCounts();
        // Move selected labels back to panel 1
        for (JLabel label : selectedLabels) {
            panel2.remove(label);
            panel1.add(label);
        }

        // Show changes
        panel1.revalidate();
        panel1.repaint();
        panel2.revalidate();
        panel2.repaint();
    }
    
    // Method for if you move a liked song to playlist 1
    private void addToGenre1() {
    // Get all components in panel 2
    Component[] components = panel2.getComponents();
    List<JLabel> selectedLabels = new ArrayList<>();

    // Finds labels
    for (Component component : components) {
        if (component instanceof JLabel) {
            JLabel label = (JLabel) component;
            if (label.getBackground().equals(Color.CYAN)) {
                selectedLabels.add(label);
            }
        }
    }
    // Updating song count
    updateSongCounts();
    // Move selected labels to panel 3 
    for (JLabel label : selectedLabels) {
        panel2.remove(label);
        panelGenre1.add(label);
    }

    // Show changes
    panel2.revalidate();
    panel2.repaint();
    panelGenre1.revalidate();
    panelGenre1.repaint();
    
    
}
    
    // Method for if you move a liked song to playlist 2
    private void addToGenre2(){
        Component[] components = panel2.getComponents();
        List<JLabel> selectedLabels = new ArrayList<>();
        
        for (Component component : components){
            if (component instanceof JLabel){
                JLabel label = (JLabel) component;
                if(label.getBackground().equals(Color.CYAN)){
                    selectedLabels.add(label);
                }
            }
        }
        // Updating song count
        updateSongCounts();
        //Move selected labels to panel 4 
        for (JLabel label : selectedLabels){
            panel2.remove(label);
            panelGenre2.add(label);
        }
        // Show changes
        panel2.revalidate();
        panel2.repaint();
        panelGenre2.revalidate();
        panelGenre2.repaint();
    }
    
    // Method for if you're removing songs from playlist 1 and sending them to the home menu
    private void removeFromGenre1() {
    Component[] components = panelGenre1.getComponents();
    List<JLabel> selectedLabels = new ArrayList<>();

    for (Component component : components) {
        if (component instanceof JLabel) {
            JLabel label = (JLabel) component;
            if (label.getBackground().equals(Color.CYAN)) { 
                selectedLabels.add(label);
            }
        }
    }
    // Update song counts
    updateSongCounts();
    for (JLabel label : selectedLabels) {
        panelGenre1.remove(label);
        panel1.add(label);
    }

    panelGenre1.revalidate();
    panelGenre1.repaint();
    panel1.revalidate();
    panel1.repaint();
}
    // Method for if you're removing songs from playlist 2 and sending them to the home menu
    private void removeFromGenre2() {
    Component[] components = panelGenre2.getComponents();
    List<JLabel> selectedLabels = new ArrayList<>();

    for (Component component : components) {
        if (component instanceof JLabel) {
            JLabel label = (JLabel) component;
            if (label.getBackground().equals(Color.CYAN)) { 
                selectedLabels.add(label);
            }
        }
    }
    // Update song count
    updateSongCounts();
    for (JLabel label : selectedLabels) {
        panelGenre2.remove(label);
        panel1.add(label);
    }
    panelGenre2.revalidate();
    panelGenre2.repaint();
    panel1.revalidate();
    panel1.repaint();
}        
    
       //Filtering for the search bar
    private void filterSongList(JPanel panel, String query) {
    Component[] components = panel.getComponents();
    for (Component component : components) {
        if (component instanceof JLabel) {
            JLabel label = (JLabel) component;
            if (!label.getText().toLowerCase().contains(query.toLowerCase())) {
                label.setVisible(false);
            } else {
                label.setVisible(true);
            }
        }
    }
    panel.revalidate();
    panel.repaint();
}

    
        // Updating the song counts found at the top of each panel
    private void updateSongCounts() {
    songCount1.setText("Songs: " + (panel1.getComponentCount() - 2));
    songCount2.setText("Songs: " + (panel2.getComponentCount() - 2));
    songCount3.setText("Songs: " + (panelGenre1.getComponentCount() - 2));
    songCount4.setText("Songs: " + (panelGenre2.getComponentCount() - 2));
}
    
    //Main class
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                GenreApp2 gui = new GenreApp2();
                gui.setVisible(true);
            }
        });
    }
}