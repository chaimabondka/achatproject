pipeline {
    agent {
        label 'jenkins-agent'
    }

    tools {
        maven 'Maven'
        dockerTool 'docker'
    }
    
    environment {
        APP_NAME = "achatproject"
        RELEASE = "1.0.0"
        registry = "rahmakhamassi/achatproject"
        registryCredential = 'dockerhub'
        dockerImage = ''
        DOCKER_CREDENTIALS_ID = "docker"

    }

    stages {
        stage('GIT') {
            steps {
                script {
                    git branch: 'Rahma', url: 'https://github.com/chaimabondka/achatproject'
                }
            }
        }
        
        stage('MAVEN BUILD') {
            steps {
                sh 'mvn clean compile'
            }
        }
        
        stage('MAVEN TEST') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Maven Deploy') {
            steps {
                script {
                    configFileProvider([configFile(fileId: '7e7b9889-1a9d-40b3-b6d9-e604d1852060', variable: 'MAVEN_SETTINGS')]) {
                        sh 'mvn clean install --settings $MAVEN_SETTINGS'
                    }
            }
        }
        }


        stage('DOCKER BUILD & PUSH') {
            steps {
                script {
                    docker.withRegistry('',DOCKER_CREDENTIALS_ID) {
                        dockerImage = docker.build("${registry}:$BUILD_NUMBER")
                        dockerImage.push()
                    }
                }
            }
        }
    }
}
