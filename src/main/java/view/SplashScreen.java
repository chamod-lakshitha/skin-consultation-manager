package view;

import controller.SplashScreenController;

import javax.swing.*;
import java.awt.*;

public class SplashScreen extends JFrame {
    SplashScreenController splashScreenController;
    public SplashScreen() {
        splashScreenController = new SplashScreenController();

        /*
        creating required JComponents
         */
        JLabel jl1 = new JLabel("Westminster Skin Clinic.");
        JLabel jl2 = new JLabel(new ImageIcon(new ImageIcon("src/main/assets/cover.jpg").getImage().getScaledInstance(500, 295, Image.SCALE_SMOOTH)));
        JProgressBar jpb = new JProgressBar();
        JLabel jl3 = new JLabel("loading..");

        /*
        formatting JComponents
         */
        jl1.setOpaque(true);
        jl1.setBackground(Color.green);
        jl1.setHorizontalAlignment(SwingConstants.CENTER);
        jl1.setFont(jl1.getFont().deriveFont(25f));
        jpb.setStringPainted(true);
        jpb.setForeground(Color.blue);
        jl3.setForeground(Color.lightGray);
        jl3.setHorizontalAlignment(SwingConstants.CENTER);
        jl3.setFont(jl3.getFont().deriveFont(12f));

        /*
        placing JComponents in JFrame
         */
        jl1.setBounds(100, 15, 400, 45);
        jl2.setBounds(50, 75, 500, 295);
        jpb.setBounds(50, 363, 500, 20);
        jl3.setBounds(0, 390, 600, 20);

        /*
        adding components to JFrame
         */
        this.add(jl1);
        this.add(jl2);
        this.add(jpb);
        this.add(jl3);

        /*
        changing JFrame properties
         */
        this.setSize(600, 425);
        this.setLayout(null);
        this.setUndecorated(true);
        this.getContentPane().setBackground(Color.WHITE);
        this.setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon("src/main/assets/icon.png").getImage());
        this.setVisible(true);

        splashScreenController.load(jpb);
        new Application();
        this.dispose();
    }
}
