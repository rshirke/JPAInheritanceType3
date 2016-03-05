/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.rshirke.jpainheritance3;

/**
 *
 * @author Rohan
 */
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
public class TeachingStaff3 extends Staff3 {
   private String qualification;
   private String subjectexpertise;

   public TeachingStaff3( int sid, String sname, String qualification, String subjectexpertise ) {
      super( sid, sname );
      this.qualification = qualification;
      this.subjectexpertise = subjectexpertise;
   }

   public TeachingStaff3( ) {
      super( );
   }

   public String getQualification( ){
      return qualification;
   }
   
   public void setQualification( String qualification ) {
      this.qualification = qualification;
   }

   public String getSubjectexpertise( ) {
      return subjectexpertise;
   }

   public void setSubjectexpertise( String subjectexpertise ){
      this.subjectexpertise = subjectexpertise;
   }
}
