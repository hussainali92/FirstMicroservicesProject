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
        stage('Build Currency Conversion') {
            steps {
                dir('currency-conversion-services') {
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build Currency Conversion Image') {
            steps {
                dir('currency-conversion-services') {
                    sh 'mvn spring-boot:build-image -DskipTests -Dspring-boot.build-image.imageName=imadmin1992/currency-conversion:latest'
                }
            }
        }

        stage('Push Currency Conversion Image') {
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

                        docker push imadmin1992/currency-conversion:latest
                    '''
                }
            }
        }
        stage('Build Naming Server') {
            steps {
                dir('naming-server') {
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build Naming Server Image') {
            steps {
                dir('naming-server') {
                    sh 'mvn spring-boot:build-image -DskipTests -Dspring-boot.build-image.imageName=imadmin1992/naming-server:latest'
                }
            }
        }

        stage('Push Naming Server Image') {
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

                        docker push imadmin1992/naming-server:latest
                    '''
                }
            }
        }
        stage('Build API Gateway') {
            steps {
                dir('api-gateway') {
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build API Gateway Image') {
            steps {
                dir('api-gateway') {
                    sh 'mvn spring-boot:build-image -DskipTests -Dspring-boot.build-image.imageName=imadmin1992/api-gateway:latest'
                }
            }
        }

        stage('Push API Gateway Image') {
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

                        docker push imadmin1992/api-gateway:latest
                    '''
                }
            }
        }
        stage('Build Config Server') {
            steps {
                dir('spring-cloud-config-server') {
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build Config Server Image') {
            steps {
                dir('spring-cloud-config-server') {
                    sh 'mvn spring-boot:build-image -DskipTests -Dspring-boot.build-image.imageName=imadmin1992/config-server:latest'
                }
            }
        }

        stage('Push Config Server Image') {
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

                        docker push imadmin1992/config-server:latest
                    '''
                }
            }
        }
    }
}