# native2ascii
Convert special characters to ISO-8859-1 format

<img alt="Lines of code" src="https://img.shields.io/tokei/lines/github/Emut/native2ascii-maven-plugin"> <img alt="GitHub last commit" src="https://img.shields.io/github/last-commit/Emut/native2ascii-maven-plugin"> <img src="https://img.shields.io/badge/maven-1.0--SNAPSHOT-yellow">

To use with Maven, add below snippet to project's pom.xml
```html
<dependencies>
    <dependency>
        <groupId>com.github.emut</groupId>
        <artifactId>native2ascii-maven-plugin</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
</dependencies>

<repositories>
    <repository>
        <id>native2ascii-maven-plugin</id>
        <url>https://raw.github.com/Emut/native2ascii-maven-plugin/mvn-repo/</url>
    </repository>
</repositories>
```

note to self: to deploy to github, create 2 files:<br>
./.mvn/maven.config : 
```
--settings ./.mvn/local-settings.xml
```
./.mvn/local-settings.xml : <br>
```html
<settings>
  <servers>
    <server>
      <id>github</id>
      <password>"the generated token"</password>
    </server>
  </servers>
</settings>
```
