package com.example.springmongo.springmongo.controller;

import com.example.springmongo.springmongo.helper.ResponseBody;
import com.example.springmongo.springmongo.model.Pegawai;
import com.example.springmongo.springmongo.repository.PegawaiRepository;
import com.example.springmongo.springmongo.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/pegawai/")
public class PegawaiController {
    private final StorageService storageService;

    @Autowired
    PegawaiRepository pegawaiRepository;

    protected ResponseBody response = new ResponseBody();

    public PegawaiController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/post")
    public ResponseEntity<ResponseBody> createPegawai(@RequestParam(required = true) String first_name, String last_name, String nip, MultipartFile photo) {

        try {
            String filename = storageService.store(photo);
            Pegawai pegawai = new Pegawai(first_name,last_name,nip,filename);
            System.out.println("isi nama file nya"+filename);

            Pegawai dataPegawai = pegawaiRepository.save(pegawai);
            response.setCode(200);
            response.setMessage("Data Insert Successfull");
            response.setData(dataPegawai);
            return new ResponseEntity<>(response, HttpStatus.CREATED); //style1
//            return new ResponseEntity<>(_tutorial, HttpStatus.CREATED); //style2
//            return ResponseEntity.accepted().body(response); //style3
        } catch (Exception e) {
            response.setCode(403);
            response.setMessage(e.getMessage());
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

}
