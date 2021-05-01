# **XiangQi Project**

## A simple game of Chinese chess

This program models a XiangQi game board for those that want to learn or play Chinese chess. I find this project 
interesting because I feel like it is a great way for me to explore how to design a program with multiple interacting 
classes. 

## User Story
### Phase 1:
- As a user, I want to play a custom game by adding multiple XiangQi pieces onto the board
- As a user, I want to be able to move pieces
- As a user, I want to be able to capture pieces
- As a user, I want to be able to play and win a classic game against a friend
- As a user, I want to try again when I made a typo and entered an invalid input

### Phase 2:
- As a user, I want to be able to save my current board if I wish
- As a user, I want to be able to retrieve previous unfinished game and continue playing

### Phase 4:
- Task 2:
    - Used the Map interface in GameBoard class
- Task 3:
    - Reduce coupling between classes, especially between Piece and GameBoard. I think Piece don't need to have a
      field for GameBoard. The HashMap representation of the piece's location and the piece's own stored location 
      information really increased the amount of coupling.
    - Refactor the GUI classes to achieve single-point of control. I currently have numbers flying around and not using
      width and height fields in each class enough. Currently, if I were to change the size of the board later, it would mess up 
      the entire setup of the frame.