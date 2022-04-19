/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import java.util.List;
import Entities.Equipe;
import Entities.Jeux;

/**
 *
 * @author dorra
 */
public interface IServiceEquipe<T> {
  
 //CRUD
    public void createEquipe(Equipe E);
    public void DeleteEquipe(int id);
    public void modifyEquipe(Equipe E);
    public List<Equipe> readEquipe();
    
}