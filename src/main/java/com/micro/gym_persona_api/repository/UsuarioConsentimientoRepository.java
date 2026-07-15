package com.micro.gym_persona_api.repository;

import com.micro.gym_persona_api.model.UsuarioConsentimiento;
import com.micro.gym_persona_api.model.UsuarioConsentimientoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioConsentimientoRepository extends JpaRepository<UsuarioConsentimiento, UsuarioConsentimientoId> {

    List<UsuarioConsentimiento> findById_UsuId(Long usuId);

    Optional<UsuarioConsentimiento> findById_UsuIdAndId_ConId(Long usuId, Long conId);

    boolean existsById_UsuIdAndId_ConId(Long usuId, Long conId);

    @Query("SELECT uc FROM UsuarioConsentimiento uc WHERE uc.consentimiento.conId = :conId")
    List<UsuarioConsentimiento> findByConsentimientoConId(@Param("conId") Long conId);
}