package co.edu.uac.apmoviles.sqliteuniversidad;

public class Estudiante {

    private String codigo;
    private String programa,internet,telefono,computadora;


    public Estudiante(String codigo, String programa, String internet, String telefono, String computadora) {
        this.codigo = codigo;
        this.programa = programa;
        this.internet = internet;
        this.telefono = telefono;
        this.computadora = computadora;
    }

    public Estudiante() {
    }

    public Estudiante(String programa, String internet, String telefono, String computadora) {
        this.programa = programa;
        this.internet = internet;
        this.telefono = telefono;
        this.computadora = computadora;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getInternet() {
        return internet;
    }

    public void setInternet(String internet) {
        this.internet = internet;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getComputadora() {
        return computadora;
    }

    public void setComputadora(String computadora) {
        this.computadora = computadora;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }


}
