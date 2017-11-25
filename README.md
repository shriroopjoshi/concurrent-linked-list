# LazyList

This project is an implementation of linked lists using lazy synchronization (fine-grained threading). In addition to all standard operations it includes a <code>replace</code> operation. The replace operation takes two keys <i>k</i> and <i>l</i>. It then atomically removes <i>k</i> from the list, if present, and adds <i>l</i> to the list, if not present. it returns true if the list was modified in any way.

The algorithm I developed for replace operation is:
1. Find the window
2. Lock the window
3. Add a node with value <i>l</i>, if not present. Also, tag the node if it was not present.
4. Again find the window for node with <i>k</i> value.
5. Mark the node for deletion, and untag the node with <i>l</i> value.

I also modified contains operation to not consider tagged nodes.

### System requirements
1. JDK/JRE 1.7+
2. Apache ant runtime

### How to build
This project does not contains any third party dependencies. Just navigate to project root directory and build the project using ant.
Here's an example:<br>
(This example assumes ant is added to system path)
<pre>
    <code>
        $ cd lazylist
        $ ant
    </code>
</pre>

### How to execute
Simply navigate to project root directory and use <a href="run.cmd"><code>run.cmd</code></a> to start execution.
Here's an example:<br>
<pre>
    <code>
        $ cd lazylist
        $ run.cmd
    </code>
</pre>

This is a batch script. If you are using linux, it can be converted to a simple shell script.