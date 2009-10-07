require 'rubygems'
require 'json'

class Card
  attr_accessor :cardName, :imgUrl, :cardUrl, :cardType
  @extraInfo = []
  @rulings = []
  
  def extraInfo=(extraInfoArray)
    @extraInfo = extraInfoArray
  end
  
  def rulings=(rulingsArray)
    @rulings = rulingsArray
  end
  
  def to_json()
    {
      'card_name' => @cardName,
      'img_url' => @imgUrl,
      'card_url' => @cardUrl,
      'card_type' => @cardType,
      'extra_info' => @extraInfo,
      'rulings' => @rulings
    }.to_json()
  end
end