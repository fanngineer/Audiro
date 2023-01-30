package com.a402.audiro.config.oauth.provider;

public interface OAuth2UserInfo {
    String getEmail();
    String getName();
    String getProviderId();
}
