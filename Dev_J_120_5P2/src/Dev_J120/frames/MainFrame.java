
package Dev_J120.frames;

import Dev_J120.utils.FileLoader;
import Dev_J120.data.TableDataPreparer;
import Dev_J120.utils.CheckBoxResizer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainFrame extends JFrame{
    
    private static List<String> primaryList = new ArrayList<>();
    private static String separator = ",";
    private static boolean checkBoxCondition = false;
    private static String headTableFrame = "";
    private Object[] columnHeaders; 
    private Object[][] rowData; 
    private final Font font = new Font("Dialog", Font.BOLD, 20);
    
    public MainFrame() {
        super();
        setTitle("CSV File Viewer");
        getContentPane().setLayout(new GridLayout(4, 0));
        getContentPane().setBackground(Color.WHITE);       
        setBounds(600, 300, 550, 400);
        initComponents();
        closeApp();       
    }
    public static String getHeadTableFrame() {
        return headTableFrame;
    }
    public static List<String> getPrimaryList() {
        return primaryList;
    }   
/*
    public static boolean isCheckBoxCondition() {
        return checkBoxCondition;
    }
*/
    public static String getSeparator() {
        return separator;
    }
    
    private void initComponents(){
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        panel1.setBackground(Color.WHITE); 
        panel2.setBackground(Color.WHITE); 
        panel3.setBackground(Color.WHITE);
        panel4.setBackground(Color.WHITE);

        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
        panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));
        JLabel label = new JLabel("Welcome to the CSV File Viewer");
        label.setAlignmentX(CENTER_ALIGNMENT);
        label.setFont(font);
        
        JLabel labelCombo = new JLabel("Select the separator:");
        labelCombo.setAlignmentX(RIGHT_ALIGNMENT);
        labelCombo.setFont(font);
        
        JComboBox<String> separatorCombo = new JComboBox();
        separatorCombo.addItem("comma");
        separatorCombo.addItem("colon");
        separatorCombo.addItem("semicolon");
        separatorCombo.setEditable(false);
        separatorCombo.setAlignmentX(RIGHT_ALIGNMENT); 
        separatorCombo.setBackground(Color.WHITE); 
        separatorCombo.addItemListener((e) -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                switch ((String) e.getItem()){   
                      case "comma":
                           separator = ",";
                           break;
                      case "colon":
                           separator = ":";
                           break;
                      case "semicolon":
                           separator = ";";
                           break;      
                }
            }    
        });   
        separatorCombo.setMaximumSize(new Dimension(193, 30));
        separatorCombo.setFont(font);
        
        JCheckBox checkBox = new JCheckBox("The CSV file contains column headers:");
        checkBox.setAlignmentX(CENTER_ALIGNMENT); 
        checkBox.setBackground(Color.WHITE);
        checkBox.setHorizontalTextPosition(JCheckBox.LEFT); 
        checkBox.setFont(font);
        checkBox.addItemListener((e) -> {
            checkBoxCondition = (e.getStateChange() == ItemEvent.SELECTED);
        }); 
        CheckBoxResizer.scaleCheckBoxIcon(checkBox); 
        JButton uploadButton = new JButton("Upload File");
        uploadButton.setAlignmentX(RIGHT_ALIGNMENT);
        uploadButton.setFont(font);
        uploadButton.addActionListener((e) -> {uploadFile();});
        
        panel1.add(Box.createVerticalGlue());
        panel1.add(label);
        panel1.add(Box.createVerticalGlue());
        panel2.add(Box.createVerticalGlue());
        panel2.add(Box.createRigidArea(new Dimension(68,0)));
        panel2.add(labelCombo);
        panel2.add(Box.createRigidArea(new Dimension(5,0)));
        panel2.add(separatorCombo);
        panel3.add(checkBox);
        panel4.add(uploadButton);
        panel4.add(Box.createVerticalGlue());
        panel4.add(Box.createRigidArea(new Dimension(40,0)));
        add(panel1);
        add(panel2);
        add(panel3);
        add(panel4);        
    }    
    private void closeApp(){
        addWindowListener(new WindowAdapter() { 
            @Override
            public void windowClosing(WindowEvent e) {
                int n = JOptionPane
                        .showConfirmDialog(e.getWindow(), "Closing the app? Are you sure?",
                                "Confirmation of closing", JOptionPane.YES_NO_OPTION,
                                JOptionPane.WARNING_MESSAGE);
                if (n == 0) {
                    e.getWindow().setVisible(false);
                    System.exit(0); }          
                else
                    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            }
        });
    }   
    private void uploadFile() {
        JFileChooser filechooser = new JFileChooser(System.getProperty("user.dir"));   
        filechooser.setFileFilter(new FileNameExtensionFilter("CSV files", "csv"));        
        int ret = filechooser.showDialog(this, "Upload a file");                        
        if (ret == JFileChooser.APPROVE_OPTION) {        
            File file = filechooser.getSelectedFile();
            try {
                primaryList = FileLoader.fileLoader(file.toPath());
            } catch (IOException | NullPointerException | SecurityException ex) {
                JOptionPane.showMessageDialog(this, 
                                "The file is missing or access to it is prohibited.", "Error. file access error.",
			JOptionPane.ERROR_MESSAGE); 
            }
            headTableFrame = "File " + file.getName() + " uploaded";
            TableDataPreparer tdp = new TableDataPreparer(checkBoxCondition, separator);
            columnHeaders = tdp.columnHeadersMaker();
            rowData = tdp.rowDataMaker();
            new TableFrame(rowData, columnHeaders).setVisible(true);  
            }
    }    
}
