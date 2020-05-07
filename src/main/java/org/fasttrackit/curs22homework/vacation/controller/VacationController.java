package org.fasttrackit.curs22homework.vacation.controller;

import org.fasttrackit.curs22homework.vacation.domain.Vacation;
import org.fasttrackit.curs22homework.vacation.services.VacationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vacation")
public class VacationController {
    private final VacationService vacationService;

    public VacationController(VacationService vacationService) {
        this.vacationService = vacationService;
    }

    @GetMapping({"", "/",})
    public List<Vacation> getAll(@RequestParam(required = false) String location,
                                 @RequestParam(required = false) Integer maxPrice) {
        return vacationService.getAllVacations(maxPrice, location);
    }

    @GetMapping("/{id}")
    public Vacation getVacationById(@PathVariable int id) {
        return vacationService.getById(id);
    }

    @PostMapping
    public Vacation addVacation(@RequestBody Vacation vacation) {
        return vacationService.add(vacation);
    }

    @DeleteMapping
    public Vacation deleteVacation(@RequestBody int id) {
        return vacationService.delete(id);
    }

    @PutMapping("/{id}")
    public Vacation patchVacation(@PathVariable int id, @RequestBody Vacation vacation) {
        return vacationService.replace(id, vacation);
    }
}
