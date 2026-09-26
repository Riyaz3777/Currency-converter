package com.currencyconverter;

import javax.swing.SwingUtilities;

/**
 * Entry point for the Currency Converter application.
 */
public class Main {
    public static void main(String[] args) {
        // Run the GUI on Swing's event dispatch thread
        SwingUtilities.invokeLater(() -> {
            CurrencyConverterGUI gui = new CurrencyConverterGUI();
            gui.setVisible(true);
        });
    }
}
