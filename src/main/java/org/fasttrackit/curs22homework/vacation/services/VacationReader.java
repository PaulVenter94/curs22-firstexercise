package org.fasttrackit.curs22homework.vacation.services;

import org.fasttrackit.curs22homework.vacation.domain.Vacation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

@Component
public class VacationReader {
    private final Resource file;

    public VacationReader(@Value("${file.location:myFile.txt}") String fileLocation) {
        this.file = new ClassPathResource(fileLocation);
    }

    public List<Vacation> read() {
        List<Vacation> result = new ArrayList<>();
        try (var input = file.getInputStream()) {
            Scanner scanner = new Scanner(input);
            while (scanner.hasNext()) {
                String[] token = scanner.nextLine().split("\\|");
                result.add(new Vacation((result.size() + 1), token[1], parseInt(token[2]), parseInt(token[3])));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
