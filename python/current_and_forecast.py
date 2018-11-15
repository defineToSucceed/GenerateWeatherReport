'''
Capture and Display Current Weather and 5-Day Forecast for an given city/country combination
'''

# Built-in/Generic Imports
import requests
import json
import os
import sys

__author__ = '{nnarendra}'

## global variables
api_key = 'd1158b740a06486ee301974b05f77baf'
city = ''
country = ''
file

## current_weather Function - returning current weather for the city/country combination
def current_weather():
    cw = requests.get('http://api.openweathermap.org/data/2.5/weather?q=' + city + ',' + country + '&appid=' + api_key)
    data = cw.json()

    if (data["cod"] == "404"):
            file.write("City/Country combination does not exist to display CURRENT WEATHER\n")
    else:
            file.write("CURRENT WEATHER for City: " + data["name"] + ", Country: " + data["sys"]["country"] +"\n")
            ## print("CITY: " + data["name"] + ", COUNTRY: " + data["sys"]["country"] + ", WEATHER: " + data["weather"][0]["description"] + ", MIN_TEMP: " + str(data["main"]["temp_min"]) + ", MAX_TEMP: " + str(data["main"]["temp_max"])) 
            file.write("CITY: " + data["name"] + ", COUNTRY: " + data["sys"]["country"] + ", WEATHER: " + data["weather"][0]["description"] + ", MIN_TEMP: " + str(data["main"]["temp_min"]) + ", MAX_TEMP: " + str(data["main"]["temp_max"]) +"\n\n\n") 

## 5 day forecast Function - returning 5 day forecast for the city/country combination
def five_day_forecast():
    fd = requests.get('http://api.openweathermap.org/data/2.5/forecast?q=' + city + ',' + country + '&appid=' + api_key)
    dict = fd.json()

    if (dict["cod"] == "200"):
        select_data = dict['list']

        file.write("FIVE DAY FORECAST for City: " + dict["city"]["name"] + ", Country: " + dict["city"]["country"] +"\n")
        for box in select_data:
            ## print("CITY: " + dict["city"]["name"] + ", COUNTRY: " + dict["city"]["country"] + ", DATETIME: " + box['dt_txt'] + ", MIN_TEMP: " + str(box['main']['temp_min']) + ", MAX_TEMP: " + str(box['main']['temp_max']) + ", WEATHER: " + box['weather'][0]['description'])
            file.write("CITY: " + dict["city"]["name"] + ", COUNTRY: " + dict["city"]["country"] + ", DATETIME: " + box['dt_txt'] + ", MIN_TEMP: " + str(box['main']['temp_min']) + ", MAX_TEMP: " + str(box['main']['temp_max']) + ", WEATHER: " + box['weather'][0]['description'] +"\n")
    else:
        file.write("City/Country combination does not exist to display FIVE DAY FORECAST\n")

## Results file refresh Function 
def delete_existing_file():
    if os.path.exists("results.txt"):
          os.remove("results.txt")

## Main program flow

# check for entry of command line arguments
if len (sys.argv) == 3:
    city = sys.argv[1]
    country = sys.argv[2]

    # refresh Results.txt
    delete_existing_file()
    # reset Results.txt
    file = open("results.txt","w")

    # retrieve Current Weather
    current_weather()
    # retrieve 5-Day Forecast
    five_day_forecast()
    # close Results.txt
    file.close()
else:
    print("Enter City and Country combination")
    sys.exit (1)
