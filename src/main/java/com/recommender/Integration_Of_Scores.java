package com.recommender;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

//Contributed by Maleeha

public class Integration_Of_Scores {

	public  void integratingScores() throws FileNotFoundException

      {
	  /* The cosine similarity of all the structural properties, the competency question,
	   * the description of the ODP (that it has with the given ontology) are combined
	   * in together.
	   */
		//values_cq
		 Double[] array1 = new Double[1000];
         int i1 = 0;
         File file1 = new File(System.getProperty("user.dir")+ "/resources/ontology_cq");
         Scanner inputFile1 = null;
         try {
            inputFile1 = new Scanner(file1);
         } 
            catch (FileNotFoundException Exception) {
               System.out.println("File-cq not found!");
            }
         if (inputFile1 != null) {
            try {
               while (inputFile1.hasNext()) {
                  if (inputFile1.hasNextDouble()) {
                     array1[i1] = inputFile1.nextDouble();
                     //System.out.println(array1[i1]);
                     i1++;
                  } 
                  else {
                     inputFile1.next();
                  }
               }
            } 
            finally {
               inputFile1.close();
            }

}
        
         //Values_DESCRIPTION
         Double[] array5 = new Double[1000];
         int i5 = 0;
         File file5 = new File(System.getProperty("user.dir")+ "/resources/ontology_description");
         Scanner inputFile5 = null;
         try {
            inputFile5 = new Scanner(file5);
         } 
            catch (FileNotFoundException Exception) {
               System.out.println("File- description not found!");
            }
         if (inputFile5 != null) {
            try {
               while (inputFile5.hasNext()) {
                  if (inputFile5.hasNextDouble()) {
                     array5[i5] = inputFile5.nextDouble();
                    // System.out.println(array4[i4]);
                     i5++;
                  } 
                  else {
                     inputFile5.next();
                  }
               }
            } 
            finally {
               inputFile5.close();
            }

}
         //Values_disjointClasses
         Double[] array6 = new Double[1000];
         int i6 = 0;
         File file6 = new File(System.getProperty("user.dir")+ "/resources/disjointClasses_ontology");
         Scanner inputFile6 = null;
         try {
            inputFile6 = new Scanner(file6);
         } 
            catch (FileNotFoundException Exception) {
               System.out.println("File - disjoint not found!");
            }
         if (inputFile6 != null) {
            try {
               while (inputFile6.hasNext()) {
                  if (inputFile6.hasNextDouble()) {
                     array6[i6] = inputFile6.nextDouble();
                    // System.out.println(array4[i4]);
                     i6++;
                  } 
                  else {
                     inputFile6.next();
                  }
               }
            } 
            finally {
               inputFile6.close();
            }

}
   //Values_ObjectDomainProperty
         Double[] array7 = new Double[1000];
         int i7 = 0;
         File file7 = new File(System.getProperty("user.dir")+ "/resources/objectdomain_values");
         Scanner inputFile7 = null;
         try {
            inputFile7 = new Scanner(file7);
         } 
            catch (FileNotFoundException Exception) {
               System.out.println("File- obj domain not found!");
            }
         if (inputFile7 != null) {
            try {
               while (inputFile7.hasNext()) {
                  if (inputFile7.hasNextDouble()) {
                     array7[i7] = inputFile7.nextDouble();
                    // System.out.println(array4[i4]);
                     i7++;
                  } 
                  else {
                     inputFile7.next();
                  }
               }
            } 
            finally {
               inputFile7.close();
            }

}
         //Values_objectPropertyRange
         Double[] array8 = new Double[1000];
         int i8 = 0;
         File file8 = new File(System.getProperty("user.dir")+ "/resources/objectrange_values");
         Scanner inputFile8 = null;
         try {
            inputFile8 = new Scanner(file8);
         } 
            catch (FileNotFoundException Exception) {
               System.out.println("File- object range not found!");
            }
         if (inputFile8 != null) {
            try {
               while (inputFile8.hasNext()) {
                  if (inputFile8.hasNextDouble()) {
                     array8[i8] = inputFile8.nextDouble();
                    // System.out.println(array4[i4]);
                     i8++;
                  } 
                  else {
                     inputFile8.next();
                  }
               }
            } 
            finally {
               inputFile8.close();
            }
         }
            //values_Signtaure
            Double[] array9 = new Double[1000];
            int i9 = 0;
            File file9 = new File(System.getProperty("user.dir")+ "/resources/signature_values");
            Scanner inputFile9 = null;
            try {
               inputFile9 = new Scanner(file9);
            } 
               catch (FileNotFoundException Exception) {
                  System.out.println("File-sig not found!");
               }
            if (inputFile9 != null) {
               try {
                  while (inputFile9.hasNext()) {
                     if (inputFile9.hasNextDouble()) {
                        array9[i9] = inputFile9.nextDouble();
                       // System.out.println(array4[i4]);
                        i9++;
                     } 
                     else {
                        inputFile9.next();
                     }
                  }
               } 
               finally {
                  inputFile9.close();
               }

   }

               //values_subclassproperty123
            Double[] array10= new Double[1000];
            int i10 = 0;
            File file10 = new File(System.getProperty("user.dir")+ "/resources/subclass_values");
            Scanner inputFile10 = null;
            try {
               inputFile10 = new Scanner(file10);
            } 
               catch (FileNotFoundException Exception) {
                  System.out.println("File- subclassnot found!");
               }
            if (inputFile10 != null) {
               try {
                  while (inputFile10.hasNext()) {
                     if (inputFile10.hasNextDouble()) {
                        array10[i10] = inputFile10.nextDouble();
                       // System.out.println(array4[i4]);
                        i10++;
                     } 
                     else {
                        inputFile10.next();
                     }
                  }
               } 
               finally {
                  inputFile10.close();
               }

            }
            //values_subobjectProperty
            Double[] array12= new Double[1000];
            int i12 = 0;
            File file12 = new File(System.getProperty("user.dir")+ "/resources/subObjectProperty_ontology");
            Scanner inputFile12 = null;
            try {
               inputFile12 = new Scanner(file12);
            } 
               catch (FileNotFoundException Exception) {
                  System.out.println("File-sub object not found!");
               }
            if (inputFile12 != null) {
               try {
                  while (inputFile12.hasNext()) {
                     if (inputFile12.hasNextDouble()) {
                        array12[i12] = inputFile12.nextDouble();
                       // System.out.println(array4[i4]);
                        i12++;
                     } 
                     else {
                        inputFile12.next();
                     }
                  }
               } 
               finally {
                  inputFile12.close();
               }

   }
            
            //sub-class isomophism
            
            Double[] array13= new Double[1000];
            int i13 = 0;
            File file13 = new File(System.getProperty("user.dir")+ "/resources/isomorphic_values");
            Scanner inputFile13 = null;
            try {
               inputFile13 = new Scanner(file13);
            } 
               catch (FileNotFoundException Exception) {
                  System.out.println("File-sub class isomorphism not found!");
               }
            if (inputFile13 != null) {
               try {
                  while (inputFile13.hasNext()) {
                     if (inputFile13.hasNextDouble()) {
                        array13[i13] = inputFile13.nextDouble();
                       // System.out.println(array4[i4]);
                        i13++;
                     } 
                     else {
                        inputFile13.next();
                     }
                  }
               } 
               finally {
                  inputFile13.close();
               }

   }
            
            //object property isomorphism
            Double[] array14= new Double[1000];
            int i14 = 0;
            File file14 = new File(System.getProperty("user.dir")+ "/resources/isomorphicvalues_objectproperty");
            Scanner inputFile14 = null;
            try {
                inputFile14 = new Scanner(file14);
             } 
                catch (FileNotFoundException Exception) {
                   System.out.println("File- object property isomorphism not found!");
                }
            if (inputFile14 != null) {
                try {
                   while (inputFile14.hasNext()) {
                      if (inputFile14.hasNextDouble()) {
                         array14[i14] = inputFile14.nextDouble();
                        // System.out.println(array4[i4]);
                         i14++;
                      } 
                      else {
                          inputFile14.next();
                       }
                    }
                 } 
                 finally {
                    inputFile14.close();
                 }
     }
     
            
        
         //INTEGRATING THE SCORES TOGETHER
         PrintStream p123=new PrintStream(new File(System.getProperty("user.dir")+ "/resources/integrated_values"));
         System.setOut(p123);
         Double totalAgg[]=new Double[1000];
         for(int i=0;i<73;i++){
        	 
        	 totalAgg[i]=array1[i]+array5[i]+array6[i]+array7[i]+array8[i]+array9[i]+array10[i]+array12[i]+array13[i]+array14[i];
        	 System.out.println(totalAgg[i]);
         }
        
         
}
}

