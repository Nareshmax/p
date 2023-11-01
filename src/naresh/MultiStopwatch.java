package naresh;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MultiStopwatch {
    public static void main(String[] args) {

        JFrame frame = new JFrame("Multi Stopwatch");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(2, 2));

        StopwatchPanel[] stopwatchPanels = new StopwatchPanel[4];

        for (int i = 0; i < 4; i++) {
            stopwatchPanels[i] = new StopwatchPanel("Stopwatch " + (i + 1));
            frame.add(stopwatchPanels[i]);
        }

        frame.setVisible(true);
        frame.setResizable(false);
    }
}

class StopwatchPanel extends JPanel {
    private JLabel label;
    private JButton startButton;
    private JButton stopButton;
    private Stopwatch stopwatch;

    public StopwatchPanel(String stopwatchName) {
        label = new JLabel(stopwatchName);
        startButton = new JButton("Start/Resume");
        stopButton = new JButton("Pause/Stop");

        stopwatch = new Stopwatch();

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopwatch.run();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopwatch.stop();
                label.setText(stopwatchName + ":- " + stopwatch.getTimeElapsed()/1000 + " s");
            }
        });

        setLayout(new GridLayout(3, 1));
        add(label);
        add(startButton);
        add(stopButton);
    }
}

class Stopwatch implements Runnable {
    private long startTime;
    private long Time;
    private boolean running;
    
    @Override
    public void run() {
        if (!running) {
            startTime = System.currentTimeMillis() - Time;
            running = true;

            Thread timerThread = new Thread(() -> {
                while (running) {
                    Time = System.currentTimeMillis() - startTime;
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            timerThread.start();
        }
    }

    public void stop() {
        running = false;
    }

    public long getTimeElapsed() {
        return Time;
    }
}

