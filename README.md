# Deathmatch
![alt Minecraft Version][MC] ![alt Version][VERSION]

Open-Source Recreation of the populair minecraft minigame Deathmatch (found on the dutch SerpentGames server). In deathmatch you spawn in with your kit and you will have to search for loot and try to survive as long as possible.

## The Game
The point of deathmatch is that you survive with as many kills as possible. You will spawn in with a kit and you will have to search for loot (chests). Each chest has its own category and will drop different loot types. The game also modify existing items, such as grenades (snowballs), magic wands (blaze rod) and much more.

*This Plugin is made to run on a server dedicated for DeathMatch and does not work in combination with any other gamemodes or plugins at the moment.*

### Setup

 1. Install the plugin on your bukkit server (or spigot or something)
 2. Run the server once
 3. Open the config file and set maintenance to true and give yourself OP or the appropiate permissions
 4. Build the map
 5. Set the main spawn and add a couple game spawns (`/deathmatch spawn add [main/game]`)
 6. In the main spawn area add a sign with on the first line `[dmjoin]`
 7. Open the config file and set maintenance to false
 8. Restart the Server
 9. Have Fun!

### Permissions
```
deathmatch.*:
     description: Gives access to all Deathmatch commands
     default: op
     children:
       deathmatch.help.admin: true
       deathmatch.join: true
       deathmatch.join.maintenance: true
       deathmatch.leave: true
       deathmatch.place.sign: true
       deathmatch.reload: true
   deathmatch.help.admin:
     description: Show admin commands
     default: op
   deathmatch.join:
     description: Allows you to join deathMatch
     default: true
   deathmatch.join.maintenance:
     description: Allows you to join the server when the server is in maintenance mode
     default: op
   deathmatch.leave:
     description: Allow you to leave deathmatch trough a command
     default: true
   deathmatch.place.sign:
     description: Allows you to place DeathMatch command signs (such as join)
     default: op
   deathmatch.reload:
     description: Allows you to reload the game config files
     default: op
   deathmatch.spawn.modify:
     description: Allows you to modify all spawns
     default: op
```

### Commands
`/deathmatch` / `/dm` - main command
`/deathmatch help` - Show  all commands
`/deathmatch help admin` - Show all admin commands
`/deathmatch join` - Join Deathmatch
`/deathmatch leave` - Leave Deathmatch
`/deathmatch reload` - Reload the Deathmatch Config
`/deathmatch spawn [add/delete] [main/game]` - Set or delete the Main spawn or add or delete a game spawn

## Contributing
If you want to help developing Deathmatch fork the project and send a pull request with your features/fixes.

## Known Issues
None :D

## Todo
 - [ ] Add Chests with items (different chest categories)
 - [ ]  Add Kits
 - [ ] Add Stats
 - [ ] Add Special Items (grenades etc)
 - [ ] Add Coinsystem

![alt Chicken][CHICKEN]

[MC]: https://img.shields.io/badge/Minecraft%20Version-1.12.2-blue.svg
[VERSION]: https://img.shields.io/badge/Plugin%20Version-v0.1.0-green.svg
[CHICKEN]:https://i.pinimg.com/originals/c8/da/21/c8da215a501a4bf13e0cccfed6bddc07.png
