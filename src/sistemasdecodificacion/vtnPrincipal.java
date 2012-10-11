/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemasdecodificacion;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.ColorUIResource;
import imagenes.ImageLoader;

/**
 *
 * @author Denk
 */
public class vtnPrincipal extends JFrame{
    
    Pizarra pizarra;
    Paleta paleta;
    JRadioButton jrb1, jrb2, jrb3, jrb4;
    ButtonGroup bg;
    JTextField jtf;
    JComboBox jcb;
    JMenuBar jmb;
    JMenu archivo, ayuda;
    JMenuItem nuevaSimulacion, dibujarGrafico, random, instrucciones, acercaDe, salir;
    JPanel titulo, contenedor, sistemasDeCodificacion, bits, level;
    JTextField sistema1 = new JTextField(14), sistema2 = new JTextField(14), bits1 = new JTextField(14), bits2 = new JTextField(14);
    JButton graficar, randomizar, revelar, comprobar;
    Cuadro c1, c2, c3, c4 ,c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19, c20, c21, c22, c23, c24, c25, c26, c27, c28;    
    Cuadro c29, c30, c31, c32, c33, c34, c35, c36, c37, c38, c39, c40, c41, c42, c43, c44, c45, c46, c47, c48, c49, c50, c51, c52, c53, c54, c55, c56;
    Cuadro c57, c58, c59, c60, c61, c62, c63, c64, c65, c66, c67, c68, c69, c70, c71, c72, c73, c74, c75, c76;
    Rectangulo r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19;
    
    ArrayList<Punto> puntos = new ArrayList<Punto>();
    ArrayList<Bit> bites = new ArrayList<Bit>();
    ArrayList cuadros = new ArrayList<Cuadro>();
    ArrayList barras1 = new ArrayList<Rectangulo>();
    ArrayList barras2 = new ArrayList<Rectangulo>();
    ArrayList barras2Seleccionadas = new ArrayList<Rectangulo>();
    ArrayList flujoBits = new ArrayList();
    ArrayList flujoBits2 = new ArrayList();
    ArrayList c = new ArrayList<Cuadro>();
    ArrayList ba1 = new ArrayList<Rectangulo>();
    ArrayList ba2 = new ArrayList<Rectangulo>();
    ArrayList baq2 = new ArrayList<Rectangulo>();
    ArrayList fb = new ArrayList();
    ArrayList fb2 = new ArrayList();
    
    String textoBits, textoSeleccion, textoRandom;
    Random rnd = new Random();
    int xs[] = {100,150,200,250,300,350,400,450,500};
    int inicialX, inicialY, contador, x = 50, posicion, nivel;
    int permiteFuncionalidad = 0, numeroDeBits, controladorDeNumeros = 3;   
    boolean alto = false, revelarPintado = false, randomMode = false, controlDeRepaintInicial = false;                       
    
    public vtnPrincipal(){
        setSize(770,500);
        setLocationRelativeTo(null);
        setResizable(false); 
        agregarImagenFondo(); //Esta es la imagen incial, esa imagen fea que hay que reemplazar.
        agregarBarraDeHerramientas();        
        setTitle("Simulador de Esquemas de Codificación v.1");
        this.setIconImage(new ImageIcon(getClass().getResource("/sistemasdecodificacion/imagenes/icono2.png")).getImage());
        setVisible(true);
        addWindowListener(new ventanaListener());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);        
    }
    
    void agregarBarraDeHerramientas(){
        jmb = new JMenuBar();
        archivo = new JMenu("    Archivo");
        ayuda = new JMenu("    Ayuda");
        nuevaSimulacion = new JMenuItem("Nueva Simulación");     
        agregarFuncionalidadItems(nuevaSimulacion,titulo,1);
            
        dibujarGrafico = new JMenuItem("Dibujar gráfico");
        flujoBits.clear();
        flujoBits2.clear();
        agregarFuncionalidadItems(dibujarGrafico,titulo,2);
        random = new JMenuItem("Randomizar");
            agregarFuncionalidadItems(random,titulo,3);
        instrucciones = new JMenuItem("Soporte");     
            agregarFuncionalidadItems(instrucciones,titulo,4);
        acercaDe = new JMenuItem("Acerca de...");     
            agregarFuncionalidadItems(acercaDe,titulo,5);
        salir = new JMenuItem("Salir");
        salir.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                int resp = JOptionPane.showConfirmDialog(null,"¿Está seguro de querer salir?","¡Atención!",0,0,new ImageIcon(ImageLoader.class.getResource("x.gif")));
                if(resp==0)
                    System.exit(0);
            }                        
        });
        archivo.add(nuevaSimulacion);
        archivo.add(dibujarGrafico);
        archivo.add(random);
        archivo.add(salir);
        ayuda.add(instrucciones);
        ayuda.add(acercaDe);
        jmb.add(archivo);
        jmb.add(ayuda);
        add(jmb,BorderLayout.NORTH);
    }
    
    void agregarImagenFondo(){
        titulo = new JPanel();
        titulo.setPreferredSize(new Dimension(700,470));
        titulo.setBackground(new Color(26,125,146));        
        add(titulo,BorderLayout.CENTER);
    }
    
    void agregarFuncionalidadItems(JMenuItem jmi, final JPanel pnl, final int clave){
        jmi.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                pnl.removeAll();
                //OPCION DE NUEVA SIMULACION                
                if(clave == 1){
                    controlDeRepaintInicial  = true;
                    permiteFuncionalidad = 0;
                    controladorDeNumeros = 0;
                    pnl.setBackground(null);
                    pnl.setLayout(new BorderLayout());
                    contenedor = new JPanel();
                    contenedor.setPreferredSize(new Dimension(200,470));                    
                    JLabel space1 = new JLabel(" ");
                    space1.setPreferredSize(new Dimension(200,20));
                    contenedor.add(space1);
                    //----------------------------------------------------------
                    sistemasDeCodificacion = new JPanel(new GridLayout(4,1));
                    sistemasDeCodificacion.setPreferredSize(new Dimension(180,150));                    
                    jrb1 = new JRadioButton("  -  NRZ");                    
                    jrb2 = new JRadioButton("  -  NRZ-I");
                    jrb3 = new JRadioButton("  -  Manchester");
                    jrb4 = new JRadioButton("  -  Manchester Dif.");
                    jrb1.setOpaque(false);
                    jrb2.setOpaque(false);
                    jrb3.setOpaque(false);
                    jrb4.setOpaque(false);
                    bg = new ButtonGroup();
                    bg.add(jrb1);
                    bg.add(jrb2);
                    bg.add(jrb3);
                    bg.add(jrb4);
                    sistemasDeCodificacion.add(jrb1);
                    sistemasDeCodificacion.add(jrb2);
                    sistemasDeCodificacion.add(jrb3);
                    sistemasDeCodificacion.add(jrb4);                    
                    TitledBorder title1 = BorderFactory.createTitledBorder("[Esquemas de Codificación]");    
                    title1.setTitleJustification(TitledBorder.CENTER);
                    sistemasDeCodificacion.setBorder(title1);
                    sistemasDeCodificacion.setOpaque(false);                                        
                    contenedor.add(sistemasDeCodificacion);
                    //----------------------------------------------------------
                    JLabel space2 = new JLabel(" ");
                    space2.setPreferredSize(new Dimension(200,10));
                    space2.setOpaque(false);                    
                    contenedor.add(space2);
                    //----------------------------------------------------------
                    bits = new JPanel();
                    bits.setPreferredSize(new Dimension(180,80));
                    TitledBorder title2 = BorderFactory.createTitledBorder("[Flujo de Bits]");    
                    title2.setTitleJustification(TitledBorder.CENTER);
                    bits.setBorder(title2);
                    bits.setOpaque(false);
                    jtf = new JTextField(14);    
                    jtf.setCaretColor(new Color(26,125,146));
                    bits.add(jtf);
                    JLabel texto1 = new JLabel("Número máximo de bits: 8");
                    texto1.setForeground(Color.GRAY);
                    bits.add(texto1);
                    contenedor.add(bits);                    
                    //----------------------------------------------------------
                    JLabel space3 = new JLabel(" ");
                    space3.setPreferredSize(new Dimension(200,10));
                    space3.setOpaque(false);
                    contenedor.add(space3);
                    //----------------------------------------------------------
                    level = new JPanel();
                    level.setPreferredSize(new Dimension(180,60));
                    TitledBorder title3 = BorderFactory.createTitledBorder("[Nivel inicial]");    
                    title3.setTitleJustification(TitledBorder.CENTER);
                    level.setBorder(title3);
                    level.setOpaque(false);
                    String levels[] = {"Alto", "Bajo"};
                    jcb = new JComboBox(levels);
                    jcb.setPreferredSize(new Dimension(150,20));                    
                    level.add(jcb);                    
                    contenedor.add(level);                    
                    //----------------------------------------------------------
                    JLabel space4 = new JLabel(" ");
                    space4.setPreferredSize(new Dimension(200,10));
                    space4.setOpaque(false);
                    contenedor.add(space4);
                    //----------------------------------------------------------
                    graficar = new JButton("¡Graficar!");
                    graficar.setPreferredSize(new Dimension(180,40));                        
                    graficar.setIcon(new ImageIcon(ImageLoader.class.getResource("graficar.jpg")));                    
                    contenedor.add(graficar);
                    pnl.add(contenedor,BorderLayout.WEST);
                    //----------------------------------------------------------
                    pizarra = new Pizarra(); 
                    pizarra.setPreferredSize(new Dimension(500,470));
                    agregarFuncionalidadBoton(graficar,pizarra);
                    pnl.add(pizarra,BorderLayout.CENTER);
                }
                //OPCION GRAFICAR
                if(clave == 2){                    
                    controlDeRepaintInicial  = true;
                    permiteFuncionalidad = 0;                                
                    flujoBits.clear();
                    flujoBits2.clear();
                    pnl.setBackground(null);
                    pnl.setLayout(new BorderLayout());                    
                    paleta = new Paleta();                                        
                    paleta.setLayout(new GridBagLayout());
                    barras1.clear();
                    barras2.clear();
                    cuadros.clear();
                    agregarComponentes(paleta);
                    pnl.add(paleta,BorderLayout.CENTER);
                    panelSuperior norte = new panelSuperior();
                    panelInferior sur = new panelInferior(barras1,barras2,cuadros);
                    sur.setBackground(Color.red);                    
                    panelDerecho este = new panelDerecho();                    
                    panelIzquierdo oeste = new panelIzquierdo();                    
                    pnl.add(norte,BorderLayout.NORTH);
                    pnl.add(sur,BorderLayout.SOUTH);
                    pnl.add(este,BorderLayout.EAST);
                    pnl.add(oeste,BorderLayout.WEST);
                }
                if(clave == 3){
                    controlDeRepaintInicial  = true;
                    permiteFuncionalidad = 0;
                    controladorDeNumeros = 3;
                    pnl.setBackground(null);
                    pnl.setLayout(new BorderLayout());                    
                    contenedor = new JPanel();
                    contenedor.setPreferredSize(new Dimension(200,470));
                    //----------------------------------------------------------
                    JLabel space = new JLabel(" ");
                    space.setPreferredSize(new Dimension(200,345));
                    space.setIcon(new ImageIcon(ImageLoader.class.getResource("random.gif")));
                    contenedor.add(space);
                    //----------------------------------------------------------
                    pizarra = new Pizarra(); 
                    pizarra.setPreferredSize(new Dimension(500,470));                                        
                    //----------------------------------------------------------
                    randomizar = new JButton("¡Random!");
                    randomizar.setPreferredSize(new Dimension(180,40));
                    randomizar.setIcon(new ImageIcon(ImageLoader.class.getResource("btnrandom.jpg")));
                    agregarFuncionalidadRandom(randomizar,pizarra);
                    contenedor.add(randomizar);
                    //----------------------------------------------------------
                    revelar = new JButton("¡Revelar!");
                    revelar.setPreferredSize(new Dimension(180,40));
                    revelar.setIcon(new ImageIcon(ImageLoader.class.getResource("btnrevelar.jpg")));
                    agregarFuncionalidadRevelar(revelar,pizarra);
                    contenedor.add(revelar);
                    pnl.add(contenedor,BorderLayout.WEST);
                    //----------------------------------------------------------                    
                    pnl.add(pizarra,BorderLayout.CENTER);
                }
                if(clave == 4){
                    JLabel lbl = new JLabel();
                    lbl.setPreferredSize(new Dimension(770,450));
                    lbl.setIcon(new ImageIcon(ImageLoader.class.getResource("ayuda.jpg")));
                    pnl.add(lbl);
                }
                if(clave == 5){
                    JLabel lbl = new JLabel();
                    lbl.setPreferredSize(new Dimension(770,450));
                    lbl.setIcon(new ImageIcon(ImageLoader.class.getResource("bout.jpg")));
                    pnl.add(lbl);
                }
                pnl.updateUI();
            }
        });
    }                     
    public void agregarComponentes(Paleta pizarra){
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        c1 = new Cuadro(1);
        agregarPintado(c1);
            pizarra.add(c1,gbc);
            cuadros.add(c1);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        c2 = new Cuadro(2);
        agregarPintado(c2);
            pizarra.add(c2,gbc);
            cuadros.add(c2);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        c3 = new Cuadro(3);
        agregarPintado(c3);
            pizarra.add(c3,gbc);
            cuadros.add(c3);
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        c4 = new Cuadro(4);
        agregarPintado(c4);
            pizarra.add(c4,gbc);
            cuadros.add(c4);
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        c5 = new Cuadro(5);
        agregarPintado(c5);
            pizarra.add(c5,gbc);
            cuadros.add(c5);
        gbc.gridx = 5;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        c6 = new Cuadro(6);
        agregarPintado(c6);
            pizarra.add(c6,gbc);
            cuadros.add(c6);
        gbc.gridx = 6;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        c7 = new Cuadro(7);
        agregarPintado(c7);
            pizarra.add(c7,gbc);
            cuadros.add(c7);
        gbc.gridx = 7;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        c8 = new Cuadro(8);
        agregarPintado(c8);
            pizarra.add(c8,gbc);
            cuadros.add(c8);
        gbc.gridx = 8;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        c9 = new Cuadro(9);
        agregarPintado(c9);
            pizarra.add(c9,gbc);
            cuadros.add(c9);
        gbc.gridx = 9;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        c10 = new Cuadro(10);
        agregarPintado(c10);
            pizarra.add(c10,gbc);
            cuadros.add(c10);
        gbc.gridx = 10;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        c11 = new Cuadro(11);
        agregarPintado(c11);
            pizarra.add(c11,gbc);
            cuadros.add(c11);
        gbc.gridx = 11;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        c12 = new Cuadro(12);
        agregarPintado(c12);
            pizarra.add(c12,gbc);
            cuadros.add(c12);
        gbc.gridx = 12;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        c13 = new Cuadro(13);
        agregarPintado(c13);
            pizarra.add(c13,gbc);
            cuadros.add(c13);
        gbc.gridx = 13;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        c14 = new Cuadro(14);
        agregarPintado(c14);
            pizarra.add(c14,gbc);
            cuadros.add(c14);
        gbc.gridx = 14;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c15 = new Cuadro(15);
        agregarPintado(c15);
            pizarra.add(c15,gbc);
            cuadros.add(c15);
        gbc.gridx = 15;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c16 = new Cuadro(16);
        agregarPintado(c16);
            pizarra.add(c16,gbc);
            cuadros.add(c16);
        gbc.gridx = 16;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c17 = new Cuadro(17);
        agregarPintado(c17);
            pizarra.add(c17,gbc);
            cuadros.add(c17);
        gbc.gridx = 17;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c18 = new Cuadro(18);
        agregarPintado(c18);
            pizarra.add(c18,gbc);
            cuadros.add(c18);
        gbc.gridx = 18;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c19 = new Cuadro(19);
        agregarPintado(c19);
            pizarra.add(c19,gbc);
            cuadros.add(c19);
        gbc.gridx = 19;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c20 = new Cuadro(20);
        agregarPintado(c20);
            pizarra.add(c20,gbc);
            cuadros.add(c20);
        gbc.gridx = 20;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c21 = new Cuadro(21);
        agregarPintado(c21);
            pizarra.add(c21,gbc);
            cuadros.add(c21);
        gbc.gridx = 21;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c22 = new Cuadro(22);
        agregarPintado(c22);
            pizarra.add(c22,gbc);
            cuadros.add(c22);
        gbc.gridx = 22;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c23 = new Cuadro(23);
        agregarPintado(c23);
            pizarra.add(c23,gbc);
            cuadros.add(c23);
        gbc.gridx = 23;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c24 = new Cuadro(24);
        agregarPintado(c24);
            pizarra.add(c24,gbc);
            cuadros.add(c24);
        gbc.gridx = 24;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c25 = new Cuadro(25);
        agregarPintado(c25);
            pizarra.add(c25,gbc);
            cuadros.add(c25);
        gbc.gridx = 25;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c26 = new Cuadro(26);
        agregarPintado(c26);
            pizarra.add(c26,gbc);
            cuadros.add(c26);
        gbc.gridx = 26;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c27 = new Cuadro(27);
        agregarPintado(c27);
            pizarra.add(c27,gbc);
            cuadros.add(c27);
        gbc.gridx = 27;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c65 = new Cuadro(28);
        agregarPintado(c65);
            pizarra.add(c65,gbc);
            cuadros.add(c65);
        gbc.gridx = 28;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c66 = new Cuadro(29);
        agregarPintado(c66);
            pizarra.add(c66,gbc);
            cuadros.add(c66);
        gbc.gridx = 29;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c67 = new Cuadro(30);
        agregarPintado(c67);
            pizarra.add(c67,gbc);
            cuadros.add(c67);
        gbc.gridx = 30;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c68 = new Cuadro(31);
        agregarPintado(c68);
            pizarra.add(c68,gbc);
            cuadros.add(c68);
        gbc.gridx = 31;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c69 = new Cuadro(32);
        agregarPintado(c69);
            pizarra.add(c69,gbc);
            cuadros.add(c69);
        gbc.gridx = 32;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c70 = new Cuadro(33);
        agregarPintado(c70);
            pizarra.add(c70,gbc);
            cuadros.add(c70);
        gbc.gridx = 33;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c71 = new Cuadro(34);
        agregarPintado(c71);
            pizarra.add(c71,gbc);
            cuadros.add(c71);
        gbc.gridx = 34;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c72 = new Cuadro(35);
        agregarPintado(c72);
            pizarra.add(c72,gbc);
            cuadros.add(c72);
        gbc.gridx = 35;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c73 = new Cuadro(36);
        agregarPintado(c73);
            pizarra.add(c73,gbc);
            cuadros.add(c73);
        gbc.gridx = 36;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c74 = new Cuadro(37);
        agregarPintado(c74);
            pizarra.add(c74,gbc);
            cuadros.add(c74);
            
        //----------------------------------------------------------------------    
        //----------------------------------------------------------------------
        //----------------------------------------------------------------------
        //-------------------BARRAS1 EXTREMOS--------BARRAS2 CENTRALES-----------
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        r1 = new Rectangulo(1);
        agregarPintado(r1);
            pizarra.add(r1,gbc);
            barras1.add(r1);
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        r2 = new Rectangulo(2);
        agregarPintado(r2);
            pizarra.add(r2,gbc);
            barras2.add(r2);
        gbc.gridx = 5;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        r3 = new Rectangulo(3);
        agregarPintado(r3);
            pizarra.add(r3,gbc);
            barras1.add(r3);
        gbc.gridx = 7;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        r4 = new Rectangulo(4);
        agregarPintado(r4);
            pizarra.add(r4,gbc);
            barras2.add(r4);
        gbc.gridx = 9;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        r5 = new Rectangulo(5);
        agregarPintado(r5);
            pizarra.add(r5,gbc);
            barras1.add(r5);
        gbc.gridx = 11;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        r6 = new Rectangulo(6);
        agregarPintado(r6);
            pizarra.add(r6,gbc);
            barras2.add(r6);
        gbc.gridx = 13;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        r7 = new Rectangulo(7);
        agregarPintado(r7);
            pizarra.add(r7,gbc);
            barras1.add(r7);
        gbc.gridx = 15;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        r8 = new Rectangulo(8);
        agregarPintado(r8);
            pizarra.add(r8,gbc);
            barras2.add(r8);
        gbc.gridx = 17;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        r9 = new Rectangulo(9);
        agregarPintado(r9);
            pizarra.add(r9,gbc);
            barras1.add(r9);
        gbc.gridx = 19;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        r10 = new Rectangulo(10);
        agregarPintado(r10);
            pizarra.add(r10,gbc);
            barras2.add(r10);
        gbc.gridx = 21;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        r11 = new Rectangulo(11);
        agregarPintado(r11);
            pizarra.add(r11,gbc);
            barras1.add(r11);
        gbc.gridx = 23;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        r12 = new Rectangulo(12);
        agregarPintado(r12);
            pizarra.add(r12,gbc);
            barras2.add(r12);
        gbc.gridx = 25;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        r13 = new Rectangulo(13);
        agregarPintado(r13);
            pizarra.add(r13,gbc);
            barras1.add(r13);
        gbc.gridx = 27;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        r14 = new Rectangulo(14);
        agregarPintado(r14);
            pizarra.add(r14,gbc);
            barras2.add(r14);
        gbc.gridx = 29;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        r15 = new Rectangulo(15);
        agregarPintado(r15);
            pizarra.add(r15,gbc);
            barras1.add(r15);
        gbc.gridx = 31;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        r16 = new Rectangulo(16);
        agregarPintado(r16);
            pizarra.add(r16,gbc);
            barras2.add(r16);
        gbc.gridx = 33;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        r1 = new Rectangulo(17);
        agregarPintado(r1);
            pizarra.add(r1,gbc);
           
        //----------------------------------------------------------------------    
        //----------------------------------------------------------------------
        //----------------------------------------------------------------------
            
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c29 = new Cuadro(38);
        agregarPintado(c29);
            pizarra.add(c29,gbc);
            cuadros.add(c29);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c30 = new Cuadro(39);
        agregarPintado(c30);
            pizarra.add(c30,gbc);
            cuadros.add(c30);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c31 = new Cuadro(40);
        agregarPintado(c31);
            pizarra.add(c31,gbc);
            cuadros.add(c31);
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c32 = new Cuadro(41);
        agregarPintado(c32);
            pizarra.add(c32,gbc);
            cuadros.add(c32);
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c33 = new Cuadro(42);
        agregarPintado(c33);
            pizarra.add(c33,gbc);
            cuadros.add(c33);
        gbc.gridx = 4;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c34 = new Cuadro(43);
        agregarPintado(c34);
            pizarra.add(c34,gbc);
            cuadros.add(c34);
        gbc.gridx = 5;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c35 = new Cuadro(44);
        agregarPintado(c35);
            pizarra.add(c35,gbc);
            cuadros.add(c35);
        gbc.gridx = 6;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c36 = new Cuadro(45);
        agregarPintado(c36);
            pizarra.add(c36,gbc);
            cuadros.add(c36);
        gbc.gridx = 7;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c37 = new Cuadro(46);
        agregarPintado(c37);
            pizarra.add(c37,gbc);
            cuadros.add(c37);
        gbc.gridx = 8;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c38 = new Cuadro(47);
        agregarPintado(c38);
            pizarra.add(c38,gbc);
            cuadros.add(c38);
        gbc.gridx = 9;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c39 = new Cuadro(48);
        agregarPintado(c39);
            pizarra.add(c39,gbc);
            cuadros.add(c39);
        gbc.gridx = 10;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c40 = new Cuadro(49);
        agregarPintado(c40);
            pizarra.add(c40,gbc);
            cuadros.add(c40);
        gbc.gridx = 11;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c41 = new Cuadro(50);
        agregarPintado(c41);
            pizarra.add(c41,gbc);
            cuadros.add(c41);
        gbc.gridx = 12;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c42 = new Cuadro(51);
        agregarPintado(c42);
            pizarra.add(c42,gbc);
            cuadros.add(c42);
        gbc.gridx = 13;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c43 = new Cuadro(52);
        agregarPintado(c43);
            pizarra.add(c43,gbc);
            cuadros.add(c43);
        gbc.gridx = 14;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c44 = new Cuadro(53);
        agregarPintado(c44);
            pizarra.add(c44,gbc);
            cuadros.add(c44);
        gbc.gridx = 15;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c45 = new Cuadro(54);
        agregarPintado(c45);
            pizarra.add(c45,gbc);
            cuadros.add(c45);
        gbc.gridx = 16;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c46 = new Cuadro(55);
        agregarPintado(c46);
            pizarra.add(c46,gbc);
            cuadros.add(c46);
        gbc.gridx = 17;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c47 = new Cuadro(56);
        agregarPintado(c47);
            pizarra.add(c47,gbc);
            cuadros.add(c47);
        gbc.gridx = 18;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c48 = new Cuadro(57);
        agregarPintado(c48);
            pizarra.add(c48,gbc);
            cuadros.add(c48);
        gbc.gridx = 19;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c49 = new Cuadro(58);
        agregarPintado(c49);
            pizarra.add(c49,gbc);
            cuadros.add(c49);
        gbc.gridx = 20;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c50 = new Cuadro(59);
        agregarPintado(c50);
            pizarra.add(c50,gbc);
            cuadros.add(c50);
        gbc.gridx = 21;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c51 = new Cuadro(60);
        agregarPintado(c51);
            pizarra.add(c51,gbc);
            cuadros.add(c51);
        gbc.gridx = 22;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c52 = new Cuadro(61);
        agregarPintado(c52);
            pizarra.add(c52,gbc);
            cuadros.add(c52);
        gbc.gridx = 23;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c53 = new Cuadro(62);
        agregarPintado(c53);
            pizarra.add(c53,gbc);
            cuadros.add(c53);
        gbc.gridx = 24;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c54 = new Cuadro(63);
        agregarPintado(c54);
            pizarra.add(c54,gbc);
            cuadros.add(c54);
        gbc.gridx = 25;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c55 = new Cuadro(64);
        agregarPintado(c55);
            pizarra.add(c55,gbc);
            cuadros.add(c55);
        gbc.gridx = 26;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c56 = new Cuadro(65);
        agregarPintado(c56);
            pizarra.add(c56,gbc);
            cuadros.add(c56);
        gbc.gridx = 27;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c57 = new Cuadro(66);
        agregarPintado(c57);
            pizarra.add(c57,gbc);
            cuadros.add(c57);
        gbc.gridx = 28;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c58 = new Cuadro(67);
        agregarPintado(c58);
            pizarra.add(c58,gbc);
            cuadros.add(c58);
        gbc.gridx = 29;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c59 = new Cuadro(68);
        agregarPintado(c59);
            pizarra.add(c59,gbc);
            cuadros.add(c59);
        gbc.gridx = 30;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c60 = new Cuadro(69);
        agregarPintado(c60);
            pizarra.add(c60,gbc);
            cuadros.add(c60);
        gbc.gridx = 31;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c61 = new Cuadro(70);
        agregarPintado(c61);
            pizarra.add(c61,gbc);
            cuadros.add(c61);
        gbc.gridx = 32;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c62 = new Cuadro(71);
        agregarPintado(c62);
            pizarra.add(c62,gbc);
            cuadros.add(c62);
        gbc.gridx = 33;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c63 = new Cuadro(72);
        agregarPintado(c63);
            pizarra.add(c63,gbc);
            cuadros.add(c63);
        gbc.gridx = 34;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c64 = new Cuadro(73);
        agregarPintado(c64);
            pizarra.add(c64,gbc);
            cuadros.add(c64);
        gbc.gridx = 35;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c75 = new Cuadro(74);
        agregarPintado(c75);
            pizarra.add(c75,gbc);
            cuadros.add(c75);
        gbc.gridx = 36;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        c76 = new Cuadro(75);
        agregarPintado(c76);
            pizarra.add(c76,gbc);
            cuadros.add(c76);
    }
    
    public void agregarPintado(final Cuadro c){        
        c.addMouseListener(new MouseListener (){            
            @Override
            public void mouseClicked(MouseEvent e) {  
                c.pintado = !c.pintado;
                c.pintar = !c.pintar;
            }

            @Override
            public void mousePressed(MouseEvent e){}

            @Override
            public void mouseReleased(MouseEvent e){}

            @Override
            public void mouseEntered(MouseEvent e) {
                c.pintar = !c.pintar;
                c.repaint(); 
                c.pintado = true;                
            }

            @Override
            public void mouseExited(MouseEvent e){}   
        });
    }
    
    public void agregarPintado(final Rectangulo r){        
        r.addMouseListener(new MouseListener (){
            @Override
            public void mouseClicked(MouseEvent e) {            
              r.pintado = !r.pintado;
              r.pintar = !r.pintar;
            }

            @Override
            public void mousePressed(MouseEvent e){}

            @Override
            public void mouseReleased(MouseEvent e){}

            @Override
            public void mouseEntered(MouseEvent e) {
                r.pintar = !r.pintar;
                r.repaint(); 
                r.pintado = true;                
            }

            @Override
            public void mouseExited(MouseEvent e){}   
        });
    }     
    
    void agregarFuncionalidadBoton(JButton boton, final Pizarra pnl){
        boton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(permiteFuncionalidad == 0){
                    int centinel1 = 0, centinel2 = 0;
                    textoBits = "";   
                    posicion = 0;                                    
                    contador = 0;
                    x = 50;
                    for(int n = 0;n < puntos.size();n++){
                        puntos.remove(n);
                    }
                    if(jrb1.isSelected()){                                        
                        pnl.id = 1;             
                        centinel2 = 1;                    
                    }
                    if(jrb2.isSelected()){                          
                        pnl.id = 2;
                        centinel2 = 1;                    
                    }
                    if(jrb3.isSelected()){                                       
                        pnl.id = 3;
                        centinel2 = 1;                     
                    }
                    if(jrb4.isSelected()){                                       
                        pnl.id = 4;
                        centinel2 = 1;                     
                    }
                    if(centinel2 == 0){
                        JOptionPane.showMessageDialog(null,"No ha seleccionado una codificación","¡Alerta!",1);
                        jtf.setText("");
                        centinel1 = 1;
                    }
                    if(centinel1 == 0){
                        textoBits = jtf.getText();                        
                        if(textoBits.length() < 8){
                            JOptionPane.showMessageDialog(null,"El número de bits no es el requerido","¡Alerta!",1);
                            jtf.setText("");
                        }else{
                            textoBits = textoBits + "1";
                            textoSeleccion = (String)jcb.getSelectedItem();
                            if(textoSeleccion == "Alto"){
                                inicialY = 140;
                                alto = true;
                            }else{
                                inicialY = 336;
                                alto = false;
                            }
                            jtf.setEnabled(false);
                            jcb.setEnabled(false);
                            pnl.pintar = true;
                            pnl.updateUI();
                            puntos.clear();
                            bites.clear();
                            numeroDeBits = 0;
                            permiteFuncionalidad = 1;
                            Simulador s = new Simulador();
                            s.start();                        
                        }
                    }                
                }
            }
        });        
    } 
    
    void agregarFuncionalidadRandom(JButton boton, final Pizarra pnl){
        boton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(permiteFuncionalidad == 0){
                    permiteFuncionalidad = 1;                    
                    pnl.revalidate();                
                    textoBits = "";   
                    posicion = 0;
                    randomMode = true;
                    xs[0] = 100; xs[1] = 150; xs[2] = 200; xs[3] = 250;
                    xs[4] = 300; xs[5] = 350; xs[6] = 400; xs[7] = 450;
                    xs[8] = 500;                
                    x = 50;
                    while(textoBits.length() < 9){
                        int tmp = rnd.nextInt(2);                
                        if(tmp == 0)
                            textoBits = textoBits + "0";
                        else if(tmp == 1)
                            textoBits = textoBits + "1";
                    }                
                    pnl.id = rnd.nextInt(4) + 1;
                    int nvl = rnd.nextInt(2);
                    if(nvl == 0){
                        inicialY = 140;
                        alto = true;
                    }else{
                        inicialY = 336;
                        alto = false;
                    }                
                    pnl.pintar = true;
                    pnl.updateUI();
                    puntos.clear();
                    bites.clear();
                    numeroDeBits = 0;
                    permiteFuncionalidad = 1;
                    Simulador s = new Simulador();
                    s.start();  
                }
            }
        });
    }
    
    void agregarFuncionalidadRevelar(JButton btn, final Pizarra pnl){
        btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(permiteFuncionalidad == 1){
                    randomMode = false;
                    pnl.repaint();
                    permiteFuncionalidad = 2;
                }
            }
        });
    }
    
    public void paint(Graphics g){
        super.paint( g );  
        if(!controlDeRepaintInicial){
            g.setColor(Color.white);        
            g.setFont(new Font("arial",Font.BOLD,70));
            g.drawString("Simulador",75,220);
            g.setFont(new Font("arial",Font.BOLD,30));
            g.drawString("de",85,270);
            g.setFont(new Font("arial",Font.BOLD,70));
            g.drawString("Esquemas",130,285);
            g.setFont(new Font("arial",Font.BOLD,30));
            g.drawString("de",85,345);
            g.setFont(new Font("arial",Font.BOLD,65));
            g.drawString("Codificación",130,360);        
            g.drawLine(700,100,600,100);
            g.drawLine(600,100,600,150);
            g.drawLine(600,150,700,150);
            g.drawLine(700,150,700,200);
            g.drawLine(700,200,600,200);
            g.drawLine(600,200,600,250);
            g.drawLine(600,250,700,250);
            g.drawLine(700,250,700,300);
            g.drawLine(700,300,600,300);
            g.drawLine(600,300,600,350);
            g.drawLine(600,350,700,350);
            g.drawLine(700,350,700,400);
            g.drawLine(700,400,600,400);
            g.drawLine(600,400,600,450);
            g.drawLine(600,450,700,450);
        }
    }
    
    /*
     * Clase interna: Pizarra
     * -------------------------------------------------------------------------
     * Descripción:
     * Esta clase es el area sobre la cual se simula el flujo de bits.
     */
    public class Pizarra extends JPanel{
    
        boolean pintar = false, cambiandoNivel = false;
        boolean subiendo = false;
        int y;
        int id, numeroCirculos = 0, numeroCirculos2 = 0, contadorNivel = 0, nivel = 2; 
        int controlDeNivel = 0;

        public Pizarra(){   
            //setOpaque(false);    
            setBackground(Color.WHITE);
        }

        public void paint(Graphics g){
            super.paint( g );
            if(pintar){
                if(controladorDeNumeros > 0){
                   g.setFont(new Font("Arial",Font.BOLD,300));                   
                   g.drawString("" + controladorDeNumeros,220,335);                   
                   controladorDeNumeros--;
                }else{
                    g.setColor(Color.black);
                    g.drawLine(50,100,50,380);
                    g.drawLine(50,240,520,240);
                    g.drawString("High",20,140);
                    g.drawString("value",20,153);
                    g.drawString("Low",20,340);
                    g.drawString("value",20,353);
                    Graphics2D g2d=(Graphics2D)g;
                    g2d.setColor(Color.GRAY);
                    float dash[] = {6};
                    g2d.setStroke(new BasicStroke(1,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,5.0f, dash, 0.0f));
                    g2d.drawLine(xs[0],100,xs[0],380);
                    g2d.drawLine(xs[1],100,xs[1],380);
                    g2d.drawLine(xs[2],100,xs[2],380);
                    g2d.drawLine(xs[3],100,xs[3],380);
                    g2d.drawLine(xs[4],100,xs[4],380);
                    g2d.drawLine(xs[5],100,xs[5],380);
                    g2d.drawLine(xs[6],100,xs[6],380);
                    g2d.drawLine(xs[7],100,xs[7],380);                
                    g.setColor(Color.BLACK.brighter());
                    g.setFont(new Font("Arial",Font.BOLD,20));                
                    if(id == 1){
                        g.drawString("NRZ",270,50);
                        g.setColor(new Color(49,168,193));
                        if(x <= 97){ //Hasta llegar al primer bit.
                            puntos.add(new Punto(x,inicialY));                                                
                            x = x + 3;
                        }else{
                            //TIENE QUE CAMBIARSE DE NIVEL.
                            if(controlDeNivel == 0){
                                if((textoBits.charAt(posicion) == '1') && (inicialY >= 140)){
                                    if(inicialY <= 336){
                                        cambiandoNivel = true;
                                        puntos.add(new Punto(x,inicialY));
                                        inicialY = inicialY + 3;
                                    }else{
                                        controlDeNivel = 1;
                                        cambiandoNivel = false;
                                    }
                                }else if((textoBits.charAt(posicion) == '0') && (inicialY <= 336)){
                                    if(inicialY >= 140){
                                        cambiandoNivel = true;
                                        puntos.add(new Punto(x,inicialY));
                                        inicialY = inicialY - 3;
                                    }else{
                                        controlDeNivel = 1;
                                        cambiandoNivel = false;
                                    }
                                }
                                if(!cambiandoNivel)
                                    x = x + 3;
                            }else{ //(si ya llegué al tope)
                                //COMIENZA EL DESARROLLO COMPLETO.
                                if(numeroCirculos < 15){
                                    if(textoBits.charAt(posicion) == '1')
                                        y = 336;                                                                    
                                    else if(textoBits.charAt(posicion) == '0')
                                        y = 140;                                   
                                    puntos.add(new Punto(x,y));
                                    numeroCirculos++;                                
                                }else{
                                    if(textoBits.charAt(posicion) != textoBits.charAt(posicion + 1)){
                                        controlDeNivel = 0;                                                                     
                                        inicialY = y;
                                    }                                             
                                    bites.add(new Bit(x-25,425,textoBits.charAt(posicion)));
                                    posicion = posicion + 1;
                                    numeroDeBits = posicion;
                                    xs[posicion] = x + 5;
                                    puntos.add(new Punto(x,y));
                                    numeroCirculos = 0;                                
                                }                    
                                if(!cambiandoNivel)
                                    x = x + 3;
                            }
                        }                    
                    }

                    //--------------------------------------------------------------
                    //--------------------------------------------------------------
                    //--------------------------------------------------------------

                    if(id == 2){                        
                        g.drawString("NRZ-I",270,50);
                        g.setColor(new Color(49,168,193));
                        if(x <= 97){ //Hasta llegar al primer bit.
                            puntos.add(new Punto(x,inicialY));
                            x = x + 3;
                        }else{
                            //TIENE QUE CAMBIARSE DE NIVEL.
                            if(controlDeNivel == 0){ //Si es cero, me indica que debo cambiarme de nivel
                                if(textoBits.charAt(posicion) == '1'){
                                    cambiandoNivel = true;
                                    if(alto){
                                        if(inicialY <= 336)
                                            inicialY = inicialY + 3;
                                        else{                                        
                                            controlDeNivel = 1;
                                            cambiandoNivel = false;                                        
                                            nivel = 1;
                                        }
                                    }else if(!alto){
                                        if(inicialY >= 140){                                        
                                            inicialY = inicialY - 3;
                                        }else{                                              
                                            controlDeNivel = 1;
                                            cambiandoNivel = false;
                                            nivel = 0;
                                        }
                                    }
                                    if(nivel == 1)
                                        alto = false;
                                    else if(nivel == 0)
                                        alto = true;
                                    puntos.add(new Punto(x,inicialY));
                                }else if(textoBits.charAt(posicion) == '0'){
                                    if(numeroCirculos < 15){
                                        puntos.add(new Punto(x,inicialY));
                                        numeroCirculos++;                                
                                    }else{
                                        bites.add(new Bit(x-25,425,textoBits.charAt(posicion)));
                                        posicion = posicion + 1;
                                        numeroDeBits = posicion;
                                        xs[posicion] = x + 5;
                                        puntos.add(new Punto(x,inicialY));
                                        numeroCirculos = 0;    
                                    }
                                }
                                if(!cambiandoNivel)
                                    x = x + 3;
                            }else{ //(si ya llegué al tope)
                                //COMIENZA EL DESARROLLO COMPLETO.
                                if(numeroCirculos < 15){
                                    puntos.add(new Punto(x,inicialY));
                                    numeroCirculos++;                                
                                }else{
                                    if(textoBits.charAt(posicion + 1) == '1')
                                        controlDeNivel = 0;   
                                    bites.add(new Bit(x-25,425,textoBits.charAt(posicion)));
                                    posicion = posicion + 1;
                                    numeroDeBits = posicion;
                                    xs[posicion] = x + 5;
                                    puntos.add(new Punto(x,inicialY));
                                    numeroCirculos = 0;
                                }                    
                                if(!cambiandoNivel)
                                    x = x + 3;
                            }
                        }                    
                    }

                    //--------------------------------------------------------------
                    //--------------------------------------------------------------
                    //--------------------------------------------------------------

                    if(id == 3){
                        g.drawString("Manchester",235,50);
                        g.setColor(new Color(49,168,193));
                        if(x <= 97){ //Hasta llegar al primer bit.
                            puntos.add(new Punto(x,inicialY));                                                
                            x = x + 3;
                        }else{
                            if(alto){
                                if(textoBits.charAt(posicion) == '0'){
                                    if(numeroCirculos < 8){
                                        puntos.add(new Punto(x,inicialY));
                                        numeroCirculos++;
                                    }else{
                                        if(inicialY <= 336){
                                            puntos.add(new Punto(x,inicialY));
                                            inicialY = inicialY + 3;
                                            cambiandoNivel = true;
                                        }else{
                                            cambiandoNivel = false;
                                            if(numeroCirculos2 < 7){
                                                puntos.add(new Punto(x,inicialY));
                                                numeroCirculos2++;
                                            }else{
                                                alto = false;
                                                numeroCirculos = 0;
                                                numeroCirculos2 = 0;    
                                                bites.add(new Bit(x-25,425,textoBits.charAt(posicion)));
                                                posicion = posicion + 1;
                                                numeroDeBits = posicion;
                                                xs[posicion] = x+5;
                                                subiendo = false;
                                                puntos.add(new Punto(x,inicialY));                                            
                                            }
                                        }                                    
                                    }
                                }else if(textoBits.charAt(posicion) == '1'){
                                    if(!subiendo){                                    
                                        puntos.add(new Punto(x,inicialY));
                                        inicialY = inicialY + 3;
                                        cambiandoNivel = true;
                                        xs[posicion] = x+2;
                                        if(inicialY >= 336)
                                            subiendo = true;                                    
                                    }else{                                    
                                        if(numeroCirculos < 9){
                                            puntos.add(new Punto(x,inicialY));
                                            cambiandoNivel = false;
                                            numeroCirculos++;
                                            if(numeroCirculos == 9)
                                                cambiandoNivel = true;
                                        }else{                                                
                                            if(cambiandoNivel){                                                                                        
                                                inicialY = inicialY - 3;
                                                puntos.add(new Punto(x,inicialY));
                                                if(inicialY <= 140){                                                
                                                    cambiandoNivel = false;                                                
                                                }
                                            }else{                                            
                                                if(numeroCirculos2 < 7){
                                                    puntos.add(new Punto(x,inicialY));
                                                    numeroCirculos2++;
                                                }else{
                                                    alto = true;
                                                    numeroCirculos = 0;
                                                    numeroCirculos2 = 0;
                                                    subiendo = false;
                                                    bites.add(new Bit(x-25,425,textoBits.charAt(posicion)));
                                                    posicion = posicion + 1; 
                                                    numeroDeBits = posicion;
                                                    xs[posicion] = x+5;
                                                    puntos.add(new Punto(x,inicialY));
                                                }
                                            }
                                        }
                                    }                                
                                }//Termina alto--
                            }else{//Si es bajo... . . . . . .   
                                if(textoBits.charAt(posicion) == '0'){
                                    if(!subiendo){
                                        puntos.add(new Punto(x,inicialY));
                                        inicialY = inicialY - 3;
                                        cambiandoNivel = true;
                                        xs[posicion] = x+2;
                                        if(inicialY <= 140)
                                            subiendo = true;                                    
                                    }else{
                                        if(numeroCirculos < 9){
                                            puntos.add(new Punto(x,inicialY));
                                            cambiandoNivel = false;
                                            numeroCirculos++;
                                            if(numeroCirculos == 9)
                                                cambiandoNivel = true;
                                        }else{                                                
                                            if(cambiandoNivel){                                            
                                                puntos.add(new Punto(x,inicialY));
                                                inicialY = inicialY + 3;                                            
                                                if(inicialY >= 336){
                                                    cambiandoNivel = false;
                                                    puntos.add(new Punto(x,inicialY));
                                                }
                                            }else{                                            
                                                if(numeroCirculos2 < 7){
                                                    puntos.add(new Punto(x,inicialY));
                                                    numeroCirculos2++;
                                                }else{
                                                    alto = false;
                                                    numeroCirculos = 0;
                                                    numeroCirculos2 = 0;
                                                    bites.add(new Bit(x-25,425,textoBits.charAt(posicion)));
                                                    posicion = posicion + 1; 
                                                    numeroDeBits = posicion;
                                                    xs[posicion] = x+5;
                                                    subiendo = false;
                                                    puntos.add(new Punto(x,inicialY));
                                                }
                                            }
                                        }
                                    }
                                }else if(textoBits.charAt(posicion) == '1'){
                                    if(numeroCirculos < 8){
                                        puntos.add(new Punto(x,inicialY));
                                        numeroCirculos++;
                                    }else{
                                        if(inicialY >= 140){
                                            puntos.add(new Punto(x,inicialY));
                                            inicialY = inicialY - 3;
                                            cambiandoNivel = true;
                                        }else{
                                            cambiandoNivel = false;
                                            if(numeroCirculos2 < 7){
                                                puntos.add(new Punto(x,inicialY));
                                                numeroCirculos2++;
                                            }else{
                                                alto = true;
                                                numeroCirculos = 0;
                                                numeroCirculos2 = 0;
                                                bites.add(new Bit(x-25,425,textoBits.charAt(posicion)));
                                                posicion = posicion + 1;
                                                numeroDeBits = posicion;
                                                xs[posicion] = x+5;
                                                puntos.add(new Punto(x,inicialY));
                                            }
                                        }                                    
                                    }
                                }
                            }
                            if(!cambiandoNivel)
                                x = x + 3;
                        }                    
                    }

                    //--------------------------------------------------------------
                    //--------------------------------------------------------------
                    //--------------------------------------------------------------

                    if(id == 4){
                        g.drawString("Manchester Diferencial",195,50);
                        g.setColor(new Color(49,168,193));
                        if(x <= 97){ //Hasta llegar al primer bit.
                            puntos.add(new Punto(x,inicialY));                                                
                            x = x + 3;
                        }else{
                            if(alto){
                                if(textoBits.charAt(posicion) == '0'){
                                    if(!subiendo){                                    
                                        puntos.add(new Punto(x,inicialY));
                                        inicialY = inicialY + 3;
                                        cambiandoNivel = true;
                                        xs[posicion] = x+2;
                                        if(inicialY >= 336)
                                            subiendo = true;                                    
                                    }else{
                                        if(numeroCirculos < 9){
                                            puntos.add(new Punto(x,inicialY));
                                            cambiandoNivel = false;
                                            numeroCirculos++;
                                            if(numeroCirculos == 9)
                                                cambiandoNivel = true;
                                        }else{                                                
                                            if(cambiandoNivel){                                                                                        
                                                inicialY = inicialY - 3;
                                                puntos.add(new Punto(x,inicialY));
                                                if(inicialY <= 140){                                                
                                                    cambiandoNivel = false;                                                
                                                }
                                            }else{                                            
                                                if(numeroCirculos2 < 7){
                                                    puntos.add(new Punto(x,inicialY));
                                                    numeroCirculos2++;
                                                }else{
                                                    alto = true;
                                                    numeroCirculos = 0;
                                                    numeroCirculos2 = 0;
                                                    subiendo = false;
                                                    bites.add(new Bit(x-25,425,textoBits.charAt(posicion)));
                                                    posicion = posicion + 1;
                                                    numeroDeBits = posicion;
                                                    xs[posicion] = x+5;
                                                    puntos.add(new Punto(x,inicialY));
                                                }
                                            }
                                        }
                                    }                                
                                }else if(textoBits.charAt(posicion) == '1'){
                                    if(numeroCirculos < 8){
                                        puntos.add(new Punto(x,inicialY));
                                        numeroCirculos++;
                                    }else{
                                        if(inicialY <= 336){
                                            puntos.add(new Punto(x,inicialY));
                                            inicialY = inicialY + 3;
                                            cambiandoNivel = true;
                                        }else{
                                            cambiandoNivel = false;
                                            if(numeroCirculos2 < 7){
                                                puntos.add(new Punto(x,inicialY));
                                                numeroCirculos2++;
                                            }else{
                                                alto = false;
                                                numeroCirculos = 0;
                                                numeroCirculos2 = 0;                                        
                                                bites.add(new Bit(x-25,425,textoBits.charAt(posicion)));
                                                posicion = posicion + 1;
                                                numeroDeBits = posicion;
                                                xs[posicion] = x+5;
                                                subiendo = false;
                                                puntos.add(new Punto(x,inicialY));                                            
                                            }
                                        }                                    
                                    }
                                }
                            }else{
                                if(textoBits.charAt(posicion) == '0'){
                                    if(!subiendo){
                                        puntos.add(new Punto(x,inicialY));
                                        inicialY = inicialY - 3;
                                        cambiandoNivel = true;
                                        xs[posicion] = x+2;
                                        if(inicialY <= 140)
                                            subiendo = true;                                    
                                    }else{
                                        if(numeroCirculos < 9){
                                            puntos.add(new Punto(x,inicialY));
                                            cambiandoNivel = false;
                                            numeroCirculos++;
                                            if(numeroCirculos == 9)
                                                cambiandoNivel = true;
                                        }else{                                                
                                            if(cambiandoNivel){                                            
                                                puntos.add(new Punto(x,inicialY));
                                                inicialY = inicialY + 3;                                            
                                                if(inicialY >= 336){
                                                    cambiandoNivel = false;
                                                    puntos.add(new Punto(x,inicialY));
                                                }
                                            }else{                                            
                                                if(numeroCirculos2 < 7){
                                                    puntos.add(new Punto(x,inicialY));
                                                    numeroCirculos2++;
                                                }else{
                                                    alto = false;
                                                    numeroCirculos = 0;
                                                    numeroCirculos2 = 0;
                                                    bites.add(new Bit(x-25,425,textoBits.charAt(posicion)));
                                                    posicion = posicion + 1;
                                                    numeroDeBits = posicion;
                                                    xs[posicion] = x+5;
                                                    subiendo = false;
                                                    puntos.add(new Punto(x,inicialY));
                                                }
                                            }
                                        }
                                    }
                                }else if(textoBits.charAt(posicion) == '1'){
                                    if(numeroCirculos < 8){
                                        puntos.add(new Punto(x,inicialY));
                                        numeroCirculos++;
                                    }else{
                                        if(inicialY >= 140){
                                            puntos.add(new Punto(x,inicialY));
                                            inicialY = inicialY - 3;
                                            cambiandoNivel = true;
                                        }else{
                                            cambiandoNivel = false;
                                            if(numeroCirculos2 < 7){
                                                puntos.add(new Punto(x,inicialY));
                                                numeroCirculos2++;
                                            }else{
                                                alto = true;
                                                numeroCirculos = 0;
                                                numeroCirculos2 = 0;
                                                bites.add(new Bit(x-25,425,textoBits.charAt(posicion)));
                                                posicion = posicion + 1;
                                                numeroDeBits = posicion;
                                                xs[posicion] = x+5;
                                                puntos.add(new Punto(x,inicialY));
                                            }
                                        }                                    
                                    }
                                }
                            }
                            if(!cambiandoNivel)
                                x = x + 3;
                        }                    
                    }    
                    for(int i=0;i<puntos.size();i++){
                        Punto p = puntos.get(i);                    
                        g.drawOval(p.x,p.y,6,6);
                        g.fillOval(p.x,p.y,6,6);                    
                    }
                    g.setColor(Color.GRAY);
                    g.setFont(new Font("Arial",Font.BOLD,20));  
                    if(!randomMode){
                        for(int j=0;j<bites.size();j++){
                            Bit b = bites.get(j);                    
                            g.drawString(b.a+"",b.x,b.y);                    
                        } 
                    }
                }
            }
            System.out.println(x +" - "+ y);            
        }                
    }
            

    /*
     * Clase Interna: Simulador
     * -------------------------------------------------------------------------
     * Descripción:
     * Esta clase es la que permite realizar la simulación del flujo de bits,
     * mediante la implementación de Threads.
     */
    public class Simulador extends Thread implements Runnable{                        
        boolean continuar = true;        
        
        @Override
        public void run(){            
            while(numeroDeBits < 8){                
                repaint();
                try{              
                    if(controladorDeNumeros > 0)
                        Thread.sleep(1000);
                    else
                        Thread.sleep(1);                    
                }catch(Exception e){}                                
            }            
        }
    }
        
    /*
     * Clase interna: Punto
     * -------------------------------------------------------------------------
     * Descripción:
     * Permite almacenar las coordenadas que serán repintadas.
     */
    public class Punto{    
        int x;
        int y;

        public Punto(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    
    /*
     * Clase Interna: Bit
     * -------------------------------------------------------------------------
     * Descripción:
     * Permite almacenar los parámetros requeridos para luego poder mostrar el
     * flujo de bits.
     */
    public class Bit{
        int x;
        int y;
        char a;

        public Bit(int x, int y, char a){
            this.x = x;
            this.y = y;
            this.a = a;
        }
    }
    
    /*
    * Clase Interna: panelInferior
    * Uso: panelInferior pnl = new panelInferior(b1,b2,cua);
    * ------------------------------------------------
    * Descripción: 
    * Esta clase permite instaciar un panel en el graficador, el mismo que 
    * contiene el argoritmo de verificación de cada gráfico.
    */
    public class panelInferior extends JPanel{    
        
        public panelInferior(ArrayList <Rectangulo> b1,ArrayList <Rectangulo> b2,ArrayList <Cuadro> cua){
            ba1=b1;
            ba2=b2;
            c=cua;        
            setOpaque(false);
            setPreferredSize(new Dimension(770,128));
            setLayout(new GridBagLayout());
            agregarComponentes();
        }
        
        void agregarComponentes(){
            JPanel fondo1 = new JPanel();
            fondo1.setPreferredSize(new Dimension(195,80));
            TitledBorder title1 = BorderFactory.createTitledBorder("[Esquema de Codificación]");    
            title1.setTitleJustification(TitledBorder.CENTER);
            fondo1.setBorder(title1);
            JPanel fondo2 = new JPanel();
            fondo2.setPreferredSize(new Dimension(195,80));
            TitledBorder title2 = BorderFactory.createTitledBorder("[Flujo de bits]");    
            title2.setTitleJustification(TitledBorder.CENTER);
            fondo2.setBorder(title2);
            sistema1 = new JTextField(14);
            sistema1.setEditable(false);
            sistema2 = new JTextField(14);
            sistema2.setEditable(false);
            bits1 = new JTextField(14);
            bits1.setEditable(false);
            bits2 = new JTextField(14);
            bits2.setEditable(false);

            JLabel sub11 = new JLabel("1) ");
            JLabel sub12 = new JLabel("2) ");
            JLabel sub21 = new JLabel("1) ");
            JLabel sub22 = new JLabel("2) ");
            fondo1.add(sub11);
            fondo1.add(sistema1);
            fondo1.add(sub12);
            fondo1.add(sistema2);
            fondo2.add(sub21);
            fondo2.add(bits1);
            fondo2.add(sub22);
            fondo2.add(bits2);        
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridheight =1;
            gbc.gridwidth = 13;
            JLabel espacio = new JLabel(" ");
            espacio.setPreferredSize(new Dimension(700,25));
            add(espacio,gbc);        
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.gridheight =1;
            gbc.gridwidth = 2;
            JLabel espacio1 = new JLabel(" ");       
            espacio1.setPreferredSize(new Dimension(40,20));
            add(espacio1,gbc);
            gbc.gridx = 2;
            gbc.gridy = 1;
            gbc.gridheight =1;
            gbc.gridwidth = 2;
            add(fondo1,gbc);
            gbc.gridx = 4;
            gbc.gridy = 1;
            gbc.gridheight =1;
            gbc.gridwidth = 2;
            JLabel espacio2 = new JLabel(" ");
            espacio2.setPreferredSize(new Dimension(50,20));
            add(espacio2,gbc);        
            gbc.gridx = 6;
            gbc.gridy = 1;
            gbc.gridheight =1;
            gbc.gridwidth = 2;
            add(fondo2,gbc);
            gbc.gridx = 8;
            gbc.gridy = 1;
            gbc.gridheight =1;
            gbc.gridwidth = 2;
            JLabel espacio3 = new JLabel(" ");
            espacio3.setPreferredSize(new Dimension(50,20));
            add(espacio3,gbc);
            gbc.gridx = 10;
            gbc.gridy = 1;
            gbc.gridheight =2;
            gbc.gridwidth = 1;        
            comprobar = new JButton("¡Comprobar!");
            comprobar.setPreferredSize(new Dimension(150,40)); 
            comprobar.setIcon(new ImageIcon(ImageLoader.class.getResource("comprobar.jpg")));
            agregarFuncionalidadBoton(comprobar);
            add(comprobar,gbc);
        }
    
        void agregarFuncionalidadBoton(JButton comprobar){
            comprobar.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){                
                    baq2=barrasSeleccionadas(ba2);                                       
                    if((baq2.size()<8)&&(!baq2.isEmpty())){
                    JOptionPane jop = new JOptionPane();
                        JOptionPane.showMessageDialog(null,"¡Gráfico de señal incorrecto!","¡Error!",0,new ImageIcon(ImageLoader.class.getResource("no.gif")));
                    }
                    else{
                        flujoBits.clear();
                        flujoBits2.clear();
                        sistema1.removeAll();
                        bits1.removeAll();
                        sistema2.removeAll();
                        bits2.removeAll();
                        reconocerCodificacion(ba1,ba2,c,baq2);
                    }
                }
            });        
        }

        public void paint(Graphics g){
            super.paint(g);
            g.drawLine(88,0,88,30);
            Graphics2D g2d=(Graphics2D)g;
            g2d.setColor(Color.GRAY);
            float dash[] = {6};
            g2d.setStroke(new BasicStroke(1,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,5.0f, dash, 0.0f));
            g2d.drawLine(121,0,121,30);
            g2d.drawLine(185,0,185,30);
            g2d.drawLine(248,0,248,30);
            g2d.drawLine(312,0,312,30);
            g2d.drawLine(377,0,377,30);
            g2d.drawLine(440,0,440,30);
            g2d.drawLine(504,0,504,30);
            g2d.drawLine(568,0,568,30);
            g2d.drawLine(631,0,631,30);  
        }    

        public void reconocerCodificacion(ArrayList <Rectangulo> baw1,ArrayList <Rectangulo> baw2,ArrayList <Cuadro> cua, ArrayList <Rectangulo> bar2se){       
            sistema1.setFont(new Font("arial",Font.BOLD,15));
            bits1.setFont(new Font("arial",Font.BOLD,15));
            sistema2.setFont(new Font("arial",Font.BOLD,15));
            bits2.setFont(new Font("arial",Font.BOLD,15));            
            if((cua.get(0).idc==1)&&(cua.get(0).pintado )) 
                nivel=0;
            else
                nivel=1;
            if(!bar2se.isEmpty()){//abajo
                if(nivel==1){
                    if((baw1.get(0).pintado)){
                        flujoBits.add(0);
                        System.out.println("bajo");
                    }else
                        flujoBits.add(1);
                    for(int i=1;i<baw1.size();i++){
                        if(baw1.get(i).pintado)
                            flujoBits.add(flujoBits.get(i-1));                              
                        else{
                            if(flujoBits.get(i-1)==0)
                                flujoBits.add(1);
                            else
                                flujoBits.add(0);
                        }
                    }                        
                }
                if(nivel==0){
                    if(baw1.get(0).pintado){
                        flujoBits.add(1);
                            System.out.println("alto");
                    }else
                        flujoBits.add(0);                           
                    for(int i=1;i<baw1.size();i++){
                        if(baw1.get(i).pintado ==true)
                            flujoBits.add(flujoBits.get(i-1));                              
                        else{
                            if(flujoBits.get(i-1)==0)
                                flujoBits.add(1);
                            else
                                flujoBits.add(0);
                        }
                    }
                }
                for(Rectangulo rec2:baw1){
                    if(rec2.pintado == true)
                        flujoBits2.add(0);
                    else
                        flujoBits2.add(1);
                }                   
                sistema1.setText("Manchester");            
                bits1.setText(flujoBits.toString());            
                sistema2.setText("Manchester Dif.");            
                bits2.setText(flujoBits2.toString());                                 
            }  
            else{
                for(Rectangulo rec3:baw1){
                    if(rec3.pintado==true)
                        flujoBits.add(1);
                    else
                        flujoBits.add(0);
                }                
                for(int j=3;j<33;j=j+4){
                    if((cua.get(j).idc==4)&&(cua.get(j).pintado)||(cua.get(j).idc==8)&&(cua.get(j).pintado)||
                    (cua.get(j).idc==12)&&(cua.get(j).pintado)||(cua.get(j).idc==16)&&(cua.get(j).pintado)||
                    (cua.get(j).idc==20)&&(cua.get(j).pintado)||(cua.get(j).idc==24)&&(cua.get(j).pintado)||
                    (cua.get(j).idc==28)&&(cua.get(j).pintado)||(cua.get(j).idc==32)&&(cua.get(j).pintado))
                        flujoBits2.add(0);
                    else
                        flujoBits2.add(1);
                }
                sistema1.setText("NRZ");
                bits1.setText(flujoBits2.toString());
                sistema2.setText("NRZ-I");
                bits2.setText(flujoBits.toString());                
            }
            System.out.println(flujoBits);
            System.out.println(flujoBits2);                        
            for(Rectangulo a:baw1){
                a.pintado=false;
            }
            
            for(Rectangulo b:baw2){
                b.pintado=false;
            }
            
            for(Cuadro z:cua){
                z.pintado=false;
            }
            
            for(Rectangulo a2:bar2se){
                a2.pintado=false;
            }
            flujoBits.clear();
            flujoBits2.clear();
            sistema1.removeAll();
            bits1.removeAll();
            sistema2.removeAll();
            bits2.removeAll();
        }

        public ArrayList<Rectangulo> barrasSeleccionadas(ArrayList <Rectangulo> baw2){
            ArrayList <Rectangulo> bar2s = new ArrayList<Rectangulo>();
            for(Rectangulo rectan:baw2){
                if(rectan.pintado)
                    bar2s.add(rectan);            
            }
            return bar2s;    
        }    
    }
    
    /*
    * Clase Interna: ventanaListener
    * Uso: addWindowListener(new ListenerInicial());
    * ------------------------------------------------
    * Descripción: Este listener lanza un mensaje de confirmación para cerrar la ventana. 
    */
    class ventanaListener implements WindowListener{        
        @Override
        public void windowOpened(WindowEvent e){}
        @Override
        public void windowClosed(WindowEvent e){}    
        @Override
        public void windowClosing(WindowEvent e){
            int i=0;
            UIManager.put("OptionPane.background",new ColorUIResource(255,255,255));
            i = JOptionPane.showConfirmDialog(null,"¿Está seguro de querer salir?"
                                                  ,"Salir",JOptionPane.YES_NO_CANCEL_OPTION
                                                  ,JOptionPane.INFORMATION_MESSAGE
                                                  ,new ImageIcon(ImageLoader.class.getResource("x.gif")));
            if(i==0)
                System.exit(0);
        }
        @Override
        public void windowIconified(WindowEvent e){}
        @Override
        public void windowDeiconified(WindowEvent e){}
        @Override
        public void windowActivated(WindowEvent e){}
        @Override
        public void windowDeactivated(WindowEvent e){}
    }    
}

