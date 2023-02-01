package com.example.websocket.messagingstompwebsocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class GreetingController {

    @MessageMapping("/{channel}")
    @SendTo("/topic/{channel}")
    public Greeting greeting(HelloMessage message) throws Exception{
        Thread.sleep(500);
        return new Greeting(HtmlUtils.htmlEscape(message.getName()) + ">> " + HtmlUtils.htmlEscape(message.getMessage()));
    }

}
