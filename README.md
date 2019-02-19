# taulogo
A Logo environment built on the Tau language.

# Introduction
This is a mostly functional Logo environment that was built on the Tau language. The user interface comes with a console and a multi-tab editor for Tau programs.

# Current State
1) The language is Tau, which provides all the functionality we need for basic Logo.
2) The following Logo commands are implemented:
   * forward / fd
   * back / bk
   * left / lt
   * right / rt
   * penup
   * pendown
   * wait
   * setforeground  (it might have been just foreground?)
   * setbackground  (it might have been just background?)
   * clearscreen
3) When drawing and erasing lines (fd 100 setforeground backgroundColor bk 100) you end up with some minor artifacts.
