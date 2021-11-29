---
title: Development
nav_order: 7
has_children: true
---

# Getting Started

Caffeinated plugins are coded in Java.  

## Setting up your maven project
After creating a maven project, you should modify your `pom.xml` to include the following respositories and dependencies.
```xml
	<repositories>
		<repository>
			<id>jitpack.io</id>
			<url>https://jitpack.io</url>
		</repository>
	</repositories>

	<dependencies>
		<dependency> <!-- For Eclipse users -->
			<groupId>org.jetbrains</groupId>
			<artifactId>annotations</artifactId>
			<version>19.0.0</version>
			<scope>provided</scope>
		</dependency>

		<dependency>
			<groupId>com.github.casterlabs.casterlabs-caffeinated.app-framework</groupId>
			<artifactId>PluginSDK</artifactId>
			<version>1.2.0</version>
			<scope>compile</scope>
		</dependency>
	</dependencies>
```

## Plugin Loading
While the SDK may look similar to Bukkit, it is not the same.  
Your plugin class will need to be annotated with `@PluginImplementation` to be found and loaded, you can even load multiple plugins in the same jar file.  

```java
@PluginImplementation
public class MyFirstPlugin extends CaffeinatedPlugin {

    @Override
    public void onInit() {
        this.getLogger().info("Hello World!");
    }

    @Override
    public void onClose() {
        this.getLogger().info("Goodbye World!");
    }

    @Override
    public String getName() {
        return "My First Plugin";
    }

    @Override
    public String getId() {
        return "com.example.firstplugin";
    }

}
```
As you can see, the plugin class extends `CaffeinatedPlugin` and implements `onInit()`, `onClose()`, and `getName()`, and `getId()`.  
`getName()` and `getId()` must return a name and unique id respectively, otherwise the plugin will fail to load.  
  
When the plugin loads, you will see a message in the console like this:
`[INFO  ] [My First Plugin] Hello World!`  

[Click here](/caffeinated-sdk/development/creating-a-widget) to continue to the next section, Creating a Widget.