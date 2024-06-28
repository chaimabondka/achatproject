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
                    configFileProvider([configFile(fileId: 'cef941ae-22da-4582-8818-63efd8032ded', variable: 'MyGlobalSettings')]) {
                        sh 'mvn clean install --settings $MyGlobalSettings'
                        sh 'mvn deploy -DskipTests' 
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
