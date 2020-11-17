import java.io.*;

public class Administrador {
    private String matricula;
    private String password;
    private Sistema sistema;

    public Administrador(String matricula, String password, Sistema sistema) {
        this.matricula = matricula;
        this.password = password;
        this.sistema = sistema;
    }

    public void bloquearUsuario(Usuario user){
        try{
            BufferedReader br=new BufferedReader(new FileReader("usuarios.txt"));
            String usuarios="";
            String linea=br.readLine();
            while (linea!=null){
                String[] userpass= linea.split(" ");
                if(userpass[0].equals(user.getMatricula())&&userpass[1].equals(user.getPassword())){
                    linea=br.readLine();
                    continue;
                }else {
                    usuarios += userpass[0] + " " + userpass[1] + "\n";
                    linea = br.readLine();
                }
            }
            for(int i=0;i<sistema.usuarios.size();i++){
                Usuario use=sistema.usuarios.get(i);
                if(use.getMatricula().equals(user.getMatricula())){
                    sistema.usuarios.remove(use);
                    break;
                }
            }
            PrintWriter pw = new PrintWriter(new FileWriter("usuarios.txt"));
            pw.write(usuarios);
            pw.close();
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void actualizarReservacion(int id, Usuario usuario){
        for(int i = 0; i<3;i++){
            if(usuario.getReservaciones()[i]!=null){
                if(usuario.getReservaciones()[i].getNumeroReserva()==id){
                    usuario.getReservaciones()[i].activo = false;
                }
            }
        }
    }

    public void multarUsuario(Usuario user,String razon,boolean estado, int monto){
        Multa m = new Multa(0,user,monto,razon,estado);
        user.multas.add(m);
    }

    public Libro agregarLibro(long isbn, String titulo, String autor,String editorial){
        Libro l = new Libro(isbn,titulo,autor,editorial);
        this.sistema.libreria.add(l);
        return l;
    }

    public void actualizarMulta(int monto,boolean estado, Usuario usuario, int multaID){
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

    public static void main(String[] args) {
        Administrador adm= new Administrador("asd","asd",new Sistema());
        adm.bloquearUsuario(new Usuario("Alice","234","Alice"));
    }
}
