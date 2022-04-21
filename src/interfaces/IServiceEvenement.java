/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import models.Evenement;

/**
 *
 * @author admin
 */
public interface IServiceEvenement {
    
    //CRUD
    public void createEvenement(Evenement E);
    public void DeleteEvenement(int id);
    public void modifyEvenement(Evenement E);
    public List<Evenement> readEvenements();
    
}