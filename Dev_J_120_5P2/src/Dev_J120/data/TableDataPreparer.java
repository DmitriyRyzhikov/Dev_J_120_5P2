
package Dev_J120.data;

import Dev_J120.frames.MainFrame;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;

public class TableDataPreparer {
    
    private Object[] columnHeaders;
    private Object[][] rowData;
    private final List<String> primaryList;
    private boolean checkBoxCondition = false;
    private String separator = ",";
    private int maxRowLength = 0;
    
    public TableDataPreparer(boolean checkBoxCondition, String separator){
        
        this.checkBoxCondition = checkBoxCondition;
        this.separator = separator;
        primaryList = MainFrame.getPrimaryList();
        primaryList.removeIf(x -> (x.equals("")));
    }
    
    public Object[] columnHeadersMaker(){
        if(checkBoxCondition) { 
            //заголовки есть
           columnHeaders = CellDataMaker.cellsMaker(primaryList.get(0), separator, maxRowLength);
        }
        else {
            //заголовков нет
            columnHeaders = new Object[dimensionAnalyzer()]; 
             for(int i = 0; i<dimensionAnalyzer(); i++)
                 columnHeaders[i] = i+1;
        }
        return columnHeaders;
    }    
        
    public Object[][] rowDataMaker(){
        if(checkBoxCondition)
           primaryList.remove(0);
        Object[] cell;        
        Object[] temp = primaryList.toArray();  
        rowData = new Object[temp.length][columnHeaders.length];
        try {
        for(int i = 0; i < temp.length; i++) {
            cell = CellDataMaker.cellsMaker(primaryList.get(i), separator, maxRowLength);  
            System.arraycopy(cell, 0, rowData[i], 0, columnHeaders.length); 
           } 
        } catch (ArrayIndexOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(null, 
                                "The file cannot be converted.It is possible that a non-csv file is selected, "
                                + "or the selected file was created in violation of the requirements "
                                + "of the csv standard", "Error. File conversion error.",
			JOptionPane.ERROR_MESSAGE); }
          return rowData;
    } 
    /*метод определяет максимальное количство столбцов таблицы.  
      метод полезен в случае, если csv файл не содержит заголовков,
      а длина строк (количество ячеек в строке таблицы) различны. 
    */
   public int dimensionAnalyzer() {        
        for(int i = 0; i<primaryList.size(); i++)  {     
            int tempRowLength = CellDataMaker.mainPart(primaryList.get(i), separator).size(); 
        maxRowLength = (maxRowLength >= tempRowLength) ? maxRowLength : tempRowLength; 
        }  
        return maxRowLength;
    }    
}
