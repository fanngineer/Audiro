package com.example.websocket.messagingstompwebsocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.HtmlUtils;

@Controller
@Slf4j
public class GreetingController {
//
//    @MessageMapping("/{channel}")
//    @SendTo("/topic/{channel}")
//    public Greeting greeting(@DestinationVariable("channel") String channel, HelloMessage message) throws Exception{
//        Thread.sleep(500);
//        log.warn("channel" + channel);
//        return new Greeting(HtmlUtils.htmlEscape(message.getName()) + ">> " + HtmlUtils.htmlEscape(message.getMessage()));
//    }

}
