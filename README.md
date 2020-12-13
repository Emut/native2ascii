# native2ascii
Convert special characters to ISO-8859-1 format



note to self: to deploy to github, create 2 files:<br>
./.mvn/maven.config : --settings ./.mvn/local-settings.xml
<br>
./.mvn/local-settings.xml : <br>
\<settings>
  \<servers>
    \<server><br>
      \<id>github\</id><br>
      \<password>"the generated token"\</password><br>
    \</server>
  \</servers>
\</settings>
