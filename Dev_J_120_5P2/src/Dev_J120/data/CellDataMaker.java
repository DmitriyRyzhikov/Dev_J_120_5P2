
package Dev_J120.data;

import java.util.ArrayList;
import java.util.List;

public class CellDataMaker {
    
    private static int quotationMarks = 0;
    private static String cell = "";
    private static StringBuilder sb = new StringBuilder("");
    
    public static Object[] cellsMaker(String line, String separator){
        List<String> tempResultList = new ArrayList<>();
        line = line.trim();
        char[] charArray = line.toCharArray(); 
        for(int i = 0; i<charArray.length; i++)  {
            if(charArray[i] == '\"' && quotationMarks == 0) {
                quotationMarks = 1;
                i++; }
            if(charArray[i] == '\"' && quotationMarks == 1 && i != charArray.length - 1) 
              { if(charArray[i+1] == '\"')
                   i++;
                else {
                   quotationMarks = 0;
                    i++; }
              }
            if(charArray[i] == '\"' && quotationMarks == 1 && i == charArray.length - 1) 
                 { quotationMarks = 0;
                   charArray[i] = Character.MIN_VALUE; }                        
            if(charArray[i] != separator.charAt(0)) 
              {sb = sb.append(charArray[i]);
                if(i == charArray.length - 1) {
                   cell = sb.toString();
                   tempResultList.add(cell.trim());
                   sb.delete(0, cell.length()); }
               }
            else if(charArray[i] == separator.charAt(0) && quotationMarks == 0) 
               {cell = sb.toString();
                tempResultList.add(cell.trim());
                if(i == charArray.length - 1) 
                   tempResultList.add("");
                sb.delete(0, cell.length());
                }
            else if(charArray[i] == separator.charAt(0) && quotationMarks == 1) {
                sb = sb.append(charArray[i]); }                
            }
        Object[] cells = tempResultList.toArray();
        return cells;
    }
}
