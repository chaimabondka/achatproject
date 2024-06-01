pipeline {
    agent {
        label 'jenkins-agent'
    }
    tools {
        jdk 'java'
        maven 'maven'
    }

    stages {
        stage('Git Pull') {
            steps {
                script {
                    git branch: 'Rahma', url: 'https://github.com/chaimabondka/achatproject'
                }
            }
        }
        stage('Maven Install') {
            steps {
                script {
                    sh "cd achatproject; mvn clean install"
                }
            }
        }
        stage('Test') {
            steps {
                script {
                    sh "cd achatproject; mvn clean test"
                }
            }
        }

}
}
