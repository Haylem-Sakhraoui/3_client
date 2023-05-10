/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import entities.*;
import utiles.MyDB;

/**
 *
 * @author Baklouti
 */
public class ServicesTags implements IservicesA {

    MyDB cnx = new MyDB();

    public boolean check_tag(Tag t) {
        System.out.println(t.toString());
        Connection cn = cnx.getConnexion();
        try {
            PreparedStatement posted = cn.prepareStatement("SELECT * FROM tag where tag=?;");
            posted.setString(1, t.getName());
            ResultSet result = posted.executeQuery();
            if (result.next()) {
                System.out.println(result.getString("tag"));
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public ArrayList<String> Check_hashtags(String text) {
        System.out.println(text);
        ArrayList<String> array = new ArrayList<String>();
        String[] arrOfStr = text.split("#");
        if (arrOfStr != null) {
            for (int i2 = 1; i2 != arrOfStr.length; i2++) {
                String[] mentioned = arrOfStr[i2].split(" ");
                array.add(mentioned[0]);
            }
            System.out.println(array);
            return array;
        } else {
            return null;
        }
    }

    public int fetchId(Tag t) {
        try {
            Connection cn = cnx.getConnexion();
            PreparedStatement posted = cn.prepareStatement("SELECT * From tag where tag=?;");
            posted.setString(1, t.getName());
            ResultSet result = posted.executeQuery();
            while (result.next()) {
                return result.getInt("id_tag");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return -1;
    }

    public String fetchName(Tag t) {
        try {
            Connection cn = cnx.getConnexion();
            PreparedStatement posted = cn.prepareStatement("SELECT * From tag where id_tag=?;");
            posted.setInt(1, t.getId());
            ResultSet result = posted.executeQuery();
            while (result.next()) {
                return result.getString("tag");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return "";
    }

    public boolean add_relation(Publication p) {
        int id_pub = p.getId();
        Connection cn = cnx.getConnexion();
        ArrayList<String> tags = Check_hashtags(p.getText());
        try {

            for (int i = 0; i <= tags.size() - 1; i++) {
                Tag t = new Tag(tags.get(i));
                int id = fetchId(t);
                t.setId(id);
                if (check_tag(t) == false) {
                    insert(t);
                    id = fetchId(t);
                    t.setId(id);
                    PreparedStatement posted = cn.prepareStatement("INSERT INTO `tag_publication` (`id_tag`,`id_pub`) VALUES (?,?);");
                    posted.setInt(1, t.getId());
                    posted.setInt(2, id_pub);
                    posted.executeUpdate();
                } else {
                    PreparedStatement posted = cn.prepareStatement("INSERT INTO `tag_publication` (`id_tag`,`id_pub`) VALUES (?,?);");
                    posted.setInt(1, t.getId());
                    posted.setInt(2, id_pub);
                    posted.executeUpdate();
                }
            }

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public boolean delete_relation(Publication p) {
        int id_pub = p.getId();
        Connection cn = cnx.getConnexion();
        try {

            PreparedStatement posted = cn.prepareStatement("DELETE FROM `tag_publication` WHERE `tag_publication`.`id_pub` =?");
            posted.setInt(1, id_pub);
            posted.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    //public ArrayList<>
    @Override
    public ArrayList select_all(Object t) {
        Tag ta = (Tag) t;
        try {
            Connection cn = cnx.getConnexion();
            PreparedStatement posted = cn.prepareStatement("SELECT * From tag;");
            ResultSet result = posted.executeQuery();
            ArrayList<String> array = new ArrayList<String>();
            int i = 0;
            while (result.next()) {
                System.out.println("(" + i + ") :");
                System.out.println("Hashtag: " + result.getString("tag") + "/// ");
                System.out.println("|||||||||||||||||||||||||||||||||||||||||||||||||||||");
                array.add(result.getString("tag"));
                i++;
            }
            return array;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public void update(Object t) {
        Tag ta = (Tag) t;
        Connection cn = cnx.getConnexion();
        try {

            PreparedStatement posted = cn.prepareStatement("UPDATE `tag` SET `tag` =? where id_tag =?");
            posted.setString(1, ta.getName());
            posted.setInt(2, ta.getId());
            posted.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void insert(Object t) {
        Tag ta = (Tag) t;
        try {
            Connection cn = cnx.getConnexion();
            if (!check_tag(ta)) {
                PreparedStatement posted = cn.prepareStatement("INSERT INTO `tag` (`tag`) VALUES (?);");
                posted.setString(1, ta.getName());
                posted.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void delete(Object t) {
        Tag ta = (Tag) t;
        try {
            Connection cn = cnx.getConnexion();
            if (check_tag(ta)) {
                PreparedStatement posted = cn.prepareStatement("DELETE FROM `tag` WHERE `id_tag` =?;");
                posted.setInt(1, ta.getId());
                posted.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public ArrayList get_pubs_from_tag(int id_tag) {
        try {
            Connection cn = cnx.getConnexion();
            PreparedStatement posted = cn.prepareStatement("SELECT * from tag_publication where id_tag=?;");
            posted.setInt(1, id_tag);
            ResultSet result = posted.executeQuery();
            ArrayList<Integer> array = new ArrayList<>();
            while (result.next()) {
                array.add(result.getInt("id_pub"));
                System.out.println(result.getInt("id_pub"));
            }
            System.out.println("Array from Get_Pubs: " + array);
            return array;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

}
