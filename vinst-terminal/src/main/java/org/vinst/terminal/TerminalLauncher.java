package org.vinst.terminal;

import javax.swing.*;

/**
 * @author Sergey Mischenko
 */
public class TerminalLauncher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Terminal().start());
    }
}
