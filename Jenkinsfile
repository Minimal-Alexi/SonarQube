pipeline {
    agent any

    environment {
        // Docker Hub credentials ID
        DOCKERHUB_CREDENTIALS_ID = '766ad170-3f44-470a-bf6f-15ea59065be6'
        // Docker Hub repository name
        DOCKERHUB_REPO = 'popalexdocker/sonarqube-example'
        // Docker image tag
        DOCKER_IMAGE_TAG = 'latest_v1'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/Minimal-Alexi/SonarQube'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Code Coverage') {
            steps {
                bat 'mvn jacoco:report'
            }
        }

        stage('Publish Test Results') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }

        stage('Publish Coverage Report') {
            steps {
                jacoco()
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('SonarQubeServer') {
                    bat """
                    sonar-scanner ^
                    -Dsonar.projectKey=devops-demo ^
                    -Dsonar.sources=src ^
                    -Dsonar.projectName=DevOps-Demo ^
                    -Dsonar.host.url=http://localhost:9000 ^
                    -Dsonar.login=${env.SONAR_TOKEN} ^
                    -Dsonar.java.binaries=target/classes
                    """
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}")
                }
            }
        }

        stage('Push Docker Image to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', DOCKERHUB_CREDENTIALS_ID) {
                        docker.image("${DOCKERHUB_REPO}:${DOCKER_IMAGE_TAG}").push()
                    }
                }
            }
        }
    }
}
