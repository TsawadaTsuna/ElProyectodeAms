import javax.swing.*;

public class Ventana extends JFrame {

    public Ventana(){
        super("biblioteca");
        Menu m= new Menu();
        //Logica de cambio de panel
        this.add(m.loginView);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        Ventana v= new Ventana();
    }
}
