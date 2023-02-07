import requests

local_url = "http://localhost:8080"

request_header = {
    'Auth': 'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9VU0VSIiwidXNlcklkIjo1LCJuaWNrTmFtZSI6IuyCrOyaqeyekDUiLCJpYXQiOjE2NzU3NjU2NDYsImV4cCI6MTY3NTc3MTY0Nn0.b66aiYT0VmVFJzsRV1t2o3JCoINxuhFCixKwJ0jm5N8'
}

postcard_url = local_url + "/api/postcard"
postcard_param = {'postcardId' : 1}

response = requests.get(postcard_url, headers= request_header, params=postcard_param)

res_json = response.json()
print(res_json.get('id'))
print(res_json.get('postcardImg'))
print(res_json.get('song'))
print(res_json.get('singer'))
print(res_json.get('song_Url'))
print(res_json.get('regTime'))