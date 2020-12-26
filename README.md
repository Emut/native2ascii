# native2ascii

Convert special characters to plain ASCII text

<img alt="GitHub last commit" src="https://img.shields.io/github/last-commit/Emut/native2ascii-maven-plugin">
<img src="https://img.shields.io/badge/maven-1.0.0-yellow">
<img src="https://img.shields.io/github/license/Emut/native2ascii">

# Usage

To use with Maven, add below snippets to project's pom.xml.

Project is not on maven central, it is hosted in this repo. Because of that, it is needed to add this repo as a source in the pom.xml.

```html

<repositories>
    <repository>
        <id>native2ascii-maven-plugin</id>
        <url>https://raw.github.com/Emut/native2ascii-maven-plugin/mvn-repo/</url>
    </repository>
</repositories>
```

```html

<build>
    <plugins>
        <plugin>
            <groupId>com.github.emut</groupId>
            <artifactId>native2ascii-maven-plugin</artifactId>
            <version>1.0-SNAPSHOT</version>
            <configuration>
                <conversionParameters>
                    <fileParameters>
                        <fileParameter>
                            ...
                        </fileParameter>
                        <fileParameter>
                            ...
                        </fileParameter>
                    </fileParameters>
                </conversionParameters>
            </configuration>
            <executions>
                <execution>
                    <goals>
                        <goal>generate</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
</build>
```

# Examples

For the following configuration:

```html

<fileParameters>
    <fileParameter>
        <inputFile>file.properties</inputFile>
        <outputFile>file.properties</outputFile>
        <commentPrefix>#</commentPrefix>
    </fileParameter>
</fileParameters>
```

<table>
<tr>
<th> Before </th>
<th> After </th>
</tr>
<tr>
<td colspan="2">Characters are converted and
original line is inserted as a comment with the "[n2a]" prefix</td> 
</tr>
<tr>
<td>

```properties
String = with simple chars
Getting = ÇŗæƵƴ
```
</td>
<td>

```properties
String = with simple chars
#[n2a]Getting = ÇŗæƵƴ
Getting = \u00C7\u0157\u00E6\u01B5\u01B4
```
</td>
</tr>
<tr>
<td colspan="2">Changes to comments are reflected to converted text</td> 
</tr>
<tr>
<td>

```properties
String = with simple chars
#[n2a]Getting = CræƵƴ
Getting = \u00C7\u0157\u00E6\u01B5\u01B4
```
</td>
<td>

```properties
String = with simple chars
#[n2a]Getting = CræƵƴ
Getting = Cr\u00E6\u01B5\u01B4
```
</td>
</tr>
<tr>
<td colspan="2">If a comment exists, changes to converted lines are discarded<br>
Changes must be made to the [n2a] comment above the line</td> 
</tr>
<tr>
<td>

```properties
String = with simple chars
#[n2a]Getting = CræƵƴ
Getting = Crazy
```
</td>
<td>

```properties
String = with simple chars
#[n2a]Getting = CræƵƴ
Getting = Cr\u00E6\u01B5\u01B4
```
</td>
</tr>
<tr>
<td colspan="2">Commented lines are simply ignored (excluding [n2a] tagged comments)</td> 
</tr>
<tr>
<td>

```properties
String = with simple chars
#[n2a]Getting = CræƵƴ
Getting = Crazy
#Çömment lınes are ığnöred
```
</td>
<td>

```properties
String = with simple chars
#[n2a]Getting = CræƵƴ
Getting = Cr\u00E6\u01B5\u01B4
#Çömment lınes are ığnöred
```
</td>
</tr>
<tr>
<td colspan="2">However inserting comments between [n2a] comments and
converted lines is not a good idea,<br>
as the line below [n2a] comment is assumed to be the related converted line</td> 
</tr>
<tr>
<td>

```properties
String = with simple chars
#[n2a]Getting = CræƵƴ
#Çömment lınes are ığnöred
Getting = CræƵƴ
```
</td>
<td>

```properties
String = with simple chars
#[n2a]Getting = CræƵƴ
Getting = Cr\u00E6\u01B5\u01B4
#[n2a]Getting = CræƵƴ
Getting = Cr\u00E6\u01B5\u01B4
```
</td>
</tr>
</table>

[comment]: <> (note to self: to deploy to github, create 2 files:<br>)

[comment]: <> (./.mvn/maven.config :)

[comment]: <> (```)

[comment]: <> (--settings ./.mvn/local-settings.xml)

[comment]: <> (```)

[comment]: <> (./.mvn/local-settings.xml : <br>)

[comment]: <> (```html)

[comment]: <> (<settings>)

[comment]: <> (    <servers>)

[comment]: <> (        <server>)

[comment]: <> (            <id>github</id>)

[comment]: <> (            <password>"the generated token"</password>)

[comment]: <> (        </server>)

[comment]: <> (    </servers>)

[comment]: <> (</settings>)

[comment]: <> (```)
