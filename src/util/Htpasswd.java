package util;

public class Htpasswd {
	private String username;
	private String password;
	private String path;
	
	
	/**
	 * @param username
	 * @param password
	 * @param path
	 */
	public Htpasswd(String username, String password, String path) {
		super();
		this.username = username;
		this.password = password;
		this.path = path;
	}
	
	public Htpasswd() {
		super();
	}
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
	
	
	
}
