package com.example.springmongo.springmongo.repository;

import com.example.springmongo.springmongo.model.Pegawai;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PegawaiRepository extends MongoRepository<Pegawai, String> {
    List<Pegawai> findByFirstNameContaining(String firstName);
}
