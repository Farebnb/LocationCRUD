package com.cognizant.locationcrud.controller;

import com.cognizant.locationcrud.model.Location;
import com.cognizant.locationcrud.repo.LocationRepo;
import com.cognizant.locationcrud.service.LocationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/location")
@CrossOrigin(origins = "*")
public class LocationController {

    @Autowired
    private LocationRepo lr;

    @Autowired
    private LocationService ls;

    @Autowired
    private LocationController(LocationRepo lr, LocationService ls){
        this.lr = lr;
        this.ls = ls;
    }

    @GetMapping("/")
    public ResponseEntity<Location> getById(@RequestParam int id){
        return new ResponseEntity<>(ls.getById(id), HttpStatus.ACCEPTED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Location>> getAll(){
        return new ResponseEntity<>(ls.getAll(),HttpStatus.ACCEPTED);
    }


    @PutMapping("/update")
    public ResponseEntity<Location> deleteLocation(@PathVariable("id") int id){
        Location l = ls.getById(id);
        if(l == null){
            return ResponseEntity.notFound().build();
        }

        ls.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
