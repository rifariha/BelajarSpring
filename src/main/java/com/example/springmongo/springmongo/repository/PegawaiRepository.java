package com.example.springmongo.springmongo.repository;

import com.example.springmongo.springmongo.model.Pegawai;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Component
@Repository
public interface PegawaiRepository extends MongoRepository<Pegawai, String> {
    List<Pegawai> findByFirstNameContaining(String firstName);
    List<Pegawai> findAll();
}
