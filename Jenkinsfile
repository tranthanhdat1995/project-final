pipeline {
  agent {
    docker {
      image 'maven:3-alpine'
      args '-u root'
    }

  }
  stages {
    stage('build') {
      steps {
        sh 'ls -a'
        sh 'mvn -v'
        sh 'cd starter_code'
        sh 'mvn -X compile'
      }
    }

  }
}
