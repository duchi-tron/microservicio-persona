package com.micro.gym_persona_api.repository;

import com.micro.gym_persona_api.model.Consentimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsentimientoRepository extends JpaRepository<Consentimiento, Long> {

    Consentimiento findByConVersionDocumento(String versionDocumento);

    boolean existsByConVersionDocumento(String versionDocumento);
}
