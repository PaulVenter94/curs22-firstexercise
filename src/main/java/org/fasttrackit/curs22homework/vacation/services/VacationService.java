package org.fasttrackit.curs22homework.vacation.services;

import org.fasttrackit.curs22homework.vacation.domain.Vacation;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VacationService {
    private final List<Vacation> vacations;

    public VacationService(VacationReader reader) {
        this.vacations = reader.read();
    }

    public Vacation getById(int id) {
        return vacations.stream()
                .filter(vacation -> vacation.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Vacation> getAllVacations() {
        return Collections.unmodifiableList(vacations);
    }

    public List<Vacation> getAllVacations(Integer maxPrice, String location) {
        if (maxPrice == null) maxPrice = Integer.MAX_VALUE;
        Integer finalMaxPrice = maxPrice;
        if (location != null) {
            return vacations.stream()
                    .filter(vacation -> vacation.getLocation().equalsIgnoreCase(location))
                    .filter(vacation -> vacation.getPrice() <= finalMaxPrice)
                    .collect(Collectors.toList());
        } else {
            return vacations.stream()
                    .filter(vacation -> vacation.getPrice() <= finalMaxPrice)
                    .collect(Collectors.toList());
        }
    }

    public List<Vacation> getAllVacations(int maxPrice) {
        return vacations.stream()
                .filter(vacation -> vacation.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    public Vacation add(Vacation vacation) {
        Vacation newVacation = new Vacation(findId(), vacation.getLocation(), vacation.getPrice(), vacation.getDuration());
        vacations.add(newVacation.getId() - 1, newVacation);
        return newVacation;
    }

    public Vacation delete(int id) {
        Vacation vacation = vacations.get(id - 1);
        vacations.remove(id - 1);
        return vacation;
    }

    public Vacation delete(Vacation vacationToDelete) {
        vacations.remove(vacationToDelete);
        return vacationToDelete;
    }

    public Vacation replace(Vacation newVacation) {
        vacations.remove(newVacation.getId());
        vacations.add(newVacation);
        return newVacation;
    }

    public Vacation replace(int idToReplace, Vacation newVacation) {
        vacations.remove(idToReplace - 1);
        return add(newVacation);
    }

    private int findId() {
        if (vacations.size() == 0) return 1;
        int counter = 1;
        for (int i = 0; i < vacations.size(); i++) {
            if (vacations.get(i).getId() != counter) {
                return counter;
            } else {
                counter++;
            }
        }
        return vacations.size() + 1;
    }
}
