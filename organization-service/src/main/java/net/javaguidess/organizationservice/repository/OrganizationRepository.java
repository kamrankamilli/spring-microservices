package net.javaguidess.organizationservice.repository;

import net.javaguidess.organizationservice.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization,Long> {
    Optional<Organization> findByCode(String organizationCode);
}
