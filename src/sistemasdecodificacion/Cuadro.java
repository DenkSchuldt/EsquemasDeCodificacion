/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasdecodificacion;
import java.awt.*;
import javax.swing.*;

/*
 *
 * @author Denk
 * 
 */
public class Cuadro extends JButton{
    
    boolean pintar = false;
    boolean pintado=false;
    int idc;
    
    public Cuadro(int id){
        idc=id;
        setPreferredSize(new Dimension(15,15));
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
