pipeline {
    agent any
    tools {
        maven 'M2_HOME'
    }
    stages {
        stage('pull_code') {
            steps {
                echo 'pulling'
                git branch: 'chourouk', url: 'https://github.com/chaimabondka/achatproject'
            }
        }
        stage('clean') {
            steps {
                echo 'Running mvn clean'
                sh 'mvn clean'
            }
        }
        stage('compile') {
            steps {
                echo 'Running mvn compile'
                sh 'mvn compile'
            }
        }
        stage('SONARQUBE') {
            steps {
                sh 'mvn sonar:sonar \
                        -Dsonar.host.url=http://192.168.50.4:9000 \
                        -Dsonar.login=admin \
                        -Dsonar.password=sonar'
            }
        }
        stage('MOCKITO') {
            steps {
                sh 'mvn test'
            }
        }
         stage('Nexus') {
            steps {
                sh 'mvn deploy'
            }
        }
            stage('Build Image') {
            steps {
                sh 'docker build -t achatimage:last -f Dockerfile ./'
            }
        }
        stage ('docker hub push') {
           steps {
               sh 'docker login -u chouroukarfaoui -p sunshine123'
               sh 'docker tag achatimage:last chouroukarfaoui/achatimage:achatimage'
               sh 'docker push chouroukarfaoui/achatimage:achatimage'
           }
        }
             stage ('docker compose') {
            steps {
               sh 'docker compose up -d'
            }
        }
        stage('GRAFANA') {
            steps {
                echo 'GRAFANA'
            }
        }
    }
}
