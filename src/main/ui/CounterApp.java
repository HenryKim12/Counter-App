package ui;

import model.Counter;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CounterApp {

    private JFrame frame;
    private JPanel buttonPanel;
    private JPanel counterPanel;
    private JLabel count;
    private Counter counter = new Counter();
    private JButton increaseButton;
    private JButton decreaseButton;
    private JButton resetButton;
    private JsonReader reader = new JsonReader("./data/counter.json");
    private JsonWriter writer = new JsonWriter("./data/counter.json");

    public CounterApp() {
        frame = new JFrame("Counter App");
        frame.setSize(500, 250);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        setIncreaseButton();
        setDecreaseButton();
        setResetButton();

        setCounterPanel();
        setButtonPanel();

        setSaveAndLoad();

        frame.setVisible(true);
    }

    private void setSaveAndLoad() {
        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                setCounterPanel();
            }

            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    writer.open();
                    writer.writeCounter(counter);
                    writer.close();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {}

            @Override
            public void windowIconified(WindowEvent e) {}

            @Override
            public void windowDeiconified(WindowEvent e) {}

            @Override
            public void windowActivated(WindowEvent e) {
                try {
                    reader.readCounter();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
    }

    public void setIncreaseButton() {
        increaseButton = new JButton("increase");
        try {
            counter.setCount(reader.readCounter());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        increaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                counter.increase();
                count.setText(String.valueOf(counter.getCount()));
            }
        });
    }

    public void setDecreaseButton() {
        decreaseButton = new JButton("decrease");
        decreaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                counter.decrease();
                count.setText(String.valueOf(counter.getCount()));
            }
        });
    }

    public void setResetButton() {
        resetButton = new JButton("reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                counter.reset();
                count.setText(String.valueOf(counter.getCount()));
            }
        });
    }

    private void setCounterPanel() {
        counterPanel = new JPanel();

        try {
            int prev = reader.readCounter();
            count = new JLabel();
            count.setText(String.valueOf(prev));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        counterPanel.add(count);
        frame.add(counterPanel, BorderLayout.NORTH);
    }

    public void setButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.add(increaseButton);
        buttonPanel.add(decreaseButton);
        buttonPanel.add(resetButton);

        frame.add(buttonPanel);
    }
}
