package bea;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import models.Evenement;
import models.Classement;
import services.ServiceClassement;
import services.ServiceEvenement;

/**
 *
 * @author acer
 */
public class bea {
    
    public static void main(String[] args) {
        // TODO code application logic here 

        //test d'ajout
        ServiceEvenement EvenSer = new ServiceEvenement();
        java.sql.Date currentDate = new java.sql.Date(Date.from(Instant.now()).getTime());
        Evenement Even;
        Even = new Evenement("comicon", "AnimeLovers", "An event for weeboos", "test.png", currentDate);
        ServiceEvenement myService = new ServiceEvenement();
        myService.createEvenement(Even);
        
        //test affichage
        
        List<Evenement> allEvents = myService.readEvenements();
        System.out.println("all events : ");
        System.out.println(allEvents);

        //test modification
        Evenement exEvent = myService.readEvenementById(3);
        exEvent.setOrganisateur("Wowzi");
        myService.modifyEvenement(exEvent);
        
        
        
        //test suppression
        //myService.DeleteEvenement(exEvent.getId());

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
