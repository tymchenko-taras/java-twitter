package com.example.sweater.service;

import com.example.sweater.model.dto.CaptchaResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class CaptchaService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${captcha.sitekey}")
    private String captchaSiteKey;

    @Value("${captcha.secret}")
    private String captchaSecret;

    private String url = "https://www.google.com/recaptcha/api/siteverify";

    public String getSiteKey(){
        return captchaSiteKey;
    }

    public boolean isSuccess(
            String captchaResponse
    ){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        map.add("secret", captchaSecret);
        map.add("response", captchaResponse);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
        CaptchaResponseDto response = restTemplate.postForObject(url, request, CaptchaResponseDto.class);

        return response.isSuccess();
    }
}
