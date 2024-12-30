import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Arayuz {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Yapılacaklar Listesi");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 1));
        inputPanel.setBackground(new Color(128, 0, 128));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel taskInputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        taskInputPanel.setBackground(new Color(128, 0, 128));

        JLabel taskLabel = new JLabel("Görev:");
        taskLabel.setForeground(Color.WHITE);

        JTextField taskField = new JTextField(20);
        taskField.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JPanel dateInputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        dateInputPanel.setBackground(new Color(128, 0, 128));

        JLabel dateLabel = new JLabel("Hatırlatma Tarihi (yyyy-MM-dd):");
        dateLabel.setForeground(Color.WHITE);

        JTextField dateField = new JTextField(10);
        dateField.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JLabel timeLabel = new JLabel("Hatırlatma Saati (HH:mm):");
        timeLabel.setForeground(Color.WHITE);

        JTextField timeField = new JTextField(5);
        timeField.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JButton addButton = new JButton("Ekle");
        addButton.setBackground(new Color(60, 179, 113));
        addButton.setForeground(Color.WHITE);

        taskInputPanel.add(taskLabel);
        taskInputPanel.add(taskField);

        dateInputPanel.add(dateLabel);
        dateInputPanel.add(dateField);
        dateInputPanel.add(timeLabel);
        dateInputPanel.add(timeField);

        inputPanel.add(taskInputPanel);
        inputPanel.add(dateInputPanel);

        JPanel taskPanel = new JPanel();
        taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.Y_AXIS));
        taskPanel.setBackground(new Color(245, 245, 245));
        JScrollPane scrollPane = new JScrollPane(taskPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(128, 0, 128));
        buttonPanel.add(addButton);

        addButton.addActionListener(e -> {
            String taskText = taskField.getText();
            String dateText = dateField.getText();
            String timeText = timeField.getText();

            if (!taskText.isEmpty() && !dateText.isEmpty() && !timeText.isEmpty()) {
                try {
                    String dateTimeString = dateText + " " + timeText;
                    Date remindDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(dateTimeString);
                    YapilacaklarListesi.addTask(taskText, remindDate, taskPanel);
                    taskField.setText("");
                    dateField.setText("");
                    timeField.setText("");
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(frame, "Tarih veya saat formatı hatalı! Lütfen doğru formatta girin.", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
