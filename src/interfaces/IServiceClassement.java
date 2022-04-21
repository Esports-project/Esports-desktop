/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;
import models.Classement;

/**
 *
 * @author admin
 * 
 */
public interface IServiceClassement {
    
    //CRUD
    public void createClassement(Classement C);
    public void DeleteClassement(int id);
    public void modifyClassement(Classement C);
    public List<Classement> readClassements();
    
}