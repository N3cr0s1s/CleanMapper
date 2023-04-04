def nexusCredentialsId = 'nexus-password'
def nexusRepositoryUrl = 'https://nexus.partybeast.org/repository/maven-releases/me/necrosis/mapper/CleanMapper/0.1.0/CleanMapper-0.1.0.jar'

pipeline {
  agent any
  tools {
    maven "Maven"
    jdk "Java 17"
  }
  stages {
    stage('Maven') {
      steps {
        sh 'mvn --version'
      }
    }
    stage('Java') {
      steps {
        sh 'java --version'
      }
    }
    stage('Test') {
        steps {
            sh "mvn test"
        }
    }
    stage('Build') {
      steps {
        sh "mvn clean package"
      }
      post {
        always {
          archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
        }
      }
    }
    stage('Upload to Nexus') {
        steps {
            withCredentials([usernamePassword(credentialsId: nexusCredentialsId, passwordVariable: 'NEXUS_PASSWORD', usernameVariable: 'NEXUS_USERNAME')]) {
                sh "curl -v -u ${env.NEXUS_USERNAME}:${env.NEXUS_PASSWORD} --upload-file target/CleanMapper-1.0.jar ${nexusRepositoryUrl}"
            }
        }
    }
  }
}