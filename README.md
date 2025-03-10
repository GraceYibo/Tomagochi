# My Personal Project

## Summary of the project
This is the project of the **CPSC 210** course offered by the University of British Columbia. 
This application is a simple version of a simulator game for rasing pets (inspired by Tamagotchi) which targets people of all ages. 
This project will be raising a pet, feeding it, cleaning it and interacting with it. After it grows up you can choose to let it get married or keep taking care of it, if you choose to let it get maried you will then raise it's child, if you choose to continue rasing it you will raise it until it dies, then you will raise a new pet.
I choose this project since I really like to play Tomagochi which is a pet simulator game, and I have always been cuirious of how this game have been coded. Now, having this opportunity to design this type of game myself is very engaing to me.

## How this project works

- feed the pet
- clean the pet
- play games with the pet
- if the pet get sick, heal the pet



## *User Stories*

For example, in the context of a tomagochi game application:

- As a user, I want to be able to add a charactor to my tomagachi
- As a user, I want to be able to view the list of charactors that are alive in my tomagachi
- As a user, I want to be able to view the list of charactors that have died in my tomagachi
- As a user, I want to be able to see the different levels of each charactors such as fullness, cleaness etc.
- As a user, I want to be able to feed, clean and interact with each charactors to raise levels. 
- As a user, I want to be able to remove a charactor from my tomagachi
- As a user, I want to be able to save my list of pets of the game.
- As a user, I want to be able to load my list of pets of the previous game.



## Instructions for End User

A list of pet is generated when game starts, everytime user raise a new pet the new pet will be put in to the list of pets automatically.
When starting a new game, if the pet user raised before user closed the game is still alive, pannel will ask whether the user still wants to continue with that pet. If yes then user continue raising that pet.
If no, a new game starts. The user choose the name and gender of the pet, also whether to load a pst game or not. If past game loaded all the saved pets will reoccur. 
In the game pannel, user could click buttons to feed, clean, play, heal the pet. There is also buttons for check status, view the pet, view all pets, search pet, save and quit game. Each button brings up a new window and changed the status of the pet. 
When clicked view all pets, user could choose to remove a pet from the list of pets. If removed current pet being raised a new game will appear. Could search for a past pet when clicking search pet. 
Clicking save will save all pets. Clicking quit will quit this game, a window of asking whether user want to save game before quiting will pop up, if yes the game saved if no the game quited without saving.
The pet's status and health changes with time, everyday the pet increase age by 1, when age of 5 user could chose to let pet get married or continue raising it. Chose marry will mean the pet get married of and user will raise a new pet, the pet's status will change to married instead of alive. Chose continue, the pet will die of oldness when hits an random age.
If all pet's level hits 0 and health status is false then pet will die. User will raise a new pet.
Visual components are in every window of this game.

## Phase 4: Task 3

For my UML diagram I included all the packages and all classes, interface etc in those packages. I drew the assiciationa between them and color coded them to show more clearly.

Reflecting on the design presented in the UML class diagram, one key area for improvement is the separation of concerns between the GUI components and the core application logic. Currently, several GUI classes (e.g., PullDataWindow, GenderWindow) directly manipulate or initialize objects from the model package (like Pet and AllPets). This coupling makes it harder to test and maintain the core logic independently from the GUI and can hinder scalability.

If given more time, I would refactor the design to introduce a Controller layer that acts as an intermediary between the GUI and the model package. This refactoring would involve extracting the logic for initializing and modifying Pet and AllPets instances from the GUI classes and encapsulating it in dedicated controller classes. For example, a PetController could handle tasks like creating pets, setting their attributes, and interacting with the persistence layer (e.g., reading or writing JSON files). The GUI classes would then delegate these responsibilities to the controller, reducing their direct dependency on the model.

This refactoring would improve the design by adhering to the Model-View-Controller (MVC) pattern, enhancing modularity and testability. By isolating business logic in controllers, the application would also be more adaptable to changes in the GUI (e.g., replacing Swing with another framework). While this approach adds complexity in the short term, the long-term benefits in maintainability and scalability make it a worthwhile tradeoff.




