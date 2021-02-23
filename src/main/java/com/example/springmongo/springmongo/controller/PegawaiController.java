package com.example.springmongo.springmongo.controller;

import com.example.springmongo.springmongo.helper.ResponseBody;
import com.example.springmongo.springmongo.model.Pegawai;
import com.example.springmongo.springmongo.repository.PegawaiRepository;
import com.example.springmongo.springmongo.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Validated
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
    public ResponseEntity<ResponseBody> createPegawai(@Valid @RequestBody(required = true) Pegawai pegawai) {

        try {
//            String filename = storageService.store(photo);
            Pegawai pegawais = new Pegawai(pegawai.getFirstName(),pegawai.getLastName(),pegawai.getNip(),"filename");
//            System.out.println("isi nama file nya"+filename);

            Pegawai dataPegawai = pegawaiRepository.save(pegawai);
            response.setCode(200);
            response.setMessage("Data Insert Successfull");
            response.setData(dataPegawai);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.setCode(403);
            response.setMessage(e.getMessage());
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

//    @GetMapping("/users/export/excel")
//    public void exportToExcel(HttpServletResponse response) throws IOException {
//        response.setContentType("application/octet-stream");
//        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
//        String currentDateTime = dateFormatter.format(new Date());
//
//        String headerKey = "Content-Disposition";
//        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
//        response.setHeader(headerKey, headerValue);
//
//        List<Pegawai> listPegawai = pegawaiRepository.findAll();
//
//        UserExcelExporter excelExporter = new UserExcelExporter(listUsers);
//
//        excelExporter.export(response);
//    }

}
