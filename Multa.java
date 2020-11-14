public class Multa {
    public String matricula;
    public int monto;
    public String razon;
    public String estado;
    public int multaID;

    public Multa(Usuario usuario, int monto, String razon, String matricula) {

    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getMultaID() {
        return multaID;
    }
}
