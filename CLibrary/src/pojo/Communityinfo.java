package pojo;

public class Communityinfo {
	private int coid;
	private String coname;
	private String cocard;
	private String coaddress;
	private String cophone;
	private String libraryid;
	public int getCoid() {
		return coid;
	}
	public void setCoid(int coid) {
		this.coid = coid;
	}
	public String getConame() {
		return coname;
	}
	public void setConame(String coname) {
		this.coname = coname;
	}
	public String getCocard() {
		return cocard;
	}
	public void setCocard(String cocard) {
		this.cocard = cocard;
	}
	public String getCoaddress() {
		return coaddress;
	}
	public void setCoaddress(String coaddress) {
		this.coaddress = coaddress;
	}
	public String getCophone() {
		return cophone;
	}
	public void setCophone(String cophone) {
		this.cophone = cophone;
	}
	public String getLibraryid() {
		return libraryid;
	}
	public void setLibraryid(String libraryid) {
		this.libraryid = libraryid;
	}	
	/*
	public String toString(String coname){
		return this.coname;
	}

	public String toString(String cocard){
		return this.cocard;
	}
	public String toString3(){
		return this.coaddress;
	}
	public String toString4(){
		return this.cophone;
	}
*/	
	public String toString(){
		return this.libraryid;
	}

	
}
