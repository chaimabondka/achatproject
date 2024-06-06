pipeline {
    agent any
    environment {
        SONARQUBE_URL = 'http://10.6.252.45:9000/'
        SONARQUBE_USERNAME = 'admin'
        SONARQUBE_PASSWORD = 'sonar'
        JAVA_HOME = tool name: 'JAVA_HOME', type: 'jdk' // Assurez-vous d'avoir configuré JDK 17 dans Jenkins
        PATH = "${env.JAVA_HOME}/bin:${env.PATH}"
    }
    tools  {
        maven 'M2_HOME'
    }
    stages {
        stage('Check Java Version') {
            steps {
                sh 'java -version'
            }
        }
        stage('Tool Install') {
            steps {
                script {
                    env.PATH = "${env.M2_HOME}/bin:${env.PATH}"
                }
            }
        }
        stage ('GIT') {
            steps {
                git branch: 'molkaa',
                url : 'https://github.com/chaimabondka/achatproject.git'
            }
        }
        stage ('MAVEN BUILD') {
            steps {
                sh 'mvn clean';
                sh 'mvn compile';
            }
        }
        stage('MAVEN SONARQUBE') {
            steps {

                  sh """mvn sonar:sonar -Dsonar.host.url=${SONARQUBE_URL} -Dsonar.login=${SONARQUBE_USERNAME} -Dsonar.password=${SONARQUBE_PASSWORD} -DskipTests """

           }
        }
        stage('TEST') {
            steps {
                // Exécution des tests Mockito avec Maven
                sh 'mvn test'
            }
        }
        stage ('NEXUS') {
            steps {
                sh 'mvn deploy -DaltDeploymentRepository=nexus-releases::default::http://10.6.252.45:8081/repository'
            }
        }
    }

    }
