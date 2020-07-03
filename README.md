# ODPReco
ODPReco is a tool for recommending ODPs (Ontology Design Patterns) to a given ontology. The ODPs recommended by ODPReco can be used in the ontology to make it modular.

# Table of Contents:

i.	[Introduction](#Introduction)

ii.	[Goal](#Goal)

iii. [Approach](#Approach)

iv. [About the Repository](#About-the-Repository)

v. [Usage](#Usage)

v.i [Prerequisites](#Prerequisites)

vi.	[Examples](#Examples)

vii.	[Future Work](#Future-Work)


# INTRODUCTION

Ontologies are defined as the representation of a particular domain. All the relationships, entities and individuals involved in a particular concept can be easily represented via an ontology. Ontologies are helpful as they help in the knowledge representation which can be shared and re-used across a particular domain. As ontologies easily define the terms used to describe and represent a particular area of knowledge, so these can be used in applications to capture relationships and help in knowledge management. 

An ontology can thus, be stated as a framework for representing shareable and reusable knowledge across a domain. Ontologies can be easily developed by Protégé. Protégé is an editor which provides graphic user interface to define ontologies. An ontology is defined by its classes, subclasses, properties (like domain, range,subclass property etc) and individuals.

One of the main challenges of an ontology design is its re-usability. Ontologies can be re-used and adapted according to the requirements of the project. For re-usability, small ontologies can be treated as basic building blocks. These basic building blocks are referred as the ontology design patterns (ODPs). ODPs are small, self-contained ontologies that provide a solution to commonly occurring modelling problems across different domains. Large ontologies can make use of these ODPs and hence, can help in improving the quality of an ontology.



# GOAL

Our work is based on recommending ODPs for an ontology. By using an ODP in an ontology, the user can re-use the features of ODP according to his domain and make the ontology more modular.

In order to recommend ODPs, ODPReco maintains a list of avaiable ODPs. The details of the ODPs maintained are - their OWL file, competency questions and their description. 73 ODPs are maintained from three datasets.

1. ODPs from the ODP repository http://ontologydesignpatterns.org. Out of the 220 ODPs available, we have considered 41 ODPs in our collection. Not all ODPs are included in our collection because several ODPs either do not have downloadable OWL file or have similar OWL files. So, to avoid redundancy of OWL files, only 41 ODPs are included. 

2. MODL: Modular Ontology Design Library is a well-documented, downloadable collection of ODPs. Some of the ODPs present in this dataset are taken from the ODP repository and their ordered and well-organised OWL file along with the competency questions is created. For our collection, all the 17 ODPs present in MODL have been considered. 

3. Manchester ODPs: These ODPs are exclusively maintained for the biological domain. The ODPs present are divided into three categories - Extension ODPs (bypassing the limitation of OWL), Good Practice ODPs (for obtaining robust and a cleaner ontology) and Domain Modelling ODPs (modelling solutions in the domain of biology). 15 ODPs are present in total and all 15 present are included in our collection.

# APPROACH

The ODPs are recommended on the basis of 3 analysis - structural, behavioural and lexical. 

The analysis is done using two approaches- Doc2Vec and Lucene approach. Both the approaches are executed and the top recomemndations from them are displayed. 

*Structural Analysis-*

It is the one in which the OWL file of the given ontology is compared with the OWL file of all the listed ODPs. This is done via Doc2Vec. The OWL file is analysed by its properties- SubClass, ObjectPropertyDomain, ObjectPropertyRange, DisjointClasses, etc. The axioms of the ontology are extracted using the OWL API. Apart from the axioms, the structure of the ontology is also analysed through its graph.

*Behavioural Analysis-*

Competency Questions are considered to be important for an ontology. The competency questions of an ontology are mapped with the CQs of ODPs. Competency questions (CQs) represent the domain knowledge that is involved in the ontology. They are important in the life-cycle of an ontology as these represent the requirements and the scope of an ontology. The CQs of the ontology are compared with the CQs of our collection to carry out the behavioural analysis.

*Lexical Analysis-*

The signature of the ontology is compared with the signature of ODPs present in our collection. The signature includes the names of the classes, properties and instances of an ontology. Apart from the signature, description (brief overview) of the ontology is also used in this analysis.

After doing the analysis of these three dimensions, the numeric values obtained are added for each listed ODP. So, we obtain 73 values (of ODPs) against an ontology. The values obtained are normalized so that they can be ranged between 0-1.  The threshold for recommending an ODP is set at .8. Hence, all the ODPs having value >= .8 are listed in the recommendation. 

Lucene approach is also implemented for the recommendations. In this approach, ODPs are recommended based on the structural analysis only. ODP files (73 in number) are maintained. Each file contains the signature and the axioms. The user ontology is mapped with these ODP files using lucene in order to determine the relevant ODPs for the given ontology. Top 5 recommendations from this approach are integrated with the previous approach and displayed to the user.

The recommendations from both the approaches are combined together and displayed to the user.

The 73 ODPs that are considered in our tool are available with the OWL file, Competency Questions and the Description.

The necessary condition for using this tool is to upload the OWL file. If the OWL file of an ontology is not provided, then our tool will not be able to provide any recommendations. However, if the description or competency questions of an ontology aren’t available, then still the ODP recommendation is made. 

# About the Repository

This repository has all the relevant files related to ODPReco tool. The java code for ODPReco is under the recommender package. Under the 'resources' folder, it has the files  that are required during execution. The odp files are also present in this folder. If a user wishes to add odps to our collection, then the user has to update the odp files by appending the new odp (at line 74) and its properties to the respective associated files. The pom file that is used for compiling and building this tool is also presnt separately. Under the 'META-INF' folder, manifest file is present which contains the details about the main class. The 'target' folder is used to store the jar file when this tool is compiled. Pdf file of ODPList is present. This pdf has the description of all the 73 ODPs that are used in our tool.

# USAGE



 # &nbsp; &nbsp; a. Prerequisites :
 
 The user must have java installed in the system inorder to run the tool. Apart from java, maven should also be installed in the system. Inorder to know the recommendations for an ontology, owl file for that ontology should be avaiable. OWL file can be constructed in protégé. Also, the user has to pass the description file for that ontology along with the competency questions file. 
 
 # &nbsp; &nbsp; b. Execution Procedure :
 
 Clone this project and download its zip folder. Extract it and save it in a folder.
 
 Open command line and go to the location where this folder is saved. After entering into the folder, follow the sequence as mentioned below-
 
 cd ODPReco-master  
 
 mvn compile
 
 mvn install
 
 After this step, the jar file alongwith dependencies will be created in target folder. 
 
 Copy the resources folder (present in -master folder) into the target folder and change the path in command line to target folder by entering - cd target
 
 jar file can be executed from this folder by using the command : 
 
 **java -jar odpreco.jar -ontdes [des.txt] -ontcq [cq.txt] -ontowl [ontology.owl]**
 
 ontdes takes the description file as argument, ontcq takes competency question file of the ontology as input and ontowl takes the ontology file as input.
 
 It is mandatory to enter the OWL file path; the description and competency questions can be skipped by leaving the square brackets empty or keeping their files empty. 
 
 
 Recommendations follow after user enters the input.
 
 Examples:
 
 1. java -jar odpreco.jar -ontdes [] -ontcq [] -ontowl [C:\Users\Admin\Desktop\Ontology\population_ontology.owl]
 
 In this example, description and competency questions aren't passed as input and only the ontology file is passed as the input.
 
 2. java -jar odpreco.jar -ontdes [C:\Users\Admin\Desktop\des.txt] -ontcq [] -ontowl [C:\Users\Admin\Desktop\Ontology\population_ontology.owl]
 
 In this example, description and owl file of the ontology is passed as input.
 
 3. java -jar odpreco.jar -ontdes [C:\Users\Admin\Desktop\des.txt] -ontcq [C:\Users\Admin\Desktop\cq.txt] -ontowl [C:\Users\Admin\Desktop\Ontology\population_ontology.owl]
 
 In this example, all the three dimensions are passed as input.
 
 
 After ODPReco has recommended ODPs, kindly fill this form https://forms.gle/SV3vgdsDqJ8hMVw68 .It is user feedback form.
 
# EXAMPLES

ODPReco is tested with some ontologies that are available online. Moderate to good results are obtained. It is observed that most appropriate recommendations are provided when all the three components ,that is, the OWL file, competency questions and description is given by the user while as average results are provided when only the OWL file or OWL file with competency questions or description is given by the user.

1. Chess Game

It is the content ontology pattern present in the ODP repository. We have used this pattern in our collection (as ODP) and we are testing this on ODPReco to check its correctness. We tested this ontology in 4 different ways. First, we passed only the OWL file, then in the second test, we passed the OWL file and description, in the third test, OWL file and CQs were passed and in the fourth test, all three dimensions, that is, OWL file, CQ and description were passed. Same results were obtained in all the four tests



| Ontology   |    	Relevant/Not Relevant	   | Comment                                        |
|------------|------------------------------|------------------------------------------------|
|  Chess ODP |         Yes	                 |            -                                   |
| Agent-Role |         Yes                  |            -                                   |
| Event ODP  |         Yes                  |            -                                   |

As Chess ODP is recommended, it suggests that our tool is showing relevant results. Also, other ODPs like - Task-Role, Event that can be used in Chess pattern  have recommendation scores between 0.65 - 0.78.

2. Enslaved Ontology :

It is an ontology about the historic slave trade. It captures data about historic persons and the events associated. It is a modular ontology that uses ODPs like Event, Place, Temporal ones etc. We have used this ontology for validation.  

No CQs are present for this ontology. So, we tested our tool in two ways- first only with the OWL file and then in the second test we passed OWL file and description to our tool.

The results obtained with OWL file - 

|    Ontology	                 |     Relevant/Not Relevant	|      Comment                                   |
|------------------------------|---------------------------|------------------------------------------------|
|Spatio-Temporal Extent ODP	   |      yes	                 |         -                                      |
|     Chess ODP                |      yes	                 |      as it uses concept of events              |
|     Place ODP                |	     yes                  |                 -                              |
|   Temporal Extent ODP        |	     yes	                 |                 -                              |
|   Entity-Feature ODP         |	     no                   |  covers cell features in biological domain     |
|      Toco                    |	     no	                  |  as used with telecommunication                |                         

The results obtained when OWL file and description are passed to ODPReco-


|    Ontology	                 |     Relevant/Not Relevant	 |      Comment                                   |
|------------------------------|----------------------------|------------------------------------------------|
|Spatio-Temporal Extent ODP	   |       yes	                 |         -                                      |
|     Chess ODP                |       yes	                 |      as it uses concept of events              |
|     Place ODP                |	      yes                  |                 -                              |
|   Temporal Extent ODP        |	      yes	                 |                 -                              |
|    Tagging ODP               |       yes                  |    uses agent-role concept in it               |
|   Entity-Feature ODP         |	      no                   |  covers cell features in biological domain     |

When both, OWL file and description are passed to ODPReco, it gives moderatley better results.

3. Be-Aware Ontology :

It is a crisis management ontology for climate related natural disasters. It consists of climatic disasters, analysis of data from sensors and rescue team assignments. This ontology has OWL file, description as well as CQs. We ran 4 tests on this ontology- one only with OWL file, other with OWL file and description , third one with OWL file and CQs and the last with all the three. The best recommendation results were shown when all the dimensions were tested. The results are as-

|       Ontology	              | Relevant/Not Relevant	 |      Comment                                   |
|------------------------------|------------------------|------------------------------------------------|
|  Hazardous ODP	              |        yes	            |         -                                      |
|     DUL ODP                  |        yes	            |      describes object, event,, region          |
|     Place ODP                |	       yes             |                 -                              |
|   Task RoleODP               |	       yes	            |     assigning tasks to roles                   |
|    TaskExecution ODP         |        yes             |       tasks are used                           |
|  Spatio-Temporal ODP         |        yes             |       time concept is used                     |
|   Policy ODP                 |	       no              |         -                                      |


4.	Radiation Ontology:

It has been taken from NCBIO. The OWL file and the description were given to ODPReco tool.

|Ontology	   |       Relevant/Not Relevant	|      Comment                                   |
|------------|---------------------------- |------------------------------------------------|
|    DUL	    |       yes	                  |         Event, Agent, Activity                 |
|Componency	 |       yes	                  |      isPartOf, isComponentOf                   |
|Gear Species|	       no	                  |                 -                              |
|Invoice     |	       no	                  |                 -                              |
|News Report |	      yes	                  |        Agent, Event, Situation                 |




These scores and relevance is provided by us only. We intent to do a user study soon inorder to obtain genuine feedback from the experts in this domain.

# FUTURE WORK

ODPReco tool can be made as a plug-in in protégé so that the user can get the recommendations on the protégé platform only.
Also, for recommendation, other software pattern approaches can be used so that the efficiency of ODPReco increases.





