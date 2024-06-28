pipeline {
    agent any

    tools {
        maven 'Maven'
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

        stage('SonarQube Analysis') {
            steps {
                script {
                        sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=jenkins2024/'
                    }
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

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t rahmakhamassi/achatproject:1.0.0 .'
            }
        }
        stage('Push Docker Image to DockerHub') {
            steps {
                sh 'docker login -u rahmakhamassi -p "jenkins2024/"'
                sh 'docker push rahmakhamassi/achatproject:1.0.0'
            }
        }
        
        stage('Run Docker Compose') {
            steps {
                sh 'docker compose up -d'
            }
        }
    

    }
}
