package com.msgrande.infraestructure.dao;


import com.msgrande.infraestructure.entity.EmpleadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpleadoRepository extends JpaRepository<EmpleadoEntity, Long> {
    Optional<EmpleadoEntity> findByNumDoc(String numDoc);
    Boolean existsByNumDoc(String numDoc);
}
