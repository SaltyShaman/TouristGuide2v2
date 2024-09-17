package tourism.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import tourism.model.TouristAttraction;
import tourism.repository.TouristRepository;



import java.util.*;

@Service
public class TouristService {

    private final TouristRepository touristRepository;

    //@Autowired
    public TouristService(TouristRepository touristRepository) {
        this.touristRepository = touristRepository;
    }

    // Get all attractions
    public List<TouristAttraction> getAllAttractions() {
        return touristRepository.getAllAttractions();
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
    public void updateAttraction(String name, TouristAttraction updatedAttraction) {
        touristRepository.updateAttraction(name, updatedAttraction);
    }

    // Delete an attraction by name
    public void deleteAttraction(String name) {
        touristRepository.deleteAttraction(name);
    }
}
