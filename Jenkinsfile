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
                    sh 'mvn spring-boot:build-image -DskipTests -Dspring-boot.build-image.imageName=imadmin1992/currency-exchange:latest'
                }
            }
        }
        stage('Push Docker Image') {
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: 'dockerhub-credentials',
                        usernameVariable: 'DOCKER_USERNAME',
                        passwordVariable: 'DOCKER_TOKEN'
                    )
                ]) {
                    sh '''
                        echo "$DOCKER_TOKEN" | docker login \
                        --username "$DOCKER_USERNAME" \
                        --password-stdin

                        docker push imadmin1992/currency-exchange:latest
                    '''
                }
            }
        }
    }
}