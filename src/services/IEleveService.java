package services;

import models.Eleve;

import java.util.List;

public interface IEleveService {

    public Eleve add(Eleve eleve );
    public void update(Eleve eleve  );
    public boolean delete(int identifiant);
    public List<Eleve> getAll();
    public void getOne(int identifiant);
}
