package com.example.solaceToDB.SolaceToDB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolaceToDBRepository extends JpaRepository<AiroplaneEventData, Integer> {

}
