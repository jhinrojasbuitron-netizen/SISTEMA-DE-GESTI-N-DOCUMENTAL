/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal;

/**
 *
 * @author User
 */
public class Alumno {
    private int codAlumno;
    private String nameAlumno;
    private String apellidoAlumno;
    private String correoAlumno;
    private String dniAlumno;
    private int codGenero;
    private int estadoAlumno;

    //Creamos los costructores para listar, buscar, agregar y actualizar
    public Alumno(String nameAlumno, String apellidoAlumno, String correoAlumno, String dniAlumno, int codGenero) {
        this.nameAlumno = nameAlumno;
        this.apellidoAlumno = apellidoAlumno;
        this.correoAlumno = correoAlumno;
        this.dniAlumno = dniAlumno;
        this.codGenero = codGenero;
        this.estadoAlumno = 1;
    }

    public Alumno(int codAlumno, String nameAlumno, String apellidoAlumno, String correoAlumno, String dniAlumno, int codGenero, int estadoAlumno) {
        this.codAlumno = codAlumno;
        this.nameAlumno = nameAlumno;
        this.apellidoAlumno = apellidoAlumno;
        this.correoAlumno = correoAlumno;
        this.dniAlumno = dniAlumno;
        this.codGenero = codGenero;
        this.estadoAlumno = estadoAlumno;
    }

    //Creamos los Getters y Setters
    public int getCodAlumno() {
        return codAlumno;
    }

    public void setCodAlumno(int codAlumno) {
        this.codAlumno = codAlumno;
    }

    public String getNameAlumno() {
        return nameAlumno;
    }

    public void setNameAlumno(String nameAlumno) {
        this.nameAlumno = nameAlumno;
    }

    public String getApellidoAlumno() {
        return apellidoAlumno;
    }

    public void setApellidoAlumno(String apellidoAlumno) {
        this.apellidoAlumno = apellidoAlumno;
    }

    public String getCorreoAlumno() {
        return correoAlumno;
    }

    public void setCorreoAlumno(String correoAlumno) {
        this.correoAlumno = correoAlumno;
    }

    public String getDniAlumno() {
        return dniAlumno;
    }

    public void setDniAlumno(String dniAlumno) {
        this.dniAlumno = dniAlumno;
    }

    public int getCodGenero() {
        return codGenero;
    }

    public void setCodGenero(int codGenero) {
        this.codGenero = codGenero;
    }

    public int getEstadoAlumno() {
        return estadoAlumno;
    }

    public void setEstadoAlumno(int estadoAlumno) {
        this.estadoAlumno = estadoAlumno;
    }
    
    //Creamos el toString
    @Override
    public String toString() {
        return codAlumno + ";" + nameAlumno + ";" + apellidoAlumno + ";" + correoAlumno + ";" + dniAlumno + ";" + codGenero + ";" + estadoAlumno;
    }
}
