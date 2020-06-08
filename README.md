# ODPReco
ODPReco is a tool for recommending ODPs (Ontology Design Patterns) to a given ontology. The ODPs recommended by ODPReco can be used in the ontology to make it modular.

# Table of Contents:

i.	Introduction

ii.	 Goal

iii. Approach

iv. Usage

&nbsp; &nbsp; &nbsp; a. Prerequisites

&nbsp; &nbsp; &nbsp; b. Execution Procedure

iv.	Examples

v.	Future Work


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

*Structural Analysis-*

It is the one in which the OWL file of the given ontology is compared with the OWL file of all the listed ODPs. This is done via Doc2Vec. The OWL file is analysed by its properties- SubClass, ObjectPropertyDomain, ObjectPropertyRange, DisjointClasses, etc. The axioms of the ontology are extracted using the OWL API.

*Behavioural Analysis-*

Competency Questions are considered to be important for an ontology. The competency questions of an ontology are mapped with the CQs of ODPs. Competency questions (CQs) represent the domain knowledge that is involved in the ontology. They are important in the life-cycle of an ontology as these represent the requirements and the scope of an ontology. The CQs of the ontology are compared with the CQs of our collection to carry out the behavioural analysis.

*Lexical Analysis-*

The signature of the ontology is compared with the signature of ODPs present in our collection. The signature includes the names of the classes, properties and instances of an ontology. Apart from the signature, description (brief overview) of the ontology is also used in this analysis.

After doing the analysis of these three dimensions, the numeric values obtained are added for each listed ODP. So, we obtain 73 values (of ODPs) against an ontology. The values obtained are normalized so that they can be ranged between 0-1.  The threshold for recommending an ODP is set at .8. Hence, all the ODPs having value >= .8 is listed in the recommendation. 

The 73 ODPs that are considered in our tool are available with the OWL file, Competency Questions and the Description.

The necessary condition for using this tool is to upload the OWL file. If the OWL file of an ontology is not provided, then our tool will not be able to provide any recommendations. However, if the description or competency questions of an ontology aren’t available, then still the ODP recommendation is made. 

# USAGE

Jar file of ODPReco has been created and the user can run the jar file on command line to know the ODP recommendations for the given ontology.

 &nbsp; &nbsp; a. Prerequisites:
 
 The user must have java installed in the system inorder to run the jar file. Inorder to know the recommendations for an ontology, owl file for that ontology should be avaiable. OWL file can be constructed in protégé. Also, the user has to enter the description for that ontology along with the competency questions.
 
 &nbsp; &nbsp; b. Execution Procedure:
 
 Clone this project and download its zip folder. Extract it and save it in a folder.
 
 Open command line and go to the location where this folder is saved. After entering into the folder, follow the sequence as mentioned below-
 
 cd corenlp_123  
 
 mvn compile
 
 mvn install
 
 After this step, the jar file alongwith dependencies will be created in target folder. 
 
 Copy the resources folder (present in corenlp_123 folder) into the target folder and change the path in command line to target folder by entering cd target
 
 jar file can be executed from this folder by using the command - java -jar odpreco-with-dependencies.jar
 
 After entering this command, the user is asked to enter the description, competency questions and the OWL file path. It is mandatory to enter the OWL file path, the description and competency questions can be skipped. Recomemndations follow after user enters the input.
 
 After ODPReco has recommended ODPs, kindly fill this form https://forms.gle/SV3vgdsDqJ8hMVw68 .It is user feedback form.
 
 
# EXAMPLES

ODPReco is tested with some ontologies that are available online. Moderate to good results are obtained. It is observed that most appropriate recommendations are provided when all the three components ,that is, the OWL file, competency questions and description is given by the user while as average results are provided when only the OWL file or OWL file with competency questions or description is given by the user.

1. Chess Game

It is the content ontology pattern present in the ODP repository. We have used this pattern in our collection (as ODP) and we are testing this on ODPReco to check its correctness. We tested this ontology in 4 different ways. First, we passed only the OWL file, then in the second test, we passed the OWL file and description, in the third test, OWL file and CQs were passed and in the fourth test, all three dimensions, that is, OWL file, CQ and description were passed. Same results were obtained in all the four tests



| Ontology   |    	Score	  | Relevant/Not Relevant	   | Comment                                        |
|------------|--------------|--------------------------|------------------------------------------------|
|  Chess ODP |        1	    |     Yes	                 |                                                |

As Chess ODP is recommended, it suggests that our tool is showing relevant results. Also, other ODPs like - Task-Role, Event that can be used in Chess pattern  have recommendation scores between 0.65 - 0.78.

2. Enslaved Ontology :

It is an ontology about the historic slave trade. It captures data about historic persons and the events associated. It is a modular ontology that uses ODPs like Event, Place, Temporal ones etc. We have used this ontology for validation.  

No CQs are present for this ontology. So, we tested our tool in two ways- first only with the OWL file and then in the second test we passed OWL file and description to our tool.

The results obtained with OWL file - 

|    Ontology	                 |     Score	  |    Relevant/Not Relevant	|      Comment                                   |
|------------------------------|--------------|---------------------------|------------------------------------------------|
|Spatio-Temporal Extent ODP	   |     .90      |     yes	                  |         -                                      |
|     Chess ODP                |     .89	    |     yes	                  |      as it uses concept of events              |
|     Place ODP                |	   .83	    |      yes                  |                 -                              |
|   Temporal Extent ODP        |	   .82      |      yes	                |                 -                              |
|   Entity-Feature ODP         |	   .81      |	     no                   |  covers cell features in biological domain     |
|      Toco                    |	    .8	    |      no	                  |  as used with telecommunication                |                         

The results obtained when OWL file and description are passed to ODPReco-


|    Ontology	                 |     Score	  |    Relevant/Not Relevant	|      Comment                                   |
|------------------------------|--------------|---------------------------|------------------------------------------------|
|Spatio-Temporal Extent ODP	   |     .90      |     yes	                  |         -                                      |
|     Chess ODP                |     .89	    |     yes	                  |      as it uses concept of events              |
|     Place ODP                |	   .83	    |      yes                  |                 -                              |
|   Temporal Extent ODP        |	   .82      |      yes	                |                 -                              |
|    Tagging ODP               |      .81     |      yes                  |    uses agent-role concept in it               |
|   Entity-Feature ODP         |	   .81      |	     no                   |  covers cell features in biological domain     |

When both, OWL file and description are passed to ODPReco, it gives moderatley better results.

3. Be-Aware Ontology :

It is a crisis management ontology for climate related natural disasters. It consists of climatic disasters, analysis of data from sensors and rescue team assignments. This ontology has OWL file, description as well as CQs. We ran 4 tests on this ontology- one only with OWL file, other with OWL file and description , third one with OWL file and CQs and the last with all the three. The best recommendation results were shown when all the dimensions were tested. The results are as-

|       Ontology	             |     Score	  |    Relevant/Not Relevant	|      Comment                                   |
|------------------------------|--------------|---------------------------|------------------------------------------------|
|  Hazardous ODP	             |     .90      |     yes	                  |         -                                      |
|     DUL ODP                  |     .89	    |     yes	                  |      describes object, event,, region          |
|     Place ODP                |	   .86	    |      yes                  |                 -                              |
|   Task RoleODP               |	   .85      |      yes	                |     assigning tasks to roles                   |
|    TaskExecution ODP         |     .82      |      yes                  |       tasks are used                           |
|  Spatio-Temporal ODP         |     .81      |      yes                  |       time concept is used                     |
|   Policy ODP                 |	   .81      |	     no                   |         -                                      |


4.	Radiation Ontology:

It has been taken from NCBIO. The OWL file and the description were given to ODPReco tool.

|Ontology	   |     Score	  |    Relevant/Not Relevant	|      Comment                                   |
|------------|--------------|---------------------------|------------------------------------------------|
|    DUL	   |      1	      |     yes	                  |         Event, Agent, Activity                 |
|Componency	 |     .93	    |     yes	                  |      isPartOf, isComponentOf                   |
|Gear Species|	   .88	    |      no	                  |                 -                              |
|Invoice     |	   .84	    |      no	                  |                 -                              |
|News Report |	   .81      |	     yes	                |        Agent, Event, Situation                 |




These scores and relevance is provided by us only. We intent to do a user study soon inorder to obtain genuine feedback from the experts in this domain.

# FUTURE WORK

ODPReco tool can be made as a plug-in in protégé so that the user can get the recommendations on the protégé platform only.
Also, for recommendation, other software pattern approaches can be followed so that the efficiency of ODPReco increases.





