pipeline {
    agent {
        label 'jenkins-agent'
    }

    tools {
        jdk   'jdk17'
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
                script {
                    sh 'mvn clean package'
                    sh 'mvn clean compile'
                }
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
                        sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.8.0.2131:sonar'
                        sh 'mvn sonar:sonar'
                    }
                }
            }
        }
    }
}
