/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal;

/**
 *
 * @author User
 */
public class Genero {
    private int codGenero;
    private String nameGenero;

    //Creamos el constructor necesarios para el listar
    public Genero(int codGenero, String nameGenero) {
        this.codGenero = codGenero;
        this.nameGenero = nameGenero;
    }

    public int getCodGenero() {
        return codGenero;
    }

    public void setCodGenero(int codGenero) {
        this.codGenero = codGenero;
    }

    public String getNameGenero() {
        return nameGenero;
    }

    public void setNameGenero(String nameGenero) {
        this.nameGenero = nameGenero;
    }
    
}
