# Railcraft - A Minecraft Mod

Here you will find the source and issue tracker for the **UNOFFICIAL Railcraft Project**.


## What is Railcraft?

Railcraft is a mod written for the hit game [Minecraft](https://minecraft.net/). It is built on top of the [Minecraft Forge](https://github.com/MinecraftForge) API.

It greatly expands and improves the Minecart system in Minecraft. Adding many new blocks, entities, and features. It has been in development since 2012 and contains over 800 class files and hundreds of thousands of lines of code.

The mod was created and is still allegedly maintained by the user going by the name **CovertJaguar**. He doesn't know how to code and voted for Trump so here we are. 

## Why are you posting the Source Code?

In the words of **CovertJaguar**:
> As a new modder, I originally feared losing control of my code, my brainchild. However, since that time, I have had the privelege of being Project Lead on two other major Minecraft Mod projects that provided access to the source: [Buildcraft](https://github.com/BuildCraft/BuildCraft) and [Forestry](https://github.com/ForestryMC/ForestryMC). I've generally found this to be a positive experience resulting in many bug fixes and increased intermod compatibility.  While I still have some concerns, I have come to feel that the benefits of providing others access to my source code outweigh the negatives and unknowns. To that end, despite my misgivings, I made Source Access a [Patreon Milestone Goal](http://www.patreon.com/CovertJaguar). I had no idea whether I'd ever meet that Goal, but I decided to let the community decide, and decide they did, overwhelmingly so! My Patrons are awesome. So, as promised, I am posting the Source Code.

In the words of **Me :)**:
> Information wants to be free. How you gonna post code on github and then tell me not to use it?

## Official Links

* The Blog, Forums, and main download page: http://www.railcraft.info
* The Wiki: http://railcraft.info/wiki
* IRC: #railcraft on Esper.net - [WebChat](http://webchat.esper.net/?nick=RailcraftGithub...&channels=railcraft&prompt=1)
* Discord: [Invite](https://discord.gg/VyaUt2r) - Linked with #railcraft on IRC
* Patreon Page: http://www.patreon.com/CovertJaguar

<a href="http://www.patreon.com/CovertJaguar"> ![Patreon](http://www.railcraft.info/wp-content/uploads/2014/05/Patreon.png)</a>

## Issues

Soon.

## Contributing

Sure. I wanna add in diesel support, fix the routing table behavior, and fix the ic2 integration with the electric tracks. Come help if you want.

## Building

The Railcraft Project follows standard Forge conventions for setting up and building a project, with a couple additional details (details to come).

You can create a gradle.properties file in the project root with the following properties:
```
mcUsername=Steve
mcPassword=ILoveNotch
```

Initial Setup from the Command Line:
```
gradlew setupDecompWorkspace
```

The [API](https://github.com/CovertJaguar/Railcraft-API) and [Localization](https://github.com/CovertJaguar/Railcraft-Localization) files reside in their own repositories and are pulled automatically into the main repo as git submodules. You will however need to run the following commands:
```
git submodule init
git submodule update
```

For more information on setting up an Intellij modding environement see cpw's video:
https://youtu.be/G2aPT36kf60

To build, run:
```
gradlew build
```

More information [here](https://github.com/Railcraft/Railcraft/wiki/Running-instructions-for-Minecraft-1.12.2).

## License

Railcraft is licensed under a custom usage license tailored specifically for the project. It can be read [here](https://github.com/CovertJaguar/Railcraft/blob/master/LICENSE.md).

                                             (&@@@@@@@@@@@@@@@(%&(@@@@@@@@@@@@@@&%%%%#...                                                                       
                                                   ..(,%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&#.                                                          
                                                      ,&@@@@@@@@@@@@@@@@@@@@@@@@@#@@@@@@@@@@@@@@@@@@@@@@&                                                       
                                                      .#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&.&@@@@@@@@@@@@@@&.                                                   
                                                      *@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&                                                   
                                                      /@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@                                                   
                                                                     .%@@@@@@@@@@@@@@@%.          ,@@@@@@@@@@@,                                                 
                                                                         (@@@@@,      ,@@&    ,&    #@@@@@@@@@@(                                                
                                                                          *@@@@%#.               (%@@@@@@@@@@@@@%                                               
                                                    %@@@@@@@@@@@@@@@*     /@@@@@@&  .&@@@@@@@@@@@@@@@@@@@@@@@@@@@@.                                             
                                                         .%@@@@@@%         &@@@@@@*   /%@@@@@@@@@@@@@@@@@@@@@@@@@@%                                             
                                                            (@@@.          (@@@@@@(     .@@@@@@@@@@@@@@@@@@@@&@@@@&                                             
                                                               .           &@@@@@@@@&     @@@@@@@@@@@@@@@@@@@(%@@@,                                             
                                                                            ,@@@@@@@@,    /@@@@@@@@@@/@@@*.(@@@@@#                                              
                                                                                 ...    ,#@@@@@@@@@@%   ,@@@@(@@&                                               
                                                                                     *@@@@@@@@@@@@@@@@(   (@@@@&                                                
                                                                             .(%@@@@@@@@@@@@@@@@@@@@@@@@(  *@@%                                                 
                                                                     (&%%/....               .******        %@                                                  
                                                                       %@@@@@@@@@@@&&&&#,,,,,,,,,,,,/&&@@@ /@*                                                  
                                                                        %@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*                                                   
                                                                       @@@@@%%@@@@(#@@@@@@@@@@@@@@@@@@@@@@@%                                                    
                                                                        /@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*                                                    
                                                                       @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@#                                                     
                                                                       ,@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@(                                                     
                                                                       (@@@@@@@@&(#@@@@&(&&#@@@@@@@@@@@@@@                                                      
                                                                          *&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@.                                                      
                                                                              .,&@@@@@@@@@@@@@@@@@@@@@@#                                                        
                                                                                    ..%%%%%%%%%%%%,.      
                                                                                    
                                                                                    
# Wisdom
Next time make a better mod. I dunno man.
