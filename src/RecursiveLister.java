import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class RecursiveLister extends JFrame
{
    private JTextArea textArea;
    public RecursiveLister()
    {
        super("Recursive Lister");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JLabel titleLbl = new JLabel("Recursive File Lister");
        titleLbl.setFont(new Font("Arial", Font.BOLD, 18));

        textArea  = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JButton startBtn = new JButton("Start");
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                showFileChooser();
            }
        });

        JButton quitBtn = new JButton("Quit");
        quitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setLayout(new BorderLayout());
        JPanel topPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPnl.add(titleLbl);
        add(topPnl, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        JPanel btnPnl = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnPnl.add(startBtn);
        btnPnl.add(quitBtn);
        add(btnPnl, BorderLayout.SOUTH);
    }

    private void showFileChooser()
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION)
        {
            File selDir = fileChooser.getSelectedFile();
            listFiles(selDir);
        }
    }

    private void listFiles(File directory)
    {
        textArea.setText("");
        listFilesRecursive(directory);
    }

    private void listFilesRecursive(File directory)
    {
        File[] files = directory.listFiles();
        if (files != null)
        {
            for (File file : files)
            {
                if (file.isDirectory())
                {
                    listFilesRecursive(file);
                }
                else
                {
                    textArea.append(file.getAbsolutePath() + "\n");
                }
            }
        }
    }
}
