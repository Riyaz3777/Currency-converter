package com.currencyconverter;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

/**
 * A simple, clean Swing GUI for converting between currencies.
 * The actual conversion runs on a background thread (SwingWorker)
 * so the UI never freezes while waiting on the network.
 */
public class CurrencyConverterGUI extends JFrame {

    // Common currency codes, shown in the dropdowns
    private static final String[] CURRENCIES = {
            "USD", "EUR", "GBP", "INR", "JPY", "AUD", "CAD",
            "CHF", "CNY", "SGD", "AED", "NZD", "ZAR", "SEK"
    };

    private final JTextField amountField;
    private final JComboBox<String> fromBox;
    private final JComboBox<String> toBox;
    private final JLabel resultLabel;
    private final JButton convertButton;
    private final JButton swapButton;

    private final ExchangeRateService exchangeRateService;
    private final DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

    public CurrencyConverterGUI() {
        exchangeRateService = new ExchangeRateService();

        setTitle("Currency Converter");
        setSize(420, 260);
        setMinimumSize(new Dimension(380, 240));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Amount
        gbc.gridx = 0; gbc.gridy = 0;
        mainPanel.add(new JLabel("Amount:"), gbc);
        amountField = new JTextField("1");
        gbc.gridx = 1; gbc.gridy = 0; gbc.gridwidth = 2;
        mainPanel.add(amountField, gbc);
        gbc.gridwidth = 1;

        // From currency
        gbc.gridx = 0; gbc.gridy = 1;
        mainPanel.add(new JLabel("From:"), gbc);
        fromBox = new JComboBox<>(CURRENCIES);
        fromBox.setSelectedItem("USD");
        gbc.gridx = 1; gbc.gridy = 1;
        mainPanel.add(fromBox, gbc);

        // Swap button
        swapButton = new JButton("\u21C4");
        swapButton.setToolTipText("Swap currencies");
        gbc.gridx = 2; gbc.gridy = 1;
        mainPanel.add(swapButton, gbc);

        // To currency
        gbc.gridx = 0; gbc.gridy = 2;
        mainPanel.add(new JLabel("To:"), gbc);
        toBox = new JComboBox<>(CURRENCIES);
        toBox.setSelectedItem("INR");
        gbc.gridx = 1; gbc.gridy = 2;
        mainPanel.add(toBox, gbc);

        // Convert button
        convertButton = new JButton("Convert");
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(convertButton, gbc);

        // Result
        resultLabel = new JLabel(" ", SwingConstants.CENTER);
        resultLabel.setFont(resultLabel.getFont().deriveFont(Font.BOLD, 16f));
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 3;
        mainPanel.add(resultLabel, gbc);

        add(mainPanel);

        convertButton.addActionListener(e -> performConversion());
        swapButton.addActionListener(e -> swapCurrencies());
        amountField.addActionListener(e -> performConversion()); // Enter key triggers convert
    }

    private void swapCurrencies() {
        String from = (String) fromBox.getSelectedItem();
        String to = (String) toBox.getSelectedItem();
        fromBox.setSelectedItem(to);
        toBox.setSelectedItem(from);
    }

    private void performConversion() {
        String amountText = amountField.getText().trim();
        double amount;
        try {
            amount = Double.parseDouble(amountText);
        } catch (NumberFormatException ex) {
            resultLabel.setForeground(Color.RED);
            resultLabel.setText("Please enter a valid number.");
            return;
        }

        String from = (String) fromBox.getSelectedItem();
        String to = (String) toBox.getSelectedItem();

        convertButton.setEnabled(false);
        resultLabel.setForeground(Color.DARK_GRAY);
        resultLabel.setText("Converting...");

        // SwingWorker keeps the network call off the Event Dispatch Thread
        SwingWorker<Double, Void> worker = new SwingWorker<>() {
            @Override
            protected Double doInBackground() throws Exception {
                return exchangeRateService.convert(amount, from, to);
            }

            @Override
            protected void done() {
                convertButton.setEnabled(true);
                try {
                    double converted = get();
                    resultLabel.setForeground(new Color(0, 128, 0));
                    resultLabel.setText(decimalFormat.format(amount) + " " + from
                            + " = " + decimalFormat.format(converted) + " " + to);
                } catch (Exception ex) {
                    resultLabel.setForeground(Color.RED);
                    resultLabel.setText("Conversion failed. Check your internet connection.");
                }
            }
        };
        worker.execute();
    }
}
