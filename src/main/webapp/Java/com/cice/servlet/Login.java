package com.cice.servlet;

import com.cice.beans.DaoUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@WebServlet("/login")
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("index.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Pattern p = null;
        Matcher m = p.matcher(email);
        DaoUser daoUser = new DaoUser();
        if(email.isEmpty() || password.isEmpty()){ session.setAttribute("Error", "Hay campos vacios");}
        if(m.find()){session.setAttribute("Error","Email no encontrado");}
        try{
            daoUser.conectar();
                if(daoUser.isCuentaRegistrada(email,password)){
                    String nombreUsuario = daoUser.getNombrePorEmail(email);
                    session.setAttribute("sessionNombre",nombreUsuario);
                    session.setAttribute("sessionEmail",email);
                }else {
                    session.setAttribute("Error", "Esta dirección de correo ya fue registrada");
                }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                daoUser.desconectar();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        resp.sendRedirect("login.jsp");
    }
}
