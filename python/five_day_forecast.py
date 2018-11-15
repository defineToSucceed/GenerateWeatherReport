import requests
import json
import os
import sys

api_key = 'd1158b740a06486ee301974b05f77baf'
city = sys.argv[1]
country = sys.argv[2]

def five_day_forecast():
    fd = requests.get('http://api.openweathermap.org/data/2.5/forecast?q=' + city + ',' + country + '&appid=' + api_key)
    dict = fd.json()

    file = open("5day_forecast.txt","w")

    if (dict["cod"] == "200"):
        select_data = dict['list']

        for box in select_data:
            ## print("CITY: " + dict["city"]["name"] + ", COUNTRY: " + dict["city"]["country"] + ", DATETIME: " + box['dt_txt'] + ", MIN_TEMP: " + str(box['main']['temp_min']) + ", MAX_TEMP: " + str(box['main']['temp_max']) + ", WEATHER: " + box['weather'][0]['description'])
            file.write("CITY: " + dict["city"]["name"] + ", COUNTRY: " + dict["city"]["country"] + ", DATETIME: " + box['dt_txt'] + ", MIN_TEMP: " + str(box['main']['temp_min']) + ", MAX_TEMP: " + str(box['main']['temp_max']) + ", WEATHER: " + box['weather'][0]['description'] +"\n")
    else:
        file.write("City/Country combination does not exist")

    file.close()
    
def delete_existing_file():
    if os.path.exists("5day_forecast.txt"):
          os.remove("5day_forecast.txt")
    else:
          print("5day_forecast.txt does not exist")

delete_existing_file()
five_day_forecast()
