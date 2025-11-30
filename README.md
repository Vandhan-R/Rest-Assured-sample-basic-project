# Rest-Assured Sample API Automation Project

This is a simple sample project using [Rest Assured](https://rest-assured.io/) (Java) to demonstrate a full **CRUD-style API automation flow** against a public test API.  

It shows how to:  
- Create a new resource (place)  
- Extract the resource ID from the response  
- Update the resource  
- Fetch and verify the updated resource  
- Delete the resource  
- Validate deletion  

Use this project as a starting point or reference for your own API automation learning or work.

---

## Tech Stack

- Java 17+  
- Maven  
- Rest Assured  
- Git + GitHub (for version control) 

---

## How to run

1. Clone the repository  
   ```bash
   git clone https://github.com/Vandhan-R/Rest-Assured-sample-basic-project.git
   cd Rest-Assured-sample-basic-project
2. Make sure you have Java + Maven installed. Then build and run:
    "mvn clean compile exec:java -Dexec.mainClass=com.yourpackage.Project1"
    
##Test Flow
	
##Create 
- Sends a POST request to create a new place
- Includes JSON body and query parameter key
- Parse the JSON response
- Extract place_id using JsonPath

##Update
- Sends a PUT request to update the address
- Uses the extracted place_id

##Get & Verify
- GET request retrieves the updated place
- Asserts that the new address matches expected value

##Delete
- DELETE request removes the created place

##Delete again or fetch again
- API returns an error confirming the resource no longer exists

This flow covers a complete CRUD lifecycle, making it ideal for learning API automation basics.
