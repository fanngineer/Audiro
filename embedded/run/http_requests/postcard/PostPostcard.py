import requests
import json

local_url = "http://localhost:8080"

request_header = {
    'Auth': 'eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9VU0VSIiwidXNlcklkIjo1LCJuaWNrTmFtZSI6IuyCrOyaqeyekDUiLCJpYXQiOjE2NzU3NzE3NjcsImV4cCI6MTY3NTc3Nzc2N30.VxF8ZErUiHfO-o6JGQTQymoFI3hHWfZ3tm8Z06fSYqo',
    'Content-Type': 'application/json; charset=utf-8'
}

postcard_url = local_url + "/api/postcard"
phone_number = ""

postcard_data = {
    'nickname' : 'sh',
    'phoneNumber' : phone_number,
    'passwd' : 'python~~',
    'songId' : 1,
    'spotId' : 1,
    'postcardImg' : 'img path string,,,,'
}

pd = json.dumps(postcard_data)

response = requests.post(postcard_url, headers=request_header, data=pd)

print(response.text)