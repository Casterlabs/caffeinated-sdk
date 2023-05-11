---
title: Creating the Widget Source
parent: Development
nav_order: 3
---

# Creating the Widget Source

{: .no_toc }

There are two sides to creating a widget:

1.  Widget Side (The .jar that runs in Caffeinated)
2.  Stream Side (The .html that runs in OBS or is embedded elsewhere)

## Messaging

Widget Side and Stream Side can communicate with eachother over a Json based event system, this is done by implementing `Widget#onNewInstance()` and registering listeners using `WidgetInstance#on()` and sending messages back by using `WidgetInstance#emit()`.

In our case, we just want to log some basic info for the purpose of demonstrating how the system works, so that's what we'll do:

```java
@Override
public void onNewInstance(@NonNull WidgetInstance instance) {
    instance.on("message", (JsonElement e) -> {
        // We just print the raw json.
        FastLogger.logStatic("Received message from a widget instance: %s", e);

        // instance.emit("mytype", mymessage);
    });
}
```

## Initializing Stream Side

We first need to load in our widget html, this is done by implementing `Widget#getWidgetBasePath()` and telling Caffeinated what resource it needs to load. Next, we implement `CaffeinatedPlugin#getResource()` and actually load the file from our Jar.

```java
@Override
public @NonNull String getWidgetBasePath(WidgetInstanceMode mode) {
    return "/chat";
}
```

```java
@Override
public @Nullable Pair<String, String> getResource(String resource) throws IOException {
    // Figure out the mime tpye of the requested file.
    String mimeType = "application/octet-stream";
    String[] split = resource.split("\\.");
    if (split.length > 1) {
        mimeType = MimeTypes.getMimeForType(split[split.length - 1]);
    }
    // Read the file from our jar's resources. More advanced widgets can override
    // this behavior to serve a modern "site" such as React or Vue or SvelteKit.
    try (InputStream in = MyFirstPlugin.class.getClassLoader().getResourceAsStream(resource)) {
        return new Pair<>(IOUtil.readInputStreamString(in, StandardCharsets.UTF_8), mimeType);
    }
}
```

Next, we need to make our widget html. The example provided just writes a simple chatlog on the screen (with some basic customization), but you can do anything you want.

```html
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8" />
    <title>Custom Widget</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/webfont/1.6.28/webfontloader.js"></script>
  </head>

  <body>
    <span id="chatlog"></span>
  </body>

  <footer>
    <script>
      const chatLog = document.getElementById("chatlog");
      let oldConfig = {};

      // We use webfont loader because it's easy, that's it.
      function changeFont(fontname) {
        fontname = fontname || "Poppins";

        document.documentElement.style = "font-family: '" + fontname + "';";

        WebFont.load({
          google: {
            families: [fontname],
          },
        });
      }

      changeFont("Poppins");

      Widget.on("init", () => {
        console.log("Init!");

        // Manually trigger an update
        Widget.broadcast("update");
      });

      Widget.on("update", () => {
        changeFont(Widget.getSetting("chat_style.font"));
        chatDiv.style.color = Widget.getSetting("chat_style.text_color");
        chatDiv.style.fontSize =
          Widget.getSetting("chat_style.font_size") + "px";
      });

      // When we get chat messages we want to append them to the chat log.
      Koi.on("chat", (event) => {
        // escapeHtml is a helper function that escapes HTML characters. It's provided by the loader.
        // Additionally, we replace all spaces with non-breaking spaces so they don't get broken up.
        const html = `${event.sender.displayname} > ${escapeHtml(
          event.message
        ).replace(/ /g, "&nbsp;")}<br />`;

        // Append it.
        chatLog.innerHTML += html;
      });
    </script>
  </footer>
</html>
```

After creatring a new instance of this widget and loading it into OBS (or just a normal browser) you will see a basic chat history on the screen.

![The Stream Side in action](https://i.imgur.com/iN0okhV.png)

## Note about events

Koi events and music events are already sent to the Stream Side, so you don't need to implement your own event system.

## Note about frameworks

Casterlabs places no restrictions on what can be loaded by your plugin, just keep in mind that if you use a framework you will need to set it's base path to `/$caffeinated-sdk-root$` otherwise the app will not know how to serve the contents (that string gets replaced on every loaded file). Example SvelteKit config [here](https://github.com/Casterlabs/Casterlabs/blob/dev/caffeinated/widgets/svelte.config.js).
