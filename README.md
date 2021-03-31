ReST-Devs

PROJECT NAME: Vocation By Location: Job Search in the US by Industry and City.

Problem Statement: 
The purpose of this web service is to allow developers to find the major job centers in the US for a given industry. 
Imagine that a developer is creating an app so that a user can easily look up where the highest concentrations of their given 
job industry would be. This information is available, but it is extremely tedious and time-consuming to get it in dev-friendly, 
usable JSON. We have taken care of that process, and will provide a simple, well-documented, no-nonsense API so that the developer 
can get the data they need, and only that data, quickly and efficiently. 

Resources and Operations:
Our docs will instruct our users to use two endpoint URLs which we provide. One will give them a list of industry names with 
their corresponding industry codes. The other URL will take an industry code as a parameter and return a list of names and 
populations of the geographic regions with a high concentration of people working in that industry. A user can include 
additional parameters to sort by region or by population. Our docs will include a simple, guiding example of a request and an 
expected response.

Our service will get its data from datausa.io/api/data


