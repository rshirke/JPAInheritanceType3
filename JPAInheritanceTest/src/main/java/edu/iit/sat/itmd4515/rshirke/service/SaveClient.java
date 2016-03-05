/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rshirke.service;

/**
 *
 * @author Rohan
 */
import edu.iit.sat.itmd4515.rshirke.jpainheritancetest.NonTeachingStaff;
import edu.iit.sat.itmd4515.rshirke.jpainheritancetest.TeachingStaff;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class SaveClient {

   public static void main( String[ ] args ) {
   
      EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "MidtermPU" );
      EntityManager entitymanager = emfactory.createEntityManager( );
      entitymanager.getTransaction( ).begin( );

      //Teaching staff entity 
      TeachingStaff ts1=new TeachingStaff(1,"Gopal","MSc MEd","Maths");
      TeachingStaff ts2=new TeachingStaff(2, "Manisha", "BSc BEd", "English");
      
      //Non-Teaching Staff entity
      NonTeachingStaff nts1=new NonTeachingStaff(3, "Satish", "Accounts");
      NonTeachingStaff nts2=new NonTeachingStaff(4, "Krishna", "Office Admin");

      //storing all entities
      entitymanager.persist(ts1);
      entitymanager.persist(ts2);
      entitymanager.persist(nts1);
      entitymanager.persist(nts2);
      
      entitymanager.getTransaction().commit();
      
      entitymanager.close();
      emfactory.close();
   }
}
