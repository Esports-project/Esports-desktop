/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import java.util.List;
import Entities.Jeux;

/**
 *
 * @author dorra
 */
public interface IServiceJeux<T> {
  
 //CRUD
    public void createJeux(Jeux J);
    public void DeleteJeux(int id);
    public void modifyJeux(Jeux J);
    public List<Jeux> readJeux();
    
}