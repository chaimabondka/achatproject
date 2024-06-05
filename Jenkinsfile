pipeline {
    agent any
    tools {
        maven 'M2_HOME'
    }
    stages {
        stage ('GIT') {
            steps {
                git branch: 'mariem', url: 'https://github.com/chaimabondka/achatproject'
            }
        }
        stage ('MAVEN BUILD') {
            steps {
                sh 'mvn clean'
                sh 'mvn compile'
            }
        }
        stage ('MAVEN SONARQUBE') {
            steps { 
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=admin1'
            }    
        }
        stage('TEST') {
            steps {
                sh 'mvn test'
            }
        }
        stage ('NEXUS') {
            steps {
                sh 'mvn deploy -DskipTests' 
            }
        }
        stage('Build Docker Image') {
            steps {
                sh 'docker build -t mariemabd19/achatprojet:1.0.0 .'
            }
        }
        stage('Push Docker Image to DockerHub') {
            steps {
                sh 'docker login -u mariemabd19 -p "Abdelmoula0612++"'
                sh 'docker push mariemabd19/achatprojet:1.0.0'
            }
        }
        stage('Run Docker Compose') {
            steps {
                sh 'docker-compose up -d'
            }
        }
    }
}
