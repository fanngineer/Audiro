# from stomp import StompClient
#
# #Instantiate the class with all the valid parameters
# stomp_client = StompClient(...)
# # Runs until interrupt
# stomp_client.create_connection()

import json
import time

from client import Client
import logging


def print_frame(frame):
    print(json.loads(frame.body))
    client.disconnect()


if __name__ == '__main__':
    LOG_FORMAT = "%(asctime)s - %(levelname)s - %(message)s"
    logging.basicConfig(level=logging.DEBUG, format=LOG_FORMAT)

    # open transport
    client = Client("ws://localhost:8080/notifications/websocket")

    # connect to the endpoint
    # client.connect(login="name",
    #                passcode="45C82C421EBA87C8131E220F878E4145",
    #                timeout=0)
    client.connect()

    # subscribe channel
    client.subscribe("/sub/25", callback=print_frame)

    # send msg to channel
    client.send("/pub/25", body=json.dumps({'message': 'tom'}))


