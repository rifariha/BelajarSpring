package com.example.springmongo.springmongo.controller;

import com.example.springmongo.springmongo.helper.ResponseBody;
import com.example.springmongo.springmongo.helper.UserExcelExporter;
import com.example.springmongo.springmongo.model.Pegawai;
import com.example.springmongo.springmongo.model.Tutorial;
import com.example.springmongo.springmongo.repository.PegawaiRepository;
import com.example.springmongo.springmongo.repository.TutorialRepository;
import com.example.springmongo.springmongo.storage.StorageService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/pegawai/")
public class PegawaiController {
    public final StorageService storageService;

    public final PegawaiRepository pegawaiRepository;
    public final TutorialRepository tutorialRepository;

    public ResponseBody response = new ResponseBody();

    @Autowired
    public PegawaiController(StorageService storageService, PegawaiRepository pegawaiRepository, TutorialRepository tutorialRepository) {
        this.storageService = storageService;
        this.pegawaiRepository = pegawaiRepository;
        this.tutorialRepository = tutorialRepository;
    }

    @PostMapping("/post")
    public ResponseEntity<ResponseBody> createPegawai(@RequestParam(required = true) String first_name, String last_name, String nip, MultipartFile photo) {

        try {
            String filename = storageService.store(photo);
            Pegawai pegawai = new Pegawai(first_name,last_name,nip,filename);

            Pegawai dataPegawai = pegawaiRepository.save(pegawai);

            ObjectId pegawaiId = new ObjectId(dataPegawai.getId());

            Tutorial tutorial = new Tutorial("jalan merdeka", "jalan jalan merdeka",true,124515, pegawaiId);
            System.out.println("isi tutorial : "+tutorial.toString());

            Tutorial dataTutor = tutorialRepository.save(tutorial);

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

    @GetMapping("/users/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Pegawai> listPegawai = pegawaiRepository.findAll();

        UserExcelExporter excelExporter = new UserExcelExporter(listPegawai);

        excelExporter.export(response);
    }

}
