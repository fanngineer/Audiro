package com.a402.audiro.service;

import com.a402.audiro.dto.NaverSmsMessageDTO;
import com.a402.audiro.dto.NaverSmsRequestDTO;
import com.a402.audiro.dto.NaverSmsResponseDTO;
import com.a402.audiro.dto.PostcardDTO;
import com.a402.audiro.exception.NaverSmsException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Primary
@Service
public class SmsServiceNaver implements SmsService{

    @Value("${naver-cloud-sms.accessKey}")
    private String accessKey;

    @Value("${naver-cloud-sms.secretKey}")
    private String secretKey;

    @Value("${naver-cloud-sms.serviceId}")
    private String serviceId;

    @Value("${naver-cloud-sms.senderPhone}")
    private String phone;

    private String makeSignature(Long time) throws NoSuchAlgorithmException, UnsupportedEncodingException, InvalidKeyException {
        String space = " ";
        String newLine = "\n";
        String method = "POST";
        String url = "/sms/v2/services/"+ this.serviceId+"/messages";
        String timestamp = time.toString();
        String accessKey = this.accessKey;
        String secretKey = this.secretKey;

        String message = new StringBuilder()
                .append(method)
                .append(space)
                .append(url)
                .append(newLine)
                .append(timestamp)
                .append(newLine)
                .append(accessKey)
                .toString();

        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);

        byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
        String encodeBase64String = Base64.encodeBase64String(rawHmac);

        return encodeBase64String;
    }

    public NaverSmsResponseDTO sendSMS(NaverSmsMessageDTO messageDTO) throws JsonProcessingException, RestClientException, URISyntaxException, InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException{
        Long time = System.currentTimeMillis();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("x-ncp-apigw-timestamp", time.toString());
        headers.set("x-ncp-iam-access-key", accessKey);
        headers.set("x-ncp-apigw-signature-v2", makeSignature(time));

        List<NaverSmsMessageDTO> messages = new ArrayList<>();
        messages.add(messageDTO);

        NaverSmsRequestDTO request = NaverSmsRequestDTO.builder()
                .type("LMS")
                .contentType("COMM")
                .countryCode("82")
                .from(phone)
                .content(messageDTO.getContent())
                .messages(messages)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(request);
        HttpEntity<String> httpBody = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        NaverSmsResponseDTO response = restTemplate.postForObject(new URI("https://sens.apigw.ntruss.com/sms/v2/services/"+ serviceId +"/messages"), httpBody, NaverSmsResponseDTO.class);

        return response;
    }

    @Override
    public void sendMessage(PostcardDTO postcardDTO) {
        postcardDTO.init();

        log.info("Naver API를 이용해 SMS 발송을 시작합니다.");
        NaverSmsMessageDTO smsMessageDTO = NaverSmsMessageDTO.builder()
                .to(postcardDTO.getPhoneNumber())
                .content(postcardDTO.getMessage())
                .build();

        try{
            NaverSmsResponseDTO smsResponseDTO = sendSMS(smsMessageDTO);
            log.info("메세지 전송에 성공했습니다 : " + smsResponseDTO.getStatusName());

        }catch(Exception e){
            log.error(e.getMessage());
            throw new NaverSmsException(e.getMessage());
        }

    }
}
