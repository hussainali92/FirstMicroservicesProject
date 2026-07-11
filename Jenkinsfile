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
    }
}