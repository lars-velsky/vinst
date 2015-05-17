package org.vinst.terminal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vinst.core.Core;
import org.vinst.core.CoreFactory;

import javax.swing.*;
import java.awt.*;
import java.util.Properties;

/**
 * @author Sergey Mischenko
 */
public class Terminal {
    private static final Logger logger = LoggerFactory.getLogger(Terminal.class);

    public void start() {
        // Show "connecting window"
        JFrame frame = new JFrame("vinst");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Connecting...");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label, BorderLayout.CENTER);
        frame.setContentPane(panel);

        frame.setSize(new Dimension(400, 400));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Start background task for create core
        logger.info("Creating core");
        SwingWorker<Core, Object> coreCreator = new SwingWorker<Core, Object>() {
            @Override
            protected Core doInBackground() throws Exception {
                Properties properties = new Properties();
                return CoreFactory.createCore(properties);
            }

            @Override
            protected void done() {
                logger.info("Core created");
                label.setText("Connected");
            }
        };
        coreCreator.execute();
    }
}
