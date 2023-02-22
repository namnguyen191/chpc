package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBC {
    private static final String username = "root";
    private static final String password = "123456";//root1234
    private static final String driverName = "com.mysql.cj.jdbc.Driver";
    private static final String connectionString = "jdbc:mysql://localhost:3306/housing?useSSL=false&serverTimezone=UTC";
    private static Connection connection;

    public JDBC(){
    }
    public  List<Region> getRegions(String regionName,String from,String end){
        List<Region> regions= new ArrayList<>();
        try{
            Class.forName(driverName);
            connection = DriverManager.getConnection(connectionString, username, password);

            String sql = "select * from house where GEO REGEXP ? and `New housing price indexes`= \"House only\" and REF_DATE >= ? and REF_DATE <= ? ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setObject(1,regionName);
            ps.setObject(2,from);
            ps.setObject(3,end);
            //display all the results
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                regions.add(new Region(rs.getString(2),Double.parseDouble(rs.getString(11)),rs.getString(1)));
                //outPut += "\n" +rs.getString(1)+"\t" +rs.getString(2)+"\t\t" + rs.getString(11)+"\t";
            }
            //System.out.println(outPut);


        } catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return regions;
    }


}
