#COMP2042 SM Coursework - BRICK DESTROYER- Wang Xubin

#### Javedocs and Git history screenshot saved under the readme file ！！！

#  Project Introduction

This project is built using `jdk17`、`javafx15`、`maven3.6.1`.

The project uses the new feature of `java 9`, `Modular System`, which has the following advantages:

- The main change in Java 9 is that it is now a modular system with a modular JDK, a modular source code, and a modular runtime image.
- Internal API hidden in modules.
- The modular system creates more opportunities for project development that can handle distributed data processing. For example, the new Java 9 has been used to build IoT solutions and platforms that need to process a variety of data at the same time.
- Modules in Java now also make methods exposed inside modules and restrict access for normal users.

#  Project refactoring

Compared to the code provided by GitHub's original game, I made the following modification points:

1. Introduce `module-info.java` files to build modular projects to improve performance and security

2. use `maven` build project, the convention is greater than the configuration programming
3. Use the `mvc` architecture to handle the game's views, models, controls, and call between each layer by providing an exposed interface
4. The 'control' and `fxml` modules of `javafx` are used, the API provided by the `control` package is designed for the main user's game logic, and the support provided by the `fxml` package is used for the display of the page.
5. Added a level selection interface, I designed 12 default levels for the game, each with different difficulty levels with different difficulty. In some levels you may be able to get surprises.
6. Add custom settings, players can customize the size of the game screen, language and the sound size of the game
7. Keyboard or mouse operations performed in the Command design mode
8. The collision logic and collision detection of the original code are used; Optimized the original brick generation method (Canvas change to Image)
9. Diverse game voices
10. The logging function records various states, fps values, error logs, etc. during the game running
11. At the end of the game, a new high score pop-up window has been added and a new high score list has been created to permanently record the player's game score
12. Game debug function, easy to test game function
13. More gorgeous UI interface optimization, closer to the contemporary aesthetic of games
14. The game is controlled by using the keyboard's up, down, left, right and left buttons instead of the original WASD to control the movement of the game
15. Added some meaningful junit test
16. Setting page is drawn using `fxml` and in `mvc` mode. Supports setting game username, game window size, game music sound size, shortcut settings and other functions
17. Debug feature is drawn using `fxml` and in `mvc` mode. The implementation of the debug function is designed using the command line mode, and the game uses the 'debug' window. The command is currently supported:
```cmd
clear
help
list
paddleSize
restart
shortcuts
showFPS
win
```
The method of use can be viewed with help 'help', as follows:
```cmd
help help
help - shows help for given command
SYNTAX: help [COMMAND]
```
```
help list
list - lists all possible commands
```

#REPOSITORY url: https://github.com/Invinciblekaro/COMP2042_CW_hfyxw2
