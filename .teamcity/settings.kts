import jetbrains.buildServer.configs.kotlin.*
import jetbrains.buildServer.configs.kotlin.amazonEC2CloudImage
import jetbrains.buildServer.configs.kotlin.amazonEC2CloudProfile

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2022.04"

project {

    features {
        amazonEC2CloudImage {
            id = "PROJECT_EXT_3"
            profileId = "amazon-1"
            agentPoolId = "-2"
            name = "tmpl2"
            vpcSubnetId = "__TEMPLATE__VALUE__"
            iamProfile = "__TEMPLATE__VALUE__"
            keyPairName = "__TEMPLATE__VALUE__"
            instanceType = "t2.medium"
            userScript = "echo a"
            customizeLaunchTemplate = true
            source = LaunchTemplate(templateId = "lt-0435e6b9962b39280", version = AmazonEC2CloudImage.LATEST_VERSION)
        }
        amazonEC2CloudImage {
            id = "PROJECT_EXT_5"
            profileId = "amazon-1"
            agentPoolId = "-2"
            name = "mmm"
            instanceType = "t2.medium"
            securityGroups = listOf("sg-1cdaad75")
            source = Source("ami-057a4b45218ec9769")
        }
        amazonEC2CloudProfile {
            id = "amazon-1"
            name = "ec2"
            terminateIdleMinutes = 30
            region = AmazonEC2CloudProfile.Regions.US_EAST_N_VIRGINIA
            authType = accessKey {
                keyId = "credentialsJSON:22283d83-b64e-4149-b16f-8f3eba562cb3"
                secretKey = "credentialsJSON:c8369df2-4742-4785-a4bf-493b9ca875af"
            }
        }
    }
}
