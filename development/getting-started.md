---
title: Getting Started
parent: Development
nav_order: 1
---

# Getting Started
{: .no_toc }

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
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<version>1.18.22</version>
			<scope>provided</scope>
		</dependency>
		<dependency> <!-- For Eclipse users -->
			<groupId>org.jetbrains</groupId>
			<artifactId>annotations</artifactId>
			<version>19.0.0</version>
			<scope>provided</scope>
		</dependency>

		<dependency>
			<groupId>com.github.casterlabs.casterlabs-caffeinated</groupId>
			<artifactId>PluginSDK</artifactId>
			<version>1.2.0</version>
			<scope>provided</scope>
		</dependency>
	</dependencies>
```

## Plugin Loading
While the SDK may look similar to Bukkit, it is not the same.  
Your plugin class will need to be annotated with `@CaffeinatedPluginImplementation` to be found and loaded. (You can have multiple plugins in the same jar file)  

```java
@CaffeinatedPluginImplementation
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

As you can see, the plugin class extends `CaffeinatedPlugin` and implements `onInit()`, `onClose()`, `getName()`, and `getId()`.  
`getName()` and `getId()` must return a name and unique id respectively, otherwise the plugin will fail to load.  
  
When the plugin loads, you will see a message in the console like this:
`[INFO  ] [My First Plugin] Hello World!`  

To build your plugin jar, just run `mvn install` in the root of your project or use your IDEs built-in maven features.
Place your jar into `%appdata%/casterlabs-caffeinated/plugins` for it to be loaded.

<br>
<br>
<br>
<span class="fs-3">
	[Next, Creating a widget.](/caffeinated-sdk/development/creating-a-widget){: .btn .float-right }
</span>