pipeline {
  
  agent any

  stages {

    stage('Checkout Source') {
      steps {
        git branch: 'master', credentialsId: 'd201bdef-d93d-4cde-b490-220e2996e6a9', url: 'https://github.com/rohitkr114/parivesh_backend.git'
      }
    }

    stage('Build image') {
      steps{
        script {
          dockerImage = docker.build "rohitkr115/parivesh2-dev:4.0"
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
