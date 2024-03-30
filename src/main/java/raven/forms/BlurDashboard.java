package raven.forms;

import com.formdev.flatlaf.FlatClientProperties;
import net.miginfocom.swing.MigLayout;
import raven.chart.ChartLegendRenderer;
import raven.chart.bar.HorizontalBarChart;
import raven.chart.data.category.DefaultCategoryDataset;
import raven.chart.data.pie.DefaultPieDataset;
import raven.chart.line.LineChart;
import raven.chart.pie.PieChart;
import raven.data.DateCalculator;
import raven.swing.blur.BlurChild;
import raven.swing.blur.style.GradientColor;
import raven.swing.blur.style.Style;
import raven.swing.blur.style.StyleBorder;
import raven.swing.blur.style.StyleOverlay;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class BlurDashboard extends JPanel {

    public BlurDashboard() {
        init();
    }

    private void init() {
        setOpaque(false);
        setLayout(new MigLayout("wrap,fillx", "[fill]", "[top]"));
        createLineChart();
        createBarChart();
        createPieChart();
        lineChart.startAnimation();
        barChart1.startAnimation();
        pieChart.startAnimation();
    }

    private BlurChild createBlur() {
        BlurChild panel = new BlurChild(new Style()
                .setBlur(10)
                .setBorder(new StyleBorder(10)
                        .setBorderWidth(0.8f)
                        .setOpacity(0.1f)
                        .setBorderColor(new GradientColor(new Color(150, 150, 150), new Color(200, 200, 200), new Point2D.Float(0, 0), new Point2D.Float(1f, 0)))
                )
                .setOverlay(new StyleOverlay(new Color(250, 250, 250), 0.08f))
        );
        return panel;
    }

    private void createLineChart() {
        lineChart = new LineChart();
        BlurChild panel = createBlur();
        panel.setLayout(new BorderLayout());
        panel.putClientProperty(FlatClientProperties.STYLE, ""
                + "border:5,5,5,5,$Component.borderColor,,20;"
                + "background:$Chart.background");
        JScrollPane scroll = new JScrollPane(lineChart);
        scroll.putClientProperty(FlatClientProperties.STYLE, ""
                + "border:0,0,0,0");

        lineChart.setOpaque(false);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scroll.getHorizontalScrollBar().setOpaque(false);
        scroll.getHorizontalScrollBar().putClientProperty(FlatClientProperties.STYLE, "" +
                "trackArc:999;" +
                "width:5;" +
                "thumbInsets:0,0,0,0");

        panel.add(scroll);
        add(panel);
        createLineChartData();
    }

    private void createLineChartData() {
        DefaultCategoryDataset<String, String> categoryDataset = new DefaultCategoryDataset<>();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("MMM dd, yyyy");
        Random ran = new Random();
        int randomDate = 7;
        for (int i = 1; i <= randomDate; i++) {
            String date = df.format(cal.getTime());
            if (i == 1) {
                categoryDataset.addValue(ran.nextInt(1) + 5, "Income", date);
                categoryDataset.addValue(ran.nextInt(1) + 5, "Expense", date);
                categoryDataset.addValue(ran.nextInt(1) + 5, "Profit", date);
            } else {
                categoryDataset.addValue(ran.nextInt(700) + 5, "Income", date);
                categoryDataset.addValue(ran.nextInt(700) + 5, "Expense", date);
                categoryDataset.addValue(ran.nextInt(700) + 5, "Profit", date);
            }

            cal.add(Calendar.DATE, 1);
        }

        /**
         * Control the legend we do not show all legend
         */
        try {
            Date date = df.parse(categoryDataset.getColumnKey(0));
            Date dateEnd = df.parse(categoryDataset.getColumnKey(categoryDataset.getColumnCount() - 1));

            DateCalculator dcal = new DateCalculator(date, dateEnd);
            long diff = dcal.getDifferenceDays();

            double d = Math.ceil((diff / 10f));
            lineChart.setLegendRenderer(new ChartLegendRenderer() {
                @Override
                public Component getLegendComponent(Object legend, int index) {
                    if (index % d == 0) {
                        return super.getLegendComponent(legend, index);
                    } else {
                        return null;
                    }
                }
            });
        } catch (ParseException e) {
            System.err.println(e);
        }

        lineChart.setCategoryDataset(categoryDataset);
        lineChart.getChartColor().addColor(Color.decode("#38bdf8"), Color.decode("#fb7185"), Color.decode("#34d399"));
        JLabel header = new JLabel("Income Data");
        header.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:+2");
        lineChart.setHeader(header);
    }

    private void createBarChart() {
        // BarChart 1
        barChart1 = new HorizontalBarChart();
        barChart1.setOpaque(false);
        JLabel header1 = new JLabel("Monthly Income");
        header1.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:+2");
        barChart1.setHeader(header1);
        barChart1.setBarColor(Color.decode("#f97316"));
        barChart1.setDataset(createData());
        BlurChild panel = createBlur();
        panel.setLayout(new BorderLayout());
        panel.putClientProperty(FlatClientProperties.STYLE, ""
                + "border:5,5,5,5,$Component.borderColor,,20;"
                + "background:$Chart.background");
        panel.add(barChart1);
        add(panel, "split 2,gap 0 20, width 500");
    }

    private void createPieChart() {
        pieChart = new PieChart();
        pieChart.setOpaque(false);
        JLabel header1 = new JLabel("Monthly Income");
        header1.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:+2");
        pieChart.setHeader(header1);
        pieChart.getChartColor().addColor(Color.decode("#f87171"), Color.decode("#fb923c"), Color.decode("#fbbf24"), Color.decode("#a3e635"), Color.decode("#34d399"), Color.decode("#22d3ee"), Color.decode("#818cf8"), Color.decode("#c084fc"));

        pieChart.setDataset(createData());
        BlurChild panel = createBlur();
        panel.setLayout(new BorderLayout());
        panel.putClientProperty(FlatClientProperties.STYLE, ""
                + "border:5,5,5,5,$Component.borderColor,,20;"
                + "background:$Chart.background");
        panel.add(pieChart);
        add(panel);
    }

    private DefaultPieDataset createData() {
        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
        Random random = new Random();
        dataset.addValue("July (ongoing)", random.nextInt(100));
        dataset.addValue("June", random.nextInt(100));
        dataset.addValue("May", random.nextInt(100));
        dataset.addValue("April", random.nextInt(100));
        dataset.addValue("March", random.nextInt(100));
        dataset.addValue("February", random.nextInt(100));
        return dataset;
    }

    private LineChart lineChart;
    private PieChart pieChart;
    private HorizontalBarChart barChart1;
}