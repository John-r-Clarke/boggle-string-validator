package bogglevalidator;


/**
 * Simple class to define a cell in a Boggle board
 */
public class BoardCell
{
    private final int idY;
    private final int idX;

    public BoardCell(int y, int x)
    {
        this.idY = y;
        this.idX = x;
    }

    /**
     * Identify if this cell is adjacent with another (horizontally, vertically, diagonally)
     * @param b The other cell
     * @return Whether the other cell is adjacent to this
     */
    public boolean adjacentTo(BoardCell b)
    {
        if (this.idX == b.idX && this.idY == b.idY) return false; //Same cell

        if (b.idX > this.idX + 1 || b.idX < this.idX - 1) return false; //X out of range

        if (b.idY > this.idY + 1 || b.idY < this.idY - 1) return false; //Y out of range

        return true;
    }
}
