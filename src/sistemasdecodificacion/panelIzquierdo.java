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
public class panelIzquierdo extends JPanel{
    
    public panelIzquierdo(){
        setOpaque(false);
        setPreferredSize(new Dimension(90,270));
    }
    
    public void paint(Graphics g){
        super.paint(g);
        g.drawLine(88,121,90,121);
        g.drawLine(88,0,88,270);
        g.drawLine(83,7,90,7);
        g.drawLine(83,224,90,224);
        g.drawString("High",52,10);
        g.drawString("value",52,23);
        g.drawString("Low",52,214);
        g.drawString("value",52,227);
    }    
}
