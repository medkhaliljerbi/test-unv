pipeline{
    agent any
    stages{
        stage('Git clone'){
            steps{
                git branch: 'feat/integrate-jenkins-&-docker', credentialsId: 'Github-MohamedAliAyedi',
                url: 'git@github.com:MohamedAliAyedi/future-university.git'
            }
        }
        stage('Compile'){
            steps{
               sh 'cd backend && mvn compile -DskipTests'
            }

        }
        stage('Build'){
            steps{
               sh 'cd backend && mvn clean install -DskipTests'
            }

        }
        stage('Package code'){
            steps{
               sh 'cd backend && mvn package -DskipTests'
            }
            
        }
        stage('Run Docker'){
            steps{
                script {
                    sh 'cd backend && docker compose -f docker-compose.yml up -d'
                }
            }
        }
        stage('Upload Docker'){
            steps{
                script {
                    sh 'cd backend && docker image build -t future_university:v1.$BUILD_ID .'
                    sh 'cd backend && docker image tag future_university:v1.$BUILD_ID dalidockerhub/future_university:v1.$BUILD_ID'
                    sh 'cd backend && docker image tag future_university:v1.$BUILD_ID dalidockerhub/future_university:latest'
                    sh 'echo "z2_uxs2@JPA2!=_" | docker login -u dalidockerhub --password-stdin' 
                    sh 'cd backend && docker image push dalidockerhub/future_university:v1.$BUILD_ID'
                }
            }
        }
        stage('SonarQube analysis'){
            steps{
                script{
                  withSonarQubeEnv(credentialsId: 'SonarFutureUnv') {
                  sh 'cd backend && mvn sonar:sonar'
                   
               }
            }
          }
        }
            stage('Upload To Nexus'){
            steps{
                script{
                   def readPomVersion = readMavenPom file: 'backend/pom.xml'
                   def nexusRepo= readPomVersion.version.endsWith("SNAPSHOT") ? "backend-snapshot" : "backend-release"
                   nexusArtifactUploader artifacts: 
                   [
                       [
                           artifactId: 'future-university', classifier: '', file: 'backend/target/backend.jar', type: 'jar'
                       ]
                    ],
                     credentialsId: 'Jenkins-Nexus',
                     groupId: 'com.esprit.exam',
                     nexusUrl: 'localhost:8081',
                     nexusVersion: 'nexus3',
                     protocol: 'http',
                     repository: 'future-university',
                     version: "${readPomVersion.version}"
            }
          }
        }
        }
    post{
          failure {  
             mail bcc: '',
             body: "<b>Example</b><br>Project: ${env.JOB_NAME} <br>Build Number: ${env.BUILD_NUMBER} <br> URL de build: ${env.BUILD_URL}",
             cc: '',
             charset: 'UTF-8',
             from: '',
             mimeType: 'text/html',
             replyTo: '',
             subject: "ERROR CI: Project name -> ${env.JOB_NAME}",
             to: "mohamed.ali.ayedi.b@gmail.com";  
         } 
    }
}