import numpy as np

DIRECTIONS = list(map(np.array, [[0,1],[0,-1],[1,0],[-1,0]]))
to_char = lambda a: "ABCDE"[a[0]]
to_array = lambda s: np.array(["ABCDE".index(s[0]), int(s[1]) - 1])

class Game():
    def __init__(self):
        print("Welcome to the game.")
        print("While looking at the board, note that P means Player (you), F means Finish (that's where you want to go), and X is an obstacle.")
        self.board = [
            [1, 0, 3, 0, 0],
            [3, 0, 3, 0, 0],
            [0, 0, 3, 3, 0],
            [0, 3, 0, 0, 0],
            [0, 0, 0, 3, 2]
        ]
        # Player ID
        self.player = 1
        
    def show_board(self):
        print(
            "\n"
            + "\n".join(
                ["  1 2 3 4 5 "]
                + [
                    "ABCDE"[k]
                    + " "
                    + " ".join(
                        [[".", "P", "F", "X"][self.board[k][i]] for i in range(5)]
                    )
                    for k in range(5)
                ]
                + [""]
            )
        )
    def possible_moves(self):
        moves = []
        for row_index, row in enumerate(self.board):
            for column_index, column in enumerate(row):
                if column == self.player:
                    for d in DIRECTIONS:
                        potential_move = [row_index+d[0], column_index+d[1]]
                        if (0 <= potential_move[0] < 5) and (0 <= potential_move[1] < 5) and (self.board[potential_move[0]][potential_move[1]] in [0, 2]):
                            moves.append(to_char([row_index]) + str(column_index+1) + "=>" + to_char([row_index+d[0]]) + str(column_index+1+d[1]))
        return moves
    def show_move_helper(self):
        print("Potential moves are: ", self.possible_moves())
        move = input("What is your move?")
        return move
    def make_move(self, move):
        print ("Making the move: ", move)
        try:
            if move not in self.possible_moves():
                raise Exception("That move is illegal! Please enter a valid move")
        except:
            return            
        move_total = move.split("=>")
        move_from = to_array(move_total[0])
        move_to = to_array(move_total[1])
        self.board[move_from[0]][move_from[1]] = 0
        self.board[move_to[0]][move_to[1]] = self.player
        return True    
    def is_win(self):
        # If there is no Finish field on the board, it means that game is done.
        for row in self.board:
            for field in row:
                if field == 2:
                    return False
        return True
    def play(self):
        while(not(self.is_win())):
            self.show_board()
            self.make_move(self.show_move_helper())
        print("You have won the game, congrats!")
if __name__ == "__main__":
    game = Game()
    game.play()