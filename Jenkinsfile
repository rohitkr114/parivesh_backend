pipeline {

  environment {
    dockerimagename = "rohitkr115/parivesh2-dev"
    dockerImage = ""
  }

  agent any

  stages {

    stage('Checkout Source') {
      steps {
        git 'https://github.com/rohitkr114/parivesh_backend.git''
      }
    }

    stage('Build image') {
      steps{
        script {
          dockerImage = docker.build rohitkr115/parivesh2-dev
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
            dockerImage.push("latest")
          }
        }
      }
    }

    stage('Deploying parivesh-backend container to Kubernetes') {
      steps {
        script {
          kubernetesDeploy(configs: "parivesh2_dev_deployment.yml", "parivesh2_dev_service.ym")
        }
      }
    }

  }

}
