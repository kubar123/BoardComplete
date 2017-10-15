/**
 * Jakub Rybicki
 * 2015 - 11 - 18
 * version 1.0
 */
package com.JakubRybicki.assignment;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
	
	//win/ loss status
	static boolean isLost=false;
	static boolean isWon=false;
	
	static GridView gridview;
	static Item[] gridArray = new Item[16];

	static ImageAdapter iAdapter;
	static ImageView refreshView;
	static TextView txtTimer;
	
	static Set<Integer> markedSet;
	
	//countdown timer ----- 
	public static CountDownTimer timerItem=null;
	public static int counter;
    public static int timeLeft;
    public static int difficultyTimer=60000; // 60 seconds
    
    //if the timer is being resumed...
    public static long millsUntilFinish;
	// countdown timer -----
	
    //help menu
    AlertDialog help;
	private int themeNo;
	protected static boolean isHelpLastSection;
    public static byte helpSection;
    
    //shared preferences
    public SharedPreferences prefs;
    public boolean isHardmode;
    public ArrayList<Score> highscoreList = new ArrayList<Score>();
	
    @Override
 	public void onResume() {
 		super.onResume();
 		System.out.println("PAUSE: RESUME - "+millsUntilFinish/1000);
 		System.out.println("PAUSE: STRT");
 		anotherTimer(millsUntilFinish, true);
 		System.out.println("PAUSE: END");
 	}
 	
	 @Override
	public void onPause() {
		super.onPause();
		timerItem.cancel();
		System.out.println("PAUSE: PAUSE");
		
	}
	 
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    	
 		gridview = (GridView) findViewById(R.id.gridview);
 		refreshView=(ImageView) findViewById(R.id.refreshImageView);
 		
 		txtTimer= (TextView) findViewById(R.id.txtTimer);
 		
 		iAdapter = new ImageAdapter(this, gridArray);
 		gridview.setAdapter(iAdapter);
 		
 		//check if this is the users first time playing the game
    	
    	
    	newGame();
    	firstTimeCheck();
    	
    	
    	
 		// RESET BUTTON CLICK -- make a new game.
 		refreshView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				newGame();					
			}
		});
	 	// -------------------------- ITEM PRESS ------------------- \\
 		gridview.setOnItemClickListener(new OnItemClickListener() {
 			public void onItemClick(AdapterView<?> parent, View v,
 					int position, long id) {
 				
 				//check if the game has been won/lost. if so, dont do anything
 				// (disable input)
 				if(isLost || isWon)
 					return;
 				
 				//a square may be disabled (when auto generated) - if so, do nothing
 				if(!gridArray[position].isEnabled())
 					return;
 				
 				//set item's colour
 				int c=gridArray[position].colorOne();
 				((ImageView) v).setImageResource(c);
            	
            	//check if game is won/lost
            	setWinLoss(position);
 			}
 		});
 		// ------------------------ LONG PRESS ---------------------- \\
 		gridview.setOnItemLongClickListener(new OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View v,
                    int pos, long id) {
            	//int c = gridArray[pos].nextColor();
            	if(isLost || isWon)
            		return false;
            	
            	int c=gridArray[pos].colorTwo();
            	
            	Log.i("BTNS",gridArray[pos].getRDC()+" : "+gridArray[pos].colorTwo());
            	
            	
            	//Toast.makeText(getApplicationContext(),"LONG id: "+gridArray[pos].columnId+"x"+gridArray[pos].rowId, Toast.LENGTH_LONG).show();
 				((ImageView) v).setImageResource(c);
				setWinLoss(pos);		
                return true;
            }
        }); 
 		
	}
	
    // ------------------ TIMER ------------------
	// making a new timer
	public void anotherTimer(long startFrom, final boolean isCancel){
		if(startFrom<=0){
			System.out.println("PAUSE: timer 0, returned");
			return;
		}else{
			System.out.println("PAUSE: timer >0");
		}
		timerItem=null;
		timerItem=new CountDownTimer(startFrom, 1000) {
			
			public void onTick(long millisUntilFinished) {
				System.out.println("PAUSE: TICK");
				counter++;
				timeLeft=(int) (millisUntilFinished/1000);
				MainActivity.millsUntilFinish=millisUntilFinished;
				System.out.println("PAUSE: "+millsUntilFinish);
				txtTimer.setText(timeLeft+"");
			}
			
			@Override
			public void onFinish() {
				//check if cancel is called, if so, do nothing
				if(isCancel)
					return;
				
				System.out.println("Done ticking");
				isLost=true;
				gameWonLossPopup(false);
			}
		}.start();
	}
	
    public void makeTimer(){
    	//new Timer().scheduleAtFixedRate(countTime(), 30000, 1000);
    	timerItem=new CountDownTimer(difficultyTimer, 1000) {
			
    		public void onStart(){
    			System.out.println("New tick");
    		}
		
			@Override
			public void onTick(long millisUntilFinished) {
				counter++;
				timeLeft=(int) (millisUntilFinished/1000);
				millsUntilFinish=millisUntilFinished;
				txtTimer.setText(timeLeft+"");
			}
			
			@Override
			public void onFinish() {
				// TODO Auto-generated method stub
				System.out.println("Done ticking");
				isLost=true;
				gameWonLossPopup(false);
			}
		
	}.start();
}
	
	
	public void setWinLoss(int position){
		//update game progress variables
		isLost=Logic.isGameLost(position, gridArray, isHardmode);
		isWon=Logic.isGameWon(position, gridArray, isHardmode);
		//save to highscore if game is won
		int score=Integer.parseInt(txtTimer.getText().toString());
		if(isWon)
			saveHighscore(score);
		
		//show popups
		if(isLost)		gameWonLossPopup(false);
    	else if(isWon)	gameWonLossPopup(true);
    	
    }

	
	//getting random set of numbers which will be used as the starting 'set' squares.
	public void markRandomSquares(){	
		markedSet=new HashSet<Integer>();
		int max=gridArray.length-1;
		int min=0;
		Random r = new Random();
		
		//using sets - does not allow duplicates
		while (markedSet.size() < 4) 
		    markedSet.add(r.nextInt(max - min + 1) + min);
		
		//for each item within the set, mark the appropriate box.
		//markedSet=markedSet;
	}
	public static void echo(String message){
		System.out.println(message);
	}
					// ------- NEW GAME ------ \\
	public void newGame(){
		prefs = getSharedPreferences("settings.set",MODE_PRIVATE);
		//set time limit option, default is easy/60 secs
		difficultyTimer=prefs.getInt("timerInt", 60);
		//convert timer to milli secs
		difficultyTimer=difficultyTimer*1000;
		
    	//check if hard mode is on
    	isHardmode=prefs.getBoolean("IsHardmodeOn", false);
		
		//check if custom theme is on 
		boolean isTheme=prefs.getBoolean("isThemeOn", false);
		if(isTheme){
			// check for new theme 
			int themeInt = prefs.getInt("theme", 999);
			if(themeInt==999){
				echo("THEME: Theme missing. Using default");
				themeInt=0;
			}
			else 				echo("THEME: Using theme: "+themeNo);
			themeNo=themeInt;
			
			// set the theme
			Item.setTheme(themeNo);
		}else{
			echo("THEME: Custom theme off, use default");
			Item.setTheme(0);
		}
		 
		markRandomSquares();
		//reset won & lost status	
		isLost=false;
		isWon=false;
		if(timerItem!=null)
			timerItem.cancel();
		makeTimer();
		
		boolean isBlue=true;
		//reset all squares to grey (inactive)
		int gridSize=16;
		if(isHardmode)	{
			gridSize=20;
			gridArray = new Item[gridSize];
		}else{
			gridArray = new Item[gridSize];
		}
		Log.d("SIZE",gridSize+" HMode:"+isHardmode);
		for (int i = 0; i < gridSize; i++){
				//go through the Set of numbers (randomly chosen)
			for(Integer a:markedSet){
				// if the Set is same as i, mark the square & alternate colours.
				if(a==i){
					if(isBlue){
						gridArray[i]=new Item(Item.color1,"blue",i,false);
						isBlue=!isBlue;
					}else{
						gridArray[i]=new Item(Item.color2,"green",i,false);
						isBlue=!isBlue;
					}
					break;
				}else{// if i != Set, square is grey.
					gridArray[i] = new Item(Item.color3, "grey",i,true);
				}
			}
		}
		
		iAdapter = new ImageAdapter(this, gridArray);
		gridview.setAdapter(iAdapter);
	}
	
	//save highscore
	public void saveHighscore(int score){
		//Log.d("TTT",highscoreList.get(0).score+"");
		Gson gson = new Gson();
		String json = prefs.getString("highscore", null);
		Type type = new TypeToken<ArrayList<Score>>() {}.getType();
		
		//do not change the highScoreList if the json returned null (shared preference not set)
		if(json!=null)		highscoreList = gson.fromJson(json, type);
		

		//1 = easy, 2 = med, 3 = hard
		byte difficulty=1;
		if(difficultyTimer/1000==60)
			difficulty=1;
		else if(difficultyTimer/1000==40)
			difficulty=2;
		else if(difficultyTimer/1000==20)
			difficulty=3;
		
//		if(highscoreList==null){
			
//		}else{
		// add highscore to arraylist
			highscoreList.add(new Score(score, difficulty));
			prefs = getSharedPreferences("settings.set",MODE_PRIVATE);
			Editor editor = prefs.edit();
//		}
		
		//Gson gson = new Gson(); 
		String json2 = gson.toJson(highscoreList);
		editor.putString("highscore", json2);

//		Set<Score> set = new HashSet<Score>();
//		set.addAll(highscoreList);
		
		editor.commit();
		
	}
	// -----
	// --------------------- POPUP ----------------------------
	public void gameWonLossPopup(boolean isWon){
		//stop the timer
		timerItem.cancel();
		String titleWin="You Win!!";
		String messageWin="Play again?";
		String titleLoss="You lose!";
		String titleMessage;
		if(isWon)
			titleMessage=titleWin;
		else
			titleMessage=titleLoss;
		
		new AlertDialog.Builder(MainActivity.this)
	    .setTitle(titleMessage)
	    .setMessage(messageWin)
	    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	        	newGame();
	        }
	     })
	    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	            // do nothing
	        }
	     })
	    .setIcon(android.R.drawable.ic_dialog_alert)
	     .show();
	}
	// ------------------ END WIN / LOSS -------------
	public void helpMenu(){
		String titleWin="How to play";
		String messageWin="Play again?";
		String titleLoss="You lose!";
		String titleMessage;
		if(isWon)
			titleMessage=titleWin;
		else
			titleMessage=titleLoss;
		
		help=new AlertDialog.Builder(MainActivity.this)
	    .setTitle(" ")
	    .setMessage("1. Tap to select colour 1 \n 2. Tap and hold to select colour 2")
	    .setPositiveButton("Next", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	        	//newGame();
	        	//helpMenuChange();
	        }
	     })
//	    .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
//	        public void onClick(DialogInterface dialog, int which) { 
//	            onResume();
//	        }
//	     })
	    .setIcon(R.drawable.help_tap)
	     .show();
		
		final Button positiveButton = help.getButton(DialogInterface.BUTTON_POSITIVE);
		positiveButton.setOnClickListener(new OnClickListener() {
		    @Override
		    public void onClick(View onClick) {
		        if(isHelpLastSection){
		        	//restarting page number to 0
		        	helpSection=0;
		        	isHelpLastSection=false;
		        	help.dismiss();
		        	
		        }
		       helpMenuChange(positiveButton);

//		        if (valid) {
//		            dialog.dismiss();
//		        } else {
//		            //  Not valid
//		        }

		    }
		});
	}
	public void helpMenuChange(Button positiveButton){
		helpSection++;
		if(helpSection==1){
			help.setIcon(R.drawable.help_game_lost);
			help.setMessage("You lose by getting 3 of the same colour in a row...");
		}else if(helpSection==2){
			help.setIcon(R.drawable.help_game_lost_2);
			help.setMessage("Or by running out of time.");
		}else if(helpSection==3){
			help.setIcon(R.drawable.help_game_win);
			help.setMessage("You win by filling the board with colours.");
			positiveButton.setText("Finish");
			isHelpLastSection=true;
		}
	}

	// first time opening of application - show help menu
	public void firstTimeCheck(){
		SharedPreferences prefs = getSharedPreferences("settings.set",MODE_PRIVATE);
		//if preference is not found show true, and...
		if (prefs.getBoolean("my_first_time", true)) {
			//show the help menu
			onPause();
			helpMenu();
			//dont show help menu on start
			prefs.edit().putBoolean("my_first_time", false).commit();
		}
		
	}
	 // -------------------------- MENU AND OPTIONS ----------------------------------
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    // Inflate the menu; this adds items to the action bar if it is present.
	    getMenuInflater().inflate(R.menu.main, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle action bar item clicks here. The action bar will
	    // automatically handle clicks on the Home/Up button, so long
	    // as you specify a parent activity in AndroidManifest.xml.
		Intent i;
	
		switch (item.getItemId()) {
	//			case R.id.activityPlay:
	//				i = new Intent(this, MainActivity.class);
	//				startActivity(i);
	//				return true;
			case R.id.activitySetting:
				onPause();
				i=new Intent(this, SettingsActivity.class);
				startActivity(i);
				return true;
			case R.id.activityHiScore:
				i=new Intent(this, HighScore.class);
				startActivity(i);
				return true;
			case R.id.activityHelp:
//				i=new Intent(this, Help.class);
//				startActivity(i);
				onPause();
				helpMenu();
				return true;
		}
		return false;
	}

}
