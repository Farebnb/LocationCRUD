package com.cognizant.locationcrud.service;


import com.cognizant.locationcrud.model.Location;
import com.cognizant.locationcrud.repo.LocationRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    private final LocationRepo lr;

    public LocationService(LocationRepo lr){
        this.lr = lr;
    }

    public Location getById(int id){
        return lr.findById(id);
    }

    public List<Location> getAll() { return lr.findAll();}

    public void delete(int id){
        lr.deleteById(id);
    }

    public Location update(Location l){
        return lr.saveAndFlush(l);
    }
}