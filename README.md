# ODPReco
This document provides a reference documentation of ODPReco. It is a tool for recommending ODPs (Ontology Design Patterns) to a given ontology. 

# Table of Contents:

i.	Introduction

ii.	Goal

iii.	Approach

iv.	Examples

v.	Future Work

# INTRODUCTION

Ontologies are defined as the representation of a particular domain. All the relationships, entities and individuals involved in a particular concept can be easily represented via an ontology. Ontologies are helpful as they help in the knowledge representation which can be shared and re-used across a particular domain. As ontologies easily define the terms used to describe and represent a particular area of knowledge, so these can be used in applications to capture relationships and help in knowledge management. 

An ontology can thus, be stated as a framework for representing shareable and reusable knowledge across a domain. Ontologies can be easily developed by Protégé. Protégé is an editor which provides graphic user interface to define ontologies. An ontology is defined by its classes, subclasses, properties (like domain, range,subclass property etc) and individuals.

In order to ensure the correctness of an ontology, Ontology Design Patterns (ODPs) can be suggested. An ODP is a well-defined generic ontology pattern that can be re-used in making of a particular domain ontology. 

# GOAL

Our work is based on recommending an ODP for an ontology. By using an ODP in an ontology, the user can re-use the features of ODP according to his domain and make the ontology more modular.

In our tool, the ODPs in total used are 73. Out of these 73, 58 ODPs are obtained from the official site of design patterns http://ontologydesignpatterns.org/wiki/Main_Page  and 15 are taken from the Manchester odp site in which the ODPs suggested are primarily from the biological domain.

So, among these 73, ODPs can be recommended according to the domain problem. 

The tool is built in Java-Eclipse and uses libraries like- NLP,Doc2Vec,SpringBoot etc.

# APPROACH

The ODPs are recommended on the basis of 3 analysis - structural, behavioural and lexical.

*Structural Analysis-*

It is the one in which the OWL file of the given ontology is compared with the OWL file of all the listed ODPs. This is done via Doc2Vec. The OWL file is analysed by its properties- SubClass, ObjectPropertyDomain, ObjectPropertyRange, ChainOf Property , Data Property etc.

*Behavioural Analysis-*

Competency Questions are considered to be important for an ontology. The competency questions of an ontology are mapped with the CQs of ODPs.

*Lexical Analysis-*

It includes the mapping of  the description along with the class/property names of an ontology with those of the ODPs.

On doing these 3 analysis, the numeric values obtained are added for each listed ODP. So, we obtain 73 values (of ODPs) against an ontology. The values obtained are normalized so that they can be ranged between 0-1.  The threshold for recommending an ODP is set at .8. Hence, all the ODPs having value >= .8 is listed in the recommendation. 

The 73 ODPs that are considered in our tool are available with the OWL file, Competency Questions and the Description.

The necessary condition for an ontology to be used for our tool is only the OWL file. If the OWL file of an ontology is not provided, then our tool will not be able to provide any recommendations. However, if the description or competency questions of an ontology aren’t available, then still the ODP recommendation is made. 

To ensure the utility of this tool, web page made is -  _______________________________. 

Here the user can import the ontology with the set of competency questions and description and check for the recommendations. The Web Page is made by adding the SpringBoot dependencies in our JAVA project.

# EXAMPLES

ODPReco is tested with some ontologies that are available online. Moderate to good results are obtained. 

1.	Ontology- Infectious disease ontology taken from NCBIO

| Ontology   |    	Score	  | Relevant/Not Relevant	   | Comment                                        |
|------------|--------------|--------------------------|------------------------------------------------|
|  Region	   |        1	    |     Yes	                 |derivesFrom,isPartOf                            |
|   DUL	     |      .87	    |     Yes 	               |isDescribed,ispartOf                            |
|DigitalVideo|	    .91	    |      No	                 |    -                                           |
|News Report |	     .82	  |      Yes 	               |isAbout,actsThrough,Agent,Event,Situation       |
|Upper Level |	     .83	  |      Yes	               |  processes,organismOccurant ,Pathological      |

Score that we can give to this recommendation- 4/5

2.	Radiation Ontology

|Ontology	   |     Score	  |    Relevant/Not Relevant	|      Comment                                   |
|------------|--------------|---------------------------|------------------------------------------------|
|    DUL	   |      1	      |     yes	                  |         Event, Agent, Activity                 |
|Componency	 |     .93	    |     yes	                  |      isPartOf, isComponentOf                   |
|Gear Species|	   .88	    |      no	                  |                 -                              |
|Invoice     |	   .84	    |      no	                  |                 -                              |
|News Report |	   .81      |	     yes	                |        Agent, Event, Situation                 |
|EPQ         |	    .81	    |      no	                  |                 -                              |

Score that we can give to this recommendation - 3/6

These scores and relevance is provided by us only. We intent to do a user study soon inorder to obtain genuine feedback from the experts in this domain.

# FUTURE WORK

ODPReco tool can be made as a plug-in in protégé so that the user can get the recommendations on the protégé platform only.

Also, for recommendation, other software pattern approaches can be followed so that the efficiency of ODPReco increases.





