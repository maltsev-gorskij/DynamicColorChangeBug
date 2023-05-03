## Repository information
Minimal example to reproduce bug in dynamic transition background color change using Odyssey library 

## Description
In one of my applications, there was a need to implement a dynamic theme change in the application.

In the Odyssey library, the background color when animating the transition between screens is set by a separate parameter backgroundColor in the OdysseyConfiguration class, which is passed to setNavigationContent when constructing the navigation graph.

In this example, i tried to simulate a situation, when changing the theme programmatically, the transition background color also changes dynamically.

Initially, there are no problems, but after the first transition background color change, the screen is become colored with the solid color of the current background. The content on the screen disappears.

Way to reproduce: Click on "Set Dark Transition Background" button on first screen.

## Tech stack
- Navigation
    - [Odyssey](https://github.com/AlexGladkov/Odyssey) - Navigation
