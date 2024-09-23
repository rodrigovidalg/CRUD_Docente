/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author user
 */
public class Docente {
    private int codigo;
    private String nit, nombres, apellidos, direccion, telefono, fecha_nacimiento, salario, fil, fir;
    Conexion cn;
    
    public Docente(){}

    public Docente(int codigo, String nit, String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento, String salario, String fil, String fir) {
        this.codigo = codigo;
        this.nit = nit;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fecha_nacimiento = fecha_nacimiento;
        this.salario = salario;
        this.fil = fil;
        this.fir = fir;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getFil() {
        return fil;
    }

    public void setFil(String fil) {
        this.fil = fil;
    }

    public String getFir() {
        return fir;
    }

    public void setFir(String fir) {
        this.fir = fir;
    }
    
   
    
    
    public DefaultTableModel leer(){
        DefaultTableModel tabla = new DefaultTableModel();
        try{
            cn= new Conexion();
            cn.abrir_conexion();
            String query;
            query = "SELECT codigo as codigo, nit, nombres, apellidos, direccion, telefono, fecha_nacimiento, salario, fil, fir FROM docentes;";
            ResultSet consulta = cn.conexionDB.createStatement().executeQuery(query);
            
            String encabezado[] = {"codigo","Nit","Nombres","Apellidos","Direccion","Telefono","Nacimiento" , "Salario", "Ingreso Laboral", "Ingreso Registro"};
            tabla.setColumnIdentifiers(encabezado);
            
            String datos[]=new String[10];
            
            while(consulta.next()){
                datos[0] = consulta.getString("codigo");
                datos[1] = consulta.getString("nit");
                datos[2] = consulta.getString("nombres");
                datos[3] = consulta.getString("apellidos");
                datos[4] = consulta.getString("direccion");
                datos[5] = consulta.getString("telefono");
                datos[6] = consulta.getString("fecha_nacimiento");
                datos[7] = consulta.getString("salario");
                datos[8] = consulta.getString("fil");
                datos[9] = consulta.getString("fir");
                
                tabla.addRow(datos);
            }
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println("Error: "+ ex.getMessage());
        }
        return tabla;
    }
    public void agregar() {
    try {
        PreparedStatement parametro;
        Conexion cn = new Conexion();
        cn.abrir_conexion();
        String query = "INSERT INTO docentes ( nit, nombres, apellidos, direccion, telefono, fecha_nacimiento, salario, fil, fir) VALUES (?,?,?,?,?,?,?,?,?);";
        parametro = cn.conexionDB.prepareStatement(query);
        parametro.setString(1, this.getNit());
        parametro.setString(2, this.getNombres());
        parametro.setString(3, this.getApellidos());
        parametro.setString(4, this.getDireccion());
        parametro.setString(5, this.getTelefono());
        parametro.setString(6, this.getFecha_nacimiento());
        parametro.setString(7, this.getSalario());
        parametro.setString(8, this.getFil());
        parametro.setString(9, this.getFir());
        
        
        int executer = parametro.executeUpdate();
        System.out.println("Inserto exitoso");
        cn.cerrar_conexion();
    } catch (SQLException ex) {
        System.out.println("Algo salió mal: " + ex.getMessage());   
    }
    }
    public void actualizar(){
        try{
           PreparedStatement parametro;
           cn = new Conexion();
           cn.abrir_conexion();
           String query = "UPDATE docentes SET nit = ?, nombres = ?, apellidos = ?, direccion = ?, telefono = ?, fecha_nacimiento = ?, salario = ?, fil = ?, fir = ? WHERE codigo = ?;";
           parametro = (PreparedStatement) cn.conexionDB.prepareStatement(query);
           parametro.setString(1, getNit());
           parametro.setString(2, getNombres());
           parametro.setString(3, getApellidos());
           parametro.setString(4, getDireccion());
           parametro.setString(5, getTelefono());
           parametro.setString(6, getFecha_nacimiento());
           parametro.setString(7, getSalario());
           parametro.setString(8, getFil());
           parametro.setString(9, getFir());
           parametro.setInt(10, this.getCodigo());
           
           int executer = parametro.executeUpdate();
           System.out.println("Modificacion exitosa");
           cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println("Error al actualizar" + ex.getMessage());
        }
    }
    
    public void eliminar(){
        try{
           PreparedStatement parametro;
           cn = new Conexion();
           cn.abrir_conexion();
           String query = "DELETE FROM docentes WHERE codigo = ?;";
           parametro = (PreparedStatement) cn.conexionDB.prepareStatement(query);
           parametro.setInt(1, this.getCodigo());
           int executer = parametro.executeUpdate();
           System.out.println("Eliminacion exitosa");
           cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println("Error al eliminar" + ex.getMessage());
        }
    }
    
}
