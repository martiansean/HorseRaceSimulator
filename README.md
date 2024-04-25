# HorseRaceSimulator
The program is split into two parts: text-based horse racing simulator (part1) 
and the Simulation with GUI (part2)

## Dependencies
- **Java Development Kit (JDK)**
- **Java Swing Library** - This should be include in the JDK


## Usage and setup

### Setting up the project
1. Clone the repository.
2. Navigate into the folder `HorseRaceSimulator`
3. Navigate into your desire part  
**If you are running through an IDE, open each part individually**

### Part 1: Text-based simulation
#### How to compile and run the program
1. Ensure that you are in the correct directory (part1)
2. Execute the following command in the terminal `javac Race.java` in order to compile the program
3. Check if files with `.class` extension are present (They should retain the same file name as .java)
4. To run the program, in the terminal, run the command `java Race.java startRace`

#### Understand the program
- The program should automatically starts once executed.
- It should generate a text-based track with three horses each represented by a single character.
- The horse can fall and will be represented with a cross symbol.
- Once the race is finished, single horse can win or all can fall and lose, and the program will stop. 

### Part 2: Graphical User interface Simulation
#### How to compile and run the program
1. Ensure that you are in the correct directory (part2)
2. Execute the following command in the terminal `javac HorseRaceUI.java` in order to compile the program
3. Check if files with `.class` extension are present (They should retain the same file name as .java)
4. To run the program, in the terminal, run the command `java HorseRaceUI.java startRaceGUI`

#### Understand the program
**Initial setup**
- The program should generate a dark-blue user interface with two text input.
- Type in the number of horses you want to simulate, and the length of the track which will be used when compute speed of the horse.
- Hit generate and the program should create a dialogue box for you to enter the horse name and the colour of the horse armour.
- By default, if nothing is entered, the hose will be name "Horse_" follow by the track number. The default color is also RED.

**Betting Aspect**
- The user will receive initial 1000 credit to bet.
- The odds of the horse will be calculated by the win rate. This means as you play the odds will change.
- At start, the odds of the horse will be 1 since no data is available, so any horse win would double to money.
- There will be the betting text box for each horse at the bottom panel of the screen.
- by default, if no bet is enter, the amount will be 0.

**Running the game**
- Press start button and the horse will run.
- Horse that reach the end first is the winner.
- Hit start again to restart the game.
- To reset the entire game, simply hit generate track again.