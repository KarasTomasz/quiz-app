# Quiz-app

![Custom badge](https://img.shields.io/badge/java-11-lightgrey)
![Custom badge](https://img.shields.io/badge/spring_boot-2.6.7-brightgreen)
![Custom badge](https://img.shields.io/badge/mongoDB-4.4.2-success)
![Custom badge](https://img.shields.io/badge/lombok-1.18.24-red)
![Custom badge](https://img.shields.io/badge/jenkins-2.332.2-orange)
![Custom badge](https://img.shields.io/badge/testContainers-yellowgreen)

### Information

<ul style="list-style-type:discs">

<li>This application has two profiles: local and heroku. The first one uses docker image of mongoDB, while heroku configures access to MongoDB Atlas service.</li> 
<li>The quiz-app uses jenkins tool, which is responsible for building, testing and deploying application on heroku.</li>

</ul>

### Rest API

Api is available at this link: https://quiz-app-v002.herokuapp.com/swagger-ui.html (it may take a few seconds for heroku to run)


### Jenkins

The result of pipeline from Jenkinsfile that is added to this project:

![App_image](src/main/resources/images/jenkins.PNG)

### How to use the app
<ul style="list-style-type:decimal">

To use swagger you need to be authorized by a token. To get the token you should log in using the signIn method.
If you are not registered yet, you should use the registerAppUser.
There are already two users in database with different permissions:
<li> role: ADMIN, email: admin@mail.com, password: adminPass</li>
<li> role: USER, email: user@mail.com, password: userPass</li>
</ul>

<ul style="list-style-type:circle">

The user role are allowed to:
<li> fetch random questions by category </li>
<li> fetch all questions by category </li>
<li> check answer </li>

The admin role has wider privileges:
<li> add question </li>
<li> update question by id </li>
<li> delete question by id </li>
<li> fetch random questions by category</li>
<li> fetch all questions by category </li>

<li> fetch all appUsers </li>
<li> update appUser by email</li>
<li> deleteAppUser by email</li>

<li> check answer </li>
<li> update answer </li>


</ul>

<ul style="list-style-type:decimal">

</ul>

