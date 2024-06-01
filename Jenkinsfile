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
         stage ('MAVEN BUILD') {
            steps {
                sh 'mvn clean';
                sh 'mvn compile';
            }
        }
        stage('MAVEN TEST') {
            steps {
                sh 'mvn test';
            }
        }

}
}
