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
        stage('DOCKER BUILD'){
            steps {
             sh 'ls -la'  // Pour vérifier que le Dockerfile est présent dans le répertoire courant
             sh 'cat Dockerfile'  // Pour vérifier le contenu du Dockerfile
             sh 'docker build -t chaima12/achat-devops:1.0.0 .';
            }
        }
        stage('DOCKER PUSH'){
            steps {
             sh '''docker login -u chaimabondka123 -p Dockerhub
                   docker push chaima12/achat-devops:1.0.0''';
            }
        }
        stage('docker_compose'){
            steps {
             sh 'docker compose up -d'
            }
        }
    }
}
