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

Api is available at this link: https://quiz-app-v002.herokuapp.com/swagger-ui.html (it may take a few for heroku to run)


### Jenkins

The result of pipeline from Jenkinsfile that is added to this project:
![App_image](src/main/resources/images/jenkins.PNG)


