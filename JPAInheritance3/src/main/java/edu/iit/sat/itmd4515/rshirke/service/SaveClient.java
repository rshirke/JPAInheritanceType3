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
import edu.iit.sat.itmd4515.rshirke.jpainheritance3.NonTeachingStaff3;
import edu.iit.sat.itmd4515.rshirke.jpainheritance3.TeachingStaff3;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class SaveClient {
   public static void main( String[ ] args ) {
      EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "MidtermPU" );
      EntityManager entitymanager = emfactory.createEntityManager( );
      entitymanager.getTransaction( ).begin( );

      //Teaching staff entity 
      TeachingStaff3 ts1 = new TeachingStaff3(1,"Gopal","MSc MEd","Maths");
      TeachingStaff3 ts2 = new TeachingStaff3(2, "Manisha", "BSc BEd", "English");
      
      //Non-Teaching Staff entity
      NonTeachingStaff3 nts1 = new NonTeachingStaff3(3, "Satish", "Accounts");
      NonTeachingStaff3 nts2 = new NonTeachingStaff3(4, "Krishna", "Office Admin");

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
