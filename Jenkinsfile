pipeline {
  
  tools {
        maven 'Maven 3.8.8'
        jdk 'jdk'
      }
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/rohitkr114/parivesh_backend.git'
            }
        }
        
    stage('Maven build') {
            steps {  
        script { 
                sh 'mvn install'
              }
            }
        }
      
    stage('Build image') {
      steps{
        script { 
          sh "docker build -t rohitkr115/parivesh2_dev:4.0 ."
        }
      } 
    }

    stage('Pushing Image') {
      environment {
               registryCredential = 'dockerhub-credentials'
           }
      steps{
        script {
          docker.withRegistry( 'https://registry.hub.docker.com', registryCredential ) {
            dockerImage.push("4.0")
          }
        }
      }
    }

    stage('Deploying parivesh-backend container to Kubernetes') {
      steps {
        script {
          kubernetesDeploy(configs: "parivesh2_dev_deployment.yml", "parivesh2_dev_service.yml")
        }
      }
    }
  }
}
