package com.powell.jeremym;

import java.util.ArrayList;
import java.util.List;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ViewMagicCardList extends ListActivity {
	private String searchText;
	private List<MagicCard> magicCards = new ArrayList<MagicCard>();
	private MagicCardAdapter adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		this.setContentView(R.layout.viewmagiccardgamelist);
	}
	
	class MagicCardAdapter extends ArrayAdapter<MagicCard> {
		MagicCardAdapter() {
			super(ViewMagicCardList.this, android.R.layout.simple_list_item_1, magicCards);
		}
		
		public View getView(int position, View convertView, ViewGroup parent){
			View row = convertView;
			
			MagicCardWrapper wrapper = null;
			
			if (row == null) {
				LayoutInflater inflater = getLayoutInflater();
				row = inflater.inflate(R.layout.row, null);
				wrapper = new MagicCardWrapper(row);
				row.setTag(wrapper);
			} else {
				wrapper = (MagicCardWrapper) row.getTag();
			}

			wrapper.populateFrom(magicCards.get(position));

			return row;
		}
	}
	
	class MagicCardWrapper {
		// this class exists to help performance in binding the magic card list
		private View row = null;
		private TextView name = null;
		private TextView gameId = null;

		public MagicCardWrapper(View row) {
			this.row = row;
		}

		void populateFrom(MagicCard card) {
			getName().setText(card.getCardName());
			getGameId().setText("1");
		}

		TextView getName() {
			if (name == null) {
				name = (TextView) row.findViewById(R.id.name);
			}
			return name;
		}

		TextView getGameId() {
			if (gameId == null) {
				gameId = (TextView) row.findViewById(R.id.gameId);
			}
			return gameId;
		}
	}
}
