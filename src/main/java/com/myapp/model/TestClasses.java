package com.myapp.model;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class TestClasses {

	public static void main(String[] args) {
		  // Création d'objets de type Person et Employee
        Person person = new Person(1, "John", "Doe", 30);
        Employee employee = new Employee(2, "Jane", "Doe", 28, "Development");

        // Création d'un objet de type Director
        Director director = new Director(3, "Alice", "Smith", 35, "Management");

        // Assignation d'un manager à l'employé (devrait réussir)
        try {
            employee.assignManager(director);
            System.out.println(employee.getFirstName() + " a pour manager " + employee.getManager().getFirstName());
        } catch (Exception e) {
            System.out.println("Erreur lors de l'assignation du manager à l'employé: " + e.getMessage());
        }

        // Tentative d'assignation d'un manager à un directeur (devrait échouer et lancer une exception)
        try {
            director.assignManager(employee);
        } catch (UnsupportedOperationException e) {
            System.out.println("Erreur attendue: " + e.getMessage());
        }

        // Affichage pour vérifier le bon comportement des objets
        System.out.println("Person -> Id: " + person.getId() + ", Nom: " + person.getLastName());
        System.out.println("Employee -> Id: " + employee.getId() + ", Département: " + employee.getDepartment());
        System.out.println("Director -> Id: " + director.getId() + ", Département: " + director.getDepartment());
        
      //TP2 FileSteame
        String inputFilePath = "c:/temp/dictionary.csv";
        String outputFilePath = "c:/temp/dictionary_sorted.csv";
        List<Person> people = new ArrayList<>();

        // Lecture et stockage de données du fichier csv
         try (BufferedReader br = Files.newBufferedReader(Paths.get(inputFilePath), Charset.forName("Windows-1252"))) {
            String line;
            br.readLine(); // Pour ignorer la première ligne (en-tête)
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                Person personFromFile = new Person(
                        Integer.parseInt(values[0].trim()), // ID
                        values[1].trim(), // Nom
                        values[2].trim(), // Prénom
                        Integer.parseInt(values[3].trim())); // Âge
                people.add(personFromFile);
            }
            // Votre code de lecture...
         } catch (MalformedInputException e) {
             System.err.println("Erreur de lecture du fichier. Vérifiez l'encodage du fichier, il doit être UTF-8.");
             e.printStackTrace();  // Pour le débogage
             // Vous pouvez également log cette exception si vous avez un système de log
         } catch (IOException e) {
             System.err.println("Erreur d'entrée/sortie lors de la lecture du fichier.");
             e.printStackTrace();  // Pour le débogage
         }

        // Trie de la liste des personnes par ID
        Collections.sort(people, Comparator.comparingInt(Person::getId));

        // Écriture des données triées dans le nouveau fichier CSV
        try (BufferedWriter bw = Files.newBufferedWriter(Paths.get(outputFilePath))) {
            bw.write("id,nom,prenom,age\n");
            for (Person personToWrite : people) {
                bw.write(personToWrite.getId() + "," + personToWrite.getFirstName() + "," + personToWrite.getLastName()+ "," + personToWrite.getAge() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}
