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
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table
@Inheritance( strategy = InheritanceType.TABLE_PER_CLASS )

public class Staff3 implements Serializable {

   @Id
   @GeneratedValue( strategy = GenerationType.AUTO )

   private int sid;
   private String sname;

   public Staff3( int sid, String sname ) {
      super( );
      this.sid = sid;
      this.sname = sname;
   }

   public Staff3( ) {
      super( );
   }

   public int getSid( ) {
      return sid;
   }

   public void setSid( int sid ) {
      this.sid = sid;
   }

   public String getSname( ) {
      return sname;
   }

   public void setSname( String sname ) {
      this.sname = sname;
   }
}
