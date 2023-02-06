package com.a402.audiro.controller;

import com.a402.audiro.dto.GiftThumbnailDTO;
import com.a402.audiro.dto.ManitoDTO;
import com.a402.audiro.service.ManitoService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("manito")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Builder
public class ManitoController {

    private final ManitoService manitoService;

    @GetMapping
    public ResponseEntity<?> getManitoList(){
        try{
            List<GiftThumbnailDTO> manitoList = manitoService.getManitoList();
            return ResponseEntity.ok().body(manitoList);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> addManito(@RequestBody(required = true)ManitoDTO manitoDTO){
        try{
            manitoService.addManito(manitoDTO);
            return ResponseEntity.ok().body("Manito is uploaded");
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
