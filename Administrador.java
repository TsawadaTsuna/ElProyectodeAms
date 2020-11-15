public class Administrador {
    private String matricula;
    private String password;
    private Sistema sistema;

    public Administrador(String matricula, String password, Sistema sistema) {
        this.matricula = matricula;
        this.password = password;
        this.sistema = sistema;
    }

    private void bloquearUsuario(Usuario user){
        sistema.blackList.add(user);
    }

    private void actualizarReservacion(int id, Usuario usuario){
        for(int i = 0; i<3;i++){
            if(usuario.getReservaciones()[i]!=null){
                if(usuario.getReservaciones()[i].getNumeroReserva()==id){
                    usuario.getReservaciones()[i].activo = false;
                }
            }
        }
    }

    private Multa multarUsuario(Usuario user,String razon,boolean estado, int monto){
        Multa m = new Multa(0,user,monto,razon,estado);
        user.multas.add(m);
        return m;
    }

    private Libro agregarLibro(long isbn, String titulo, String autor,String editorial){
        Libro l = new Libro(isbn,titulo,autor,editorial);
        this.sistema.libreria.add(l);
        return l;
    }

    private void actualizarMulta(int monto,boolean estado, Usuario usuario, int multaID){
        for (int i=0; i<usuario.multas.size();i++){
            Multa m = usuario.multas.get(i);
            if(m.getMultaID() == multaID){
                m.setMonto(monto);
                m.setEstado(estado);
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
}
