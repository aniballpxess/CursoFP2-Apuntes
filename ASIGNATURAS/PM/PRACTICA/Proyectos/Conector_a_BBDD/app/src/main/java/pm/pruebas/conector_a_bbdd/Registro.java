package pm.pruebas.conector_a_bbdd;

public class Registro {
    private String id;
    private String nombre;
    private String numRegistro;

    public Registro(String id, String nombre, String numRegistro) {
        this.id = id;
        this.nombre = nombre;
        this.numRegistro = numRegistro;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNumRegistro() {
        return numRegistro;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNumRegistro(String numRegistro) {
        this.numRegistro = numRegistro;
    }
}
