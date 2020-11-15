import java.util.ArrayList;

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

    public Reservacion reservar(Libro libro){
        Reservacion r1 = new Reservacion(0,this.matricula,libro,"11/11/20",4);
        for (int i = 0; i<this.reservaciones.length;i++){
            if(reservaciones[i]==null){
                reservaciones[i] = r1;
                return r1;
            }
        }
        return null;
    }

    public void cancelarReservacion(Reservacion reservacion){
        for (int i = 0; i<this.reservaciones.length;i++){
            if(this.reservaciones[i].equals(reservacion)){
                this.reservaciones[i] = null;
                System.out.println("Reservacion cancelada");
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

}
