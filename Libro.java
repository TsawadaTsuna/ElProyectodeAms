public class Libro {
    protected long iSBN;
    protected String titulo;
    protected String autores;
    protected String editorial;

    public Libro(long iSBN, String titulo, String autores, String editorial) {
        this.iSBN = iSBN;
        this.titulo = titulo;
        this.autores = autores;
        this.editorial = editorial;
    }
    public Libro(){

    }

    public long getiSBN() {
        return iSBN;
    }

    public void setiSBN(long iSBN) {
        this.iSBN = iSBN;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }
}
