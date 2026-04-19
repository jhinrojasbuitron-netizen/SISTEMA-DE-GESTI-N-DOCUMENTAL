/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal;

/**
 *
 * @author User
 */
public class Usuario {
    private int codUsuario;
    private String usuario;
    private String password;
    private int codVista;
    private int codProfesor;

    //Creamos los costructores para listar, buscar y actualizar
    public Usuario(int codUsuario, String usuario, String password, int codVista, int codProfesor) {
        this.codUsuario = codUsuario;
        this.usuario = usuario;
        this.password = password;
        this.codVista = codVista;
        this.codProfesor = codProfesor;
    }

    //Creamos los Getters y Setters
    public Usuario(String usuario, String password, int codProfesor) {
        this.usuario = usuario;
        this.password = password;
        this.codProfesor = codProfesor;
    }

    public int getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCodVista() {
        return codVista;
    }

    public void setCodVista(int codVista) {
        this.codVista = codVista;
    }

    public int getCodProfesor() {
        return codProfesor;
    }

    public void setCodProfesor(int codProfesor) {
        this.codProfesor = codProfesor;
    }

    //Creamos el toString    
    @Override
    public String toString() {
        return codUsuario + ";" + usuario + ";" + password + ";" + codVista + ";" + codProfesor;
    }
    
}
