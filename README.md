# SENG201 Resource Towers Game

## Authors
- Megan Kulshekar
- Calan Meechang

## Cloning Project (Using IntelliJ)
1. Copy the URL of the repository
2. From IntelliJ's start screen select "Get from VCS"
3. Paste the URL and select where you want to save the project
4. Click "Clone"

## Cloning Project (Using the Command Line Interface)
1. Copy the URL of the repository
2. Open a command line interface within the directory where you want to save the project
3. Use the command `git clone URL` where URL is the link you copied
4. Press enter

## Importing Project (Using IntelliJ)
1. Launch IntelliJ and choose `Open` from the start-up window
2. Select the project and click open 
3. At this point in the bottom right notifications you may be prompted to 'load gradle scripts', If so, click load

## Run Project 
1. Open a command line interface inside the project directory and run `./gradlew run` to run the app
2. The app should then open a new window, this may not be displayed over IntelliJ but can be easily selected from the taskbar

## Build and Run Jar
1. Open a command line interface inside the project directory and run `./gradlew jar` to create a packaged Jar. The Jar file is located at build/libs/seng201_team0-1.0-SNAPSHOT.jar
2. Navigate to the build/libs/ directory (you can do this with `cd build/libs`)
3. Run the command `java -jar seng201_team0-1.0-SNAPSHOT.jar` to open the application

## Run Our Specific Jar
1. Access the Jar from the `SENG201Submission.zip` file
2. Open a command line interface within the directory where the Jar file named `cme110_mku71_ResourceTowersGame.jar` is located
3. Run the command `java -jar cme110_mku71_ResourceTowersGame.jar` to open the application