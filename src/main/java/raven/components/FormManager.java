package raven.components;

import com.formdev.flatlaf.FlatClientProperties;
import raven.swing.blur.BlurChild;
import raven.swing.blur.style.Style;
import raven.swing.blur.style.StyleBorder;
import raven.swing.blur.style.StyleOverlay;

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
        frame.add(component);
        frame.setSize(new Dimension(1000, 600));
        frame.setFrameIcon(null);
        try {
            frame.setMaximum(true);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        frame.setVisible(true);
        desktopPane.add(frame, 0);
    }

    public void showFormWithBlur(String title, Component component) {
        JInternalFrame frame = createBlurFrame(title, component);
        frame.setSize(new Dimension(1000, 600));
        try {
            frame.setMaximum(true);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        frame.setVisible(true);
        desktopPane.add(frame, 0);
    }

    private JInternalFrame createBlurFrame(String title, Component component) {
        JInternalFrame frame = new JInternalFrame(title, true, true, true, true);
        BlurChild child = new BlurChild(new Style()
                .setBlur(10)
                .setBorder(new StyleBorder(10)
                        .setBorderWidth(1.2f)
                        .setOpacity(0.1f)
                        .setMargin(new Insets(10, 0, 0, 0))
                        .setBorderColor(new Color(255, 255, 255))
                )
                .setOverlay(new StyleOverlay(new Color(0, 0, 0), 0.03f))
        );
        child.setLayout(new BorderLayout());
        child.add(component);
        frame.setFrameIcon(null);
        frame.putClientProperty(FlatClientProperties.STYLE, "" +
                "border:0,0,0,0");
        frame.add(child);
        return frame;
    }
}
