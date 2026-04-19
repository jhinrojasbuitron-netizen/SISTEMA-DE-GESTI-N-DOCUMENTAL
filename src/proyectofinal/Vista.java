/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal;

/**
 *
 * @author User
 */
public class Vista {
    private int codVista;
    private String nameVista;

    //Creamos el constructor necesarios para el listar   
    public Vista(int codVista, String nameVista) {
        this.codVista = codVista;
        this.nameVista = nameVista;
    }

    public int getCodVista() {
        return codVista;
    }

    public void setCodVista(int codVista) {
        this.codVista = codVista;
    }

    public String getNameVista() {
        return nameVista;
    }

    public void setNameVista(String nameVista) {
        this.nameVista = nameVista;
    }
    
}
