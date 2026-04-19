/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal;

/**
 *
 * @author User
 */
public class Profesor {
    private int codProfesor;
    private String nameProfesor;
    private String apellidoProfesor;
    private String telefonoProfesor;
    private String correoProfesor;
    private String dniProfesor;
    private int codCurso;
    private int estadoProfesor;

    //Creamos los costructores para listar, buscar, agregar y actualizar
    public Profesor(String nameProfesor, String apellidoProfesor, String telefonoProfesor, String correoProfesor, String dniProfesor, int codCurso) {
        this.nameProfesor = nameProfesor;
        this.apellidoProfesor = apellidoProfesor;
        this.telefonoProfesor = telefonoProfesor;
        this.correoProfesor = correoProfesor;
        this.dniProfesor = dniProfesor;
        this.codCurso = codCurso;
        this.estadoProfesor = 1;
    }

    public Profesor(int codProfesor, String nameProfesor, String apellidoProfesor, String telefonoProfesor, String correoProfesor, String dniProfesor, int codCurso, int estadoProfesor) {
        this.codProfesor = codProfesor;
        this.nameProfesor = nameProfesor;
        this.apellidoProfesor = apellidoProfesor;
        this.telefonoProfesor = telefonoProfesor;
        this.correoProfesor = correoProfesor;
        this.dniProfesor = dniProfesor;
        this.codCurso = codCurso;
        this.estadoProfesor = estadoProfesor;
    }

    //Creamos los Getters y Setters
    public int getCodProfesor() {
        return codProfesor;
    }

    public void setCodProfesor(int codProfesor) {
        this.codProfesor = codProfesor;
    }

    public String getNameProfesor() {
        return nameProfesor;
    }

    public void setNameProfesor(String nameProfesor) {
        this.nameProfesor = nameProfesor;
    }

    public String getApellidoProfesor() {
        return apellidoProfesor;
    }

    public void setApellidoProfesor(String apellidoProfesor) {
        this.apellidoProfesor = apellidoProfesor;
    }

    public String getTelefonoProfesor() {
        return telefonoProfesor;
    }

    public void setTelefonoProfesor(String telefonoProfesor) {
        this.telefonoProfesor = telefonoProfesor;
    }

    public String getCorreoProfesor() {
        return correoProfesor;
    }

    public void setCorreoProfesor(String correoProfesor) {
        this.correoProfesor = correoProfesor;
    }

    public String getDniProfesor() {
        return dniProfesor;
    }

    public void setDniProfesor(String dniProfesor) {
        this.dniProfesor = dniProfesor;
    }

    public int getCodCurso() {
        return codCurso;
    }

    public void setCodCurso(int codCurso) {
        this.codCurso = codCurso;
    }

    public int getEstadoProfesor() {
        return estadoProfesor;
    }

    public void setEstadoProfesor(int estadoProfesor) {
        this.estadoProfesor = estadoProfesor;
    }

    //Creamos el toString 
    @Override
    public String toString() {
        return codProfesor + ";" + nameProfesor + ";" + apellidoProfesor + ";" + telefonoProfesor + ";" + correoProfesor + ";" + dniProfesor + ";" + codCurso + ";" + estadoProfesor;
    }
       
}
