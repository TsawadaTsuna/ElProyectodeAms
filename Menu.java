import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Menu {
    public JTextField user, password,busTitulo,busAutor,busEdit,libroReserva;
    public JButton login,buscar,reservar;
    public Sistema s;
    public boolean conflog;

    public JPanel loginView,vistaUser,vistaAdmin,panCont;
    public JList<String> reservacionJList;
    public JList<String> multaJList;
    public JList<String> libroJList;
    public ArrayList<Libro> resultados;
    private CardLayout cl;

    public Menu(){
        cl = new CardLayout();

        panCont = new JPanel();

        loginView= new JPanel(){
            private static final long serialVersionUID = 1L;

            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image fondo = new ImageIcon("FondoLogin.png").getImage();
                g.drawImage(fondo, 0, 0, loginView.getWidth(), loginView.getHeight(), loginView);
            }
        };

        vistaUser = new JPanel(){
            private static final long serialVersionUID = 1L;

            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image fondo = new ImageIcon("FondoUser.png").getImage();
                g.drawImage(fondo, 0, 0, loginView.getWidth(), loginView.getHeight(), loginView);
            }
        };

        vistaAdmin = new JPanel(){
            private static final long serialVersionUID = 1L;

            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image fondo = new ImageIcon("FondoUser.png").getImage();
                g.drawImage(fondo, 0, 0, loginView.getWidth(), loginView.getHeight(), loginView);
            }
        };

        panCont.setLayout(cl);
        panCont.add(loginView,"Log");
        panCont.add(vistaUser,"User");

        loginView.setPreferredSize(new Dimension(800,600));
        loginView.setLayout(null);

        vistaUser.setPreferredSize(new Dimension(800,600));
        vistaUser.setLayout(null);

        s=new Sistema();
        user = new JTextField();
        user.setBounds(580, 230, 150, 25);

        password = new JPasswordField();
        password.setBounds(580, 275, 150, 25);
        login=new JButton();
        login.setBounds(740, 240, 45, 45);
        login.setContentAreaFilled(false);
        login.setBorderPainted(false);
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(user.getText().equals("admin")&&password.getText().equals("1234")){
                    conflog=true;
                    s.usuario=new Usuario("admin","1234","Admin");
                    cl.show(panCont,"User");
                }else
                    conflog=s.login(user.getText(),password.getText());

                System.out.println(conflog);
            }
        });
        

        loginView.add(user,"USER TXT");
        loginView.add(password,"PASSWORD TEXT");
        loginView.add(login,"LOGIN BTN");
       
        busTitulo= new JTextField();
        libroReserva=new JTextField();
        busAutor=new JTextField();
        busEdit=new JTextField();
        DefaultListModel<String> resmod=new DefaultListModel<>();
        reservacionJList =new JList<>(resmod);
        for(int i=0;i<3;i++){
            if(s.usuario.getReservaciones()[i]!=null){
                resmod.addElement(s.usuario.getReservaciones()[i].toString());
            }
        }
        DefaultListModel<String> multamod=new DefaultListModel<>();
        multaJList=new JList<>(multamod);
        if(s.usuario.multas.size()!=0){
            for (int i=0;i<s.usuario.multas.size();i++){
                multamod.addElement(s.usuario.multas.get(i).toString());
            }
        }
        DefaultListModel<String> libromod=new DefaultListModel<>();
        libroJList=new JList<>(libromod);
        buscar=new JButton();
        reservar=new JButton();
        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(busTitulo.getText()!=null&&busAutor.getText()!=null&&busEdit!=null){
                    libromod.clear();
                    resultados=s.busqueda(busAutor.getText(),busEdit.getText(),busTitulo.getText());
                    for(int i=0;i<resultados.size();i++){
                        libromod.addElement(resultados.get(i).toString());
                    }
                }else if(busTitulo.getText()!=null&&busAutor.getText()!=null){
                    libromod.clear();
                    resultados=s.busqueda(busAutor.getText(),busTitulo.getText());
                    for(int i=0;i<resultados.size();i++){
                        libromod.addElement(resultados.get(i).toString());
                    }
                }else if(busTitulo.getText()!=null){
                    libromod.clear();
                    resultados=s.busqueda(busTitulo.getText());
                    for(int i=0;i<resultados.size();i++){
                        libromod.addElement(resultados.get(i).toString());
                    }
                }else{
                    resultados=null;
                    JOptionPane.showMessageDialog(null,"Busqueda invalida");
                }
            }
        });

        reservar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(libroReserva.getText()!=null)
                    s.crearRservacion(new Libro(9909989l,libroReserva.getText(),"S. Skinner,","SSS"));
            }
        });


        vistaUser.add(busAutor);

        //Acomodar la vista de usuario
        /*vistaUser.add(busTitulo);
        vistaUser.add(busAutor);
        vistaUser.add(busEdit);
        vistaUser.add(libroJList);
        vistaUser.add(reservacionJList);
        vistaUser.add(multaJList);
        vistaUser.add(reservar);
        vistaUser.add(libroReserva);
        vistaUser.add(buscar);*/

        //vista del admin
        cl.show(panCont,"Log");
    }
}
