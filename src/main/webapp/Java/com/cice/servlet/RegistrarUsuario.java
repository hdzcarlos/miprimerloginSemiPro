package com.cice.servlet;
import com.cice.beans.Dao;
import com.cice.beans.DaoUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/registrarUsuario")
public class RegistrarUsuario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession respuesta = req.getSession();
        PrintWriter printWriter = resp.getWriter();

        String nombreUsuario = req.getParameter("name");
        String emailUsuario = req.getParameter("email");
        String passwordUsuario = req.getParameter("password");
        Pattern p = null;
        Matcher m = p.matcher(emailUsuario);
        Dao dao = new Dao();
        if(nombreUsuario.isEmpty() || emailUsuario.isEmpty() || passwordUsuario.isEmpty()){
            respuesta.setAttribute("Error","La direccion de email es incorrecta");
        }else {
            String s = "Welcome";
            System.out.println(s);
        }
        dao.conectar();
        try {
            ResultSet rs;
            DaoUser daoUser = new DaoUser();

            if(dao.isEmailRegistrado(emailUsuario)){
                respuesta.setAttribute("Error", "Esta dirección ya fue registrada");
            }else{
               dao.registroUsuarios(emailUsuario,passwordUsuario,nombreUsuario);
               respuesta.setAttribute("error", null);
            }
            dao.desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("register.jsp");
        }

    }