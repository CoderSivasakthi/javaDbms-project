package com.example.realestate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;

@Controller
public class EstateController {
    String jdbcurl = "jdbc:mysql://127.0.0.1:3306/realest";

    @GetMapping("/Start")
    public String Start(){
        return "login";
    }

    @PostMapping ("/sign")
    public  String sign(){
        return "demologin";
    }
    @PostMapping("/login")
    public String login(@RequestParam String button,@RequestParam("name") String name,@RequestParam("password") String password){
        Connection connection = null;
        String dbpassword="";
        try{
            connection = DriverManager.getConnection(jdbcurl,"root","root254");
            String sql = "select password from signupuser where name=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,name);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                dbpassword = rs.getString("password");
            }
        }catch (SQLException e){
            System.out.println(e);
        }
        if(button.equals("loginbutton") && password.equals(dbpassword))
            return "newlanding";
        if(!password.equals(dbpassword))
            return "login";
        return "demologin";
    }
    @PostMapping("/signin")
    public String signin(@RequestParam("name") String name,@RequestParam("email") String email,@RequestParam("password") String password){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(jdbcurl,"root","root254");
            String sql = "Insert into signupuser values(?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,name);
            statement.setString(2,email);
            statement.setString(3,password);
            statement.executeUpdate();

        }catch (SQLException e){
            System.out.println(e);
        }
        return "newlanding";
    }

    @GetMapping("propertyform")
    public String form(){
        return "property form";
    }
    @PostMapping("/property")
    public String propertyform(@RequestParam("name") String name,@RequestParam("email") String email,@RequestParam("phone") String phone,@RequestParam("wphone") String wphone,@RequestParam("lookingfor") String lookingfor,@RequestParam("flat") String flat,@RequestParam("Address") String Address,@RequestParam("flatsize") Integer flatsize){
        System.out.println(" The values are "+name+" "+email+" "+phone+" "+wphone+" "+lookingfor+" "+flat+" "+Address+" "+flatsize);
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(jdbcurl,"root","root254");
            String sql = "Insert into propertydatas values(?,?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,name);
            statement.setString(2,email);
            statement.setString(3,phone);
            statement.setString(4,wphone);
            statement.setString(5,lookingfor);
            statement.setString(6,flat);
            statement.setString(7,Address);
            statement.setInt(8,flatsize);
            statement.executeUpdate();

        }catch (SQLException e) {
            System.out.println(e);
        }
        return "ownerform";
    }

    @PostMapping( value = "sign" , produces = "text/html")
    public String submit(@RequestParam String button){

        if (button.equals("log")){
            return "newlanding";
        }
        else if(button.equals("sign")){
            return "demologin";
        }
            return "login";
    }

    @GetMapping("/getdbdata")
    public String getdbdata(){
        String jdbcurl = "jdbc:mysql://127.0.0.1:3306/realest";
        Connection  connection = null;
        try{
            connection = DriverManager.getConnection(jdbcurl,"root","root254");
            String sql = "insert into owner values('siva')";
            Statement statement = connection.createStatement();
            int row=statement.executeUpdate(sql);
            if(row>0){
                return "Row inserted Succesfully";
            }
            else{
                return "No rows Inserted";
            }
        }catch(Exception e) {
            System.out.println(e);
        }
        return "Database Connection not executed";
    }

    @GetMapping("/buyvillas")
    public String buyvilla(){
        return "buy villas";
    }
    @GetMapping("/buyhomes")
    public String buyhomes(){
        return "buy homes";
    }
    @GetMapping("/buyplots")
    public String buyplots(){
        return "buy plots";
    }
    @GetMapping("/Buy")
    public String buy(){
        return "buy plots";
    }
    @GetMapping("/renthomes")
    public String renthomes(){
        return "rent homes";
    }
    @GetMapping("/rentvillas")
    public String rentvillas(){
        return "rent villas";
    }
    @GetMapping("/rentplots")
    public String rentplots(){
        return "rent plots";
    }
    @GetMapping("/rent")
    public String rent(){
        return "rent plots";
    }

    @GetMapping("/propsdatas")
    public String propertys(){
        return "propdatas";
    }

//    @PostMapping("/customer")
//    public String addcart(@RequestParam String button,
//                          @RequestParam String name,
//                          @RequestParam String property,
//                          @RequestParam String propertytype,
//                          @RequestParam String owner,
//                          @RequestParam String amount) {
//        if (button.equals("addtocart")) {
//            try (Connection connection = DriverManager.getConnection(jdbcurl, "root", "root254")) {
//                String sql = "INSERT INTO addtocartdata (name, property, propertytype, owner, amount) VALUES ('sakthi','property 1','Home property','siva','600000')";
//                try (PreparedStatement statement = connection.prepareStatement(sql)) {
//                    statement.setString(1, name);
//                    statement.setString(2, property);
//                    statement.setString(3, propertytype);
//                    statement.setString(4, owner);
//                    statement.setString(5, amount);
//                    statement.executeUpdate();
//                    System.out.println("Data inserted successfully into addtocartdata");
//                }
//            } catch (SQLException e) {
//                System.out.println("Error inserting data into addtocartdata: " + e.getMessage());
//            }
//            return "newlanding";
//        } else if (button.equals("order")) {
//            return "login";
//        } else {
//            return "demologin";
//        }
//    }

    @PostMapping("/customer")
    public String addcart(@RequestParam String button){
        if(button.equals("addtocart")){
            Connection connection = null;
            try{
                System.out.println("customer executed");
                connection = DriverManager.getConnection(jdbcurl,"root","root254");
                String sql = "INSERT INTO addtocartdata (name, property, propertytype, owner, flatsize, amount) VALUES ('sakthi','property 1','Home property','siva','1000','600000')";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.executeUpdate();

            }catch (SQLException e){
                System.out.println(e);
            }
            return "profileorder";
        }
        else if(button.equals("order")){
            Connection connection = null;
            try{
                System.out.println("customer executed");
                connection = DriverManager.getConnection(jdbcurl,"root","root254");
                String sql = "INSERT INTO addtocartdata (name, property, propertytype, owner, flatsize, amount) VALUES ('ram','property 5','plot property','siva','6000','1200000')";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.executeUpdate();

            }catch (SQLException e){
                System.out.println(e);
            }
            return "buyers";
        }
        else {
            return "propdatas";
        }
    }

    @GetMapping("/emiloan")
    public String emis(){
        return "emi";
    }

    @GetMapping("/profile")
    public String procart(){
        return "profileorder";
    }

    @PostMapping("/ownerdata")
    public String ownerform(@RequestParam("propertyType") String propertyType,@RequestParam("address") String address,@RequestParam("size") String size,@RequestParam("Flatno") String Flatno,@RequestParam("bedrooms") String bedrooms,@RequestParam("bathrooms") String bathrooms,@RequestParam("yearBuilt") Date yearBuilt,@RequestParam("salePrice") String salePrice,@RequestParam("downPayment")String downPayment ){
        System.out.println(" The values are "+propertyType+" "+address+" "+size+" "+Flatno+" "+bedrooms+" "+bathrooms+" "+yearBuilt+" "+salePrice+" "+downPayment);
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(jdbcurl,"root","root254");
            String sql = "Insert into property values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1,propertyType);
            statement.setString(2,address);
            statement.setString(3,size);
            statement.setString(4,Flatno);
            statement.setString(5,bedrooms);
            statement.setString(6,bathrooms);
            statement.setDate(7,yearBuilt);
            statement.setString(8,salePrice);
            statement.setString(9,downPayment);
            statement.executeUpdate();

        }catch (SQLException e) {
            System.out.println(e);
        }
        return "done";
    }

    @PostMapping("/addtocart")
    public String addcart(Model model) {

        List<Map<String,Object>> data=fetchTask();
        model.addAttribute("cartdata",data);


        return "profileorder";

    }

    @ModelAttribute("cartdata")
    public List<Map<String,Object>> fetchTask() {
        List<Map<String, Object>> listofdetails = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcurl, "root", "root254");


            String sql = "Select * from addtocartdata";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Map<String, Object> mp = new HashMap<>();
                mp.put("name", rs.getString("name"));
                mp.put("property", rs.getString("property"));
                mp.put("propertytype", rs.getString("propertytype"));
                mp.put("owner", rs.getString("owner"));
                mp.put("flatsize", rs.getString("flatsize"));
                mp.put("amount", rs.getString("amount"));
                listofdetails.add(mp);
            }
            System.out.println(listofdetails);

        } catch (SQLException e) {
            System.out.println(e);
        }
        return listofdetails;
    }

    @PostMapping("/order")
    public String ordershow(Model model) {

        List<Map<String,Object>> data=fetchTask();
        model.addAttribute("orderdata",data);


        return "profileorder";

    }

    @ModelAttribute("orderdata")
    public List<Map<String,Object>> fetchorder() {
        List<Map<String, Object>> listofdetails = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcurl, "root", "root254");


            String sql = "Select * from addtocartdata";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Map<String, Object> mp = new HashMap<>();
                mp.put("name", rs.getString("name"));
                mp.put("property", rs.getString("property"));
                mp.put("propertytype", rs.getString("propertytype"));
                mp.put("owner", rs.getString("owner"));
                mp.put("flatsize", rs.getString("flatsize"));
                mp.put("amount", rs.getString("amount"));
                listofdetails.add(mp);
            }
            System.out.println(listofdetails);

        } catch (SQLException e) {
            System.out.println(e);
        }
        return listofdetails;
    }

    @GetMapping("launch1")
    public String launch(){
        return "newlaunch1";
    }

    @GetMapping("pay")
    public String pays(){
        return "paydone";
    }

}
