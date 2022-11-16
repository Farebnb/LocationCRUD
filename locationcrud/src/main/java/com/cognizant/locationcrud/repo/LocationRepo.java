package com.cognizant.locationcrud.repo;

import com.cognizant.locationcrud.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepo extends JpaRepository<Location, Integer> {

    Location findById(int id);

    List<Location> findAll();
}
