package raven.components;

import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;
import org.kordamp.ikonli.Ikon;
import org.kordamp.ikonli.entypo.Entypo;
import org.kordamp.ikonli.swing.FontIcon;
import raven.drawer.component.header.SimpleHeader;
import raven.drawer.component.header.SimpleHeaderData;
import raven.drawer.component.menu.*;
import raven.drawer.component.menu.data.Item;
import raven.forms.BlurDashboard;
import raven.swing.AvatarIcon;
import raven.swing.blur.BlurChild;
import raven.swing.blur.style.GradientColor;
import raven.swing.blur.style.Style;
import raven.swing.blur.style.StyleBorder;
import raven.swing.blur.style.StyleOverlay;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class SystemMenu extends BlurChild {

    public SystemMenu() {
        super(new Style()
                .setBlur(30)
                .setBorder(new StyleBorder(10)
                        .setOpacity(0.15f)
                        .setBorderWidth(1.2f)
                        .setBorderColor(new GradientColor(new Color(200, 200, 200), new Color(150, 150, 150), new Point2D.Float(0, 0), new Point2D.Float(1f, 0)))
                )
                .setOverlay(new StyleOverlay(new Color(0, 0, 0), 0.2f))
        );
        init();
    }

    private void init() {
        setLayout(new MigLayout("wrap,fill", "[fill]", "[grow 0][fill]"));
        simpleMenu = new SimpleMenu(getMenuOption());

        simpleMenu.setOpaque(false);
        JScrollPane scrollPane = new JScrollPane(simpleMenu);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.getVerticalScrollBar().setOpaque(false);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        scrollPane.getVerticalScrollBar().putClientProperty(FlatClientProperties.STYLE, "" +
                "trackArc:999;" +
                "width:5;" +
                "thumbInsets:0,0,0,0");

        // header
        SimpleHeader header = new SimpleHeader(getHeaderData());
        header.setOpaque(false);
        add(header);
        add(scrollPane);
    }

    private SimpleHeaderData getHeaderData() {
        return new SimpleHeaderData()
                .setTitle("Raven")
                .setDescription("Java developer")
                .setIcon(new AvatarIcon(getClass().getResource("/raven/images/profile.png"), 60, 60, 999));
    }

    private SimpleMenuOption getMenuOption() {
        raven.drawer.component.menu.data.MenuItem items[] = new raven.drawer.component.menu.data.MenuItem[]{
                new Item.Label("MAIN"),
                new Item("Dashboard", "enty-air"),
                new Item.Label("WEB APP"),
                new Item("Email", "enty-archive")
                        .subMenu("Inbox")
                        .subMenu("Read")
                        .subMenu("Compost"),
                new Item("Chat", "enty-newsletter"),
                new Item("Calendar", "enty-notifications-off"),
                new Item.Label("COMPONENT"),
                new Item("Advanced UI", "enty-old-phone")
                        .subMenu("Cropper")
                        .subMenu("Owl Carousel")
                        .subMenu("Sweet Alert")
        };
        return new SimpleMenuOption() {
            @Override
            public Icon buildMenuIcon(String path, float scale) {
                FontIcon icon = new FontIcon();
                icon.setIconColor(new Color(208, 208, 208));
                icon.setIconSize(20);
                icon.setIkon(Entypo.findByDescription(path));
                return icon;
            }
        }
                .setIconScale(0.5f)
                .setMenus(items)
                .setMenuStyle(new SimpleMenuStyle() {
                    @Override
                    public void styleMenuPanel(JPanel panel, int[] index) {
                        panel.setOpaque(false);
                    }

                    @Override
                    public void styleMenuItem(JButton menu, int[] index) {
                        menu.setContentAreaFilled(false);
                    }
                })
                .addMenuEvent(new MenuEvent() {
                    @Override
                    public void selected(MenuAction menuAction, int[] ints) {
                        System.out.print("Menu select");
                        if (ints.length == 1) {
                            int index = ints[0];
                            if (index == 0) {
                                FormManager.getInstance().showFormWithBlur("Dashboard", new BlurDashboard());
                            }
                        } else if (ints.length == 2) {
                            int index = ints[0];
                            int subIndex = ints[1];
                            if (index == 1) {
                                if (subIndex == 0) {
                                    FormManager.getInstance().showFormWithBlur("Blur Inbox Title", new JLabel("Inbox", SwingConstants.CENTER));
                                } else if (subIndex == 1) {
                                    FormManager.getInstance().showForm("Simple Read Title", new JLabel("Read", SwingConstants.CENTER));
                                }
                            }
                        }
                    }
                });

    }

    private SimpleMenu simpleMenu;
}
