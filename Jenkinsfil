pipeline {
    agent any

    triggers {
        githubPush()
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
                echo "✅ Code checked out"
            }
        }

        stage('Compile') {
            steps {
                echo "--- Compiling Java files ---"
                sh 'javac -cp . Main.java MainApplication.java'
                echo "✅ Compilation successful"
            }
        }

        stage('Package') {
            steps {
                echo "--- Creating JAR file ---"
                sh 'jar cfm StudentManagement.jar MANIFEST.MF *.class'
                archiveArtifacts artifacts: 'StudentManagement.jar',
                                 fingerprint: true
                echo "✅ JAR created and archived"
            }
        }
    }

    post {
        success {
            echo "🎉 Build #${env.BUILD_NUMBER} passed!"
        }
        failure {
            echo "❌ Build #${env.BUILD_NUMBER} failed!"
        }
        always {
            cleanWs()
        }
    }
}
