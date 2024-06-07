pipeline {
    agent any
    tools  {
        maven 'M2_HOME'
    }
    stages {
        stage ('GIT') {
            steps {
                git branch: 'main',
                url : 'https://github.com/chaimabondka/achatproject.git'
            }
        }
        stage ('MAVEN BUILD') {
            steps {
                sh 'mvn clean';
                sh 'mvn compile';
            }
        }
        stage ('MAVEN SONARQUBE') {
            steps { 
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar';
            }    
        }
        stage('mvn_test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('mvn_deploy') {
            steps {
             sh "mvn deploy -DaltDeploymentRepository=releases::default::http://169.254.230.2:8081/repository/maven-releases/"
            }
        }
       stage('DOCKER BUILD') {
            steps {
              dir('devops/achat') { 
              sh 'docker build -t chaima12/achat-devops:1.0.0 .'
           }
         }
        }
        stage('DOCKER PUSH'){
            environment {
                DOCKER_CREDENTIALS = 'e964a3fc-83b0-475c-952d-697048b765cc'
                IMAGE_NAME = 'chaima12/achat-devops'
                IMAGE_TAG = '1.0.0'
            }
            steps{
                sh '''docker login -u chaimabondka123 -p Dockerhub
                docker push chaima12/achat-devops:1.0.0''';
            }
        }

    }
}
