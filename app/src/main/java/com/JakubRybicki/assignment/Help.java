/**
 * Jakub Rybicki
 * 2015 - 10 - 16
 * version 1.0
 */
package com.JakubRybicki.assignment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Help extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.help, menu);
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
				i = new Intent(this, MainActivity.class);
				startActivity(i);
				return true;
			case R.id.activitySetting:
				i=new Intent(this, SettingsActivity.class);
				startActivity(i);
				return true;
			case R.id.activityHiScore:
				i=new Intent(this, HighScore.class);
				startActivity(i);
				return true;
//			case R.id.activityHelp:
//				i=new Intent(this, Help.class);
//				startActivity(i);
//				return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
