# Roadmap

## Project Summary

[FlyCAGE](http://flycage.herokuapp.com/) aims to utilize gene expression data and other information to infer biologic functions and relationships in gene regulatory networks in _Drosophila melanogaster_. 

Currently, FlyCAGE can perform the following

  1. Pull _Dropshila melanogaster_ gene expression data from [Intermine](http://intermine.org/)
  
  2. Take an existing gene name or custom gene expression data as an input
  
  3. Calculates the Pearson's correlation coefficient (r) between the input set and the genome set, and returns the highest ranked genes.
  
  
In the future, we hope to expand FlyCAGE to perform analysis from other organisms, take the correlation on different biological datasets, and provide explained insights into the correlation results.

## Contribute?

For development, we are looking for front-end and back-end web developers to support the implementation of the tool. For analysis, we are looking for data scientists and bioinformaticians to perform analysis on biological datasets to verify proof-of-concept features.

We are also open to accepting other contributions such as 1) biologists to validate the value of features and provide other feature ideas, and 2) graphic arists to improve website design.

For more information on contributing, please see [CONTRIBUTORS.md](#) (TODO: Create contributors.md)


## Timeline

__April 2018: Complete the development for FlyCAGE V1.0.0__
  * Notes
    * Included features 
       1. Input existing gene name or custom gene expression data.
       2. Input genes of interest to compare with the target gene.
       3. Input the amount of genes to see in the results.
       4. For inputting an existing genes, select which expression stages to compare against.
       5. Output the list of genes with relevant metadata.
       6. View simple plot comparing each output gene with the target gene
       7. Be able to normalize the result plots.

__May 2018: Deploy FlyCAGE V1.0.0 and submit first publication for review__
  * Notes
    * Deployment provides sufficient amount of resources for initial use.
    * Relevant infrastructure tools should be installed on server, such as CDCI tools (Jenkins, TravisCI, etc).

__August 2018: Complete the development for FlyCAGE V2.0.0 (+ deployment)__
  * Notes
    * Included features still in planning phase
      1. Able to enter genes from several organisms with the available data.
      2. Provide more biological insights into the results of the correlation analysis.
