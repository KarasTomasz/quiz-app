pipeline {
    agent any

    tools {
        maven "M3"
    }

    stages {
        stage('Build') {
            steps {
                sh "mvn clean compile"
            }
        }
        stage('Test') {
            agent {
                docker {
                    reuseNode true
                    image 'openjdk:11.0-jdk-slim'
                    args  '-v /var/run/docker.sock:/var/run/docker.sock --group-add 992'
                }
            }
            steps {
                sh "mvn test"
            }
        }
        stage('Deploy') {
            steps {
                sh "mvn clean heroku:deploy"
            }
        }
    } //stages
} //pipeline
