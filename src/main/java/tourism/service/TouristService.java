package tourism.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import tourism.model.TouristAttraction;
import tourism.repository.TouristRepository;



import java.util.*;

@Service
public class TouristService {

    private static TouristRepository touristRepository = new TouristRepository();

    @Autowired
    public TouristService(TouristRepository touristRepository) {
        this.touristRepository = touristRepository;
    }

    public static Set<String> getAllTags() {
        return touristRepository.getAllTags();
    }

    // Get all attractions
    public List<TouristAttraction> getAllAttractions() {
        return touristRepository.getAllAttractions();
    }
    
    public static Set<String> getAllDistricts() {
        return touristRepository.getAllDistrict();
    }
    
    // Get an attraction by name
    public TouristAttraction getAttractionByName(String name) {
        return touristRepository.getAttractionByName(name);
    }

    // Add a new attraction
    public void addAttraction(TouristAttraction attraction) {
        touristRepository.addAttraction(attraction);
    }

    // Update an existing attraction
    public void updateAttraction(TouristAttraction updatedAttraction) {
        touristRepository.updateAttraction(updatedAttraction);
    }

    // Delete an attraction by name
    public void deleteAttraction(String name) {
        touristRepository.deleteAttraction(name);
    }

    public Set<String> getTags() {
        return touristRepository.getAllTags();

    }
    public Set<String> getDescription() {
        return touristRepository.getAllDescription();
    }
}

