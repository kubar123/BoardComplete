/**
 * Jakub Rybicki
 * 2015 - 10 - 16
 * version 1.0
 */
package com.JakubRybicki.assignment;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class HighScore extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_high_score);
		
		// -----------------------------------------START TABS ---------------------------
		TabHost tabHost=(TabHost)findViewById(android.R.id.tabhost);
		tabHost.setup();
		
		TabSpec tab1 = tabHost.newTabSpec("First Tab");
	    TabSpec tab2 = tabHost.newTabSpec("Second Tab");
	    TabSpec tab3 = tabHost.newTabSpec("Third Tab");
	    
	    tab1.setIndicator("Easy");
	    tab2.setIndicator("Medium");
	    tab3.setIndicator("Hard");
	    
	    tab1.setContent(R.id.tab1);
	    tab2.setContent(R.id.tab2);
	    tab3.setContent(R.id.tab3);
	   
	    tabHost.addTab(tab1);
	    tabHost.addTab(tab2);
	    tabHost.addTab(tab3);
	    fillTabs();
	 // ------------------------------------------ END TABS ---------------------------
	}

	private void fillTabs() {
		//ArrayList<Score> highscore = new ArrayList<Score>();
		
		// ---- easy highscore options -----
		TextView tab1score1=(TextView) findViewById(R.id.tab1_1Score);
		TextView tab1score2=(TextView) findViewById(R.id.tab1_2Score);
		TextView tab1score3=(TextView) findViewById(R.id.tab1_3Score);
		TextView tab1score4=(TextView) findViewById(R.id.tab1_4Score);
		TextView tab1score5=(TextView) findViewById(R.id.tab1_5Score);
		// ----- END EASY -----
		// ----- medium highscore options -----
		TextView tab2score1=(TextView) findViewById(R.id.tab2_1Score);
		TextView tab2score2=(TextView) findViewById(R.id.tab2_2Score);
		TextView tab2score3=(TextView) findViewById(R.id.tab2_3Score);
		TextView tab2score4=(TextView) findViewById(R.id.tab2_4Score);
		TextView tab2score5=(TextView) findViewById(R.id.tab2_5Score);
		// ----- END MEDIUM ----
		// ----- hard highscore options -----
		TextView tab3score1=(TextView) findViewById(R.id.tab3_1Score);
		TextView tab3score2=(TextView) findViewById(R.id.tab3_2Score);
		TextView tab3score3=(TextView) findViewById(R.id.tab3_3Score);
		TextView tab3score4=(TextView) findViewById(R.id.tab3_4Score);
		TextView tab3score5=(TextView) findViewById(R.id.tab3_5Score);
		// ----- END HARD ----
		
		
		SharedPreferences prefs = getSharedPreferences("settings.set",MODE_PRIVATE);
		//highscore
		Gson gson = new Gson();
		String json = prefs.getString("highscore", null);
		//dont append anything if no highscores exist
		if(json==null) return;
		Type type = new TypeToken<ArrayList<Score>>() {}.getType();
		ArrayList<Score> highscoreList = gson.fromJson(json, type);
		
		//sort array - ascending
		Collections.sort(highscoreList, new Comparator<Score>(){

			@Override
			public int compare(Score lhs, Score rhs) {
				 return rhs.score-lhs.score; // Ascending
			}
			
		});
		//-------------EASY HIGHSCORE --------------------------
		ArrayList<Score> easyHighscore = new ArrayList<Score>();
		for(int i=0;i<highscoreList.size();i++){
			Log.d("TTT",highscoreList.get(i).score+"");
			//checking for difficulty
			if(highscoreList.get(i).difficulty==1){
				easyHighscore.add(highscoreList.get(i));
			}
		}
		//add the easy highscore to the screen
		int easySize=easyHighscore.size();
		switch(easySize){
		case 0:
			break;
		default:
		case 5:
			tab1score5.setText(60-easyHighscore.get(4).score+"");
		case 4:
			tab1score4.setText(60-easyHighscore.get(3).score+"");
		case 3:
			tab1score3.setText(60-easyHighscore.get(2).score+"");
		case 2:
			tab1score2.setText(60-easyHighscore.get(1).score+"");
		case 1:
			tab1score1.setText(60-easyHighscore.get(0).score+"");
			break;
		}
		
		// --------------- medium scores ------------
		
		ArrayList<Score> medHighscore = new ArrayList<Score>();
		for(int i=0;i<highscoreList.size();i++){
			//Log.d("TTT",highscoreList.get(i).score+"");
			//checking for difficulty
			if(highscoreList.get(i).difficulty==2){
				medHighscore.add(highscoreList.get(i));
			}
		}
		//add the easy highscore to the screen
		int medSize=medHighscore.size();
		switch(medSize){
		case 0:
			break;
		default:
		case 5:
			tab2score5.setText(40-medHighscore.get(4).score+"");
		case 4:
			tab2score4.setText(40-medHighscore.get(3).score+"");
		case 3:
			tab2score3.setText(40-medHighscore.get(2).score+"");
		case 2:
			tab2score2.setText(40-medHighscore.get(1).score+"");
		case 1:
			tab2score1.setText(40-medHighscore.get(0).score+"");
			break;
		}
		
		// ----------------- HARD ----------------------------
		ArrayList<Score> hardHighscore = new ArrayList<Score>();
		for(int i=0;i<highscoreList.size();i++){
			
			//checking for difficulty
			if(highscoreList.get(i).difficulty==3){
				hardHighscore.add(highscoreList.get(i));
				Log.d("TTT","Score: "+highscoreList.get(i).score+" Diff: "+highscoreList.get(i).difficulty);
			}
		}
		//add the easy highscore to the screen
		int hardSize=hardHighscore.size();
		switch(hardSize){
		case 0:
			break;
		default:
		case 5:
			tab3score5.setText(20-hardHighscore.get(4).score+"");
		case 4:
			tab3score4.setText(20-hardHighscore.get(3).score+"");
		case 3:
			tab3score3.setText(20-hardHighscore.get(2).score+"");
		case 2:
			tab3score2.setText(20-hardHighscore.get(1).score+"");
		case 1:
			tab3score1.setText(20-hardHighscore.get(0).score+"");
			break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.high_score, menu);
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
//			case R.id.activitySetting:
//				i=new Intent(this, SettingsActivity.class);
//				startActivity(i);
//				return true;
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
