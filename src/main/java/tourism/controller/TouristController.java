package tourism.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tourism.model.TouristAttraction;
import tourism.repository.TouristRepository;
import tourism.service.TouristService;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Set;

@Controller
//@RequestMapping("/attractions")
public class TouristController {

    private final TouristService touristService;
    private final TouristRepository touristRepository;

    // @Autowired
    public TouristController(TouristService touristService, TouristRepository touristRepository) {
        this.touristService = touristService;
        this.touristRepository = touristRepository;
    }

    @GetMapping
    public String showAttractions(Model model) {
        List<TouristAttraction> attractions = touristService.getAllAttractions();
        model.addAttribute("attractions", attractions);
        return "attractionList";
    }

    @GetMapping("/{name}")
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

    @PostMapping("/update")
    public String updateAttraction(@RequestParam String name, @ModelAttribute TouristAttraction updatedAttraction) {
        touristService.updateAttraction(name, updatedAttraction);
        return "redirect:/attractions";
    }

    @PostMapping("/delete/{name}")
    public String deleteAttraction(@PathVariable String name) {
        touristService.deleteAttraction(name);
        return "redirect:/attractions";
    }
    @GetMapping("/updateAttraction")
    public String showTags(Model model) {
        Set<String> descriptions = touristRepository.getAllDescription();
        Set<String> tags = touristRepository.getAllTags();
        model.addAttribute("tags", tags);
        model.addAttribute("descriptions", descriptions);
        return "updateAttraction";
    }
    @GetMapping("/form")
    public String showForm(Model model) {
        Set<String> descriptions = touristRepository.getAllDescription();
        model.addAttribute("descriptions", descriptions);
        return "form";
    }

}



