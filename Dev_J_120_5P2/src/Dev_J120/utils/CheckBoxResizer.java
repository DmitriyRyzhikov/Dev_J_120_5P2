
package Dev_J120.utils;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.UIManager;

public class CheckBoxResizer {
    
    public static void scaleCheckBoxIcon(JCheckBox checkbox){
    boolean previousState = checkbox.isSelected();
    checkbox.setSelected(false);
    FontMetrics boxFontMetrics =  checkbox.getFontMetrics(checkbox.getFont());
    Icon boxIcon = UIManager.getIcon("CheckBox.icon");
    BufferedImage boxImage = new BufferedImage(
        boxIcon.getIconWidth(), boxIcon.getIconHeight(), BufferedImage.TYPE_INT_ARGB
    );
    Graphics graphics = boxImage.createGraphics();
    try{
        boxIcon.paintIcon(checkbox, graphics, 0, 0);
    }finally{
        graphics.dispose();
    }
    ImageIcon newBoxImage = new ImageIcon(boxImage);
    Image finalBoxImage = newBoxImage.getImage().getScaledInstance(
        boxFontMetrics.getHeight(), boxFontMetrics.getHeight(), Image.SCALE_SMOOTH
    );
    checkbox.setIcon(new ImageIcon(finalBoxImage));

    checkbox.setSelected(true);
    Icon checkedBoxIcon = UIManager.getIcon("CheckBox.icon");
    BufferedImage checkedBoxImage = new BufferedImage(
        checkedBoxIcon.getIconWidth(),  checkedBoxIcon.getIconHeight(), BufferedImage.TYPE_INT_ARGB
    );
    Graphics checkedGraphics = checkedBoxImage.createGraphics();
    try{
        checkedBoxIcon.paintIcon(checkbox, checkedGraphics, 0, 0);
    }finally{
        checkedGraphics.dispose();
    }
    ImageIcon newCheckedBoxImage = new ImageIcon(checkedBoxImage);
    Image finalCheckedBoxImage = newCheckedBoxImage.getImage().getScaledInstance(
        boxFontMetrics.getHeight(), boxFontMetrics.getHeight(), Image.SCALE_SMOOTH
    );
    checkbox.setSelectedIcon(new ImageIcon(finalCheckedBoxImage));
    }    
}
