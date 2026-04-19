/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author User
 */
public class RepositorioMySQL implements RepositorioPrincipal{
    private String URL = "jdbc:mysql://localhost:3306/bd_proyectofinal";
    private String usuario = "root";
    private String password = "jorge";
    
    public RepositorioMySQL(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Conexión MySQL exitosa!");
        }catch(Exception e){
            e.printStackTrace();
        }
    }   
    
    //USUARIO
    @Override
    public void agregarUsuario(Usuario x) {
        String sql = "INSERT INTO tbl_usuario(usuario,password,codVista,codProfesor) VALUES (?,?,?,?)";
        try{
            Connection connection = DriverManager.getConnection(URL, usuario, password);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,x.getUsuario());
            statement.setString(2,x.getPassword());
            statement.setInt(3,2);   
            statement.setInt(4,x.getCodProfesor()); 
            statement.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }       
    }
    
    @Override
    public Usuario buscarUsuario(String x) {     
        List<Usuario> lista = listarUsuario();
        List<Usuario> usuarioEncontrado = lista.stream().filter(p->x.equals(p.getUsuario())).collect(Collectors.toList());  
        if(usuarioEncontrado.isEmpty()){
            Usuario usu = new Usuario(-1,"","",-1,-1);
            List<Usuario> objetoBuscado1 = new ArrayList<>();
            objetoBuscado1.add(usu);
            return objetoBuscado1.get(0);  
        }else{
            return usuarioEncontrado.get(0);            
        }
    }

    @Override
    public List<Usuario> listarUsuario() {
                List<Usuario> listaUsuarios = new ArrayList<>();
        String sql = "select * from tbl_usuario";
        try{
            Connection connection = DriverManager.getConnection(URL, usuario, password);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            //a partir de resultSet creamos una lista de objetos de la clase Usuario
            while(resultSet.next()){
                int codUsu = resultSet.getInt("codUsuario");
                String usua = resultSet.getString("usuario");
                String pass = resultSet.getString("password");
                int codVista = resultSet.getInt("codVista");
                int codProfesor = resultSet.getInt("codProfesor");
                Usuario usuario = new Usuario(codUsu,usua,pass,codVista,codProfesor);
                listaUsuarios.add(usuario);
            }
            connection.close();
            statement.close();
            resultSet.close();            
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaUsuarios;
    }
    
    @Override
    public void actualizarUsuario(Usuario x){
        String sql = "UPDATE tbl_usuario SET usuario = ?,password = ? WHERE codProfesor = ?";
        try{
            Connection connection = DriverManager.getConnection(URL, usuario, password);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,x.getUsuario());
            statement.setString(2,x.getPassword());
            statement.setInt(3,x.getCodProfesor());   
            statement.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }        
    }
    
    @Override
    public Usuario buscarPorCodigoUsuario(int x){
        for(Usuario usu:listarUsuario()){
            if(usu.getCodProfesor()==x){
                return usu;
            }
        }
        return null;
    }
    
    //ALUMNO
    @Override
    public void agregarAlumno(Alumno x){
        String sql = "INSERT INTO tbl_alumno(nameAlumno,apellidoAlumno,correoAlumno,dniAlumno,codGenero,estadoAlumno) VALUES (?,?,?,?,?,?)";
        try{
            Connection connection = DriverManager.getConnection(URL, usuario, password);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,x.getNameAlumno());
            statement.setString(2,x.getApellidoAlumno());
            statement.setString(3,x.getCorreoAlumno());
            statement.setString(4,x.getDniAlumno());
            statement.setInt(5,x.getCodGenero());
            statement.setInt(6,x.getEstadoAlumno());
            statement.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }         
    }
    
    @Override
    public void actualizarAlumno(Alumno x,int x2){
        String sql = "UPDATE tbl_alumno SET nameAlumno = ?,apellidoAlumno = ?,correoAlumno = ?,dniAlumno = ?,codGenero = ? WHERE codAlumno = ?";
        try{
            Connection connection = DriverManager.getConnection(URL, usuario, password);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,x.getNameAlumno());
            statement.setString(2,x.getApellidoAlumno());
            statement.setString(3,x.getCorreoAlumno());   
            statement.setString(4,x.getDniAlumno()); 
            statement.setInt(5,x.getCodGenero()); 
            statement.setInt(6,x2);
            statement.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @Override
    public void eliminarAlumno(int x){
        String sql = "UPDATE tbl_alumno SET estadoAlumno = ? WHERE codAlumno = ?";
        try{
            Connection connection = DriverManager.getConnection(URL, usuario, password);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,0);
            statement.setInt(2,x);
            statement.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @Override
    public Alumno buscarPorCodigoAlumno(int x){      
        List<Alumno> alu = listarAlumno().stream().filter(p->p.getCodAlumno()==x).collect(Collectors.toList());
        if(!alu.isEmpty()){
            return listarAlumno().stream().filter(p->p.getCodAlumno()==x).collect(Collectors.toList()).get(0); 
        }else{
           return null;
        }    
    }
    
    @Override
    public Alumno buscarPorNombreAlumno(String x){
        List<Alumno> alu = listarAlumno().stream().filter(p->p.getNameAlumno().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
        if(!alu.isEmpty()){
            return listarAlumno().stream().filter(p->p.getNameAlumno().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList()).get(0);
        }else{
           return null;
        }
    }
    
    @Override
    public List<Alumno> listarAlumno(){
        List<Alumno> listaAlumnos = new ArrayList<>();
        String sql = "select * from tbl_alumno";
        try{
            Connection connection = DriverManager.getConnection(URL, usuario, password);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            //a partir de resultSet creamos una lista de objetos de la clase Pelicula
            while(resultSet.next()){
                int codigo = resultSet.getInt("codAlumno");
                String nombre = resultSet.getString("nameAlumno");
                String apellido = resultSet.getString("apellidoAlumno");
                String correo = resultSet.getString("correoAlumno");
                String dni = resultSet.getString("dniAlumno");
                int genero = resultSet.getInt("codGenero");
                int estado = resultSet.getInt("estadoAlumno");

                Alumno alumno = new Alumno(codigo,nombre,apellido,correo,dni,genero,estado);
                listaAlumnos.add(alumno);
            }
            connection.close();
            statement.close();
            resultSet.close();            
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaAlumnos;      
    }
    
    @Override
    public boolean mismoAlumno(String x){
        for(Alumno alum:listarAlumno()){
            if(alum.getDniAlumno().toLowerCase().equals(x.toLowerCase())){
               return true;
            }
        } 
        return false;
    }
            
    //PROFESOR
    @Override
    public void agregarProfesor(Profesor x) {
        String sql = "INSERT INTO tbl_profesor(nameProfesor,apellidoProfesor,telefonoProfesor,correoProfesor,dniProfesor,codCurso,estadoProfesor) VALUES (?,?,?,?,?,?,?)";
        try{
            Connection connection = DriverManager.getConnection(URL, usuario, password);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,x.getNameProfesor());
            statement.setString(2,x.getApellidoProfesor());
            statement.setString(3,x.getTelefonoProfesor());
            statement.setString(4,x.getCorreoProfesor());
            statement.setString(5,x.getDniProfesor());            
            statement.setInt(6,x.getCodCurso());
            statement.setInt(7,x.getEstadoProfesor());
            statement.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        } 
        Profesor prof = buscarPorNombreProfesor(x.getNameProfesor());
        String name = extraerNombre(x.getNameProfesor());
        String ape = extraerNombre(x.getApellidoProfesor());
        String usuario = name.toLowerCase() + "." + ape.toLowerCase();
        Usuario usu = new Usuario(usuario,x.getDniProfesor(),prof.getCodProfesor());
        this.agregarUsuario(usu);
        this.agregarUsuarioCSV(this.listarUsuario());
    }

    @Override
    public void actualizarProfesor(Profesor x, int x2) {
        String sql = "UPDATE tbl_profesor SET nameProfesor=?,apellidoProfesor=?,telefonoProfesor=?,correoProfesor=?,dniProfesor=?,codCurso=? WHERE codProfesor = ?";
        try{
            Connection connection = DriverManager.getConnection(URL, usuario, password);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,x.getNameProfesor());
            statement.setString(2,x.getApellidoProfesor());
            statement.setString(3,x.getTelefonoProfesor());   
            statement.setString(4,x.getCorreoProfesor());
            statement.setString(5,x.getDniProfesor());
            statement.setInt(6,x.getCodCurso()); 
            statement.setInt(7,x2);
            statement.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }    
        Profesor prof = buscarPorNombreProfesor(x.getNameProfesor());
        String name = extraerNombre(x.getNameProfesor());
        String ape = extraerNombre(x.getApellidoProfesor());
        String usuario = name.toLowerCase() + "." + ape.toLowerCase();
        Usuario usu = new Usuario(usuario,x.getDniProfesor(),prof.getCodProfesor());
        this.actualizarUsuario(usu);
        this.agregarUsuarioCSV(this.listarUsuario());        
    }

    @Override
    public void eliminarProfesor(int x) {
        String sql = "UPDATE tbl_profesor SET estadoProfesor = ? WHERE codProfesor = ?";
        try{
            Connection connection = DriverManager.getConnection(URL, usuario, password);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,0);
            statement.setInt(2,x);
            statement.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Profesor buscarPorNombreProfesor(String x) {
        List<Profesor> profe = listarProfesor().stream().filter(p->p.getNameProfesor().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList());
        if(!profe.isEmpty()){
            return listarProfesor().stream().filter(p->p.getNameProfesor().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList()).get(0);
        }else{
           return null;
        }       
    }

    @Override
    public Profesor buscarPorCodigoProfesor(int x) {
        List<Profesor> resultado = listarProfesor().stream()
            .filter(p -> p.getCodProfesor() == x)
            .collect(Collectors.toList());

        if(!resultado.isEmpty()){
            return resultado.get(0);
        } else {
            return null;
        }
    }
    
    @Override
    public Profesor buscarPorCorreoYDni(String correo, String dni) {
        List<Profesor> resultado = listarProfesor().stream()
            .filter(p -> p.getCorreoProfesor().equalsIgnoreCase(correo) && 
                         p.getDniProfesor().equals(dni))
            .collect(Collectors.toList());

        if(!resultado.isEmpty()){
            return resultado.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<Profesor> listarProfesor() {
        List<Profesor> listaProfesor = new ArrayList<>();
        String sql = "select * from tbl_profesor";
        try{
            Connection connection = DriverManager.getConnection(URL, usuario, password);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            //a partir de resultSet creamos una lista de objetos de la clase Pelicula
            while(resultSet.next()){
                int codigo = resultSet.getInt("codProfesor");
                String nombre = resultSet.getString("nameProfesor");
                String apellido = resultSet.getString("apellidoProfesor");
                String telefono = resultSet.getString("telefonoProfesor");
                String correo = resultSet.getString("correoProfesor");
                String dni = resultSet.getString("dniProfesor");
                int espe = resultSet.getInt("codCurso");
                int estado = resultSet.getInt("estadoProfesor");
                Profesor profe = new Profesor(codigo,nombre,apellido,telefono,correo,dni,espe,estado);
                listaProfesor.add(profe);
            }
            connection.close();
            statement.close();
            resultSet.close();            
        }catch(Exception e){
            e.printStackTrace();
        }
        return listaProfesor;
    }

    @Override
    public boolean mismoProfesor(String x){
        for(Profesor profe:listarProfesor()){
            if(profe.getDniProfesor().toLowerCase().equals(x.toLowerCase())){
               return true;
            }
        } 
        return false;
    }
    
    // MATRICULA
    @Override
    public void agregarMatricula(Matricula x) {
        String sql = "INSERT INTO tbl_matricula(codAlumno,codProfesor,cursoMatricula,turnoEstudio,gradoEscolar,observacion,estadoMatricula) VALUES (?,?,?,?,?,?,?)";
        try{
            Connection connection = DriverManager.getConnection(URL, usuario, password);
            PreparedStatement statement = connection.prepareStatement(sql);         
            statement.setInt(1,x.getCodAlumno());
            statement.setInt(2,x.getCodProfesor());
            statement.setInt(3,x.getCodCurso());
            statement.setString(4,x.getTurnoEstudio());
            statement.setString(5,x.getGradoEscolar());
            statement.setString(6,x.getObsevacion());
            statement.setInt(7,x.getEstadoMatricula());
            statement.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void actualizarMatricula(Matricula x, int x2) {
        String sql = "UPDATE tbl_matricula SET codProfesor=?,cursoMatricula=?,turnoEstudio=?,gradoEscolar=? WHERE codMatricula = ?";
        //"UPDATE tbl_profesor SET nombreProfesor=?,apellidoProfesor=?,telefonoProfesor=?,correoProfesor=?,codigoEspecial=? WHERE codigoProfesor = ?"
        try{
            Connection connection = DriverManager.getConnection(URL, usuario, password);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,x.getCodProfesor());
            statement.setInt(2,x.getCodCurso());
            statement.setString(3,x.getTurnoEstudio());   
            statement.setString(4,x.getGradoEscolar());
            statement.setInt(5,x2);
            statement.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarMatricula(int x) {
        String sql = "UPDATE tbl_matricula SET estadoMatricula=? WHERE codMatricula = ?";
        //"UPDATE tbl_profesor SET nombreProfesor=?,apellidoProfesor=?,telefonoProfesor=?,correoProfesor=?,codigoEspecial=? WHERE codigoProfesor = ?"
        try{
            Connection connection = DriverManager.getConnection(URL, usuario, password);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,0);
            statement.setInt(2,x);
            statement.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Matricula buscarPorCodigoMatricula(int x,int y) {
        List<Matricula> matri = listarMatricula().stream().filter(p->p.getCodAlumno()==x && p.getCodCurso()==y).collect(Collectors.toList());
        if(!matri.isEmpty()){
            return listarMatricula().stream().filter(p->p.getCodAlumno()==x && p.getCodCurso()==y).collect(Collectors.toList()).get(0);
        }else{
            return null;
        }
    }

    @Override
    public List<Matricula> listarMatricula() {
        List<Matricula> listaMatricula = new ArrayList<>();
        String sql = "SELECT * FROM tbl_matricula";
        try{
            Connection connection = DriverManager.getConnection(URL,usuario,password);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            //a partir de resultSet creamos una lista de objetos de la clase Pelicula
            while(resultSet.next()){
                int codigo = resultSet.getInt("codMatricula");
                int codigoAlum = resultSet.getInt("codAlumno");
                int codigoProfe = resultSet.getInt("codProfesor");
                int curso = resultSet.getInt("cursoMatricula");
                String turno = resultSet.getString("turnoEstudio");
                String grado = resultSet.getString("gradoEscolar");
                String obs = resultSet.getString("observacion");
                int estado = resultSet.getInt("estadoMatricula");
                Matricula matri = new Matricula(codigo,codigoAlum,codigoProfe,curso,turno,grado,obs,estado);
                listaMatricula.add(matri);
            }
            connection.close();
            statement.close();
            resultSet.close();            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return listaMatricula;
    }

    @Override
    public List<Genero> listarGenero() {
        List<Genero> listaGenero = new ArrayList<>();
        String sql = "SELECT * FROM tbl_genero";
        try{
            Connection connection = DriverManager.getConnection(URL,usuario,password);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            //a partir de resultSet creamos una lista de objetos de la clase Pelicula
            while(resultSet.next()){
                int codigo = resultSet.getInt("codGenero");
                String nombre = resultSet.getString("nameGenero");
                Genero genero = new Genero(codigo,nombre);
                listaGenero.add(genero);
            }
            connection.close();
            statement.close();
            resultSet.close();            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return listaGenero;
    }

    @Override
    public String obtenerGenero(Alumno x) {
        return listarGenero().stream().filter(p->p.getCodGenero()==x.getCodGenero()).collect(Collectors.toList()).get(0).getNameGenero();
    }

    @Override
    public List<Curso> listarCurso() {
        List<Curso> listaCurso = new ArrayList<>();
        String sql = "SELECT * FROM tbl_curso";
        try{
            Connection connection = DriverManager.getConnection(URL,usuario,password);
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            //a partir de resultSet creamos una lista de objetos de la clase Curso
            while(resultSet.next()){
                int codigo = resultSet.getInt("codCurso");
                String nombre = resultSet.getString("nameCurso");
                Curso curso = new Curso(codigo,nombre);
                listaCurso.add(curso);                
            }
            connection.close();
            statement.close();
            resultSet.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return listaCurso;
    }
    
    @Override
    public String obtenerCurso(Profesor x) {
        return listarCurso().stream().filter(p->p.getCodCurso()==x.getCodCurso()).collect(Collectors.toList()).get(0).getNameCurso();
    }
    
    @Override
    public Curso buscarPorNombreCurso(String x) {
        return listarCurso().stream().filter(p->p.getNameCurso().toLowerCase().contains(x.toLowerCase())).collect(Collectors.toList()).get(0);
    }

    @Override
    public Curso buscarPorCodigoCurso(int x){
        return listarCurso().stream().filter(p->p.getCodCurso()==x).collect(Collectors.toList()).get(0);
    }
    
    @Override
    public String extraerNombre(String x){
        int indiceEspacio = x.indexOf(" ");
        if (indiceEspacio != -1) {
            return x.substring(0, indiceEspacio);
        } else {
            return x; // No hay espacios en el string
        }
    } 
    
    @Override
    public void agregarObservacion(Matricula x,int y){
        String sql = "UPDATE tbl_matricula SET observacion=? WHERE codMatricula = ?";
        //"UPDATE tbl_profesor SET nombreProfesor=?,apellidoProfesor=?,telefonoProfesor=?,correoProfesor=?,codigoEspecial=? WHERE codigoProfesor = ?"
        try{
            Connection connection = DriverManager.getConnection(URL, usuario, password);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,x.getObsevacion());
            statement.setInt(2,y);
            statement.executeUpdate();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //ARCHIVO CSV

    @Override
    public void agregarUsuarioCSV(List<Usuario> x) {
        // agrega/graba información a mi archivo CSV
        File archivo = new File("Usuario.csv");   
        
        try(FileWriter writer = new FileWriter("Usuario.csv")){
            //Comprobamos si el archivo CSV esta lleno para poner nuestra fila de propiedades 
            if(archivo.exists() && archivo.length() == 0){
            writer.write("Codigo;Usuario;Password;Codigo Vista;Codigo Profesor\n");
            }
            for(Usuario nom:x){
                writer.write(nom.toString()); //escribimos en el archivo nuestro toString de la clase Personaje
                writer.write("\n"); //agrega una línea nueva después del dato que se ha insertado
            }
            writer.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void agregarAlumnoCSV(List<Alumno> x) {
        // agrega/graba información a mi archivo CSV
        File archivo = new File("Alumno.csv");   
        
        try(FileWriter writer = new FileWriter("Alumno.csv")){
            //Comprobamos si el archivo CSV esta lleno para poner nuestra fila de propiedades 
            if(archivo.exists() && archivo.length() == 0){
            writer.write("Codigo;Nombre;Apellido;Correo;Dni;Codigo Genero;Estado Alumno\n");    
            }
            for(Alumno nom:x){
                writer.write(nom.toString()); //escribimos en el archivo nuestro toString de la clase Personaje
                writer.write("\n"); //agrega una línea nueva después del dato que se ha insertado
            }
            writer.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void agregarProfesorCSV(List<Profesor> x) {
        // agrega/graba información a mi archivo CSV
        File archivo = new File("Profesor.csv");   
        
        try(FileWriter writer = new FileWriter("Profesor.csv")){
            //Comprobamos si el archivo CSV esta lleno para poner nuestra fila de propiedades 
            if(archivo.exists() && archivo.length() == 0){
            writer.write("Codigo;Nombre;Apellido;Telefono;Correo;Dni;Codigo Curso;Estado Profesor\n");  
            }
            for(Profesor nom:x){
                writer.write(nom.toString()); //escribimos en el archivo nuestro toString de la clase Personaje
                writer.write("\n"); //agrega una línea nueva después del dato que se ha insertado
            }
            writer.close();
        }catch(Exception e){
            e.printStackTrace();
        }        
    }

    @Override
    public void agregarMatriculaCSV(List<Matricula> x) {
        // agrega/graba información a mi archivo CSV
        File archivo = new File("Matricula.csv");   
        
        try(FileWriter writer = new FileWriter("Matricula.csv")){
            //Comprobamos si el archivo CSV esta lleno para poner nuestra fila de propiedades 
            if(archivo.exists() && archivo.length() == 0){
            writer.write("Codigo;Codigo Alumno;Codigo Profesor;Codigo Curso;Turno Estudio;Grado Escolar;Observacion;Estado Matricula\n");
            }
            for(Matricula nom:x){
                writer.write(nom.toString()); //escribimos en el archivo nuestro toString de la clase Personaje
                writer.write("\n"); //agrega una línea nueva después del dato que se ha insertado
            }
            writer.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void agregarMatriculaActuCSV(Matricula x) {
        // agrega/graba información a mi archivo CSV
        File archivo = new File("MatriculaActu.csv");   
        
        try(FileWriter writer = new FileWriter("MatriculaActu.csv")){
            //Comprobamos si el archivo CSV esta lleno para poner nuestra fila de propiedades 
            if(archivo.exists() && archivo.length() == 0){
            writer.write("Codigo;Codigo Alumno;Codigo Profesor;Codigo Curso;Turno Estudio;Grado Escolar;Observacion;Estado Matricula\n");
            }
            writer.write(x.toString()); //escribimos en el archivo nuestro toString de la clase Personaje
            writer.write("\n"); //agrega una línea nueva después del dato que se ha insertado
            writer.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void notificarModificacion(String x) {
        // Ruta completa del archivo que se va a monitorear
        String ruta = "C:\\Users\\User\\OneDrive - Universidad San Ignacio de Loyola\\Documents\\NetBeansProjects\\ProyectoFinal_POO2\\"+x;
        
        Path directorio = Paths.get(ruta).getParent(); //getParent() devuelve el directorio en el que se encuenta el archivo
        
        try{
            //Vamos a crear un WatchService  para monitorear cambios en el sistema de archivos (FileSystems)
            WatchService watchService = FileSystems.getDefault().newWatchService();
            //Registra el directorio con el watchService para moniotorear eventos de modificacion de archivos
            directorio.register(watchService,StandardWatchEventKinds.ENTRY_MODIFY);
            //Crear un observable que emite el contenido al archivo cada vez que se modifica 
            Observable<String> archivoObservable = Observable.create(emisor->{
                // Mientras el observable permanezca activa
                while(!emisor.isDisposed()){
                    // Tomar el siguiente evento de modificacion de archivo
                    WatchKey watchKey = watchService.take(); // take() decuelve el ultimo elemento que se ha observado
                    // Para cada evento de modificacion de archivo 
                    for(WatchEvent<?> evento : watchKey.pollEvents()){
                        // Obtenemos la ruta del archivo modificado
                        Path rutaActualizada = (Path) evento.context();
                        // Verificamos si el archivo modificado es el que me interesa
                        if(rutaActualizada.endsWith(Paths.get(ruta).getFileName())){
                            // Leer el contenido del archivo y emitirlo
                            String contenidoArchivo = leerArchivo(ruta);
                            emisor.onNext(contenidoArchivo);
                        }
                    }
                    // Reseteamos el watchKey para recibir mas eventos
                    watchKey.reset();
                }
            });
            // Suscribirse al observable cada vez que este se actualiza
            archivoObservable.subscribeOn(Schedulers.io())
                    .subscribe(contenidoArchivo->System.out.println("Un contenido del archivo matriculaCSV actualziado: \n"+contenidoArchivo));

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private String leerArchivo(String ruta) throws Exception{
        StringBuilder contenidoBuilder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(ruta));
        // Leer cada linea del archivo y agregarla al StringBuilder
        String linea;
        while((linea = reader.readLine())!=null){
            contenidoBuilder.append(linea).append("\n");
        }
        reader.close();
        return contenidoBuilder.toString();
    }
    
}
