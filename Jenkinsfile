pipeline {
    agent {
        label 'jenkins-agent'
    }

    tools {
        maven 'Maven'
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
        
        stage('SonarQube Analysis') {
            steps {
                script {
                    withSonarQubeEnv(credentialsId: 'jenkins-sonar') {
                        sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.7.0.1746:sonar'
                        sh 'mvn sonar:sonar'
                    }
                }
            }
        }
    }
}
