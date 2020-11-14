
public class Reservacion extends Libro {
    public String solicitante;
    public String matricula;
    public int numeroReserva;
    public String fechaReserva;
    public String periodoRenta;

    public Reservacion(String matricula,Libro libro,String fecha,String periodo){
        this.matricula=matricula;
        fechaReserva=fecha;
        periodoRenta=periodo;
    }
}
