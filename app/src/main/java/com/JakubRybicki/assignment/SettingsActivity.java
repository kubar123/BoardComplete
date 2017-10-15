/**
 * Jakub Rybicki
 * 2015 - 10 - 16
 * version 1.0
 */
package com.JakubRybicki.assignment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.AdapterView.OnItemClickListener;

public class SettingsActivity extends Activity {
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		// DECLERATION 
		final Switch switchCustIcon =(Switch) findViewById(R.id.switchCustIcon);
		final Switch switchHardmode=(Switch) findViewById(R.id.switchHardmode);
		final ListView listCustIcon=(ListView) findViewById(R.id.listCustIcon);
		//~~difficulty settings
		final Spinner spinDifficulty = (Spinner)findViewById(R.id.spinDifficulty);
		String[] difficultyOptions = new String[]{"Easy", "Medium", "Hard"};
		ArrayAdapter<String> spinSdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, difficultyOptions);
		spinDifficulty.setAdapter(spinSdapter);
		//~~ end difficulty
		
		// Values for listView
		String[] values = new String[] { "Default","Basic","Razor","Rounded edges", "Cool"};
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				 android.R.layout.simple_list_item_1, android.R.id.text1, values);
		//Assign adapter to listView
		listCustIcon.setAdapter(adapter);	
		listCustIcon.setTextFilterEnabled(true);
		
		//setting 
		SharedPreferences prefs = getSharedPreferences("settings.set",MODE_PRIVATE); 
		boolean isTheme=prefs.getBoolean("isThemeOn", false);
		boolean isHardmode=prefs.getBoolean("IsHardmodeOn", false);
		Log.d("SIZE","hardmode : "+isHardmode);
		
		//assign selected difficulty
		prefs = getSharedPreferences("settings.set",MODE_PRIVATE);
		//set time limit option
		int difficultyTimer=prefs.getInt("timerInt", 60);
		if(difficultyTimer==60)
				spinDifficulty.setSelection(0);
		else if(difficultyTimer==40)
			spinDifficulty.setSelection(1);
		else if(difficultyTimer==20){
			spinDifficulty.setSelection(2);
		}
		//invalidate the view
		spinDifficulty.invalidate(); 
		
		if(isHardmode){
			switchHardmode.setChecked(true);
		}else{
			switchHardmode.setChecked(false);
		}
		
		if(isTheme){
			listCustIcon.setVisibility(View.VISIBLE);
			switchCustIcon.setChecked(true);
		}else{
			listCustIcon.setVisibility(View.INVISIBLE);
			switchCustIcon.setChecked(false);
		}
		 switchHardmode.invalidate(); 

		switchHardmode.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d("SIZE","CLICK hardmode: "+switchHardmode.isChecked());
				Log.d("SIZE","CLICK hardmode: ");
				
	        	SharedPreferences prefs = getSharedPreferences("settings.set", MODE_PRIVATE);
				SharedPreferences.Editor editor = prefs.edit();
				editor.putBoolean("IsHardmodeOn", switchHardmode.isChecked());
				editor.apply();
			}
		});
		spinDifficulty.setOnItemSelectedListener(new OnItemSelectedListener() {
			

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				String selected=spinDifficulty.getSelectedItem().toString();
				int timerLimit=60;
				if("Easy".equalsIgnoreCase(selected)){
					timerLimit=60;
				}else if("Medium".equalsIgnoreCase(selected)){
					timerLimit=40;
				}else if("Hard".equalsIgnoreCase(selected)){
					timerLimit=20;
				}
				SharedPreferences prefs = getSharedPreferences("settings.set", MODE_PRIVATE);
				SharedPreferences.Editor editor = prefs.edit();
				editor.putInt("timerInt", timerLimit);
				editor.apply();
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		listCustIcon.setOnItemClickListener(new OnItemClickListener() {
			
 			public void onItemClick(AdapterView<?> parent, View v,
 					int position, long id) {
 				
				SharedPreferences prefs = getSharedPreferences("settings.set", MODE_PRIVATE);
				SharedPreferences.Editor editor = prefs.edit();
				editor.putInt("theme", position);
				editor.apply();
				System.out.println("THEME: Applied theme:"+position+"");
 			}
		});
 			
		switchCustIcon.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(switchCustIcon.isChecked()){
					Log.i("CUST", "is checked");
					listCustIcon.setVisibility(View.VISIBLE);
					SharedPreferences prefs = getSharedPreferences("settings.set", MODE_PRIVATE);
					SharedPreferences.Editor editor = prefs.edit();
					editor.putBoolean("isThemeOn", true);
					editor.apply();
				}else if(!switchCustIcon.isChecked()){
					Log.i("CUST", "is checked");
					listCustIcon.setVisibility(View.INVISIBLE);
					SharedPreferences prefs = getSharedPreferences("settings.set", MODE_PRIVATE);
					SharedPreferences.Editor editor = prefs.edit();
					editor.putBoolean("isThemeOn", false);
					editor.apply();
				}
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		Intent i;

		switch (item.getItemId()) {
			case R.id.activityPlay:
				finish();
//				i = new Intent(this, MainActivity.class);
//				startActivity(i);
//				return true;
////			case R.id.activitySetting:
////				i=new Intent(this, SettingsActivity.class);
////				startActivity(i);
////				return true;
//			case R.id.activityHiScore:
//				i=new Intent(this, HighScore.class);
//				startActivity(i);
//				return true;
//			case R.id.activityHelp:
//				i=new Intent(this, Help.class);
//				startActivity(i);
//				return true;
		}
		return super.onOptionsItemSelected(item);
	}
}