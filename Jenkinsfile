pipeline {
  
  agent any
  tools {
        mvn 'Maven 3.8.8'
        jdk 'Java 17.0.4.1'
  }
  stages {
    
    stage('Checkout Source') {
      steps {
        git 'https://github.com/rohitkr114/parivesh_backend.git'
      }
    }
    
    stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
            }
        }

   stage ('Build') {
      steps {
               sh 'mvn -Dmaven.test.failure.ignore=true install' 
            }
  }
    stage('Build image') {
      steps{
          sh "docker build -t rohitkr115/parivesh2_dev:4.0 ."
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
