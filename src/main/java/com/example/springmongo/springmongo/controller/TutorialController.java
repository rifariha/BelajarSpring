package com.example.springmongo.springmongo.controller;

import com.example.springmongo.springmongo.model.Tutorial;
import com.example.springmongo.springmongo.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.springmongo.springmongo.helper.ResponseBody;

import java.util.*;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TutorialController {

    @Autowired
    TutorialRepository tutorialRepository;

    protected ResponseBody response = new ResponseBody();

    @PostMapping("/tutorials")
    public ResponseEntity<ResponseBody> createTutorial(@RequestBody Tutorial tutorial) {
        try {
            Tutorial _tutorial = tutorialRepository.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), false, tutorial.getPhone()));
            response.setCode(200);
            response.setMessage("Data Insert Successfull");
            response.setData(_tutorial);
            return new ResponseEntity<>(response, HttpStatus.CREATED); //style1
//            return new ResponseEntity<>(_tutorial, HttpStatus.CREATED); //style2
//            return ResponseEntity.accepted().body(response); //style3
        } catch (Exception e) {
            response.setCode(403);
            response.setMessage("Insert Failed");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping ("/tutorials")
    public ResponseEntity<ResponseBody> getAllTutorials(@RequestParam(required = false) String title, String description) {
        try {
            List<Tutorial> tutorials = new ArrayList<Tutorial>();

            if (title != null) {
                tutorials.addAll(tutorialRepository.findByTitleContaining(title));
            }
            else if(description != null)
            {
                tutorials.addAll(tutorialRepository.findByDescriptionLike(description));
            }
            else
            {
                tutorials.addAll(tutorialRepository.findAll());
            }

            if (tutorials.isEmpty()) {
                response.setCode(204);
                response.setMessage("Data is Empty");
                return new ResponseEntity<>(response, HttpStatus.OK);
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            response.setCode(200);
            response.setMessage("Data Successfully Retrieved");
            response.setData(tutorials);
            return new ResponseEntity<>(response, HttpStatus.OK);
//            return ResponseEntity.accepted().body(response);
//            return new ResponseEntity<>(tutorials, HttpStatus.OK);
        } catch (Exception e) {
            response.setCode(500);
            response.setMessage("Internal Server Error");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tutorials/{id}")
    public ResponseEntity<ResponseBody> getTutorialById(@PathVariable("id") String id) {
        Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

        if (tutorialData.isPresent()) {
            response.setCode(200);
            response.setMessage("Data Successfully Retrieved");
            response.setData(tutorialData);

            return ResponseEntity.accepted().body(response);
//            return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
        } else {
            response.setCode(404);
            response.setMessage("Data Not Found");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/tutorials/published")
    public ResponseEntity<ResponseBody> findByPublished() {
        List<Tutorial> tutorials;
        try {
//            tutorials = new ArrayList<Tutorial>();
            tutorials = tutorialRepository.findByPublished(true);
            System.out.println(tutorials);

            response.setCode(200);
            response.setMessage("Data Successfully Retrieved");
            response.setData(tutorials);

            return new ResponseEntity<>(response,HttpStatus.OK);
//            return ResponseEntity.accepted().body(response);
        } catch (Exception e) {
            response.setCode(500);
            response.setMessage(String.valueOf(e));
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//            return ResponseEntity.accepted().body(response);
        }
    }

    @PutMapping("/tutorials/{id}")
    public ResponseEntity<Object> updateTutorial(@PathVariable("id") String id, @RequestBody Tutorial tutorial) {
        Optional<Tutorial> tutorialData = tutorialRepository.findById(id);
        if (tutorialData.isPresent()) {
            Tutorial _tutorial = tutorialData.get();
            _tutorial.setTitle(tutorial.getTitle());
            _tutorial.setDescription(tutorial.getDescription());
            _tutorial.setPublished(tutorial.isPublished());
            return new ResponseEntity<>(tutorialRepository.save(_tutorial), HttpStatus.OK);
        } else {
            response.setCode(404);
            response.setMessage("Data Not found");
            response.setData(null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//            return ResponseEntity.accepted().body(response);
        }
    }

    @DeleteMapping("/tutorials/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") String id) {
        try {
            Optional<Tutorial> checkData = tutorialRepository.findById(id);
            if(checkData.isPresent())
            {
                tutorialRepository.deleteById(id);
                response.setCode(200);
                response.setMessage("Delete Successfull");
                return new ResponseEntity(response, HttpStatus.OK);
            }
            else
            {
               response.setCode(404);
               response.setMessage("Data not found");
               response.setData(null);
               return new ResponseEntity(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.setCode(500);
            response.setMessage(String.valueOf(e));
            return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/tutorials")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        try {
            tutorialRepository.deleteAll();
            response.setCode(200);
            response.setMessage("All Data has been deleted");
//            response.setData(null);
            return new ResponseEntity(response,HttpStatus.OK);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            response.setCode(500);
            response.setMessage(String.valueOf(e));
            response.setData(null);
            return new ResponseEntity(response,HttpStatus.INTERNAL_SERVER_ERROR);
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}