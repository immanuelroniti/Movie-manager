/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package improvemoviemanager;

import java.io.File;
import javax.swing.filechooser.*;
/**
 *
 * @author yongzari
 */
public class OpenFileFilter extends FileFilter {
    String description, fileExt;
    
    public OpenFileFilter (String fileExt){
        this.fileExt = fileExt;
    }
    
    public OpenFileFilter (String fileExt, String description){
        this.fileExt = fileExt;
        this.description = description;
    }
    
    public boolean accept(File f) {
        if (f.isDirectory())
            return true;
        return (f.getName().toLowerCase().endsWith(fileExt));
    }
    
    public String getDescription(){
        return description;
    }
}
