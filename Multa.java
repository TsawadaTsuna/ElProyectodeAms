public class Multa {
    private String matricula;
    private int monto;
    private String razon;
    private boolean estado;
    private int multaID;

    public Multa(int multaID, Usuario usuario, int monto, String razon, boolean estado) {
        this.matricula = usuario.getMatricula();
        this.multaID = multaID;
        this.monto = monto;
        this.razon = razon;
        this.estado = estado;
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

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getMultaID() {
        return multaID;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
