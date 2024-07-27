# Project documentation

## Purpose

The purpose of this project is to automate manual end to end test cases using Java and Selenium. This project is perfect for beginners in test automation. SwagLabs reperesents a real live eCommerce site, yet it is simplified. The site misses Registration form, but at the same time provides the following user options:

 - **standard_user:** normal user behavior with no built in restrictions;
 - **locked_out_user:** error message is displayed when attempting to log in with this user;
 - **problem user:** built in errors in the Checkout Your information section making it imposible to fill in all the fields and submit the form;
 - **performance_glitch_user:** created specifically for performance tests with huge delays in the website functionality in all sections;
 - **error_user:** built in error in the Checkout Overview page making the Finish button unresponsive, thus the purchase cannot be completed;
 - **visual_user:** missplaced Checkout button inside the shopping cart. Shopping cart icon missaligned and overlaps the horizontall line in upper secion of the website;

**Tools,Platforms, Browsers**

Language: Java
Framework: Selenium
Test Runner: TestNG
Reporting Tool: Extent Reports
Platform: Windows
Browser: Chrome

In scope:

 - E2E Web UI, tested with standard_user profile;
 - error messages on login screen tested with locked_out_user

Out of scope:

- API: there is no documentation provided on the available APIs and their endpoints;
- Tests with **problem_user**, **error_user** and **visual_user** are out of scope. The built in errors in the workflow for each profile are typically caught by manual testers. The presumption here is that the flow works as expected, there are no errors and we are automating regression tests. 
- Performance test is out of scope due to missing predefined criteria and max/ min acceptable values with which to compare the actual results.
