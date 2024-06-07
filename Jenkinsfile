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
               script {
                    // Spécifiez le chemin complet vers le Dockerfile avec les bonnes barres obliques inverses
                    def dockerfilePath = "C:\\Users\\bondk\\OneDrive\\Bureau\\2Alinfo5\\devops\\achat\\Dockerfile"
                    
                    // Construire l'image Docker en spécifiant le chemin complet du Dockerfile
                    sh "docker build -t chaima12/achat-devops:1.0.0 . -f ${dockerfilePath}"
                }
            }
        }
    }
}
