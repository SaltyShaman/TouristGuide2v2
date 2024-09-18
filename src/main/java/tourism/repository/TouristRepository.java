package tourism.repository;

import org.springframework.stereotype.Repository;
import tourism.controller.TouristController;
import tourism.model.TouristAttraction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class TouristRepository {
    public TouristController touristController;

    public TouristController getTouristController() {
        return touristController;
    }

    private final List<TouristAttraction> attractions = new ArrayList<>();

    public TouristRepository(){
        attractions.add(new TouristAttraction("Tivoli", "Amusement park in Copenhagen", List.of("Vesterbro") ,List.of("Family", "Entertainment")));
        attractions.add(new TouristAttraction("Nyhavn", "Historic harbor in Copenhagen", List.of("København H") ,List.of("History", "Food", "Shopping")));
        attractions.add(new TouristAttraction("Statens Museum for Kunst", "Modern art museum", List.of("Østerbro") ,List.of("Art", "Culture")));
        attractions.add(new TouristAttraction("Strøget", "Popular shopping street", List.of("København H") ,List.of("Shopping", "Sightseeing")));
    }
    public Set<String> getAllTags() {
        Set<String> tags = new HashSet<>();
        for (TouristAttraction attraction : attractions) {
            tags.addAll(attraction.getTags());  // Tilføj tags til sættet
        }
        return tags;
    }
    public Set<String> getAllDescription() {
        Set<String> descriptions = new HashSet<>();
        for (TouristAttraction attraction : attractions) {
            descriptions.addAll(attraction.getDistrict());
        }
        return descriptions;
    }

        // manipulate list
        public List<TouristAttraction> getAllAttractions() {
            return new ArrayList<>(attractions);
        }


    public TouristAttraction getAttractionByName(String name) {
        return attractions.stream()
                .filter(attraction -> attraction.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public void addAttraction(TouristAttraction attraction) {
        attractions.add(attraction);
    }
        // update
    public void updateAttraction(String name, TouristAttraction updatedAttraction) {
        for (int i = 0; i < attractions.size(); i++) {
            if (attractions.get(i).getName().equalsIgnoreCase(name)) {
                attractions.set(i, updatedAttraction);
                return;
            }
        }
    }

    public void deleteAttraction(String name) {
        attractions.removeIf(attraction -> attraction.getName().equalsIgnoreCase(name));
    }

}