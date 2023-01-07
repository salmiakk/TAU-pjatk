import unittest
from Game import Game

class GameTests(unittest.TestCase):
    def setUp(self):
        self.game = Game()
    def test_PlayerShouldMoveToAvailableField(self):
        self.game.board = [
            [0, 0, 3, 0, 0],
            [3, 0, 3, 0, 0],
            [0, 1, 3, 3, 0],
            [0, 3, 0, 0, 0],
            [0, 0, 0, 3, 2]
        ]
        result = self.game.make_move("C2=>B2")
        self.assertEqual(self.game.board[1][1], 1)
        self.assertEqual(self.game.board[2][1], 0)

    def test_PlayerShouldNotMoveToForbiddenField(self):
        self.game.board = [
            [1, 0, 3, 0, 0],
            [3, 0, 3, 0, 0],
            [0, 0, 3, 3, 0],
            [0, 3, 0, 0, 0],
            [0, 0, 0, 3, 2]
        ]
        self.assertRaises(Exception, self.game.make_move("A1=>B1"))
    
    def test_PlayerShouldWinAfterMovingToFinishField_1(self):
        self.game.board = [
            [0, 0, 3, 0, 0],
            [3, 0, 3, 0, 0],
            [0, 0, 3, 3, 0],
            [0, 3, 0, 0, 1],
            [0, 0, 0, 3, 2]
        ]
        self.game.make_move("D5=>E5")
        self.assertEqual(self.game.is_win(), True)
    def test_PlayerShouldWinAfterMovingToFinishField_2(self):
        self.game.board = [
            [1, 0, 3, 0, 0],
            [3, 0, 3, 0, 0],
            [0, 0, 3, 3, 0],
            [0, 3, 0, 0, 0],
            [0, 0, 0, 3, 2]
        ]
        self.game.make_move("A1=>A2")
        self.game.make_move("A2=>B2")
        self.game.make_move("B2=>C2")
        self.game.make_move("C2=>C1")
        self.game.make_move("C1=>D1")
        self.game.make_move("D1=>E1")
        self.game.make_move("E1=>E2")
        self.game.make_move("E2=>E3")
        self.game.make_move("E3=>D3")
        self.game.make_move("D3=>D4")
        self.game.make_move("D4=>D5")
        self.game.make_move("D5=>E5")
        self.assertEqual(self.game.is_win(), True)
    def test_PlayerShouldNotWinAfterNotMovingToFinishField(self):
        self.game.board = [
            [0, 0, 3, 0, 0],
            [3, 0, 3, 0, 0],
            [0, 0, 3, 3, 0],
            [0, 3, 0, 0, 1],
            [0, 0, 0, 3, 2]
        ]
        self.game.make_move("D5=>C5")
        self.assertEqual(self.game.is_win(), False)
    def test_PossibleMovesShouldBeReturned(self):
        self.game.board = [
            [0, 1, 3, 0, 0],
            [3, 0, 3, 0, 0],
            [0, 0, 3, 3, 0],
            [0, 3, 0, 0, 0],
            [0, 0, 0, 3, 2]
        ]
        result = self.game.possible_moves()
        self.assertIn("A2=>A1",result)
        self.assertIn("A2=>B2",result)

    def tearDown(self):
        print("")

if __name__ == "__main__":
    unittest.main()
