pipeline {
    agent any

    tools {
        jdk 'JDK17'
        maven 'myMaven'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Currency Exchange') {
            steps {
                dir('currency-exchange-services') {
                    sh 'mvn clean package -DskipTests'
                }
            }
        }
        stage('Build Docker Image') {
            steps {
                dir('currency-exchange-services') {
                    sh 'mvn spring-boot:build-image -DskipTests -Dspring-boot.build-image.imageName=hussain0792/currency-exchange:latest'
                }
            }
        }
    }
}