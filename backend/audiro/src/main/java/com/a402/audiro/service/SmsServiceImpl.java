package com.a402.audiro.service;

import com.a402.audiro.dto.PostcardDTO;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@PropertySource("classpath:sms-key-setting.properties")
public class SmsServiceImpl implements SmsService{

    @Value("${api.key}")
    private String apiKey;

    @Value("${api.secret.key}")
    private String apiSecretKey;

    @Value("${phone.number}")
    private String number;

    private DefaultMessageService messageService;

    @PostConstruct
    private void init(){
        this.messageService = NurigoApp.INSTANCE.initialize(apiKey, apiSecretKey, "https://api.coolsms.co.kr");
    }

    @Override
    public void sendMessage(PostcardDTO postcardDTO) {
        Message message = new Message();
        postcardDTO.init();

        message.setFrom(number);
        message.setTo(postcardDTO.getPhoneNumber());
        message.setText(postcardDTO.getMessage());

        SingleMessageSentResponse response = this.messageService.sendOne(new SingleMessageSendingRequest(message));

        log.info(response.toString());
        log.info(response.component8());
    }

}
