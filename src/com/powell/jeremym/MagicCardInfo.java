package com.powell.jeremym;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MagicCardInfo extends Activity {
	private EditText searchBox;
	private Button searchButton;
		
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
    	
    	// call the XML layout
		setContentView(R.layout.main);
		//initThreading();
		findViews();
		setListeners();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
    	super.onCreateOptionsMenu(menu);
    	
    	// inflate the menu from XML
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.menu, menu);

		return true;
    }
    
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.search:
			onSearchRequested();
			return true;
		}
		return false;
	}
	
	private void findViews() {
		searchBox = (EditText) findViewById(R.id.search_box);
		searchButton = (Button) findViewById(R.id.go_search);
	}
	
	private void setListeners() {
		searchButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String card = searchBox.getText().toString();
				
				if(card.length() > 0){
					// do the search.
					doSearch(card);					
				}
			}
		});
	}
	
	private void doSearch(String cardName){
		AlertDialog dialog = new AlertDialog.Builder(this).create();
		dialog.setMessage("Searching for " + cardName);
		dialog.setCancelable(true);
		dialog.show();
	}
}