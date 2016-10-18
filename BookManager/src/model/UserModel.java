package model;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.DatabaseMetaData;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSetMetaData;


import action.UserAction;
import value.User;

public class UserModel {
	private static UserModel usermodel= new UserModel();
	private ResultSet res = null;
	private static Statement state = null;
	private ArrayList<User> books = new ArrayList<User>();
	private java.sql.Connection connec;
	public String info = "";
	public String info2 = "";
	public String authordata = "";
	public  PreparedStatement ptmt = null;
	private UserModel(){
		connect();
	}

	public void connect()
	{
		 try{
	            Class.forName("com.mysql.jdbc.Driver");     //load the mysql
	            //create connection
	            connec = DriverManager.getConnection("jdbc:mysql://mnnjsrmshijz.rds.sae.sina.com.cn:10424/test","root","123456");
	            //connec = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","123");
	            state = connec.createStatement();      
	            res=state.executeQuery("select * from book");//get the execute result
	        } 
		 catch (Exception e)
	        {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	}
	public static UserModel getUserModel(){
		return usermodel;
	}
	public String show(String author) throws SQLException
	{
		 String sql = "" + " select * from author " + "where Name=?"; 
		 ptmt = (PreparedStatement) connec.prepareStatement(sql);
	     ptmt.setString(1, author);
	     res = ptmt.executeQuery();
	     //get the execute result
         java.sql.ResultSetMetaData rsmd =res.getMetaData();
         while(res.next())
         {
        	 for(int i = 1;i < 5;i++) 
        	 {
        		 info += rsmd.getColumnName(i)+":"+'\t'+res.getString(i)+"\r\n";
        		
        	 }
         }
         String temp = info;
         info = "";
         return temp;
	}
	
	public String showbook(String bookname) throws SQLException
	{
		String sql = ""+ " select * from book " + "where Title=?";
		ptmt = (PreparedStatement) connec.prepareStatement(sql);
	    ptmt.setString(1, bookname);
		res = ptmt.executeQuery();
		java.sql.ResultSetMetaData rsmd =res.getMetaData();
		while(res.next())
		{
			for(int i = 1;i < 7;i++) 
			{	
				info2 += rsmd.getColumnName(i)+":\t"+res.getString(i)+"\r\n";
			}
		}
		String temp = info2;
		info2 ="";
		return temp;
       
	}
	
	public String authordata() throws SQLException{
		String sql = "select Name from author";
		ptmt = (PreparedStatement) connec.prepareStatement(sql);
	    res = ptmt.executeQuery();
		java.sql.ResultSetMetaData rsmd =res.getMetaData();
		while(res.next())
		{
			for(int i = 1;i < 2;i++) 
			{	
				authordata += rsmd.getColumnName(i)+":\t"+res.getString(i)+"\r\n";
			}
		}
		String temp = "可查询作者姓名：" + authordata;
		authordata = "";
		return temp;
	}
	public User getUserByName(String name){
		for(int i=0;i<books.size();i++){
			User user = books.get(i);
			if(user.getBookname().equals(name)){
				return user;
			}
		}
		return null;
	}
	public ArrayList<User> getAllBooks() throws SQLException{//update all
		connect();
		books.clear();
		while(res.next())
		{
			try {
				User book = new User();
				book.setBookname(res.getString(2));
				book.setISBN(res.getInt(1));
				books.add(book);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return books;
	}
	
	public void deletebook(int isbn)
	{
		for(int i = 0;i < books.size();i++)
		{
			if((books.get(i).getISBN())==isbn) 
			{
				books.remove(i);
			}
		}
		try {
			state.executeUpdate("delete from book where ISBN=" + isbn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public boolean updateUser(User user) throws SQLException{//update one
		String name = user.getBookname();
		for(int i = 0;i < books.size();i++){
			User temp = books.get(i);
			if((temp.getBookname()).equals(name)){
				String sql = "update book set ISBN= ? where Title=?";
				ptmt = (PreparedStatement) connec.prepareStatement(sql);
				ptmt.setInt(1, user.getISBN());
				ptmt.setString(2, temp.getBookname());
				ptmt.execute();
			}
			if(temp.getISBN()== (user.getISBN()))
			{
				String sql2 = "update book set Title= ? where ISBN=?";
				ptmt = (PreparedStatement) connec.prepareStatement(sql2);
				ptmt.setString(1, name);
				ptmt.setInt(2, temp.getISBN());
				ptmt.execute();
				books.set(i, user);
			}
		}
		 return false;
	  }
	}
	