import javax.swing.*;
import java.awt.*;

public class Ventana extends JFrame {

    public Ventana(){
        super("Biblioteca");
        Menu m= new Menu();
        //Logica de cambio de panel
        this.add(m.panCont);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //Se agrega un icono personalizado
        this.setIconImage(new ImageIcon(getClass().getResource("icon.png")).getImage());        	
        this.setVisible(true);

        //Abrir al centro del monitor
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }

    public static void main(String[] args) {
        Ventana v= new Ventana();
    }
}
