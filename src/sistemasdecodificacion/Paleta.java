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
public class Paleta extends JPanel{
    
    public Paleta(){
        setPreferredSize(new Dimension(631,242));
        setLayout(new GridBagLayout());
    }
    
    public void paint(Graphics g){
        super.paint(g);
        g.drawLine(0,121,631,121);
        Graphics2D g2d=(Graphics2D)g;
        g2d.setColor(Color.GRAY);
        float dash[] = {6};
        g2d.setStroke(new BasicStroke(1,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,5.0f, dash, 0.0f));
        g2d.drawLine(31,0,31,242);        
    }
    
}
