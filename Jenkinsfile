pipeline {
    agent any
    environment {
        MAVEN_HOME = tool 'Maven'
        PATH = "${MAVEN_HOME}/bin:${env.PATH}"
        SCANNER_HOME = tool 'Sonar'
        // Cela peut être Nexus3 ou Nexus2
        NEXUS_VERSION = "nexus3"
        // peut être http ou https
        NEXUS_PROTOCOL = "http"
        //Où est exécuté votre Nexus
        NEXUS_URL = "172.17.0.2:8081"
        // Dépôt où nous téléchargerons l'artefact
        NEXUS_REPOSITORY = "repo_my_job"
        // Identifiant d'identification Jenkins pour s'authentifier auprès de Nexus OSS
        NEXUS_CREDENTIAL_ID = "NEXUS_CRED_JOB"

        ARTIFACT_VERSION = "${BUILD_NUMBER}"
    }
    stages {
        stage('Construction ou Build') {
            steps {
                sh "mvn clean install"
            }
        }
        stage('Test') {
            steps {
                sh "mvn compile"
            }
        }
        stage('Analyse SonarQube') {
            steps {
                script {
                    sh "mvn clean verify sonar:sonar -Dsonar.projectKey=testMyJob -Dsonar.projectName='testMyJob' -Dsonar.host.url=http://172.17.0.3:9000 -Dsonar.token=sqp_79cf9958dc70247e69736b20ced0234748aa227e"
                }
            }
        }
        stage('Télécharger le projet de artefact sur Nexus Repository Manager') {
            steps {
                script {
                    // Lisez le fichier XML POM à l'aide de l'étape 'readMavenPom', cette étape 'readMavenPom' est incluse dans : https://plugins.jenkins.io/pipeline-utility-steps
                    pom = readMavenPom file: "pom.xml";
                    // Rechercher l'artefact construit dans le dossier cible
                     filesByGlob = findFiles(glob: "target/*.${pom.packaging}");
                    // Imprimez quelques informations sur l'artefact trouvé
                    echo "${filesByGlob[0].name} ${filesByGlob[0].path} ${filesByGlob[0].directory} ${filesByGlob[0].length} ${filesByGlob[0].lastModified}"
                    // Extraire le chemin du fichier trouvé
                    artifactPath = filesByGlob[0].path;
                    // Attribuer à une réponse booléenne vérifiant si le nom de l'artefact existe
                    artifactExists = fileExists artifactPath;

                    if(artifactExists) {
                        echo "*** File: ${artifactPath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${pom.version}";

                        nexusArtifactUploader(
                            nexusVersion: NEXUS_VERSION,
                            protocol: NEXUS_PROTOCOL,
                            nexusUrl: NEXUS_URL,
                            groupId: pom.groupId,
                            version: ARTIFACT_VERSION,
                            repository: NEXUS_REPOSITORY,
                            credentialsId: NEXUS_CREDENTIAL_ID,
                            artifacts: [
                               // Artefact généré tel que les fichiers .jar, .ear et .war.
                               // Mais dans notre cas, on a un .jar
                               [artifactId: pom.artifactId,
                                  classifier: '',
                                  file: artifactPath,
                                  type: pom.packaging]
                               ]
                        );

                    } else {
                        error "*** File: ${artifactPath}, could not be found";
                    }
            }
    }
}
}
}
