---
title: Koi Apis
parent: Koi
---

# Koi Apis
  
## `koi.on(eventType: string, callback: Function)`  
Registers an event listener for a given event. You can also registed with an event type of `"event"` or `*` to get callbacks for ALL events.
Returns a listener id, use this to unregister your listener in the future.  
  
## `koi.removeListener(eventType: string, listenerId)`  
Unregisters an event listener.  
  
## `koi.sendChat(message: string, ?event: ChatEvent, ?chattter: string = "CLIENT")`  
Sends a message, the optional `event` parameter is to hint at koi which signed in account you intend the message for.  
`chatter` allows you to send messages as a linked chatbot account or as the streamer. If a user has not linked a chatbot account then this will always be `CLIENT`. Valid values are `CLIENT` and `PUPPET`.  
  
##  koi.`koi.getMaxLength(?event: ChatEvent)`  
Retrieves the max message length, the optional `event` parameter is to hint at koi which signed in account platform you're trying to retrieve message limits for.  
  
## koi.`koi.isAlive()`  
Makes sure koi is alive, this will not be a good indicator of internet availability. This is intended to allow developers to halt activities while Caffeinated attempts to reconnect, during which the app will blank the screen with a reconnect message.  
  
## `koi.upvote(messageId: string)`  
Upvotes a message, only caffeine.tv supports upvoting. This fails silently.  
  
## `koi.deleteMessage(messageId: string)`  
Deletes the message specified, keep in mind that not all platforms support this. This fails silently.  

## `koi.test(eventType: string)`  
Requests a test event, these will be delivered via all registered event listeners rather than returning an event object.  
