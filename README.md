# taulogo
A Logo environment built on the [Tau language](https://github.com/charlvj/taulang).

# Introduction
This is a mostly functional Logo environment that was built on the Tau language. The user interface comes with a console and a multi-tab editor for Tau programs.

Please see the [TauLang](https://github.com/charlvj/taulang) page for documentation about the language itself, however, in basic usage it is very similar to Logo.

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
3) When drawing and erasing lines _backwards_ (fd 100 setforeground backgroundColor bk 100) you end up with some minor artifacts. 
