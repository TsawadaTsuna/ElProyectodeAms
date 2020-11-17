import java.util.ArrayList;
import java.util.Random;

public class Usuario {
    private String matricula;
    private String password;
    private String nombre;
    private Reservacion[] reservaciones;
    public ArrayList<Multa> multas;


    public Usuario(String matricula, String password, String nombre) {
        this.matricula = matricula;
        this.password = password;
        this.nombre = nombre;
        this.reservaciones = new Reservacion[3];
        this.multas = new ArrayList<>();
    }

    public Usuario(){
        this.reservaciones = new Reservacion[3];
        this.multas = new ArrayList<>();
    }

    public Reservacion reservar(Libro libro){
        Random ran = new Random();
        Reservacion r1 = new Reservacion(ran.nextInt(100000),this.matricula,libro,"11/11/20",4);
        for (int i = 0; i<this.reservaciones.length;i++){
            if(reservaciones[i]==null){
                reservaciones[i] = r1;
                return r1;
            }
        }
        return null;
    }

    public void cancelarReservacion(int id){
        for (int i = 0; i<this.reservaciones.length;i++){
            if(this.reservaciones[i]!=null) {
                if (this.reservaciones[i].getNumeroReserva() == id) {
                    this.reservaciones[i] = null;
                    break;
                }
            }
        }
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Reservacion[] getReservaciones() {
        return reservaciones;
    }

    public void setReservaciones(Reservacion[] reservaciones) {
        this.reservaciones = reservaciones;
    }

    @Override
    public String toString() {
        return matricula+" "+password;
    }
}
