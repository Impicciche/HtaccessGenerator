package util;

public class Htpaccess {
	
	private String path;
	private Htpasswd htpasswd;
	
	
	/**
	 * 
	 */
	public Htpaccess() {
		super();
	}
	/**
	 * @param path
	 * @param htpasswd
	 */
	public Htpaccess(String path, Htpasswd htpasswd) {
		super();
		this.path = path;
		this.htpasswd = htpasswd;
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
	/**
	 * @return the htpasswd
	 */
	public Htpasswd getHtpasswd() {
		return htpasswd;
	}
	/**
	 * @param htpasswd the htpasswd to set
	 */
	public void setHtpasswd(Htpasswd htpasswd) {
		this.htpasswd = htpasswd;
	}
	
	
	
}
