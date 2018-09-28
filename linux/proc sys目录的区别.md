[What is in /dev, /proc and /sys?](https://unix.stackexchange.com/questions/188886/what-is-in-dev-proc-and-sys)


    The /dev tree contains device nodes, which gives user space access to the device drivers in your OS's running kernel.¹ All POSIX type OSes have a /dev tree.

    The /proc tree originated in System V Unix, where it only gave information about each running process, using a /proc/$PID/stuff scheme. Linux greatly extended that, adding all sorts of information about the running kernel's status. In addition to these read-only information files, Linux's /proc also has writable virtual files that can change the state of the running kernel. BSD type OSes generally do not have /proc at all, so much of what you find under here is non-portable.

    The intended solution for this mess in Linux's /proc is /sys. Ideally, all the non-process information that got glommed into the /proc tree should have moved to /sys by now, but historical inertia has kept a lot of stuff in /proc. Often there are two ways to effect a change in the running kernel: the old /proc way, kept for backwards compatibility, and the new /sys way that you're supposed to be using now.²

    