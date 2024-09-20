package tourism.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tourism.model.TouristAttraction;
import tourism.repository.TouristRepository;
import tourism.service.TouristService;

import java.util.List;
import java.util.Set;

@Controller
public class TouristController {

    private final TouristService touristService;
    private final TouristRepository touristRepository;

    // @Autowired
    public TouristController(TouristService touristService, TouristRepository touristRepository) {
        this.touristService = touristService;
        this.touristRepository = touristRepository;
    }

    @GetMapping("/")
    public String showAttractions(Model model) {
        List<TouristAttraction> attractions = touristService.getAllAttractions();
        model.addAttribute("attractions", attractions);
        return "attractionList";  // Return the view for listing attractions
    }

    @GetMapping("/createAttraction")
    public String createAttraction(Model model) {
        Set<String> allTowns = touristRepository.getAllDistrict(); // Hent alle towns
        Set<String> allTags = touristRepository.getAllTags(); // Hent alle tags
        model.addAttribute("descriptions", allTowns);
        model.addAttribute("tags", allTags);
        return "createAttraction";
    }

    @GetMapping("/addAttraction")
    public String showAttractionForm(Model model) {
        Set<String> allTowns = touristService.getAllDistricts(); // Get all districts
        Set<String> allTags = touristService.getAllTags();       // Get all tags
        model.addAttribute("descriptions", allTowns);
        model.addAttribute("tags", allTags);
        return "createAttraction";  // Return the form page
    }

    @PostMapping("/addAttraction")
    public String addAttraction(@RequestParam("navn") String name,
                                @RequestParam("beskrivelse") String description,
                                @RequestParam("description") String town,
                                @RequestParam("tags") List<String> tags) {
        // Create a new tourist attraction object based on form data
        TouristAttraction newAttraction = new TouristAttraction(name, description, List.of(town), tags);

        // Save the new attraction using the service layer
        touristService.addAttraction(newAttraction);

        // Redirect to the main page (or attraction list) after submission
        return "redirect:/attractionList";
    }


    @GetMapping("/tags/{name}")
    public String showAttractionDetails(@PathVariable String name, Model model) {
        TouristAttraction attraction = touristService.getAttractionByName(name);
        model.addAttribute("attraction", attraction);  // Use singular 'attraction' for the object
        return "tags";
    }

    @PostMapping("/save")
    public String saveAttraction(@ModelAttribute TouristAttraction attraction) {
        touristService.addAttraction(attraction);
        return "redirect:/attractions";
    }

    @GetMapping("/update/{name}")
    public String updateAttraction(@PathVariable String name, Model model) {
        TouristAttraction attraction = touristService.getAttractionByName(name);
        Set<String> allTags = TouristService.getAllTags();
        Set<String> allDistricts = TouristService.getAllDistricts();
        model.addAttribute("allTags", allTags);
        model.addAttribute("allTowns", allDistricts);
        model.addAttribute("attraction", attraction);
        return "update-attraction";  // Returns a view for updating an attraction
    }

    @PostMapping("/update")
    public String updateAttraction(@ModelAttribute TouristAttraction attraction) {
        touristService.updateAttraction(attraction);
        return "redirect:/";  // Redirect to the main page after updating
    }

    @PostMapping("/delete/{name}")
    public String deleteAttraction(@PathVariable("name") String name) {
        touristService.deleteAttraction(name);
        return "redirect:/";  // Redirect to the list after deletion
    }
}




