package value;

import model.UserModel;

public class User {
	public String bookname;
	private int ISBN;
	public String getBookname() {
		return bookname;
	}
	public void setBookname(String bookname) {
		this.bookname = bookname;
	}
	public int getISBN() {
		return ISBN;
	}
	public void setISBN(int ISBN) {
		this.ISBN = ISBN;
	}
	public User(String bookname, int ISBN) {
		super();
		this.bookname = bookname;
		this.ISBN = ISBN;
	}
	public User() {
		super();
	}
	@Override
	public String toString() {
		return "User [bookname=" + bookname + ", ISBN=" +ISBN+ "]";
	}
	
	
}
