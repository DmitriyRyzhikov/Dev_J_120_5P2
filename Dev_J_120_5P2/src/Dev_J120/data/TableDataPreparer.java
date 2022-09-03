
package Dev_J120.data;

import Dev_J120.data.CellDataMaker;
import Dev_J120.frames.MainFrame;
import java.util.List;

public class TableDataPreparer {
    
    private Object[] columnHeaders;
    private Object[][] rowData;
    private List<String> primaryList;
    private boolean checkBoxCondition = false;
    private String separator = ",";
    private int maxRowLenght = 0;
    
    public TableDataPreparer(boolean checkBoxCondition, String separator){
        this.checkBoxCondition = checkBoxCondition;
        this.separator = separator;
        primaryList = MainFrame.getPrimaryList();
        primaryList.removeIf(x -> (x.equals("")));
    }
    
    public Object[] columnHeadersMaker(){
        if(checkBoxCondition) {            
           columnHeaders = CellDataMaker.cellsMaker(primaryList.get(0), separator);
        }
        else {
            int l = CellDataMaker.cellsMaker(primaryList.get(0), separator).length;
            columnHeaders = new Object[l]; 
             for(int i = 0; i<l; i++)
                 columnHeaders[i] = i+1;
        }
        return columnHeaders;
    }    
        
    public Object[][] rowDataMaker(){
        if(checkBoxCondition)
           primaryList.remove(0);
        Object[] cell;        
        Object[] temp1 = primaryList.toArray();        
        String[]temp = new String[temp1.length];        
        for(int i = 0; i < temp1.length; i++){
            temp[i] = (String)temp1[i];}
        rowData = new Object[temp.length][columnHeaders.length];
        for(int i = 0; i < temp.length; i++) {
            cell = CellDataMaker.cellsMaker(primaryList.get(i), separator);            
            System.arraycopy(cell, 0, rowData[i], 0, columnHeaders.length);              
           }        
          return rowData;
    }    
    public int dimensionAnalyzer() {        
        primaryList.forEach(x -> {            
            int tempRowLenght = CellDataMaker.cellsMaker(x, separator).length; 

            maxRowLenght = (maxRowLenght >= tempRowLenght) ? maxRowLenght : tempRowLenght; 
          });         
        return maxRowLenght;
    }
    
}

/*
public Object[][] rowDataMaker(){
        
        if(checkBoxCondition)
           primaryList.remove(0);
        String[] cell;        
        Object[] temp1 = primaryList.toArray();        
        String[]temp = new String[temp1.length];        
        for(int i = 0; i < temp1.length; i++){
            temp[i] = (String)temp1[i];}
        rowData = new Object[temp.length][columnHeaders.length];
        for(int i = 0; i < temp.length; i++) {
            cell = temp[i].split(separator);            
            for(int y = 0; y < columnHeaders.length; y++)  {          
                rowData[i][y] = cell[y].trim();
            } 
        }        
        return rowData;
    }
*/