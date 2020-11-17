import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Sistema {
    public ArrayList<Libro> libreria;
    public ArrayList<Usuario> usuarios;

    public Sistema() {
        this.libreria = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        try{
            BufferedReader br=new BufferedReader(new FileReader("libros.txt"));
            String linea=br.readLine();
            String[] usepass;
            while (linea!=null){
                usepass=linea.split("/");
                this.libreria.add(new Libro(Long.parseLong(usepass[3]), usepass[0], usepass[1], usepass[3]));

                linea=br.readLine();

            }
            System.out.println(libreria.toString());
            br.close();
            BufferedReader bru=new BufferedReader(new FileReader("usuarios.txt"));
            linea=bru.readLine();
            while (linea!=null){
                usepass=linea.split(" ");
                this.usuarios.add(new Usuario(usepass[0], usepass[1], usepass[0]));
                linea=bru.readLine();

            }
            System.out.println(libreria.toString());
            bru.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean login(String user, String password, String nombre){
        try{
            BufferedReader br=new BufferedReader(new FileReader("usuarios.txt"));
            String linea=br.readLine();
            String[] usepass=linea.split(" ");
            if(user.equals(usepass[0])&&password.equals(usepass[1])){
                usuarios.add(new Usuario(user,password,nombre));
                return true;
            }
            while (linea!=null){
                usepass=linea.split(" ");
                if(user.equals(usepass[0])&&password.equals(usepass[1])){
                    usuarios.add(new Usuario(user,password,nombre));
                    return true;
                }
                linea=br.readLine();
            }
            br.close();
            return false;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    public ArrayList<Libro> busqueda(String titulo){
        ArrayList<Libro> libros=new ArrayList<>();
        for(int i=0;i<libreria.size();i++){
            Libro l=libreria.get(i);
            if(l.titulo.equals(titulo)){
                libros.add(l);
            }
        }
        return libros;
    }

    public ArrayList<Libro> busqueda(String autor, String titulo){
        ArrayList<Libro> libros=new ArrayList<>();
        for(int i=0;i<libreria.size();i++){
            Libro l=libreria.get(i);
            if(l.titulo.equals(titulo)&&l.autores.equals(autor)){
                libros.add(l);
            }
        }
        return libros;
    }

    public ArrayList<Libro> busqueda(String autor,String editorial, String titulo){
        ArrayList<Libro> libros=new ArrayList<>();
        for(int i=0;i<libreria.size();i++){
            Libro l=libreria.get(i);
            if(l.titulo.equals(titulo)&&l.autores.equals(autor)&&l.editorial.equals(editorial)){
                libros.add(l);
            }
        }
        return libros;
    }

    private boolean validarReservacion(Usuario user){
        if(user.multas.size()==0){
            Reservacion[] reservas=user.getReservaciones();
            int c=0;
            for(int i=0;i<3;i++){
                if(reservas[i]!=null) {
                    if (reservas[i].activo){
                        c++;
                    }
                }
            }
            return c<3;
        }else
            return false;
    }

    public Reservacion crearReservacion(Libro libro, Usuario usuario){
        if(validarReservacion(usuario)){
            return usuario.reservar(libro);
        }else {
            return null;
        }
    }

    public void bloquear(Usuario user){
        Administrador admin=new Administrador("admin","1234",this);
        admin.bloquearUsuario(user);
    }

    public void multar(Usuario user,String razon, boolean estado, int monto){
        Administrador admin= new Administrador("admin","1234",this);
        admin.multarUsuario(user, razon, estado, monto);
    }

}