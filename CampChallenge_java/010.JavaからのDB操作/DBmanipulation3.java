/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.camp.db;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

/**
 *
 * @author 将也
 */
public class DBmanipulation3 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Challenge_db","masaya","0000");
            
            //profilesテーブルを全て検索する。
            pst = con.prepareStatement("select * from profiles");
            
            //「select」使用の際は「.executeQuery();」を置いてDBにSQLを実施する。
            rs = pst.executeQuery();
            
            //データがある場合に「while」の中に入る。
            while(rs.next()){
                
                //カラム1つ1つを順に表示する。
                out.println("ID："+rs.getInt("profilesID")+"<br>");
                out.println("名前："+rs.getString("name")+"<br>");
                out.println("電話番号："+rs.getString("tell")+"<br>");
                out.println("年齢："+rs.getInt("age")+"<br>");
                out.println("生年月日："+rs.getDate("birthday")+"<br>"+"<br>");
            }
            
            rs.close();
            pst.close();
            con.close();
        
        } catch(SQLException sql) {
            out.println("接続時にエラーが発生しました："+sql.getMessage());
        } catch (Exception e) {
            out.println("接続時にエラーが発生しました："+e.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e){
                    out.println(e.getMessage());
                }
            if (pst != null) {
                try {
                    pst.close();
                } catch (Exception e){
                    out.println(e.getMessage());
                }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e){
                    out.println(e.getMessage());
                }
            }
            }
            }
        }
        
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
