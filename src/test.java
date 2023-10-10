
import java.awt.Desktop;
import java.io.File;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author macbookair
 */
public class test {
    
    
    public static void main(String[] args) {
        
        try {    
        File file = new File("src/uri.mp4");
        
        Desktop d = Desktop.getDesktop();
        
        d.open(file);
         } catch (Exception e) 
         {
        
         }
        
    }
    
}
