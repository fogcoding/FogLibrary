VScode的maven配置信息：



| Name                                 | Description                                                  | Default  Value                                          |
| ------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------- |
| maven.excludedFolders                | Specifies file path pattern of  folders to exclude while searching for Maven projects. | [  "**/.*", "**/node_modules", "**/target",  "**/bin" ] |
| maven.executable.preferMavenWrapper  | Specifies whether you prefer  to use Maven wrapper. If true, it tries using 'mvnw' by walking up the parent  folders. If false, or 'mvnw' is not found, it tries 'mvn' in PATH instead. | TRUE                                                    |
| maven.executable.path                | Specifies absolute path of  your 'mvn' executable. When this value is empty, it tries using 'mvn' or  'mvnw' according to the value of 'maven.executable.preferMavenWrapper'. E.g. /usr/local/apache-maven-3.6.0/bin/mvn | ``                                                      |
| maven.executable.options             | Specifies default options for  all mvn commands. E.g. -o -DskipTests | ``                                                      |
| maven.pomfile.autoUpdateEffectivePOM | Specifies whether to update  effective-pom automatically whenever changes detected. | FALSE                                                   |
| maven.pomfile.globPattern            | Specifies the glob pattern  used to look for pom.xml files.  | **/pom.xml                                              |
| maven.terminal.useJavaHome           | If this value is true, and if  the setting java.home has a value, then the environment variable JAVA_HOME  will be set to the value of java.home when a new terminal window is created. | FALSE                                                   |
| maven.terminal.customEnv             | Specifies an array of  environment variable names and values. These environment variable values will  be added to the terminal session before Maven is first executed. environmentVariable: Name of the  environment variable to set. value: Value of the environment variable to set. | []                                                      |
| maven.terminal.favorites             | Specify pre-defined favorite  commands to execute. alias: A  short name for the command. command: Content of the favorite command. | []                                                      |
| maven.view                           | Specifies the way of viewing  Maven projects. Possible values: flat, hierarchical. | flat                                                    |