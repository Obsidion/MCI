package com.powell.jeremym;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import org.json.*;

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
		
		try {
			Thread.currentThread().sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dialog.dismiss();
		
		String jsonResp = "{\"card_url\":\"http://magiccards.info/10e/en/44.html\",\"card_type\":\"Creature - Human Cleric 1/1, W (1)\"," 
			+ "\"rulings\":[\"10/4/2004: Does not trigger on a card in play being changed into a creature.\","
			+ "\"10/4/2004: The ability will not trigger on itself coming into play, but it will trigger on any other creatur"
			+ "e that is put into play at the same time Soul Warden is, or while it is in play.\","
			+ "\"8/1/2005: Two Soul Wardens coming into play at the same time will each cause the other's ability to trigger.\","
			+ "\"8/1/2005: The life gain isn't optional.\"],\"extra_info\":[\"Whenever another creature enters the battlefield,"
			+ "you gain 1 life.\",\"Count carefully the souls and see that none are lost.\",\"\u2014Vec teaching\",\"Illus. Randy Gallegos\"],"
			+ "\"img_url\":\"http://magiccards.info/scans/en/10e/44.jpg\",\"card_name\":\"Soul Warden\"}";
		
		handleJsonResp(jsonResp);
	}
	
	private void handleJsonResp(String resp){
		try {
			JSONObject json = new JSONObject(resp);
			
			MagicCard card = new MagicCard(json);
			
			ViewMagicCardList mcList = new ViewMagicCardList();
			
			Intent i = new Intent(this, ViewMagicCardList.class);
			startActivity(i);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}