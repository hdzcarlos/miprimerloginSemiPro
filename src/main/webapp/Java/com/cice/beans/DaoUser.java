package com.cice.beans;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoUser extends Dao implements Serializable {
    private int id = 0;
    private String nombre = "";
    private String email = "";
    private String password = "";

    public DaoUser() {
        super();
    }

    public DaoUser(DaoUser d) {
        this.id = d.getId();
        this.nombre = d.getNombre();
        this.email = d.getEmail();
        this.password = d.getPassword();

    }

    public DaoUser(String nombre, String email, String password) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombrePorEmail(String email){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            preparedStatement.setString(2,"email");
            preparedStatement = connection.prepareStatement(email);
            rs = preparedStatement.executeQuery();
            if(rs.next()){
                return rs.getString("name");
            }
            desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public static DaoUser load(ResultSet rs) {
        DaoUser daoUser = new DaoUser();
        try {
            daoUser.setId(rs.getInt("id"));
            daoUser.setEmail(rs.getString("email"));
            daoUser.setPassword(rs.getString("password"));
            daoUser.setNombre(rs.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
            return daoUser;


    }
}

