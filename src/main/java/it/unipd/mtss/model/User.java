////////////////////////////////////////////////////////////////////
// [MARCO] [BERNARDI] [2018528]
// [NICOLO] [TRINCA] [2011070]
////////////////////////////////////////////////////////////////////

package it.unipd.mtss.model;

import java.time.LocalDate;

public class User {
    String nome;
    String cognome;
    String email;
    LocalDate dataNascita;

    public User(String name, String surname, String mail, LocalDate birthDate){
        nome = name;
        cognome = surname;
        email = mail;
        if (birthDate == null){
            throw new IllegalArgumentException("Data di nascita non valida");
        }
        if (birthDate.isAfter(LocalDate.now())){
            throw new IllegalArgumentException("Data di nascita futura");
        }
        dataNascita = birthDate;
    }
    public String getEmail() {
        return email;
    }

    public String getName() {
        return nome;
    }

    public String getSurname() {
        return cognome;
    }

    public LocalDate getDateOfBirth() {
        return dataNascita;
    }
}