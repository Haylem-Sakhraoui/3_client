/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.CV;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import utiles.MyDB;

/**
 *
 * @author HAYLEM SAKHRAOUI
 */
public class CVService implements Icv {
    
    Connection connexion;
    Statement stm;
    Scanner scanner = new Scanner(System.in);

    public CVService() {
        connexion = MyDB.getInstance().getConnexion();
    }

    @Override
    public void addCV(CV cv) throws SQLException {
           
        String req = "INSERT INTO cv (filename, filetype, filesize, data) VALUES ('" + cv.getFilename() + "','" + cv.getFiletype() + "', '" + cv.getFilesize() + "', '" + cv.getData() + "')";
        stm = connexion.createStatement();
        stm.executeUpdate(req);
    }

    @Override
    public CV getCVById(int id) throws SQLException {
        String req = "SELECT * FROM cv WHERE id = ?";
        PreparedStatement statement = connexion.prepareStatement(req);
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            CV cv = new CV();
            cv.setId(rs.getInt("id"));
            cv.setFilename(rs.getString("filename"));
            cv.setFiletype(rs.getString("filetype"));
            cv.setFilesize(rs.getInt("filesize"));
            cv.setData(rs.getBinaryStream("data"));
            return cv;
        }
        return null;
    }

    @Override
    public List<CV> getAllCVs() throws SQLException {
        
        String req = "SELECT * FROM cv";
        stm = connexion.createStatement();
        ResultSet rs = stm.executeQuery(req);
        List<CV> cvs = new ArrayList<>();
        while (rs.next()) {
            CV cv = new CV();
            cv.setId(rs.getInt("id"));
            cv.setFilename(rs.getString("filename"));
            cv.setFiletype(rs.getString("filetype"));
            cv.setFilesize(rs.getInt("filesize"));
            cv.setData(rs.getBinaryStream("data"));
            cvs.add(cv);
        }
        return cvs;
    }

    @Override
    public void updateCV(CV cv) throws SQLException {
        
        String req = "UPDATE cv SET filename = ?, filetype = ?, filesize = ?, data = ? WHERE id = ?";
        PreparedStatement ps = connexion.prepareStatement(req);
        ps.setString(1, cv.getFilename());
        ps.setString(2, cv.getFiletype());
        ps.setInt(3, cv.getFilesize());
        ps.setBinaryStream(4, cv.getData(), cv.getFilesize());
        ps.setInt(5, cv.getId());
        ps.executeUpdate();
    }

    @Override
    public void deleteCV(int id) throws SQLException {
        String req = "DELETE FROM cv WHERE id = ?";
        PreparedStatement statement =  connexion.prepareStatement(req);
        statement.setInt(1, id);
        statement.executeUpdate();
    }
}