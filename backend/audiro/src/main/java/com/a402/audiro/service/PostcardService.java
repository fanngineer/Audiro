package com.a402.audiro.service;


import com.a402.audiro.dto.PasswordDTO;
import com.a402.audiro.dto.PostcardDTO;
import com.a402.audiro.dto.PostcardDetailDTO;

public interface PostcardService {

    public void isValidPassword(String password);

    public void savePostcard(PostcardDTO postcardDTO);

    PostcardDetailDTO getPostcardDetail(PasswordDTO passwordDTO);
}
