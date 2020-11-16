import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Menu {
    public JTextField user, password,busTitulo,busAutor,busEdit,libroReserva, regAutor, regTitulo, regEditorial, regIssbn;
    public JButton login,buscar,reservar, retorno1, retorno2, registrar, bloquear;
    public Sistema s;
    public boolean conflog;

    public JPanel loginView,vistaUser,vistaAdmin,panCont;
    public JList<String> reservacionJList;
    public JList<String> multaJList;
    public JList<String> libroJList;
    public JList<String> userJList;
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
                Image fondo = new ImageIcon("FondoAdmin.png").getImage();
                g.drawImage(fondo, 0, 0, loginView.getWidth(), loginView.getHeight(), loginView);
            }
        };

        panCont.setLayout(cl);
        panCont.add(loginView,"Log");
        panCont.add(vistaUser,"User");
        panCont.add(vistaAdmin,"Adm");

        loginView.setPreferredSize(new Dimension(800,600));
        loginView.setLayout(null);

        vistaUser.setPreferredSize(new Dimension(800,600));
        vistaUser.setLayout(null);

        vistaAdmin.setPreferredSize(new Dimension(800,600));
        vistaAdmin.setLayout(null);

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
                    cl.show(panCont,"Adm");
                }else if (s.login(user.getText(),password.getText())){
                    cl.show(panCont,"User");  
                    conflog=true;  
                }else
                    conflog=false;
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
        reservar=new JButton("Reservar");
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

        retorno1 = new JButton("");
        retorno1.setContentAreaFilled(false);
        retorno1.setBorderPainted(false);
        retorno1.setBounds(20, 527, 65, 68);
        retorno1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(panCont,"Log");
            }
        });
        vistaUser.add(retorno1);

        retorno2 = new JButton("");
        retorno2.setContentAreaFilled(false);
        retorno2.setBorderPainted(false);
        retorno2.setBounds(20, 527, 65, 68);
        retorno2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(panCont,"Log");
            }
        });
        vistaAdmin.add(retorno2);

        //Acomodar la vista de usuario
        vistaUser.add(buscar);
        buscar.setContentAreaFilled(false);
        buscar.setBorderPainted(false);
        buscar.setBounds(715, 96, 50, 50);

        vistaUser.add(busAutor);
        busTitulo.setBounds(450,112,250,20);
        vistaUser.add(busEdit);
        busAutor.setBounds(450,145,250,20);
        vistaUser.add(busTitulo);
        busEdit.setBounds(450,180,250,20);

        vistaUser.add(libroJList);
        libroJList.setBounds(350,270,300,90);

        vistaUser.add(reservar);
        reservar.setBounds(660,290,100,45);

        vistaUser.add(multaJList);
        multaJList.setBounds(350,450,400,120);

        vistaUser.add(reservacionJList);
        reservacionJList.setBounds(40, 100, 220, 367);
       
        /*
        vistaUser.add(libroReserva);
        */

        //vista del admin

        regTitulo = new JTextField();
        regAutor = new JTextField();
        regIssbn = new JTextField();
        regEditorial = new JTextField();

        vistaAdmin.add(regTitulo);
        regTitulo.setBounds(450,112,250,20);
        vistaAdmin.add(regAutor);
        regAutor.setBounds(450,145,250,20);
        vistaAdmin.add(regEditorial);
        regEditorial.setBounds(450,180,250,20);
        vistaAdmin.add(regIssbn);
        regIssbn.setBounds(450,215,250,20);

        registrar = new JButton();
        registrar.setContentAreaFilled(false);
        registrar.setBorderPainted(false);
        registrar.setBounds(715, 96, 50, 50);
        registrar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("REGISTRANDO");
            }
        });
        vistaAdmin.add(registrar);

        DefaultListModel<String> usermod=new DefaultListModel<>();
        userJList=new JList<>(usermod);
        userJList.setBounds(40, 100, 220, 367);
        vistaAdmin.add(userJList);

        bloquear = new JButton("Bloquear");
        bloquear.setBounds(170,490,90,50);
        vistaAdmin.add(bloquear);

        cl.show(panCont,"Log");
    }
}
