# Mazing
Mazing is a command-line game where you start the game in a certain room, and you gather item and use them to make your way out of the maze on time!  
The maze is made up of several rooms connected by locked or unlocked doors.  
You win if you make your way out of the maze and reach the end room.  
You lose if you decide to quit or restart the game, or if the time needed to win elapses.  

## Getting Started
This project is not deployed, but you can easily set it on your local device following these steps:
1. clone this project on your local device
1. download and install java jdk 14 from [here](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
1. download and install maven from [here](https://maven.apache.org/)
1. mark jdk 14 as the project sdk
1. build the project with maven
1. Compile and run the Starter class
1. Have Fun!

## How to play
#### Note: You "choose" a certain command [COMMAND] by writing the name of command "COMMAND_NAME" in the console and clicking enter. 

- When the map is chosen, your character starts in a certain room, with a certain amount of gold and certain orientation direction.
- Each room is defined as a block, with 4 surrounding walls, each wall could be empty, or have one of the 5 wall objects (seller,chest,door,painting,mirror). 
- Chests and doors could be locked, and if they are, you could open them with their corresponding keys.  
- Chests could have keys, flashlights and gold.  
- Sellers buy and sell certain item for a certain amount of gold.
- A painting or a mirror could have a key hidden behind it.
- Time needed, starting gold ,starting room and all other maze features are specified in the chosen map along with the exact structure of the maze.
- The goal of the game is to get your character out of the maze to the "END" room on time.  


- **Choosing the map**: When you first open the game, it will ask you to choose a map.  
    - There are two predifined maps; hard and medium, you can choose either one of them
    - You can also choose custom, which is an almost empty map, that you can define yourself, more on this below
    - You can also choose any map name if you already create one with that name in folder "json", more on this below
    
- **Commands**: After choosing the map, the game starts, and you play using the following commands:

    - Navigation:
        - **left**: The character turns left, changes orientation from north to west for example.
        - **right**: The character turns right, changes orientation from north to east for example.
        - **forward**: If the character is facing an open door, he walks through the door to the room behind it.
        - **backward**: If there is an open door behind the character, he walks through the door to the room behind it.
        
    - Status:
        - **playerstatus**: Prints the character's oreintation direction, gold count and item
    
    - Time:
        - **time**: Prints elapsed time since the game started as well as the remaining time you have until you finish.
      
    - Quit/Restart:
        - **quit**: You quit and lose the game
        - **restart**: You restart and lose the game
        
    - Looking
        - **look**: Prints the type of wall object in front of you (chest,door,painting,mirror,seller,empty wall)
        
    - Checking:
        - **check [wallObject]**: If [wallObject] is in front of your character, and it can be checked, it will be checked. Checkable wall objects->(chest,door,painting,mirror)
            - **check chest**: If chest is locked, it will print the name of the key needed to unlock, if it is open and has not been looted, it will be looted.
            - **check door**: It will print the door status (locked/unlocked), and the name of the key needed to unlock if it is locked.
            - **check mirror/painting**: It will acquire the key behind the painting/mirror if there is one.
        
    - Using Items:
        - **use [itemName]**: If [itemName] is an item with your character, and is usable, it will be used. Usable item->(key,flashlight)
            - **use key[N]**: If there is a door/chest that is locked/unlocked with the key[N] in front of your character, it will unlock/lock it. N is the keyId, key[N] becomes key5 for example.
            - **use flashlight** Turns the flashlight on/off, If the current room is not lit, it will become lit
            
    - Switch Lights:
        - **switchlights**: If the current room has lights, it will turn the lights on/off.
        
    - Trading:
        - **trade**: If there is a seller in front of your character, it will enter trading mode with this seller, after which the following list of commands will be available:
            - **buy [itemName]**: will buy the item corresponding to [itemName] from the seller if it is listed in the seller's item, and your character has enough gold
            - **sell [itemName]**: will sell the item corresponding to [itemName] from the seller, if it is listed in the seller's item, and your character has it
            - **list**: will list the seller's item again
            - **end trade**: will exit the trading mode and go back to accepting main commands
            
    - Help:
        - **help**: choosing [help] will list all of the available commands and describe their usage
            
## How to define a new map
In the folder called "json", in the root directory, there is already two complete predifined maps (hard.json, medium.json), but what if you want to play a new map?  
Maps are designed using [JSON](https://en.wikipedia.org/wiki/JSON), which uses a human readable text to define objects.  
To define a new map, you can either use the file (custom.json) inside of the "json" folder, or define a new file with a new unique name, which you can then write when the game asks you to choose a map.  
In the json file, you will define exactly how the map is constructed using the following json objects:
   
### jsonMap:
```
{
  "goldCount":0,
  "direction":"east",
  "item":[],
  "secondsNeeded": 120,
  "rooms":
  [
  ]
}
```
- goldCount: **number**; initial gold count the character should have
- direction: **string**; initial direction for character(east,west,north,south)
- item: **list of jsonItem(jsonKey,jsonFlashlight)**; initial item that the character should have
- secondsNeeded: **number**; maximum time in seconds of how much this map should
- rooms: **list of jsonRoom**; all the rooms in this map with there exact specifications


### jsonRoom:
```
{
  "id":1,
  "east":
  {
  },
  "west":
  {
  },
  "north":
  {
  },
  "south":
  {
  },
  "isLit":true,
  "hasLight":true
}
```
- id: **number**; game.game.Room id, used to identify the room
- east: **jsonWall(jsonChest,jsonDoor,jsonPainting,jsonMirror,jsonSeller)**; wall.wall.Wall object on the east of this room, with its exact specification, set to null if the wall is empty
- west: **jsonWall(jsonChest,jsonDoor,jsonPainting,jsonMirror,jsonSeller)**; wall.wall.Wall object on the west of this room, with its exact specification, set to null if the wall is empty
- north: **jsonWall(jsonChest,jsonDoor,jsonPainting,jsonMirror,jsonSeller)**; wall.wall.Wall object on the north of this room, with its exact specification, set to null if the wall is empty
- south: **jsonWall(jsonChest,jsonDoor,jsonPainting,jsonMirror,jsonSeller)**; wall.wall.Wall object on the south of this room, with its exact specification, set to null if the wall is empty
- isLit: **boolean**; are the lights in this room on?
- hasLights: **boolean**; does this room have lights?

### jsonPainting:
```
{
  "type": "painting"
}

```
- behind: **number**; keyId of the key behind this painting, set to null if there is no key


### jsonMirror:
```
{
  "type": "mirror",
  "behind": 3
}

```
- behind: **number**; keyId of the key behind this mirror, set to null if there is no key


### jsonSeller:
```
{
  "type": "seller",
  "items": 
  [

  ]
}
```
- items: **list of jsonItem(jsonKey,jsonFlashlight)**; item on sale by this seller


### jsonChest:
```
{
  "type": "chest",
  "locked": true,
  "keyId": 6,
  "inside": [

  ]
}
```
- locked: **boolean**; is this chest locked?
- keyId: **number**; keyId of the key used to lock this chest, no need to specify if chest is unlocked
- inside: **list of jsonItem(jsonKey,jsonFlashlight)**; item inside this chest


### jsonDoor:
```
{
  "type": "door",
  "locked": true,
  "keyId": 3,
  "from": 1,
  "to": 4
}
```
- locked: **boolean**; is this door locked?
- keyId: **number**; keyId of the key used to lock this door, no need to specify if door is unlocked
- from: **number**; roomId of one of the rooms this door is connected to, other than **to** (does not actually matter if it is from or to)
- to: **number**; roomId of one of the rooms this door is connected to, other than **from** (does not actually matter if it is from or to)



### jsonGold:
```
{
  "type": "gold",
  "count": 10
}
```
- count: **number**; Number of golds this gold item represents


### jsonKey:
```
{
  "type": "key",
  "keyId": 1,
  "price": 5
}
```
- keyId: **number**; item.item.Key id of this key
- price: **number**; The worth of this key in gold, useful for sellers, no need to specify if 0 or if this key is not part of a seller's list

### jsonFlashlight:
```
{
  "type": "gold",
  "price": 10
}
```
- price: **number**; The worth of this flashlight in gold, useful for sellers, no need to specify if 0 or if this flashlight is not part of a seller's list


#### Notes:
- The starting room should have a room id of **1**
- The end room should have a room id of **-1**, and should logically not have anything but the one door connecting it to the rest of the map
- You can delete the *hidden* property (or specify it as null) from a painting/mirror if it does not have a key
- You can delete the *locked* property (or specify it as null) from a door if it is unlocked
- You can delete the *east*/*west*/*north*/*south* property (or specify it as null) from a room if it has an empty wall there



All these json objects are defined in "objects.json" inside of the "json" folder, to see real examples of how a json map file would look like after it is constructed, look at "medium.json" and "hard.json".



## Auhtors
- Mohammad Abdallah












