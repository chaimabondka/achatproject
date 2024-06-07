pipeline {
    agent any
    // Définition de l'outil Maven
    tools {
        maven 'M2_HOME'
    }

    stages {
        stage('GIT') {
            steps {
                // Récupération du code depuis GitHub
                git branch: 'chaima',
                url: 'https://github.com/chaimabondka/achatproject.git'
            }
        }
        
        stage('MAVEN BUILD') {
            steps {
                // Nettoyage et compilation avec Maven
                sh 'mvn clean'
                sh 'mvn compile'
            }
        }
        
        stage('MAVEN SONARQUBE') {
            steps { 
                // Analyse avec SonarQube
                sh 'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=sonar'
            }    
        }
        
        stage('mvn_test') {
            steps {
                // Exécution des tests Maven
                sh 'mvn test'
            }
        }
        
        stage('mvn_deploy') {
            steps {
                // Déploiement Maven
                sh "mvn deploy -DaltDeploymentRepository=releases::default::http://169.254.230.2:8081/repository/maven-releases/"
            }
        }
        
        stage('DOCKER BUILD') {
            steps { 
                 script {
                   sh 'docker build -t  chaima12/achat-devops:1.0.0 .'
                }
            }
        }
    }
}
