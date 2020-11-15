import java.util.ArrayList;

public class Sistema {
    public ArrayList<Usuario> blackList;
    public ArrayList<Libro> libreria;

    public Sistema() {
        this.blackList = new ArrayList<>();
        this.libreria = new ArrayList<>();
    }

    public boolean login(String user, String password){
        return false;
    }

    public Libro[] busqueda(String autor){
        return null;
    }

    public Libro[] busqueda(String autor, String editorial){
        return null;
    }

    public Libro[] busqueda(String autor,String editorial, String titulo){
        return null;
    }

    private boolean validarReservacion(Reservacion reservacion){
        return false;
    }


}
