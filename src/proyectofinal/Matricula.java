/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal;

/**
 *
 * @author User
 */
public class Matricula {
    private int codMatricula;
    private int codAlumno;
    private int codProfesor;
    private int codCurso;
    private String turnoEstudio;
    private String gradoEscolar;
    private String observacion;
    private int estadoMatricula;

    //Creamos los costructores para listar, buscar, agregar y actualizar
    public Matricula(int codAlumno, int codProfesor, int codCurso, String turnoEstudio, String gradoEscolar,String observacion) {
        this.codAlumno = codAlumno;
        this.codProfesor = codProfesor;
        this.codCurso = codCurso;
        this.turnoEstudio = turnoEstudio;
        this.gradoEscolar = gradoEscolar;
        this.observacion = observacion;
        this.estadoMatricula = 1;
    }

    public Matricula(int codMatricula, int codAlumno, int codProfesor, int codCurso, String turnoEstudio, String gradoEscolar,String observacion, int estadoMatricula) {
        this.codMatricula = codMatricula;
        this.codAlumno = codAlumno;
        this.codProfesor = codProfesor;
        this.codCurso = codCurso;
        this.turnoEstudio = turnoEstudio;
        this.gradoEscolar = gradoEscolar;
        this.observacion = observacion;
        this.estadoMatricula = estadoMatricula;
    }

    //Creamos los Getters y Setters
    public int getCodMatricula() {
        return codMatricula;
    }

    public void setCodMatricula(int codMatricula) {
        this.codMatricula = codMatricula;
    }

    public int getCodAlumno() {
        return codAlumno;
    }

    public void setCodAlumno(int codAlumno) {
        this.codAlumno = codAlumno;
    }

    public int getCodProfesor() {
        return codProfesor;
    }

    public void setCodProfesor(int codProfesor) {
        this.codProfesor = codProfesor;
    }

    public int getCodCurso() {
        return codCurso;
    }

    public void setCodCurso(int codCurso) {
        this.codCurso = codCurso;
    }

    public String getTurnoEstudio() {
        return turnoEstudio;
    }

    public void setTurnoEstudio(String turnoEstudio) {
        this.turnoEstudio = turnoEstudio;
    }

    public String getGradoEscolar() {
        return gradoEscolar;
    }

    public void setGradoEscolar(String gradoEscolar) {
        this.gradoEscolar = gradoEscolar;
    }

    public int getEstadoMatricula() {
        return estadoMatricula;
    }

    public void setEstadoMatricula(int estadoMatricula) {
        this.estadoMatricula = estadoMatricula;
    }

    public String getObsevacion() {
        return observacion;
    }

    public void setObsevacion(String obsevacion) {
        this.observacion = obsevacion;
    }

    //Creamos el toString 
    @Override
    public String toString() {
        return codMatricula + ";" + codAlumno + ";" + codProfesor + ";" + codCurso + ";" + turnoEstudio + ";" + gradoEscolar + ";" + observacion +";" + estadoMatricula;
    }
    
}