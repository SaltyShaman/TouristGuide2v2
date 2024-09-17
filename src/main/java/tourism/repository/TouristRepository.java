package tourism.repository;

import org.springframework.stereotype.Repository;
import tourism.model.TouristAttraction;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristRepository {
    private final List<TouristAttraction> attractions = new ArrayList<>();

    public TouristRepository(){
        attractions.add(new TouristAttraction("Tivoli", "Amusement park in Copenhagen", List.of("Family", "Entertainment")));
        attractions.add(new TouristAttraction("Nyhavn", "Historic harbor in Copenhagen", List.of("History", "Food", "Shopping")));
        attractions.add(new TouristAttraction("Statens Museum for Kunst", "Modern art museum", List.of("Art", "Culture")));
        attractions.add(new TouristAttraction("Strøget", "Popular shopping street", List.of("Shopping", "Sightseeing")));
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