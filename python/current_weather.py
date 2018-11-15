import requests
import json
import os
import sys

api_key = 'd1158b740a06486ee301974b05f77baf'
city = sys.argv[1]
country = sys.argv[2]

def current_weather():
    cw = requests.get('http://api.openweathermap.org/data/2.5/weather?q=' + city + ',' + country + '&appid=' + api_key)
    data = cw.json()
    ## print(data["cod"])
    
    file = open("current_weather.txt","w")

    if (data["cod"] == "404"):
            file.write("City/Country combination does not exist")
    else:
            ## print("CITY: " + data["name"] + ", COUNTRY: " + data["sys"]["country"] + ", WEATHER: " + data["weather"][0]["description"] + ", MIN_TEMP: " + str(data["main"]["temp_min"]) + ", MAX_TEMP: " + str(data["main"]["temp_max"])) 
            file.write("CITY: " + data["name"] + ", COUNTRY: " + data["sys"]["country"] + ", WEATHER: " + data["weather"][0]["description"] + ", MIN_TEMP: " + str(data["main"]["temp_min"]) + ", MAX_TEMP: " + str(data["main"]["temp_max"])) 
    
    file.close()
    
def delete_existing_file():
    if os.path.exists("current_weather.txt"):
          os.remove("current_weather.txt")
    else:
          print("current_weather.txt does not exist")

delete_existing_file()
current_weather()



