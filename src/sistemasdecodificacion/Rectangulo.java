/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasdecodificacion;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Denk
 */
public class Rectangulo extends JButton{
    
    boolean pintar = false;
    boolean pintado=false;
    int idr;
    
    public Rectangulo(int id){
        idr=id;
        setPreferredSize(new Dimension(15,200));
        setBackground(Color.GRAY.brighter());
    }
    
    public void paint(Graphics g){
        super.paint( g );
        if(pintar){
           setBackground(Color.BLUE.darker());
           pintado=true;
        }else{
           setBackground(Color.GRAY.brighter());
           pintado=false;
        }
    }
}
