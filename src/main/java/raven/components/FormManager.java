package raven.components;

import javax.swing.*;
import java.awt.*;

public class FormManager {

    private static FormManager instance;
    private JDesktopPane desktopPane;

    public static FormManager getInstance() {
        if (instance == null) {
            instance = new FormManager();
        }
        return instance;
    }

    private FormManager() {

    }

    public void setDesktopPane(JDesktopPane desktopPane) {
        this.desktopPane = desktopPane;
    }

    public void showForm(String title, Component component) {
        JInternalFrame frame = new JInternalFrame(title, true, true, true, true);
        frame.setSize(new Dimension(600, 450));
        frame.add(component);
        frame.setVisible(true);
        desktopPane.add(frame, 0);
    }
}
