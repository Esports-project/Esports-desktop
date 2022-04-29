/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pidevdorra;

import Entities.Jeux;
import Entities.Equipe;
import Services.JeuxServices;
import Services.EquipeServices;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.time.Instant;
import java.util.ArrayList;

/**
 *
 * @author dorra
 */
public class PidevDorra {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here 
        //test d'ajout
        JeuxServices JeuSer = new JeuxServices();
        java.sql.Date currentDate = new java.sql.Date(Date.from(Instant.now()).getTime());
        Jeux jeu = new Jeux(0, "aaa", "bb");
        
        JeuxServices myService = new JeuxServices();
        myService.createJeux(jeu);

        //test affichage
//    List<Jeux> alljeu = myService.readJeux();
//        System.out.println("all Games : ");
//        System.out.println(alljeu);

        //test modification
        Jeux exjeu = myService.readJeuxById(20);
        exjeu.setNom("Wowzi");
        myService.modifyJeux(exjeu);
        
        EquipeServices myequipe = new EquipeServices ();
                Equipe eq = new Equipe(0, "ghghhg");
                

        myequipe.createEquipe(eq);

        //test suppression
        //myService.DeleteJeux(18);

        /* ServiceClassement ClassementSer= new ServiceClassement();
      
         Classement clas = new Classement(5,44,60,55);
          

      
       ClassementSer.createClassement(clas);
     //ClassementSer.DeleteClassement();
      //ClassementSer.modifyClassement(clas);
      
        
       
        
          List<Classement> classements = new ArrayList();
        classements =ClassementSer.readClassement();
   
        
        
        
        
         System.out.println(classements);
         
         
   
         
         
         */
    }

}
