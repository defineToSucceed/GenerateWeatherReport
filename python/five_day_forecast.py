import requests
import json

api_key = 'd1158b740a06486ee301974b05f77baf'
city = 'Chicago'
country = 'us'

def five_day_forecast():
    fd = requests.get('http://api.openweathermap.org/data/2.5/forecast?q=' + city + ',' + country + '&appid=' + api_key)
    dict = fd.json()
    select_data = dict['list']

    for box in select_data:
            print("CITY: " + dict["city"]["name"] + ", COUNTRY: " + dict["city"]["country"] + ", DATETIME: " + box['dt_txt'] + ", MIN_TEMP: " + str(box['main']['temp_min']) + ", MAX_TEMP: " + str(box['main']['temp_max']) + ", WEATHER: " + box['weather'][0]['description'])
            
five_day_forecast()
