package com.powell.jeremym;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MagicCard {
	private String cardUrl = "";
	private String imgUrl = "";
	private String cardName = "";
	private String cardType = "";
	private String[] extraInfo = null;
	private String[] rulings = null;
	
	private static final String C_URL = "card_url";
	private static final String I_URL = "img_url";
	private static final String C_NAME = "card_name";
	private static final String C_TYPE = "card_type";
	private static final String E_INFO = "extra_info";
	private static final String RULINGS = "rulings";
	
	public MagicCard(){}
	
	public MagicCard(JSONObject jsonObj) throws JSONException {
		if(jsonObj.has(C_URL)){
			setCardUrl(jsonObj.getString(C_URL));
		}
		if(jsonObj.has(I_URL)){
			setImgUrl(jsonObj.getString(I_URL));
		}
		if(jsonObj.has(C_NAME)){
			setCardName(jsonObj.getString(C_NAME));
		}
		if(jsonObj.has(C_TYPE)){
			setCardType(jsonObj.getString(C_TYPE));
		}
		if(jsonObj.has(E_INFO)){
			JSONArray ar = jsonObj.getJSONArray(E_INFO);
			
			if(ar.length() > 0){
				 String[] extras = new String[ar.length()];
				 
				 for(int i = 0; i < ar.length(); ++i){
					 extras[i] = ar.getString(i);
				 }
				 
				 setExtraInfo(extras);
			}
			else{
				setExtraInfo(new String[0]);  // does this work...?
			}
		}
		if(jsonObj.has(RULINGS)){
			JSONArray ar = jsonObj.getJSONArray(RULINGS);
			
			if(ar.length() > 0){
				 String[] rulings = new String[ar.length()];
				 
				 for(int i = 0; i < ar.length(); ++i){
					 rulings[i] = ar.getString(i);
				 }
				 
				 setRulings(rulings);
			}
			else{
				setRulings(new String[0]);  // does this work...?
			}
		}
	}

	public void setCardUrl(String cardUrl) {
		this.cardUrl = cardUrl;
	}

	public String getCardUrl() {
		return cardUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardType() {
		return cardType;
	}

	public void setExtraInfo(String[] extraInfo) {
		this.extraInfo = extraInfo;
	}

	public String[] getExtraInfo() {
		return extraInfo;
	}

	public void setRulings(String[] rulings) {
		this.rulings = rulings;
	}

	public String[] getRulings() {
		return rulings;
	}
	
	
}
