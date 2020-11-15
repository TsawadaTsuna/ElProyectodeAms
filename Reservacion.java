public class Reservacion extends Libro{
    private String matricula;
    private int numeroReserva;
    private String fechaReserva;
    private int periodoRenta;
    public boolean activo;

    public Reservacion(int numeroReserva, String matricula,Libro libro,String fecha,int periodo){
        this.numeroReserva = numeroReserva;
        this.matricula=matricula;
        fechaReserva=fecha;
        periodoRenta=periodo;
        this.activo = true;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getNumeroReserva() {
        return numeroReserva;
    }

    public void setNumeroReserva(int numeroReserva) {
        this.numeroReserva = numeroReserva;
    }

    public String getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(String fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public int getPeriodoRenta() {
        return periodoRenta;
    }

    public void setPeriodoRenta(int periodoRenta) {
        this.periodoRenta = periodoRenta;
    }
}
