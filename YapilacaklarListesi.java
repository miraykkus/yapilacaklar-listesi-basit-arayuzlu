import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class YapilacaklarListesi {

    // Görev ekleme fonksiyonu
    public static void addTask(String taskText, Date remindDate, JPanel taskPanel) {
        String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());

        JPanel singleTaskPanel = new JPanel();
        singleTaskPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        singleTaskPanel.setMaximumSize(new Dimension(750, 40));
        singleTaskPanel.setBackground(new Color(0, 0, 0, 0)); // Saydam arka plan

        JLabel taskLabel = new JLabel(taskText + " (Eklendi: " + currentDate + ")");
        taskLabel.setFont(new Font("Arial", Font.BOLD, 14));
        taskLabel.setForeground(Color.BLACK);

        JLabel remindDateLabel = new JLabel("Hatırlatma: " + new SimpleDateFormat("yyyy-MM-dd HH:mm").format(remindDate));
        remindDateLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        remindDateLabel.setForeground(Color.GRAY);

        JCheckBox taskCheckBox = new JCheckBox();
        taskCheckBox.setBackground(new Color(0, 0, 0, 0));

        // Düzenle butonu
        JButton editButton = new JButton("Düzenle");
        editButton.setBackground(new Color(130, 0, 130, 200));
        editButton.setForeground(Color.WHITE);
        editButton.setToolTipText("Görevi düzenle");

        // Düzenleme işlemi
        editButton.addActionListener(e -> {
            String newTaskText = JOptionPane.showInputDialog("Yeni görev giriniz:", taskText);
            if (newTaskText != null && !newTaskText.trim().isEmpty()) {
                taskLabel.setText(newTaskText + " (Eklendi: " + currentDate + ")");
            }
        });

        // Silme butonu
        JButton deleteButton = new JButton("Sil");
        deleteButton.setBackground(new Color(200, 0, 75, 200));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setToolTipText("Görevi sil");

        deleteButton.addActionListener(e -> {
            taskPanel.remove(singleTaskPanel);
            taskPanel.revalidate();
            taskPanel.repaint();
        });

        scheduleReminder(taskText, remindDate);

        singleTaskPanel.add(taskCheckBox);
        singleTaskPanel.add(taskLabel);
        singleTaskPanel.add(remindDateLabel);
        singleTaskPanel.add(editButton);
        singleTaskPanel.add(deleteButton);

        taskPanel.add(singleTaskPanel);
        taskPanel.revalidate();
        taskPanel.repaint();
    }

    private static void scheduleReminder(String taskText, Date remindDate) {
        long delay = remindDate.getTime() - System.currentTimeMillis();

        if (delay > 0) {
            CompletableFuture.runAsync(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(delay);
                    SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(null, "Görev Hatırlatma: " + taskText));
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            });
        }
    }
}
