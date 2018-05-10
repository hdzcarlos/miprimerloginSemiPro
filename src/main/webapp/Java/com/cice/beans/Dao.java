package com.cice.beans;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase va a tener la logica de la conexion con la base de datos Dao ( Data Access Object)
 */
public class Dao {
    private  Connection connection;
    private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:8889/usuarios?useServerPrepStmts=true";
    private  String userDB = "root";
    private  String passDB = "root";
    private PreparedStatement preparedStatement = null;
    private ResultSet rs = null;

    public Dao() {
    }

    /**
     * Este metodo se encarga de conectarnos con la base de datos
     */
    public void conectar() {
        try {
            Class.forName(this.driver);
            connection = DriverManager.getConnection(url, userDB, passDB);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**Este metodo nos va a desconectar de la base de datos
     */
    public void desconectar() throws SQLException {
        if(connection != null){connection.close();}
        if(preparedStatement != null){preparedStatement.close();}
        if (rs != null){rs.close();}
    }

    /**
     * Metodo que se va a encargar de realizar las consultas
     * Si un email y contraseñas pertenecen a una cuenta registrada
     */
    public boolean isCuentaRegistrada(String email, String password) throws SQLException {
        preparedStatement.setString(2,email);
        preparedStatement.setString(3,password);
        ResultSet rs = preparedStatement.executeQuery();
        return rs.next();
    }

    /**
     * Metodo que comprobara si el email recidibido ya esta registrado.
     * @param email
     * @return
     */
    public boolean isEmailRegistrado(String email) throws SQLException {
        preparedStatement.setString(2,email);
        ResultSet rs = preparedStatement.executeQuery();
        return rs.next();
    }
    public void registroUsuarios(String email, String password, String name) throws SQLException {
        conectar();
        preparedStatement =  connection.prepareStatement("INSERT INTO usuarios VALUES (null,?,?,?");
            preparedStatement.setString(2,"email");
            preparedStatement.setString(3,"password");
            preparedStatement.setString(4,"name");
            preparedStatement.executeUpdate();
        }
    public DaoUser buscarUsuarios(int id) throws SQLException {
        conectar();
        ResultSet rs = null;
        try {
            preparedStatement = connection.prepareStatement("SELECT id,nombre FROM usuarios WHERE id =?");
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return (DaoUser.load(rs));
            }
        }finally {
            if(rs != null){
                rs.close();
            }
            if (preparedStatement != null){
                preparedStatement.close();
            }
        }
        return null;
    }
    public boolean eliminarUsuario(int id) throws SQLException {
        boolean sw = false;
        if(this.buscarUsuarios(id) == null)throw new SQLException("Registro de usuario identificado con "+ id+ " , no encontrado");
        try{
            preparedStatement = connection.prepareStatement("DELETE FROM usuarios WHERE id =? ");
            preparedStatement.setInt(1,id);
            int r= preparedStatement.executeUpdate();
            if(r!=0) sw=true;
        }finally { if(preparedStatement!=null)preparedStatement.close(); }
        return sw;
    }
    public ArrayList listaUsuarios()throws SQLException {
        ArrayList lista = new ArrayList();
        try {
            preparedStatement = connection.prepareStatement("SELECT id,email,password,name");
            rs = preparedStatement.executeQuery();
            while (rs.next()){
                lista.add(DaoUser.load(rs));
            }
        }finally {
            if(rs != null){rs.close();}
            if(preparedStatement != null){preparedStatement.close();}
        }
        return lista;
    }

    public Connection getConnection() {
        return connection;
    }
}
