package action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import model.UserModel;
import value.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport{
	private UserModel um = UserModel.getUserModel();
	private String bookname;
	private String ISBN;
	private String author;
	public String data;
	public String data2;
	public String authordata;
	public ArrayList<String> list = new ArrayList<String>();
	public void setdata(String info){
		this.data = info;
	}
	public String getdata(){
		return data;
	}
	public void setdata2(String info2){
		this.data2 = info2;
	}
	public String getdata2(){
		return data2;
	}
	public void setauthordata(String info){
		this.authordata = info;
	}
	public String getauthordata(){
		return authordata;
	}
	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String ISBN) {
		this.ISBN = ISBN;
	}
	
	public String select(){
		User user = um.getUserByName(bookname);
		Map request = (Map)(ActionContext.getContext().get("request"));
		request.put("user", user);
		return SUCCESS;
	}
	
	public String delete(){
		User user = um.getUserByName(bookname);
		um.deletebook(user.getISBN());
		ArrayList<User> allBook = null;
		try {
			allBook = um.getAllBooks();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		Map request = (Map)(ActionContext.getContext().get("request"));
		request.put("books", allBook);
		return SUCCESS;
	}
	public String selectAll(){
		ArrayList<User> allBook = new ArrayList<User>();
		try {
			allBook = um.getAllBooks();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		Map request = (Map)(ActionContext.getContext().get("request"));
		request.put("books", allBook);
		return SUCCESS;
	}
	public String book() throws SQLException
	{
		User u = um.getUserByName(bookname);
		data2 = um.showbook(u.getBookname());
		bookname ="";
		return SUCCESS;
	}
	public String query() throws SQLException, ClassNotFoundException
	{
		authordata = um.authordata();
		return SUCCESS;
		
	}
	public String show()//author query
	{
		try {
			data = um.show(bookname);
			if(data == "") data = "查无此人!";
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String update(){
		ActionContext ac = ActionContext.getContext();
		Map<String,Object> req = ac.getParameters();
		User user = new User(bookname,Integer.parseInt(ISBN));
		try {
			um.updateUser(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
}
