/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasdecodificacion;

import java.awt.*;
import javax.swing.*;

public class panelSuperior extends JPanel{
    
    public panelSuperior(){
        setOpaque(false);
        setPreferredSize(new Dimension(770,90));
    }
    
    public void paint(Graphics g){
        super.paint(g);
        g.drawLine(88,60,88,90);
        Graphics2D g2d=(Graphics2D)g;
        g2d.setColor(Color.GRAY);
        float dash[] = {6};
        g2d.setStroke(new BasicStroke(1,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,5.0f, dash, 0.0f));
        g2d.drawLine(121,65,121,90);
        g2d.drawLine(185,65,185,90);
        g2d.drawLine(248,65,248,90);
        g2d.drawLine(312,65,312,90);
        g2d.drawLine(377,65,377,90);
        g2d.drawLine(440,65,440,90);
        g2d.drawLine(504,65,504,90);
        g2d.drawLine(568,65,568,90);
        g2d.drawLine(631,65,631,90);        
        g.setFont(new Font("Arial",Font.BOLD,25));                         
        g.setColor(Color.black);
        g.drawString("Graficador",330,40);
    }    
}