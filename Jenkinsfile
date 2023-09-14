pipeline {
  agent any
  tools {
    maven 'maven'
  }
    environment {
     REPOSITORY_TAG="rohitkr114/parivesh2_dev:${BUILD_NUMBER}"
    }  
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/rohitkr114/parivesh_backend.git'
            }
        }
        
    // stage('Maven build') {
    //         steps {  
    //     script { 
    //             sh 'mvn install'
    //           }
    //         }
    //     }
      
    // stage('Build image') {
    //   steps{
    //     script { 
    //       sh "docker build -t ${REPOSITORY_TAG} ."
    //     }
    //   } 
    // }

    stage('Build and Push Docker Image') {
      environment {
        DOCKER_IMAGE = "${REPOSITORY_TAG}"
        REGISTRY_CREDENTIALS = credentials('dockerhub-credentials')
      }
      steps {
        script {
            sh 'docker build -t ${REPOSITORY_TAG} .'
            def dockerImage = docker.image("${DOCKER_IMAGE}")
            docker.withRegistry('https://registry.hub.docker.com', "dockerhub-credentials") {
                dockerImage.push()
            }
        }
      }
    }
    stage('Deploying parivesh-backend container to Kubernetes') {
      steps {
               sh '''kubectl apply -f ${WORKSPACE}/parivesh2_dev_deployment.yml'''
      }
    }
  }
}
