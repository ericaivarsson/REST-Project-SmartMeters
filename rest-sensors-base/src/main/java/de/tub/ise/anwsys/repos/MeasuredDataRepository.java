package de.tub.ise.anwsys.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import de.tub.ise.anwsys.models.MeasuredData;

public interface MeasuredDataRepository extends JpaRepository<MeasuredData, Long> {
}
