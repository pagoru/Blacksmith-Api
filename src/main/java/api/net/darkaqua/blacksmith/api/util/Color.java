package net.darkaqua.blacksmith.api.util;

public class Color {

	protected int color;
	
	public Color(float red, float green, float blue){
		this((int)(red*0xFF), (int)(green*0xFF), (int)(blue*0xFF));
	}
	
	public Color(int red, int green, int blue){
		color = ((red & 0xFF) << 16) | ((green & 0xFF) << 8) | (blue & 0xFF);
	}
	
	public Color(int color){
		this.color = color;
	}
	
	public int getRed(){
		return (color >> 16) & 0xFF;
	}
	
	public int getGreen(){
		return (color >> 8) & 0xFF;
	}
	
	public int getBlue(){
		return color & 0xFF;
	}
	
	
	public int toInt(){
		return color;
	}
	
	public String toString(){
		return "0x"+Integer.toHexString(getRed())+Integer.toHexString(getGreen())+Integer.toHexString(getBlue());
	}
}
