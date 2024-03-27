package raven.components;

import raven.swing.blur.BlurChild;
import raven.swing.blur.style.GradientColor;
import raven.swing.blur.style.Style;
import raven.swing.blur.style.StyleBorder;
import raven.swing.blur.style.StyleOverlay;

import java.awt.*;
import java.awt.geom.Point2D;

public class Title extends BlurChild {

    public Title() {
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
        // add some title component here
    }
}
