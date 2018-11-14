import requests
import json

api_key = 'd1158b740a06486ee301974b05f77baf'
city = 'Boston'
country = 'us'

def five_day_forecast():
    fd = requests.get('http://api.openweathermap.org/data/2.5/forecast?q=' + city + ',' + country + '&appid=' + api_key)
    print(fd.json())
    return(fd.json())

five_day_forecast()
