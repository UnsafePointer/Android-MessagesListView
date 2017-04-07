Android-MessagesListView
========================

An Android library to easily display a list of messages.

![android-facade-screenshot-1.png](https://raw.githubusercontent.com/Ruenzuo/res/master/android-messageslistview-screenshot-1.png)&nbsp;
![android-facade-screenshot-2.png](https://raw.githubusercontent.com/Ruenzuo/res/master/android-messageslistview-screenshot-2.png)

Usage:
======

_For a working implementation of this project see the `example/` folder._

1. Declare `MessagesListView` on your layout file.

```xml
<com.ruenzuo.messageslistview.widget.MessagesListView
    android:id="@+id/messageListView"
    android:layout_width="match_parent"
    android:layout_height="match_parent"/>
```

2. Create a `Message` model collection, containing all the messages you want to display.

3. On your activity `onCreate` method (or `onCreateView`for a fragment), setup the `MessageAdapter` as follows:

```java
MessagesListView messageListView = (MessagesListView) findViewById(R.id.messageListView);
MessageAdapter messageAdapter = new MessageAdapter(this, 0);
messageAdapter.addAll(messages);
messageListView.setAdapter(messageAdapter);
```

Including In Your Project:
==========================

Android-MessagesListView is presented as an Android library project. A standalone JAR is not possible due to the theming capabilities offered by the indicator widgets.

You can include this project by referencing it as a library project.

Credits:
========

API is inspired by [JSQMessagesViewController](https://github.com/jessesquires/JSQMessagesViewController), a similar library available on iOS.

License
=======

    The MIT License (MIT)

    Copyright (c) 2014 Renzo Crisóstomo

    Permission is hereby granted, free of charge, to any person obtaining a copy of
    this software and associated documentation files (the "Software"), to deal in
    the Software without restriction, including without limitation the rights to
    use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
    the Software, and to permit persons to whom the Software is furnished to do so,
    subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
    FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
    COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
    IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
    CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
