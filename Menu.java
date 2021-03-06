
import javax.swing.*;

//import sun.jvm.hotspot.tools.SysPropsDumper;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Menu {
    public JTextField user, password,busTitulo,busAutor,busEdit,libroReserva,
            regAutor, regTitulo, regIssbn, regEditorial, mulMonto, mulMatri, mulRazon;
    public JButton login,buscar,reservar, retorno1, retorno2, registrar, bloquear, multar, actMulta, actReser, cancelar;
    public Sistema s;
    public boolean conflog;
    public String userAct;
    public Usuario act;

    public JPanel loginView,vistaUser,vistaAdmin,panCont;
    public JList<String> reservacionJList;
    public JList<String> multaJList;
    public JList<String> libroJList;
    public JList<String> userJList;
    public ArrayList<Libro> resultados;
    private CardLayout cl;

    public Menu(){
        cl = new CardLayout();

        act=new Usuario();
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
                    //s.usuarios.add(new Usuario("admin","1234","Admin"));
                    cl.show(panCont,"Adm");
                    userAct="admin";
                }else if (s.login(user.getText(),password.getText(),user.getText())){
                    cl.show(panCont,"User");
                    conflog=true;
                    userAct=user.getText();
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
        act=s.usuarios.get(0);
        for(int i=0;i<s.usuarios.size();i++){
            if(s.usuarios.get(i).getMatricula().equals(userAct)){
                act=s.usuarios.get(i);
                break;
            }
        }
        for(int i=0;i<3;i++){
            if(act.getReservaciones()[i]!=null){
                resmod.addElement(act.getReservaciones()[i].toString());
            }
        }
        DefaultListModel<String> multamod=new DefaultListModel<>();
        multaJList=new JList<>(multamod);
        if(act.multas.size()!=0){
            for (int i=0;i<act.multas.size();i++){
                multamod.addElement(act.multas.get(i).toString());
            }
        }
        DefaultListModel<String> libromod=new DefaultListModel<>();
        libroJList=new JList<>(libromod);
        buscar=new JButton();
        reservar=new JButton("Reservar");
        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Libro");
                if(!busTitulo.getText().isEmpty()&&!busAutor.getText().isEmpty()&&!busEdit.getText().isEmpty()){
                    libromod.clear();
                    resultados=s.busqueda(busAutor.getText(),busEdit.getText(),busTitulo.getText());
                    for(int i=0;i<resultados.size();i++){
                        libromod.addElement(resultados.get(i).toString());
                    }
                }else if(!busTitulo.getText().isEmpty()&&!busAutor.getText().isEmpty()){
                    libromod.clear();
                    resultados=s.busqueda(busAutor.getText(),busTitulo.getText());
                    for(int i=0;i<resultados.size();i++){
                        libromod.addElement(resultados.get(i).toString());
                    }
                }else if(!busTitulo.getText().isEmpty()){
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
                if(!libroJList.isSelectionEmpty()) {
                    String[] libroselec = libroJList.getSelectedValue().split("-");
                    s.crearReservacion(new Libro(Long.parseLong(libroselec[2]),libroselec[0] , libroselec[1], libroselec[3]), act);
                    resmod.clear();
                    for(int i=0;i<3;i++){
                        if(act.getReservaciones()[i]!=null){
                            resmod.addElement(act.getReservaciones()[i].toString());
                        }
                    }
                }
            }
        });

        cancelar = new JButton("Cancelar");
        cancelar.setBounds(170,490,90,50);
        cancelar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!reservacionJList.isSelectionEmpty()){
                    String[] reserv=reservacionJList.getSelectedValue().split(" ");
                    act.cancelarReservacion(Integer.parseInt(reserv[0]));
                    resmod.clear();
                    for(int i=0;i<3;i++){
                        if(act.getReservaciones()[i]!=null){
                            resmod.addElement(act.getReservaciones()[i].toString());
                        }
                    }
                }
                System.out.println("cancelando");
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
        vistaUser.add(cancelar);
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
                try{
                    if(regAutor.getText().isEmpty() || regTitulo.getText().isEmpty() || regEditorial.getText().isEmpty() || regIssbn.getText().isEmpty()){
                        System.out.println("Favor de llenar todos las casillas de registro ");
                    }else{
                        FileWriter registro = new FileWriter("libros.txt", true);
                        String libroRegistro = "\n" + regAutor.getText() + "/" + regTitulo.getText() + "/" + regEditorial.getText() + "/" + regIssbn.getText();
                        registro.append(libroRegistro);
                        registro.close();
                        System.out.println("REGISTRANDO");
                        s.libreria.add(new Libro(Long.parseLong(regIssbn.getText()),regTitulo.getText(),regAutor.getText(),regEditorial.getText()));
                    }
                }catch(IOException ex){
                    System.out.println("ERROR");
                    ex.printStackTrace();
                }

            }
        });
        vistaAdmin.add(registrar);

        DefaultListModel<String> usermod=new DefaultListModel<>();
        userJList=new JList<>(usermod);
        //usermod.addElement("Matricula Nombre");
        for(int i=0;i<s.usuarios.size();i++){
            usermod.addElement(s.usuarios.get(i).toString());
        }
        userJList.setBounds(40, 100, 220, 367);
        vistaAdmin.add(userJList);

        bloquear = new JButton("Bloquear");
        bloquear.setBounds(170,490,90,50);
        bloquear.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!userJList.isSelectionEmpty()){
                    String[] userpass=userJList.getSelectedValue().split(" ");
                    s.bloquear(new Usuario(userpass[0],userpass[1],"a"));
                    usermod.clear();
                    for(int i=0;i<s.usuarios.size();i++){
                        usermod.addElement(s.usuarios.get(i).toString());
                    }
                }
                System.out.println("BLOQUEANDO");
            }
        });
        vistaAdmin.add(bloquear);

        multar = new JButton("Multar");
        multar.setBounds(375,490,90,50);
        multar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               if(!mulMatri.getText().isEmpty()&&!mulRazon.getText().isEmpty()&&!mulMonto.getText().isEmpty()){
                   Usuario current=null;
                   for(int i=0;i<s.usuarios.size();i++){
                       if(s.usuarios.get(i).getMatricula().equals(mulMatri.getText())){
                           current=s.usuarios.get(i);
                           break;
                       }
                   }
                   s.multar(current,mulRazon.getText(),true,Integer.parseInt(mulMonto.getText()));
                   multamod.clear();
                   if(act.multas.size()!=0){
                       for (int i=0;i<act.multas.size();i++){
                           multamod.addElement(act.multas.get(i).toString());
                       }
                   }
               } else {
                   JOptionPane.showMessageDialog(null,"Opcion invalida");
               }
            }
        });
        vistaAdmin.add(multar);

        mulRazon = new JTextField();
        mulRazon.setBounds(425,453,100,20);
        vistaAdmin.add(mulRazon);

        mulMonto = new JTextField();
        mulMonto.setBounds(425,400,100,20);
        vistaAdmin.add(mulMonto);

        mulMatri = new JTextField();
        mulMatri.setBounds(425,344,100,20);
        vistaAdmin.add(mulMatri);

        actMulta = new JButton("Actualizar Multas");
        actMulta.setBounds(580,360,180,80);
        actMulta.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!userJList.isSelectionEmpty()){
                    String[] userpass=userJList.getSelectedValue().split(" ");
                    Usuario current=null;
                    for(int i=0;i<s.usuarios.size();i++){
                        if(s.usuarios.get(i).getMatricula().equals(userpass[0])){
                            current=s.usuarios.get(i);
                            break;
                        }
                    }
                    current.multas= new ArrayList<>();
                    multamod.clear();
                    if(act.multas.size()!=0){
                        for (int i=0;i<act.multas.size();i++){
                            multamod.addElement(act.multas.get(i).toString());
                        }
                    }
                }
            }
        });
        vistaAdmin.add(actMulta);

        actReser = new JButton("Actualizar Reservaciones");
        actReser.setBounds(580,460,180,80);
        actReser.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!userJList.isSelectionEmpty()) {
                    String[] userpass = userJList.getSelectedValue().split(" ");
                    Usuario current = null;
                    for (int i = 0; i < s.usuarios.size(); i++) {
                        if (s.usuarios.get(i).getMatricula().equals(userpass[0])) {
                            current = s.usuarios.get(i);
                            break;
                        }
                    }
                    current.setReservaciones(new Reservacion[3]);
                    resmod.clear();
                    for(int i=0;i<3;i++){
                        if(act.getReservaciones()[i]!=null){
                            resmod.addElement(act.getReservaciones()[i].toString());
                        }
                    }

                }
            }
        });
        vistaAdmin.add(actReser);


        cl.show(panCont,"Log");
    }
}