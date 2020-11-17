import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Sistema {
    public ArrayList<Usuario> blackList;
    public ArrayList<Libro> libreria;
    public Usuario usuario;

    public Sistema() {
        this.blackList = new ArrayList<>();
        this.libreria = new ArrayList<>();
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        usuario=new Usuario();
    }

    public boolean login(String user, String password){
        try{
            BufferedReader br=new BufferedReader(new FileReader("usuarios.txt"));
            String linea=br.readLine();
            String[] usepass=linea.split(" ");
            if(user.equals(usepass[0])&&password.equals(usepass[1])){
                usuario=new Usuario(user,password,"Juan");
                return true;
            }
            while (linea!=null){
                usepass=linea.split(" ");
                if(user.equals(usepass[0])&&password.equals(usepass[1])){
                    usuario=new Usuario(user,password,"Juan");
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

    public Reservacion crearRservacion(Libro libro){
        if(validarReservacion(usuario)){
            return usuario.reservar(libro);
        }else {
            return null;
        }
    }

}