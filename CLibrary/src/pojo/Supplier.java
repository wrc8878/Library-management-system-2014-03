package pojo;
public class Supplier {
   private int sid;
   private String sname;
   private String saddress;
   private String slinkname;  
   private String sphone;
public int getSid() {
	return sid;
}
public void setSid(int sid) {
	this.sid = sid;
}
public String getSname() {
	return sname;
}
public void setSname(String sname) {
	this.sname = sname;
}
public String getSaddress() {
	return saddress;
}
public void setSaddress(String saddress) {
	this.saddress = saddress;
}
public String getSlinkname() {
	return slinkname;
}
public void setSlinkname(String slinkname) {
	this.slinkname = slinkname;
}
public String getSphone() {
	return sphone;
}
public void setSphone(String sphone) {
	this.sphone = sphone;
}
public String toString(){
	return this.sname;
}


}
