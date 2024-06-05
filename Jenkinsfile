pipeline {
    agent {
        label 'jenkins-agent'
    }

    tools {
        maven 'Maven'
        jdk   'jdk17'
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
                script {
                    env.JAVA_HOME = tool name: 'jdk17', type: 'jdk'
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
                        sh 'mvn org.sonarsource.scanner.maven:sonar-maven-plugin:3.8.0.2131:sonar'
                        sh 'mvn sonar:sonar'
                    }
                }
            }
        }
    }
}
