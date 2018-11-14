import requests
import json
import os

api_key = 'd1158b740a06486ee301974b05f77baf'
city = 'Chicago'
country = 'us'

def current_weather():
    cw = requests.get('http://api.openweathermap.org/data/2.5/weather?q=' + city + ',' + country + '&appid=' + api_key)
    data = cw.json()
    
    file = open("current_weather.txt","w")
    ## print("CITY: " + data["name"] + ", COUNTRY: " + data["sys"]["country"] + ", WEATHER: " + data["weather"][0]["description"] + ", MIN_TEMP: " + str(data["main"]["temp_min"]) + ", MAX_TEMP: " + str(data["main"]["temp_max"])) 
    file.write("CITY: " + data["name"] + ", COUNTRY: " + data["sys"]["country"] + ", WEATHER: " + data["weather"][0]["description"] + ", MIN_TEMP: " + str(data["main"]["temp_min"]) + ", MAX_TEMP: " + str(data["main"]["temp_max"])) 
    file.close()
    
def delete_existing_file():
    if os.path.exists("current_weather.txt"):
          os.remove("current_weather.txt")
    else:
          print("current_weather.txt does not exist")

    
current_weather()
   

## {u'clouds': {u'all': 75}, u'name': u'Boston', u'visibility': 16093,
##  u'sys': {u'country': u'US', u'sunset': 1542144187, u'message': 0.0055, u'type': 1, u'id': 1273, u'sunrise': 1542108825},
##  u'weather': [{u'main': u'Clouds', u'id': 803, u'icon': u'04n', u'description': u'broken clouds'}],
##  u'coord': {u'lat': 42.36, u'lon': -71.06}, u'base': u'stations', u'dt': 1542150660,
##  u'main': {u'pressure': 1008, u'temp_min': 276.45, u'temp_max': 283.15, u'temp': 279.9, u'humidity': 76}, u'id': 4930956,
##  u'wind': {u'gust': 8.2, u'speed': 5.7, u'deg': 310},
##  u'cod': 200}



