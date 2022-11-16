package com.cognizant.locationcrud;

import com.cognizant.locationcrud.model.Location;
import com.cognizant.locationcrud.repo.LocationRepo;
import com.cognizant.locationcrud.service.LocationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = LocationcrudApplication.class)
@AutoConfigureMockMvc
public class LocationServiceTests {

    @MockBean
    LocationRepo lr;

    @Autowired
    LocationService ls = new LocationService(lr);

    @Test
    public void getByIdTest(){
        Location testLocation = new Location(1, "Toronto", "Canada", 123);
        when((lr).findById(anyInt())).thenReturn(testLocation);
        Location result = ls.getById(1);
        assertEquals(testLocation.getId(),result.getId(),"pass");
    }
    @Test
    public void getAllTest(){
        List<Location> locations = new ArrayList<>();
        Location testLocation = new Location(1, "Toronto", "Canada", 123);
        locations.add(testLocation);
        when((lr).findAll()).thenReturn(locations);
        List<Location> result = ls.getAll();
        assertEquals(testLocation.getId(),result.get(0).getId(),"pass");
    }

    @Test
    public void createLocationTest() {

        Location testLocation = new Location(1, "toronto", "canada", 1234);
        when(lr.save(any())).thenReturn(testLocation);
        Location result = ls.createLocation("toronto","canada",1234);
        assertEquals(testLocation.getId(), result.getId(), "pass");
    }

    @Test
    public void deleteLocationTest(){
        doNothing().when(lr).deleteById(anyInt());
        ls.delete(1001);
        verify(lr).deleteById(anyInt());

    }

    @Test
    public void updateLocationTest(){
        Location testLocation = new Location(1001, "montreal", "canada", 1234);
        when((lr).saveAndFlush(any())).thenReturn(testLocation);
        Location result = ls.update(testLocation);
        assertEquals(testLocation.getId(),result.getId(),"pass");
        assertEquals(testLocation.getCountry(),result.getCountry(),"pass");

    }



}
