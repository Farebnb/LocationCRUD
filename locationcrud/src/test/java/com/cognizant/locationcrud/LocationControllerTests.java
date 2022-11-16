package com.cognizant.locationcrud;

import com.cognizant.locationcrud.model.Location;
import com.cognizant.locationcrud.repo.LocationRepo;
import com.cognizant.locationcrud.service.LocationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import java.util.LinkedHashMap;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = LocationcrudApplication.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class LocationControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LocationService ls;

    @Autowired
    private LocationRepo lr;

    private ObjectMapper om = new ObjectMapper();

    @Test
    @Transactional
    public void getByIdTest() throws Exception{
        mockMvc.perform(get("/location/?id=1001"))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.id").value(1001));
    }

    @Test
    @Transactional
    public void getAllTest() throws Exception{

        mockMvc.perform(get("/location/all"))
                .andDo(print())
                .andExpect(status().isAccepted());

    }

    @Test
    @Transactional
    public void createLocationTest() throws Exception{

        LinkedHashMap<String,String> locationBody = new LinkedHashMap<>();
        locationBody.put("city","toronto");
        locationBody.put("country","canada");
        locationBody.put("zip","1234");
        mockMvc.perform(post("/location/create").contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(locationBody)))
                .andDo(print())
                .andExpect(status().isAccepted());
    }
    @Test
    @Transactional
    public void deleteLocationTest() throws Exception{
        mockMvc.perform(delete("/location/delete/?id=1001"))
                .andDo(print())
                .andExpect(status().isAccepted());

    }

    @Test
    @Transactional
    public void updateListingTest() throws Exception{
        Location l = new Location(1001,"York New","America", 12345);

        mockMvc.perform(put("/location/update").contentType(MediaType.APPLICATION_JSON)
                        .content(om.writeValueAsString(l)))
                .andDo(print())
                .andExpect(status().isAccepted());
    }

}
