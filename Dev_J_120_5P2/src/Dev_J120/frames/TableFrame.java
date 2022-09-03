
package Dev_J120.frames;

import Dev_J120.frames.MainFrame;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableCellRenderer;

public class TableFrame extends JFrame {
    
    private final Object[] columnHeaders;
    private final Object[][] rowData;
    int smv = 150;
    int wdt;
                
    public TableFrame(Object[][] rowData, Object[] columnHeaders) {
        super(MainFrame.getHeadTableFrame());
        this.rowData = rowData;
        this.columnHeaders = columnHeaders;
	wdt = this.columnHeaders.length * 150; 
        initLayoutTable();
        
    }
    
    private void initLayoutTable() {
        setBounds(1150, 300, wdt > 900 ? 900 : wdt, 400);
        JTable table = new JTable(rowData, columnHeaders);
        JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        DefaultTableCellRenderer centrRenderer = new DefaultTableCellRenderer();
        centrRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for(int i = 0; i < columnHeaders.length; i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centrRenderer);
            table.getColumnModel().getColumn(i).setMinWidth(smv);    }
        table.setFont(new Font("Times new roman", 0, 16));
        table.setIntercellSpacing(new Dimension(5, 5));
        table.setRowHeight(27);
	add(scrollPane);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}