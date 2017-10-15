/**
 * Jakub Rybicki
 * 2015 - 10 - 16
 * version 1.0
 */
package com.JakubRybicki.assignment;

import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

public class Item {

	private int RDC;
	static int state = 0;
	int id=0;
	String positionText;
	int columnId;
	int rowId; 
	boolean isEnabled;
	
	// ------- colours ----------
//	static int color1=R.drawable.blue;
//	static int color2=R.drawable.green;
//	static int color3=R.drawable.grey;
	static int color1;
	static int color2;
	static int color3;
	
	public static void setTheme(int themeID){
		switch(themeID){
		case 0:
			color1=R.drawable.t_1_blue_one;
			color2=R.drawable.t_1_green_one;
			color3=R.drawable.t_1_empty_one;
			break;
		case 1:
			color1=R.drawable.blue;
			color2=R.drawable.green;
			color3=R.drawable.grey;
			break;
		case 2:
			color1=R.drawable.theme_icon_razor_3;
			color2=R.drawable.theme_icon_razor_2;
			color3=R.drawable.theme_icon_razor_1;
			break;
		case 3:
			color1=R.drawable.theme_icon_sq_round_3;
			color2=R.drawable.theme_icon_sq_round_2;
			color3=R.drawable.theme_icon_sq_round_1;
			break;
	
		case 4:
			color1=R.drawable.theme_icon_cool_white_1;
			color2=R.drawable.theme_icon_cool_white_2;
			color3=R.drawable.theme_icon_cool_white;
			break;
		}
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Item(int rdc, String title, int id, boolean isEnabled) {
		this.id=id;
		this.setRDC(rdc);
		this.rowId=id/4;
		this.columnId=id%4;
		this.isEnabled=isEnabled;
	}
	boolean isEnabled(){
		return isEnabled;
	}
	public void setEnabled(boolean isEnabled){
		this.isEnabled=isEnabled;
	}
	
	int getRDC() {
		return RDC;
	}

	void setRDC(int rDC) {
		RDC = rDC;
	}
//	public String getPosition(){
//		switch(id){
//		case 0:
//			return "0x0";
//		case 1:
//			return "0x1";
//		case 2:
//			return "0x2";
//		case 3:
//			return "1x0";
//		case 4:
//			return "1x1";
//		case 5:
//			return "1x2";
//		case 6:
//			return "2x0";
//		case 7:
//			return "2x1";
//		case 8:
//			return "1x0";
//		case 9:
//			return "1x0";
//			
//		}
		
	//}

	public int nextColor() {
		RDC = R.drawable.grey;
		Log.i("BTNS",RDC+": "+state);
		if (++state > 2)
			state = 1;
		switch (state) {
		case 1:
			RDC = color2;
			break;
		case 2:
			RDC = color1;
			break;
//		case 3:
//			RDC = R.drawable.grey;
//			break;
		}
		return RDC;
	}
	public int colorOne(){
		RDC=color1;
		return color1;
	}
	public int colorTwo(){
		RDC=color2;
		return color2;
	}
	public int colorGrey(){
		RDC=color3;
		return color3;
	}
}
