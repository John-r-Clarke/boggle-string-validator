package bogglevalidator;

import java.util.*;


public class BoggleValidator
{
    public BoggleValidator()
    {
    }

    /**
     * Determines whether a String is a valid guess for a Boggle board according to game rules
     * @param board Boggle board characters
     * @param guess The guess string
     * @return Whether the guess is valid
     */
    public boolean isValidGuess(final char[][] board, final String guess)
    {
        if (guess.length() > (board.length * board.length)) return false;

        //For each unique character in the guess, get the position of each instance in the board
        Map<Character, List<BoardCell>> guessCharPositions = boardCharacterPositionsForString(board, guess);

        //Ensure the board has enough instances of each guess character
        for (Map.Entry<Character, List<BoardCell>> entry : guessCharPositions.entrySet()) //Each unique guess char
        {
            int charCount = (int) guess.chars().filter(c -> c == entry.getKey()).count(); //No. instances in the guess
            if (entry.getValue().size() < charCount) return false;
        }

        int currentGuessCharIdx = 0; //The point in the guess String we are currently visiting
        char curGuessChar = guess.charAt(currentGuessCharIdx);
        char nextGuessChar; //Next character to find in the board (adjacent to current)

        //Stack to hold the current path through the Boggle board
        Stack<BoardCell> boardPathStack = new Stack<>();
        BoardCell curCharCell = guessCharPositions.get(curGuessChar).get(0);
        boardPathStack.push(curCharCell);

        //Now, traverse the board to find a valid path giving the guess String
        while (currentGuessCharIdx < guess.length() - 1)
        {
            curGuessChar = guess.charAt(currentGuessCharIdx);
            nextGuessChar = guess.charAt(currentGuessCharIdx + 1);

            //Return false if there are no more adjacent instances of the required char
            if (guessCharPositions.get(curGuessChar).size() == 0)  return false;
            if (guessCharPositions.get(nextGuessChar).size() == 0) return false;


            //Check each unused instance of the next guess character for adjacency with the current
            boolean foundNext = false;
            for (BoardCell nextCharCell : guessCharPositions.get(nextGuessChar))
            {
                if (boardPathStack.contains(nextCharCell)) continue; //Character already in use

                if (nextCharCell.adjacentTo(boardPathStack.peek()))
                {
                    currentGuessCharIdx++;
                    if (currentGuessCharIdx == guess.length() - 1) return true; //Found a valid path

                    boardPathStack.push(nextCharCell);
                    foundNext = true;
                    break;
                }
            }

            if (!foundNext)
            {
                //Discount this cell in the board: not adjacent to the next guess char
                guessCharPositions.get(curGuessChar).remove(0);
                boardPathStack.pop();
                currentGuessCharIdx--;
            }
        }
        return true;
    }

    /**
     * Obtains the position(s) of each character in a String within a Boggle board
     * @param board Boggle board characters
     * @param s The String
     * @return Mapped to each character, a list of its positions in the Board
     */
    private Map<Character, List<BoardCell>> boardCharacterPositionsForString(final char[][] board, final String s)
    {
        Map<Character, List<BoardCell>> characterBoardIds = new HashMap<>();

        //An entry for each unique character in the string
        for (char c : s.toCharArray())
        {
            if (!characterBoardIds.containsKey(c))
            {
                characterBoardIds.put(c, new ArrayList<>());
            }
        }

        //Get the position of each board cell containing a char in the String
        for (int x = 0; x < board.length; x++)
        {
            for (int y = 0; y < board.length; y++)
            {
                char boardChar = board[y][x];
                if (s.contains(boardChar + ""))
                {
                    BoardCell cell = new BoardCell(y, x);
                    List<BoardCell> charPositions = characterBoardIds.get(boardChar);
                    charPositions.add(cell);

                    characterBoardIds.put(boardChar, charPositions);
                }
            }
        }
        return characterBoardIds;
    }
}
