package com.nlptools.corenlp_123;

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
         File file1 = new File(System.getProperty("user.dir")+ "/resources/values_cq");
         Scanner inputFile1 = null;
         try {
            inputFile1 = new Scanner(file1);
         } 
            catch (FileNotFoundException Exception) {
               System.out.println("File not found!");
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
       //VALUES_CHAINOFPROPERTY
         Double[] array2 = new Double[1000];
         int i2 = 0;
         File file2 = new File(System.getProperty("user.dir")+ "/resources/values_chainOfProperty");
         Scanner inputFile2 = null;
         try {
            inputFile2 = new Scanner(file2);
         } 
            catch (FileNotFoundException Exception) {
               System.out.println("File not found!");
            }
         if (inputFile2 != null) {
            try {
               while (inputFile2.hasNext()) {
                  if (inputFile2.hasNextDouble()) {
                     array2[i2] = inputFile2.nextDouble();
                     //System.out.println(array2[i2]);
                     i2++;
                  } 
                  else {
                     inputFile2.next();
                  }
               }
            } 
            finally {
               inputFile2.close();
            }
         }
         //VALUES_DATAPROPERTYDOMAIN
         Double[] array3 = new Double[1000];
         int i3 = 0;
         File file3 = new File(System.getProperty("user.dir")+ "/resources/values_dataPropertyDomain");
         Scanner inputFile3 = null;
         try {
            inputFile3 = new Scanner(file3);
         } 
            catch (FileNotFoundException Exception) {
               System.out.println("File not found!");
            }
         if (inputFile3 != null) {
            try {
               while (inputFile3.hasNext()) {
                  if (inputFile3.hasNextDouble()) {
                     array3[i3] = inputFile3.nextDouble();
                     //System.out.println(array3[i3]);
                     i3++;
                  } 
                  else {
                     inputFile3.next();
                  }
               }
            } 
            finally {
               inputFile3.close();
            }
         }
         //VALUES_DATAPROPERTYRANGE
         Double[] array4 = new Double[1000];
         int i4 = 0;
         File file4 = new File(System.getProperty("user.dir")+ "/resources/values_dataPropertyRange");
         Scanner inputFile4 = null;
         try {
            inputFile4 = new Scanner(file4);
         } 
            catch (FileNotFoundException Exception) {
               System.out.println("File not found!");
            }
         if (inputFile4 != null) {
            try {
               while (inputFile4.hasNext()) {
                  if (inputFile4.hasNextDouble()) {
                     array4[i4] = inputFile4.nextDouble();
                    // System.out.println(array4[i4]);
                     i4++;
                  } 
                  else {
                     inputFile4.next();
                  }
               }
            } 
            finally {
               inputFile4.close();
            }

}
         //Values_DESCRIPTION
         Double[] array5 = new Double[1000];
         int i5 = 0;
         File file5 = new File(System.getProperty("user.dir")+ "/resources/values_description");
         Scanner inputFile5 = null;
         try {
            inputFile5 = new Scanner(file5);
         } 
            catch (FileNotFoundException Exception) {
               System.out.println("File not found!");
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
         File file6 = new File(System.getProperty("user.dir")+ "/resources/values_disjointClasses");
         Scanner inputFile6 = null;
         try {
            inputFile6 = new Scanner(file6);
         } 
            catch (FileNotFoundException Exception) {
               System.out.println("File not found!");
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
         File file7 = new File(System.getProperty("user.dir")+ "/resources/values_objectDomainProperty");
         Scanner inputFile7 = null;
         try {
            inputFile7 = new Scanner(file7);
         } 
            catch (FileNotFoundException Exception) {
               System.out.println("File not found!");
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
         File file8 = new File(System.getProperty("user.dir")+ "/resources/values_objectPropertyRange");
         Scanner inputFile8 = null;
         try {
            inputFile8 = new Scanner(file8);
         } 
            catch (FileNotFoundException Exception) {
               System.out.println("File not found!");
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
            File file9 = new File(System.getProperty("user.dir")+ "/resources/values_signature");
            Scanner inputFile9 = null;
            try {
               inputFile9 = new Scanner(file9);
            } 
               catch (FileNotFoundException Exception) {
                  System.out.println("File not found!");
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
            File file10 = new File(System.getProperty("user.dir")+ "/resources/values_subClassProperty123");
            Scanner inputFile10 = null;
            try {
               inputFile10 = new Scanner(file10);
            } 
               catch (FileNotFoundException Exception) {
                  System.out.println("File not found!");
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
            //values_subdataProperty
            Double[] array11 = new Double[1000];
            int i11 = 0;
            File file11 = new File(System.getProperty("user.dir")+ "/resources/values_subDataProperty");
            Scanner inputFile11 = null;
            try {
               inputFile11 = new Scanner(file11);
            } 
               catch (FileNotFoundException Exception) {
                  System.out.println("File not found!");
               }
            if (inputFile11 != null) {
               try {
                  while (inputFile11.hasNext()) {
                     if (inputFile11.hasNextDouble()) {
                        array11[i11] = inputFile11.nextDouble();
                       // System.out.println(array4[i4]);
                        i11++;
                     } 
                     else {
                        inputFile11.next();
                     }
                  }
               } 
               finally {
                  inputFile11.close();
               }

   }
            //values_subobjectProperty
            Double[] array12= new Double[1000];
            int i12 = 0;
            File file12 = new File(System.getProperty("user.dir")+ "/resources/values_subObjectProperty");
            Scanner inputFile12 = null;
            try {
               inputFile12 = new Scanner(file12);
            } 
               catch (FileNotFoundException Exception) {
                  System.out.println("File not found!");
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
        
         //INTEGRATING THE SCORES TOGETHER
            PrintStream p123=new PrintStream(new File(System.getProperty("user.dir")+ "/resources/integrated_values"));
            System.setOut(p123);
         Double totalAgg[]=new Double[1000];
         for(int i=0;i<73;i++){
        	 //System.out.println("score");
        	 totalAgg[i]=array1[i]+array2[i]+array3[i]+array4[i]+array5[i]+array6[i]+array7[i]+array8[i]+array9[i]+array10[i]+array11[i]+array12[i];
        	 System.out.println(totalAgg[i]);
         }
        
         
}
}

