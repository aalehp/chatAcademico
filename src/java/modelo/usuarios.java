package modelo;

public class usuarios {
    private String matricula,nombre, pws, materia;
    private double promedio;

    public usuarios(String matricula, String nombre, String pws, String materia, double promedio) {
        this.matricula = matricula;
        this.nombre = nombre;
        this.pws = pws;
        this.materia = materia;
        this.promedio = promedio;
    }
    

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPws() {
        return pws;
    }

    public void setPws(String pws) {
        this.pws = pws;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }
    
    
}
