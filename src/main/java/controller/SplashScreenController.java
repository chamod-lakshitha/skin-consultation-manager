package controller;

import javax.swing.*;
import java.util.Random;

public class SplashScreenController {
    public SplashScreenController() {
    }

    /*
        load GUI
         */
    public void load(JProgressBar jpb) {
        do {
            int value = new Random().nextInt(1, 15);
            jpb.setValue(jpb.getValue() + value < 100 ? jpb.getValue() + value : 100); //assigning random values to progress bar
            try {
                Thread.sleep(300);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } while (jpb.getValue() < 100);
    }
}
