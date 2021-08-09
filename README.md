Oh hello there! This is my small project that uses some interesting technologies listed below.
#
# **N-time ticket**

## ***1.Technology stack***
1. Java 8+
2. Maven or Gradle
3. Spring boot + Spring MVC
4. JPA, Spring data, Hibernate
5. PostgreSQL
6. Angular JS (Angular) OR ReactJS OR Vie.js
7. Twitter Bootstrap
8. JWT
9. WebSockets
10. Source code to public git repo (github.com)
11. IDE: Intellij IDEA
12. Optional: Deploy app on remote server

## ***2. Project description***
The application is used to share N-time messages called *tickets*. Registered user can create a ticket and share it to anyone via URL (with email notification). Ticket is a message available for reading *N times*. *Reading a ticket* means opening a page with the ticket. Ticket become unavailable after reading OR after the set time. Ticket may warn a reader about remaining readings count.

## ***3. Ticket content***
1. Title.
2. Description.
3. Checkbox that allows to warn a reader about remaining readings count.
4. Readings count N.
5. Expiration date (optional).

## ***4. Site pages***
Main page:
* Hello text
* "Sign In" button
* "Sign Up" button

Sign Up page:
* Registration form (email, login, password)
* "Go To Sign In" button

Sign In page:
* Authorization form (email, password)
* "Go To Sign Up" button
* "Forgot password" functionallity (i hope I'll do it)

Profile page:
* "Create a ticket" button
* List of valid tickets
* "Copy link" button on every ticket

Ticket creating page:
* Form with according fields
