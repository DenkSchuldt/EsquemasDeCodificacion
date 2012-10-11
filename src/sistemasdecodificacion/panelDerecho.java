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
public class panelDerecho extends JPanel{
    
    public panelDerecho(){
        setOpaque(false);
        setPreferredSize(new Dimension(67,270));
    }
    
    public void paint(Graphics g){
        super.paint( g );
        g.drawLine(0,121,7,121);
    }    
}