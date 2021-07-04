---
title: "Chat Example: \"Hello world\""
parent: Examples
---

# Chat Example: "Hello World"

module.js:
```javascript

// Render out a settings page.
// Keep in mind that adding settings display will forcibly trigger a settings_update module event.
Module.settingsDisplay = {
    command: {
        type: "text",
        defaultValue: "!hello"
    },
    sendMessageBtn: {
        type: "button",
        label: "Send Message",
        onclick() {
            Koi.sendMessage("Hello world!", null, "PUPPET");
        }
    }
};

Module.on("settings_update", (newSettings) => {
    // Validate that the command starts with an exclaimation mark.
    if (!newSettings.command.startsWith("!")) {
        // Show an error dialog.
        alert("Command must start with an exclaimation mark (!).");
        return; // Reject the settings.
    }

    // The new settings passed validation, so we save.
    Module.settings = newSettings;
    Module.save();
});

// Listen for the command...
Koi.on("chat", (event) => {
    if (event.message === Module.settings.command) {
        // We use PUPPET to indicate that we prefer the chatbot account over the streamer's account.
        Koi.sendMessage("Hello world!", event, "PUPPET");
    }
});

```
  
modules.json:
```json
{
    "version": "1.0.0",
    "name": "Chat Example: \"Hello world\"",
    "modules": [
        {
            "namespace": "caffeinatedsdk_example_chat_hello_world",
            "src": "module.js",
            "type": ["settings"]
        }
    ],
    "required": [
        {
            "namespace": "caffeinatedsdk_example_chat_hello_world",
            "id": "chat_hello_world"
        }
    ]
}
```
  