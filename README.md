# FlyCAGE <img src="./fly_cropped.png" alt="logo" width="50px" height="50px">

> __Correlation Analysis on Gene Expression - Finding genes with similar expression activity.__

FlyCAGE is a bioinformatics web application that uses the correlation coefficient between gene expression profiles to retrieve insights about gene similarity. 

Visit [flycage.herokuapp.com](flycage.herokuapp.com) to try out our tool.

## Tool Description
### Problem
Common transcriptomic analysis methods such as differential gene expression analysis and agglomertive gene clustering are excellent ways to analyze transcriptomic data. However, the analysis from these methods are limited to the data retrieved from the experiment and does not utilize the "big-data" available.

Non-technical: most methods that analyze the activity of genes do not take advantage of public datasets.

### Solution
FlyCAGE is a web-accessible application that uses several public data sources to analyze fly mRNA expression data to discover genes with similar expression patterns. 

Non-technical: FlyCAGE tries to find genes with similar gene activity.

### Value
Due to its cost-effectiveness, many genetics labs will retrieve the gene expression profiles to understand more about the underlying experiment. This retrieval is done through RNA-seq or microarrays. There are several simple ways to analyze gene expression data (DESeq, GSEA, etc.). We hope that FlyCAGE will be another tool that scientists will use to easily retrieve insights about their experimental data (i.e. identify candidate genes involved in a given process, to discover regulatory interactions in genetic networks, and to verify the quality of gene clustering techniques).

Non-technical: Many biology labs can easily obtain data on gene activity. There are several simple ways to analyze this data - we believe that FlyCAGE can be another method scientists can add to their toolbox.

## Roadmap
Interested in the next steps of FlyCAGE? View the [ROADMAP.md](ROADMAP.md)

## Contribution
### What do we need?
Specifically, we are looking for the following:
#### Front-end developers
* Technologies consists of HTML, CSS, JS, Bootstrap 4, jQuery, Plotly.js, Datatables.
* We are interested in aesthetic revamping, migration towards a framework (i.e. Angular), improve result visualization, etc.
* __No biology knowledge required.__
#### Back-end developers
* Technologies consist of Java, Spring MVC (Boot), Thymeleaf, asynchronous Spring programming, WS and DB communication, etc.
* We are interested in defining web endpoints, rendering dynamic information, biologic data source communication, implementation of biological analysis, deployment and maintanence, etc.
* __Biology knowledge preferred for context, but not required.__
#### Bioinformaticians
* Any scripting language preferred.
* We are interested in designing or implementing bioinformatics algorithms to conduct analysis.
* __Biology knowledge necessary (in as many -omics topics as possible).__
#### Biologists
* We are interested in providing ideas for desired features of the application
* __Any discipline of life sciences desired.__
 
Although, any sort of help on the project would be much appreciated!

If you are interested in contributing, please see the [CONTRIBUTING.MD](https://github.com/CodingBash/FlyCAGE) for more information!

### Code of conduct
This project adheres to the Contributor Covenant [code of conduct](CODE_OF_CONDUCT.md). By participating, you are expected to uphold this code. 

### Development Setup
Ready to start developing? Take a look at [our workstation setup](WORKSTATION_SETUP.md).

### Contact Us
For any questions about the project, please contact the main developer at [bbecer2@ilstu.edu](mailto:bbecer2@ilstu.edu)

