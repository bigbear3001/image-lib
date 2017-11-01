# image-lib
Image lib for transcoding SVGs into java.awt.Image

The idea is to have a minified library to enable you to use SVG as images in your Swing / AWT project.

#use
If you want to use the minified version (currently still 3,2MB) add the following dependency to your project
```
		<dependency>
			<groupId>com.perhab</groupId>
			<artifactId>image-lib</artifactId>
			<version>0.0.1-SNAPSHOT</version>
			<classifier>min</classifier>
		</dependency>
```

If you want to use the normal version (e.g. for overwriting dependencies) add the following dependency to your project
```
		<dependency>
			<groupId>com.perhab</groupId>
			<artifactId>image-lib</artifactId>
			<version>0.0.1-SNAPSHOT</version>
		</dependency>
```

#build
To build the project you need to use at least Java version 8 and Maven version 3.
Execute the following steps
```
mvn package
```
