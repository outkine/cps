# Super Reflection
## Made with <3 and coffee by Anton

Oh [Java](https://www.xkcd.com/801/), the good time we've had. I definitely learned something with this project, there's no question there. I've discovered interesting features, but also nuances that made me want to rewrite everything in Visual Basic.

# Hey, yo. Compare UML ?
A bullet point list seems appropriate:
* My `Game` class (`Main` in UML) turned out to just be one `main` method - I thought that it would be a whole JFrame, but I ended up just sticking to the CLI. No need for instance variables because everything lives only inside of the `main` method.
* I correctly predicted the need for a `Human` superclass, but incorrectly predicted that `hit` will be in it. More on that in a second.
* I correctly predicted the need for a `Hand` class, but it turned out much larger than I thought. One specific difference is the need for states: very often in the `Dealer` class I had to find out what state the player or dealer is in, and the `Hand` class is perfect for this. In fact, I even made a separate enum (`HandState`) to hold the busted-or-not state.
* My UML `Dealer` class was empty, but in reality it houses most of the logic. The `startRound` method is the largest in the entire project, just because the logic of what happens in a round is the most complicated.
* My `Card` class had the right private properties, but I ended up adding a bunch of static properties. Those are the lists of all possible card states, such as ranks and suits, and the value of each rank. Although these are used primarily in other classes, the `Card` class nonetheless felt like the most natural place to put them.
* I wasn't sure about how the `Shoe` worked, which is why my UML gave it a list of decks. The final version instead concatenates all of those into one big `Card` list.
* Lastly, I didn't anticipate the need for one important superclass: `CardCollection`. It turns out that the `Deck` and `Shoe` classes are ultimately really similar, and so instead of putting logic that deals with card collections in both, I elevated it into that superclass.


# Bugs? 🐛
The Applet version of this game doesn't work on my firefox.

In all seriousness, no, I am not aware of any bugs. If you do find one, please submit an issue [here](https://github.com/outkine/cps/issues/new).

# Essay time.

I definitely see now why you do this project. The structure of a BlackJack game is incredibly friendly to the OOP paradigm, and abstracting `Card` and `Deck` functionality is very intuitive.

## Proud
Despite my dislike for Java, I think that the majority of my code is really clean and idiomatic. This is especially true of my use of streams, which I think is the only good way to deal with collections. Something that I realized after learning a couple of functional languages is the importance of lists: they show up almost everywhere, and are often the best way to deal with a problem that would otherwise require a loop. And although Java streams are far from perfect, I still think they're a good first step to providing a good set of tools to work with lists. This, combined with lambdas, allowed me to do fairly easy tasks that would have taken 20 lines in imperative code in 2 instead.

An example is finding the value of a hand. Without streams, I would have had to loop over all the cards, keeping a `sum` variable ready, and then return that. This is boilerplate-y and error prone because that accumulator variable isn't guaranteed to have the right value at the end. Instead of doing all this, I instead 1) used a stream to create a list of values from card ranks, and 2) used a reduce statement to sum that list. Not only is that shorter, but it's also significantly easier to understand.

I consider this code quality to be my "Above and beyond" element - sure, I didn't add any fancy features, but I think my code is way more bullet-proof because of how large chunks of it are coded in a more functional paradigm.

## Challenging
One interesting challenge that I faced was how to deal with the `Hand` class. On one hand, it belongs to the player, so it seemed natural to keep it private. On the other hand, this would have meant that I had to make proxy methods that basically did nothing more than just calling hand methods from the player class.

But then I remembered that a lot of people in the OOP community have been calling for an alternative to inheritance. The problem with inheritance is its lack of flexibility. Let me quote Joe Armstrong, the creator of Erlang:
```
The problem with object-oriented languages is they’ve got all this implicit environment that they carry around with them. You wanted a banana but what you got was a gorilla holding the banana and the entire jungle.
```
Because everything is so tightly coupled, a small change in one part of your code leads to disasters. And although the ultimate solution to this is to use a functional language, there's a way to minimize it while still using OOP: components.

Instead of building elaborate class hierarchies, components allow you to represent small bits of functionality with their own objects. Then, the parent object "owns" all of those behaviors in the form of properties. This means coupling, but without the whole jungle problem. That's exactly what I ended up doing with my `Hand` class: it exists as a property on the `Human` super class.

## Grade
I think I deserve a 92. Although I didn't go above and beyond with my features, I still put forth a legitimate effort and tried to be a good coder. If you do find any bugs, please do let me know! I'm always happy to learn from my mistakes :)
