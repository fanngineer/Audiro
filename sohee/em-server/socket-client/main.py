# https://github.com/GlassyWing/stomp_ws_py

import time

from client import Client
import logging

def print_frame(frame):
    # print(json.loads(frame.body))
    print(frame.body)
    print(frame.command)
    print(frame.headers)
    global token
    token = frame.body
    print("token : " + token)

if __name__ == '__main__':

    spot_id = 25;
    ws_url = 'ws://localhost:8080/notifications/websocket'
    global token
    token = ''

    LOG_FORMAT = "%(asctime)s - %(levelname)s - %(message)s"
    # logging.basicConfig(level=logging.DEBUG, format=LOG_FORMAT)

    # open transport
    client = Client(ws_url)

    client.connect()

    # subscribe channel
    client.subscribe("/sub/" + str(spot_id), callback=print_frame)

    # time.sleep(300)

    while token == '':
        pass

    client.disconnect()
