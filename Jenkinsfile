pipeline {
    agent any
    environment {
        SONARQUBE_URL = 'http://10.6.252.45:9000/'
        SONARQUBE_USERNAME = 'admin'
        SONARQUBE_PASSWORD = 'sonar'
        JAVA_HOME = tool name: 'JAVA_HOME', type: 'jdk' // Assurez-vous d'avoir configuré JDK 17 dans Jenkins
        PATH = "${env.JAVA_HOME}/bin:${env.PATH}"
        PROMETHEUS_CONTAINER = "prometheus-p"
        GRAFANA_CONTAINER = "grafana"
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
                git branch: 'master',
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
                sh 'mvn clean install';
                sh 'mvn deploy -DaltDeploymentRepository=nexus-releases::default::http://10.6.252.45:8081/repository/maven-releases/'
            }
        }
         stage('Build Docker Image') {
            steps {
                dir('/var/lib/jenkins/workspace/Molka_2ALINFO5/achatproject/'){
                sh 'docker build -t molkaadmin/achatprojet:1.0.0 .'
                }
            }
        }
        stage('Push Docker Image to DockerHub'){
            steps {
                sh '''docker login -u molkaadmin -p docker292001
                      docker push molkaadmin/achatprojet:1.0.0'''
            }
        }   
         stage('Run Docker Compose') {
            steps {
                dir('/var/lib/jenkins/workspace/Molka_2ALINFO5/achatproject/'){
                sh 'docker compose up -d'
                }
            }
        }
        stage('Configure Prometheus for Jenkins') {
            steps {
                script {
                    def prometheusConfig = """
                    - job_name: 'jenkins'
                      metrics_path: /prometheus
                      static_configs:
                      - targets: ['10.6.252.45:8080/']  # Replace with your Jenkins IP
                    """
                    sh "docker exec -i ${PROMETHEUS_CONTAINER} sh -c 'echo \"${prometheusConfig}\" >> /etc/prometheus/prometheus.yml'"
                    sh "docker restart ${PROMETHEUS_CONTAINER}"
                }
            }
        }
    }

    }
