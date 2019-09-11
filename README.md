# Java XML Scanner

## Overview
Imagine that you are writing a simple web crawler that locates a user-selected element on a web site with frequently changing information. You regularly face an issue that the crawler fails to find the element after minor page updates. 
Application makes your analyzer tolerant to minor website changes so that you don’t have to update the code every time.


## Run
Run the example

For this application you need have java 8 and maven

1. Clone this repository
2. Run 
```shell script
	mvn clean install  && java -jar target/html-scanner-1.0-SNAPSHOT.jar  -id ${id} -op ${path to original html file} -sp ${path to snippet html file}
```

Id is the only exact criteria, to find the target element in the original file.

## Support
Please enter an issue in the repo for any questions or problems. 
<br> Alternatively, please contact us at ttninefortynine@gmail.com
