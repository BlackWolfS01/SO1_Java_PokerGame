/*
November 5, 2018
Original Author Information:
Matthew J. Werner
Founder / Lead Developer
Spectre.ZERO.ONE Studios LLC
Eciplse Approved Public License
(Check Attached txt file)
(Origin: CS251 - Assignment 3)

This project is freely available to the public. Anyone can download and modify the project; we only ask that credit
be given to the original author(s). If anyone wants to get involved and contribute to the project. Please email me
at mjwerner@uwm.edu, or alternatively, at matthew.werner@spectre-zero-one-studios.comm.

Core Concept - Apart of Assigment 3. A basic five-card game of classic poker. Players could buy into a game. They'd be given
a stack of chips and dealt out five cards. Every hand is broken up into two rounds of betting. Where players can tke five
distinct actions; call, raise, check, fold, or bluff. Once a player folds, they lose and sit out the rest of the hand. After all the bets
have been placed, all players will show their cards and the player with best hand wins. Once the hand has concluded, the deck is
reshuffled and new cards are dealt.

Once a player looses all their chips, they leave the table. When only two players remain, the game enters its final hand.
If the players wins this match, they win the game.

Expanded Gameplay
Texas Hold'Em
Epic scene fromm Casino Royale, James Bond, 'Daniel Cragg', plays the final hand in a high-stakes game of Texas Hold'em.

AI Design
Built on a bare-bones, skeleton verssion of Fuzzy Interference system. FuzzyLogic in Game AI is used to constantly alter an
AI Agents' behavior. They'll prioritze certain conditions, but as circumstances change; these priortizes will be altered. A 
Fuzzy Interference system basically takes in a specific data set and produces a Fuzzy Set. In Java, a Fuzzy Set would be
treated as an 2D Array of doubles. The Agent would compare this set to its own list of rules.
Bottom line - You can setup certain strategies for the AI Agent to follow, and it can freely switch tactics on the fly. If the
AI Player is low on chips and cash, and their in danger of loosing. The Agent may decide to play more aggressively, and 
attempt a comeback. Or, they could play defensively, and try to bid their time until they get a good hand.

Plans:
Further develop the system and incorporate new features. Build it out 