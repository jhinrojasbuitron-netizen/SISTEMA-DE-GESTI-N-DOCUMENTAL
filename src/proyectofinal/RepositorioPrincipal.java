/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package proyectofinal;

import java.util.List;

/**
 *
 * @author User
 */
public interface RepositorioPrincipal {
    //USUARIO MYSQL
    void agregarUsuario(Usuario x);
    void actualizarUsuario(Usuario x);
    Usuario buscarUsuario(String x);
    List<Usuario> listarUsuario(); 
    Usuario buscarPorCodigoUsuario(int x);
    
    //ALUMNO MYSQL
    void agregarAlumno(Alumno x);
    void actualizarAlumno(Alumno x,int x2);
    void eliminarAlumno(int x);
    Alumno buscarPorNombreAlumno(String x);
    Alumno buscarPorCodigoAlumno(int x);
    List<Alumno> listarAlumno();
    boolean mismoAlumno(String x);

    //PROFESOR MYSQL
    void agregarProfesor(Profesor x);
    void actualizarProfesor(Profesor x,int x2);
    void eliminarProfesor(int x);
    Profesor buscarPorNombreProfesor(String x);
    Profesor buscarPorCodigoProfesor(int x);
    List<Profesor> listarProfesor();
    boolean mismoProfesor(String x);
    Profesor buscarPorCorreoYDni(String x, String y);
    
    //MATRICULA MYSQL
    void agregarMatricula(Matricula x);
    void actualizarMatricula(Matricula x,int x2);
    void eliminarMatricula(int x);
    Matricula buscarPorCodigoMatricula(int x,int y);
    List<Matricula> listarMatricula();    
    
    //GENERO,OBSERVACION Y ESPECIALIDAD MYSQL
    List<Genero> listarGenero();
    String obtenerGenero(Alumno x);
    List<Curso> listarCurso();
    String obtenerCurso(Profesor x);
    Curso buscarPorNombreCurso(String x);
    Curso buscarPorCodigoCurso(int x);
    String extraerNombre(String x);
    void agregarObservacion(Matricula x,int y);
    
    //ARCHIVO CSV
    void agregarUsuarioCSV(List<Usuario> x);
    void agregarAlumnoCSV(List<Alumno> x);
    void agregarProfesorCSV(List<Profesor> x);
    void agregarMatriculaCSV(List<Matricula> x);
    void agregarMatriculaActuCSV(Matricula x);
}
