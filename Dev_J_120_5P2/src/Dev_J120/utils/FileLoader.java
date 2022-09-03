
package Dev_J120.utils;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;


public class FileLoader {
    
    /*    Метод, читающий строки из файла и выполняющий анализ и первичное 
    преобразование строк;  */
    public static List<String> fileLoader(Path path) throws IOException, NullPointerException, AccessDeniedException{
        path = Objects.requireNonNull(path, "The file can't be null.");
        List<String> list = Files.readAllLines(path);
        list = killerBOM(list);
        list.replaceAll(String::trim);
    return list;    
    }
    /*
    Метод, удаляющий метку порядка байтов, если она есть в начале файла
    и сохранилась после его чтения в первую строку List.
*/  
    private static List<String> killerBOM(List<String> list){
        StringBuilder sb = new StringBuilder();
        for (char character : list.get(0).toCharArray()) { 
            if (character != '\uFEFF') 
                sb.append(character); 
        }  
        list.remove(0);
        list.add(0, sb.toString());
    return list;
    }
    
}
