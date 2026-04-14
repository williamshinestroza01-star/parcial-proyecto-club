package com.example.parcialproyectoclub.service;

import com.example.parcialproyectoclub.model.Club;
import com.example.parcialproyectoclub.repository.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClubService {

    @Autowired
    private ClubRepository clubRepository;

    public List<Club> listarTodos() {
        return clubRepository.findAll();
    }

    public Club guardar(Club club) {
        return clubRepository.save(club);
    }

    public void eliminar(Long id) {
        clubRepository.deleteById(id);
    }

    public Club buscarPorId(Long id) {
        return clubRepository.findById(id).orElse(null);
    }
}