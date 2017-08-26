package itemObj;

public class Item {
	String name = "default";
	float price = 0.0f;
	String nick = "DEF";
	String area = "NEW";
	int locX = 0;
	int locY = 0;
	
	//Set Properties
	public void setName(String str){
		name = str;
	}
	
	public void setNick(String st) {
		nick = st;
	}
	
	public void setPrice(float prc) {
		price = prc;
	}
	
	public void setArea(String ar) {
		area = ar;
	}
	
	public void setX(int x) {
		locX = x;
	}
	public void setY(int y) {
		locY = y;
	}
	
	//Get Properties
	public String getName(){
		return name;
	}
	
	public float getPrice() {
		return price;
	}
	
	public String getNick() {
		return nick;
	}
	
	public String getArea() {
		return area;
	}
	
	public int getX() {
		return locX;
	}
	
	public int getY() {
		return locY;
	}
	
}
