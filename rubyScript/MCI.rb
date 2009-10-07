require 'rubygems'
require 'Hpricot'
require 'open-uri'
require 'Card'

$MCI = 'http://magiccards.info'
$query = '/query.php?cardname='

$CardNameRegex = '<a.*>(.*)<\/a>'  

def getCard(cardTable)
  # preset them
  card = Card.new()
  
  card.cardName = 'card_name'
  card.imgUrl = 'img_url'
  card.cardUrl = 'card_url'
  card.cardType = 'card_type'
  card.extraInfo = []
  card.rulings = []
  
  tmpStr = cardTable.at("/tr/td/img")[:src]
  if tmpStr
    card.imgUrl = $MCI + tmpStr
    tmpStr = nil
  end
  
  tmpStr = cardTable.at("/tr/td[2]/h1/a")[:href]
  if tmpStr
    card.cardUrl = $MCI + tmpStr
    tmpStr = nil
  end
  
  tmpStr = cardTable.at("/tr/td[2]/h1/a").innerText
  if tmpStr 
    card.cardName = tmpStr
    tmpStr = nil
  end
  
  # first entry is cardType, rest is just extra info..
  extras = cardTable.search("/tr/td[2]/p[1]")
  extraInfo = []
  
  if extras
    #extra some times has a <sup><a href="#oracle">1</a></sup> --- REMOVE IT!
    extras.search("/sup").remove
    
    # break on <br/> or <br /> 
    lines = extras.to_s.split(/<br\s*\/?>/)
    
    if lines.length > 0
      card.cardType = lines[0].gsub(/<.*?>/, "").strip()
      
      #get extra info.. one per line
      for i in 1..(lines.length-1)
        
        extraInfo.push(lines[i].gsub(/<.*?>/, "").strip())
      end
    end
    card.extraInfo = extraInfo
  end
  
  # get card rulings
  rulings = cardTable.search("/tr/td[2]/p[2]/p")
  rules = []
  if rulings
    if rulings.to_s.include? "Gatherer Card Rulings"
      tmpRules = cardTable.search("/tr/td[2]/p[2]/ul/li")
      
      tmpRules.each do |r|
        rules.push(r.to_s.gsub(/<.*?>/, "").strip())
      end      
    end
    card.rulings = rules
  end
  
  # puts cardName
  # puts imgUrl
  # puts cardUrl
  # puts cardType
  # extraInfo.each do |ei|
    # puts ei
  # end
  # rules.each do |r|
    # puts r
  # end
  
  puts card.to_json
  
end



if ARGV[0] 
  searchString = ARGV[0] # pass this in
else
  searchString = 'cliffs'
end

#replace spaces with %20
searchString = searchString.gsub(" ", "%20")

url = $MCI + $query + searchString
doc = Hpricot(open(url))

elems = doc.search("/html/body/td/table")  # get all the tables on the cards column.

if elems.length == 0 then
  puts "Did not find any cards (or couldnt call web service?)"
  exit
end

# need to check the first table to see if we found multiple cards, or just one..
header = elems[0].search("/tr/td[2]")
singleCard = false
overTwenty = false

if header
  if header.at("b")
    # if found, we have card list
    singleCard = false
    # but could be 20+ entries, need to chedk for this.
    if elems[0].search("/tr/td[1]/a").inner_text == "next 20 cards"
      overTwenty = true
    end
  else
    # we found single card
    singleCard = true
  end
end

if singleCard
  getCard(elems[1])
else
  currentTable = 1
  tableCount = elems.length
  if overTwenty
    tableCount = elems.length - 1
  end
  
  puts "Found more than 20 entries, returning only first 20:"
  
  while currentTable < tableCount
    getCard(elems[currentTable])
    currentTable += 1
    puts "----"
  end
end

