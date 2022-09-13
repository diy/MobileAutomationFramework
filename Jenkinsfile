pipeline {
    any agent
    stages {
        stage('Checkout') {
            echo 'checking out project'
             final String url = 'https: //github.com/sudhanshudiy/MobileAutomationFramework.git'
                       withCredentials([usernameColonPassword(credentialsId: "jenkins-api-token", variable: "API_TOKEN")]) {
                       final String response = sh(script: "curl -s -u $API_TOKEN $url", returnStdout: true).trim()
                        echo response
        }

        stage('build') {
            echo 'building the project ....'
                . / gradlew clean build - x test
        }

        stage('test') {
            echo " running the test ..."
                . / gradlew clean build
        }
    }
}