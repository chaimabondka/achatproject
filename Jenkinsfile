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
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=admin1';
            }    
        }
    }
}
