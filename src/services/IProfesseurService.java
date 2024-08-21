package services;

import models.Professeur;

import java.util.List;

public interface IProfesseurService {

    public Professeur add(Professeur professeur );
    public void update(Professeur professeur  );
    public boolean delete(int identifiant);
    public List<Professeur> getAll();
    public void getOne(int identifiant);

}
