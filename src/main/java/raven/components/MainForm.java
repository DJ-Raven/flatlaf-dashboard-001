package raven.components;

import net.miginfocom.swing.MigLayout;
import raven.swing.blur.BlurBackground;
import raven.swing.blur.style.StyleOverlay;

import javax.swing.*;
import java.awt.*;

public class MainForm extends BlurBackground {

    public MainForm() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/raven/images/background2.jpg"));
        setOverlay(new StyleOverlay(new Color(50, 50, 50), 0.4f));
        setImage(icon.getImage());
        init();
    }

    private void init() {
        setLayout(new MigLayout("fill,insets 0 0 6 6", "[fill]", "[fill]"));

        systemMenu = new SystemMenu();
        title = new Title();
        desktopPane = new JDesktopPane();
        desktopPane.setLayout(null);


        desktopPane.setOpaque(false);
        FormManager.getInstance().setDesktopPane(desktopPane);

        add(systemMenu, "dock west,gap 6 6 6 6,width 280!");
        add(title, "dock north,gap 0 6 6 6,height 50!");
        add(desktopPane);
    }

    private SystemMenu systemMenu;
    private Title title;
    private JDesktopPane desktopPane;
}
