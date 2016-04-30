package id3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Felipe
 */
public class Conexion {

    private Connection conexion = null;
    private String subname;
    private String usuario;
    private String contrasena;

    public Conexion() {
    }

    public Connection getConexion() {
        if (conexion != null) {
            this.desconectar();
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql:" + subname, usuario, contrasena);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return conexion;
    }

    public void desconectar() {
        try {
            conexion.close();
            conexion = null;
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getSubname() {
        return subname;
    }

    public void setSubname(String subname) {
        this.subname = subname;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
}
